package com.nijikokun.register;

import com.nijikokun.register.listeners.server;
import com.nijikokun.register.payment.Methods;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Register
 *
 * Initializes on startup and attaches to preferred method or
 * first found method.
 *
 * @author Nijikokun <nijikokun@shortmail.com> (@nijikokun)
 * @author LRFLEW
 * @author Spice-King
 * @copyright (c) 2011
 * @license AOL license <http://aol.nexua.org>
 */
public class Register extends JavaPlugin {

	public PluginDescriptionFile info;

	public void onLoad() {
		info = this.getDescription();

		Methods.setVersion(info.getVersion());
		Methods.setMethod(this.getServer().getPluginManager());

		if (Methods.getMethod() != null) {
			System.out.println("[" + info.getName() + "] Payment method found (" + Methods.getMethod().getName() + " version: " + Methods.getMethod().getVersion() + ")");
		}
		
		System.out.print("[" + info.getName() + "] version " + info.getVersion()+ " is enabled.");
	}
	
	public void onEnable() {
		this.getServer().getPluginManager().registerEvent(Type.PLUGIN_ENABLE, new server(this), Priority.Low, this);
		this.getServer().getPluginManager().registerEvent(Type.PLUGIN_DISABLE, new server(this), Priority.Low, this);
	}
	
	public void onDisable() {
		Methods.reset();
	}
	
}
