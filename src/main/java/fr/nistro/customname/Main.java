package fr.nistro.customname;

import org.bukkit.plugin.java.JavaPlugin;

import fr.nistro.customname.command.RenameCommand;
import fr.nistro.customname.util.ConfigUtil;

public class Main extends JavaPlugin {
	
    public static String prefix;
    
    public void onEnable() {
    	this.getCommand("rename").setExecutor(new RenameCommand());

        saveDefaultConfig();
        
        prefix = getConfig().getString("prefix"); 
        
        ConfigUtil config = new ConfigUtil(this, "config.yml");
        config.save();
        
   
        getLogger().info("Plugin NoteLift enabled !");
    }
    
    @Override
    public void onDisable() {
        getLogger().info("Plugin NoteLift disabled !");
    }
    
    public static String getPrefix() {
		return prefix;
	}
}
