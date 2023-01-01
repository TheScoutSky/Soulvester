package de.theskyscout.soulvester.commands

import de.theskyscout.soulvester.Soulvester
import de.theskyscout.soulvester.listener.Silvester
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin

class SilvesterCMD constructor(plugin:JavaPlugin): CommandExecutor {
    val plugin = plugin
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        Silvester.onSilvester(plugin)
        return true
    }
}