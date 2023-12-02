package test.commands;


import me.rootdeibis.commonlib.command.annotations.Command;
import me.rootdeibis.commonlib.command.annotations.Handle;
import org.bukkit.command.CommandSender;

@Command(name = "example")
public class TestCommand {

    @Handle
    public boolean handle(CommandSender sender) {
        sender.sendMessage("Hola");

        return false;
    }
}
