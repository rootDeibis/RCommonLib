package me.rootdeibis.commonlib.command.loader;

import me.rootdeibis.commonlib.command.annotations.SubCommand;
import me.rootdeibis.commonlib.command.context.SubCommandContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubCommandClassLoader {


    private final List<SubCommandContext> subCommandContexts = new ArrayList<>();
    public SubCommandClassLoader(Class<?> ...classes) {


        for (Class<?> subCommandClass : classes) {

            try {
                Object classInstance = subCommandClass.newInstance();

                Arrays.stream(subCommandClass.getMethods()).filter(m -> m.isAnnotationPresent(SubCommand.class))
                        .map(m -> new SubCommandContext(classInstance, m,m.getAnnotation(SubCommand.class))).forEach(subCommandContexts::add);
            } catch (InstantiationException |IllegalAccessException e) {
                throw new RuntimeException(e);
            }


        }

    }

    public List<SubCommandContext> getSubCommandContexts() {
        return subCommandContexts;
    }
}
