package me.rootdeibis.commonlib.command.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

    String name();

    String permission() default "";


    String[] aliases() default {};

    Class<?>[] subCommands() default {};

}
