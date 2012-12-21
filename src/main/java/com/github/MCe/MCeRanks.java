package com.github.MCe;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created with IntelliJ IDEA.
 * User: Lemtzas
 * Date: 12/19/12
 * Time: 4:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class MCeRanks extends JavaPlugin {
    public void OnLoad() {

    }

    public void OnEnable() {
        //getCommand("basic").setExecutor(new MyPluginCommandExecutor(this));
        ServicesManager sm = this.getServer().getServicesManager();

        ;
    }

    public void OnDisable() {
        getServer().getServicesManager().unregisterAll(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return false;
    }
}
