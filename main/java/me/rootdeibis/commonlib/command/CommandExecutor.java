package me.rootdeibis.commonlib.command;

import me.rootdeibis.commonlib.command.context.CommandContext;
import me.rootdeibis.commonlib.command.context.SubCommandContext;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.util.Arrays;

public class CommandExecutor extends BukkitCommand {

    private final CommandContext context;
    public CommandExecutor(CommandContext context) {
        super(context.getName());
        this.context = context;

        this.setAliases(context.getAliases());

        if(context.getPermission() != null) this.setPermission(this.context.getPermission());

    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        String permissionMessage = this.getPermissionMessage() != null ? this.getPermissionMessage() : "&cInsuficient permissions.";
        if(args.length == 0) {
            if (this.context.getPermission() != null && !sender.hasPermission(this.context.getPermission())) {

                this.colored(sender, permissionMessage);
            } else {
                return MethodUtils.invokeMethod(this.context.getCommandClassLoaded().getClassInstance(), this.context.getMainMethod(), sender, commandLabel, args);
            }
        } else {

            SubCommandContext subCommandContext = this.context.getSubCommands().stream().filter(s -> s.getSubCommand().name().equalsIgnoreCase(args[0]))
                    .findFirst().orElse(null);

            if(subCommandContext == null) {
                if (this.context.getPermission() != null && !sender.hasPermission(this.context.getPermission())) {

                    this.colored(sender, permissionMessage);
                } else {
                    return MethodUtils.invokeMethod(this.context.getCommandClassLoaded().getClassInstance(), this.context.getMainMethod(), sender, commandLabel, args);
                }
            } else {
                if (subCommandContext.getSubCommand().permission() != null && !sender.hasPermission(subCommandContext.getSubCommand().permission())) {
                    this.colored(sender, permissionMessage);
                } else {
                    return MethodUtils.invokeMethod(subCommandContext.getInstance(), subCommandContext.getHandleMethod(), sender, commandLabel, Arrays.stream(args, 1, args.length).toArray(String[]::new));
                }
            }



        }

        return false;

    }

    private void colored(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
