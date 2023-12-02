package me.rootdeibis.commonlib.command.loader;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import me.rootdeibis.commonlib.command.CommandExecutor;
import me.rootdeibis.commonlib.command.annotations.Command;
import me.rootdeibis.commonlib.command.context.CommandContext;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CommandClassLoader {


    private final String[] packages;
    private final List<CommandClassLoaded> commandClassLoadeds = new ArrayList<>();
    public CommandClassLoader(String... packages) {
        this.packages = packages;

        this.loadAll();;

    }

    private void loadAll() {

        for (String pkgPath : this.packages) {
            try(ScanResult scanResult = new ClassGraph().enableAllInfo().acceptPackages(pkgPath).scan()) {
                scanResult.getAllClasses().stream().filter(d-> d.loadClass().isAnnotationPresent(Command.class))
                        .forEach(classInfo -> {
                            commandClassLoadeds.add(new CommandClassLoaded(classInfo.loadClass()));

                            System.out.println(classInfo.getName());
                        });
            }
        }
    }

    public CommandContext getCommand(String name) {
        return this.commandClassLoadeds.stream().map(CommandClassLoaded::getCommandContext)
                .filter(commandContext -> commandContext.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public void register() {


        this.registerInMap(this.commandClassLoadeds.stream().map(l -> new CommandExecutor(l.getCommandContext())).toArray(CommandExecutor[]::new));

    }


    private void registerInMap(CommandExecutor... executors) {


        try {
            Field f = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
            f.setAccessible(true);

            CommandMap commandMap = (CommandMap) f.get(Bukkit.getPluginManager());

            for (CommandExecutor executor : executors) {

                commandMap.register(executor.getName(), executor);

            }
        } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException | SecurityException e) {
            e.printStackTrace();
        }



    }
}
