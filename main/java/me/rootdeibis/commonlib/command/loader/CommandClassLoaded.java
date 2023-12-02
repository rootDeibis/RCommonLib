package me.rootdeibis.commonlib.command.loader;


import me.rootdeibis.commonlib.command.annotations.Command;
import me.rootdeibis.commonlib.command.context.CommandContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandClassLoaded {

    private final Class<?> commandClass;
    private final Object classInstance;

    private final CommandContext commandContext;


    public CommandClassLoaded(Class<?> commandClass) {
        try {
            this.commandClass = commandClass;
            this.classInstance = commandClass.newInstance();
            this.commandContext = new CommandContext(this, commandClass.getAnnotation(Command.class));

        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    public Class<?> getCommandClass() {
        return commandClass;
    }

    public Object getClassInstance() {
        return classInstance;
    }

    public CommandContext getCommandContext() {
        return commandContext;
    }
}
