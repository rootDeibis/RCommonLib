package me.rootdeibis.commonlib.command.context;

import me.rootdeibis.commonlib.command.annotations.Command;
import me.rootdeibis.commonlib.command.annotations.Handle;
import me.rootdeibis.commonlib.command.loader.CommandClassLoaded;
import me.rootdeibis.commonlib.command.loader.SubCommandClassLoader;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class CommandContext {

    private final CommandClassLoaded commandClassLoaded;
    private final Command command;

    private String name;
    private List<String> aliases = new ArrayList<>();

    private String permission;

    private Method mainMethod;

    private List<SubCommandContext> subCommands = new ArrayList<>();

    public CommandContext(CommandClassLoaded commandClassLoaded, Command command) {
        this.commandClassLoaded = commandClassLoaded;
        this.command = command;

        this.load();
    }

    private void load() {

        this.name = this.command.name();

        if(this.command.aliases().length > 0) this.aliases = Arrays.stream(this.command.aliases()).collect(Collectors.toList());

        if(this.command.permission().length() > 0) this.permission = this.command.permission();

        if(this.command.subCommands().length > 0) {

            SubCommandClassLoader subCommandClassLoader = new SubCommandClassLoader(this.command.subCommands());


            this.subCommands = subCommandClassLoader.getSubCommandContexts();


        }

        Method[] methods = this.commandClassLoaded.getCommandClass().getMethods();

        if (Arrays.stream(methods).anyMatch(m -> m.isAnnotationPresent(Handle.class))) {
            this.mainMethod = Arrays.stream(methods).filter(m -> m.isAnnotationPresent(Handle.class)).findFirst().get();
        }

    }

    public CommandClassLoaded getCommandClassLoaded() {
        return commandClassLoaded;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setAliases(String... aliases) {
        this.aliases = Arrays.stream(aliases).collect(Collectors.toList());
    }



    public String getName() {
        return name;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public String getPermission() {
        return permission;
    }

    public List<SubCommandContext> getSubCommands() {
        return subCommands;
    }

    public Method getMainMethod() {
        return mainMethod;
    }
}
