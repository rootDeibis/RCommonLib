package test;

import me.rootdeibis.commonlib.command.context.CommandContext;
import me.rootdeibis.commonlib.command.loader.CommandClassLoader;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private CommandClassLoader commandLoader;
    @Override
    public void onEnable() {
       this.commandLoader = new CommandClassLoader("test");

        CommandContext commandContext = this.commandLoader.getCommand("example");

        commandContext.setAliases("puto");


        this.commandLoader.register();


    }
}
