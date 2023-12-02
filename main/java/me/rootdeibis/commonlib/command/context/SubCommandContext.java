package me.rootdeibis.commonlib.command.context;

import me.rootdeibis.commonlib.command.annotations.SubCommand;

import java.lang.reflect.Method;

public class SubCommandContext {

    private final Object instance;
    private final SubCommand subCommand;

    private final Method handleMethod;
    public SubCommandContext(Object instance, Method handleMethod, SubCommand command) {

        this.instance = instance;
        this.subCommand = command;

        this.handleMethod = handleMethod;
    }


    public Object getInstance() {
        return instance;
    }

    public SubCommand getSubCommand() {
        return subCommand;
    }

    public Method getHandleMethod() {
        return handleMethod;
    }
}
