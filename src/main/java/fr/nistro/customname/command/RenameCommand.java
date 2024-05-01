package fr.nistro.customname.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.nistro.customname.Main;

public class RenameCommand implements CommandExecutor {
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	    if (!(sender instanceof Player)) {
			sender.sendMessage(Main.getPrefix() + Bukkit.getPluginManager().getPlugin("CustomName").getConfig().getString("messages.no-console"));
			return true;
		}
		final Player player = (Player) sender;

		// Vérifie si le joueur à rentrer un nom
		if (args.length == 0) {
		    player.sendMessage(Main.getPrefix() + Bukkit.getPluginManager().getPlugin("CustomName").getConfig().getString("messages.invalid-arguments"));
		} else {
			// SI l'argument 0 est égal à "reset"
			if (args[0].equalsIgnoreCase("reset")) {
				// Récupère l'item en main du joueur
		        @SuppressWarnings("deprecation")
				final
		        ItemStack item = player.getItemInHand();
		        
		        // Vérifie si l'item en main n'est pas vide
				if ((item == null) || (item.getType() == Material.AIR)) {
					player.sendMessage(Main.getPrefix() + Bukkit.getPluginManager().getPlugin("CustomName")
							.getConfig().getString("messages.invalid-item"));
					return true;
				}
				
				// Défini le nom de l'item
		        final ItemMeta itemMeta = item.getItemMeta();
		        itemMeta.setDisplayName(null);
		        item.setItemMeta(itemMeta);
		        
		        player.sendMessage(Main.getPrefix() + Bukkit.getPluginManager().getPlugin("CustomName").getConfig().getString("messages.reset"));
		        return true;
			}
			
			// Récupère tous les arguments
			String name = "";
			final StringBuilder nameTmp = new StringBuilder();
			for (final String arg : args) {
				nameTmp.append(arg).append(" ");
			}
		
		    // Création de la variable name qui contient le nom rentré par le joueur avec les couleurs
			name = ChatColor.translateAlternateColorCodes('&', nameTmp.toString().trim());
			
			if (name.length() > 32) {
				player.sendMessage(Main.getPrefix() + Bukkit.getPluginManager().getPlugin("CustomName").getConfig()
						.getString("messages.invalid-nameSize"));
				return true;
			}

		    // Récupère l'item en main du joueur
		    @SuppressWarnings("deprecation")
			final
			ItemStack item = player.getItemInHand();

		    // Vérifie si l'item en main n'est pas vide
		    if ((item == null) || (item.getType() == Material.AIR)) {
		        player.sendMessage(Main.getPrefix() + Bukkit.getPluginManager().getPlugin("CustomName").getConfig().getString("messages.invalid-item"));
		        return true;
		    }

		    // Défini le nom de l'item
		    final ItemMeta itemMeta = item.getItemMeta();
		    itemMeta.setDisplayName(name);
		    item.setItemMeta(itemMeta);

		    player.sendMessage(Main.getPrefix() + Bukkit.getPluginManager().getPlugin("CustomName").getConfig().getString("messages.rename").replace("%name%", name));
		}

		return true;
	}

	
	public RenameCommand() {}
}
