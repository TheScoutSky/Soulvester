package de.theskyscout.soulvester

import de.theskyscout.soulvester.listener.Silvester
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Soulvester : JavaPlugin() {
    override fun onEnable() {
        val manager = Bukkit.getPluginManager()
        Silvester.startSilvester(this)
    }
}