package de.theskyscout.soulvester.listener

import de.theskyscout.soulvester.utils.Firework
import de.theskyscout.soulvester.utils.LootChest
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.title.Title
import org.bukkit.Bukkit
import org.bukkit.Color
import org.bukkit.Location
import org.bukkit.Sound
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import java.util.Calendar
import kotlin.random.Random

object Silvester:Listener {
    var x = 0

    fun startSilvester(plugin:JavaPlugin) {
        object : BukkitRunnable() {
            override fun run() {
                val calendar = Calendar.getInstance()
                if(calendar.get(Calendar.MONTH)+1 != 12 && calendar.get(Calendar.DAY_OF_MONTH) != 31) return
                if(calendar.get(Calendar.HOUR_OF_DAY) ==23 && calendar.get(Calendar.MINUTE) == 59&& calendar.get(Calendar.SECOND) == 50) {
                    onSilvester(plugin)
                    cancel()
                }
                x++
                if(x >=30) {
                    x = 0
                    Bukkit.getOnlinePlayers().forEach {
                        val x = Random.nextInt(-40, 40)
                        val z = Random.nextInt(-40, 40)
                        val y = Location(
                            it.world,
                            it.location.blockX.plus(x).toDouble(),
                            0.0,
                            it.location.blockZ.plus(z).toDouble()
                        ).toHighestLocation().blockY
                        val location = Location(
                            it.world,
                            it.location.blockX.plus(x).toDouble(),
                            y.toDouble(),
                            it.location.blockZ.plus(z).toDouble()
                        )
                        val color = Color.fromRGB(Random.nextInt(255), Random.nextInt(255), Random.nextInt(255))
                        Firework(color, 1, 2, true).spawn(location)
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 20)
    }
    var timer = 10
    var silvester = false
    fun onSilvester(plugin:JavaPlugin){
        object : BukkitRunnable(){
            override fun run(){
                if(!silvester) {
                    Bukkit.getOnlinePlayers().forEach {
                        it.showTitle(Title.title(Component.text("${timer}", NamedTextColor.GREEN), Component.text("")))
                        it.playSound(it.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0F,10.0F)
                    }
                }
                timer--
                if(timer <= -1) {
                    silvester = true
                    Bukkit.getOnlinePlayers().forEach {
                        if(timer==-1)it.playSound(it.location, Sound.UI_TOAST_CHALLENGE_COMPLETE, 10.0F,10.0F)
                        if(timer==-1)it.showTitle(Title.title(Component.text("Happy New Year", NamedTextColor.GREEN), Component.text("wünscht euch SoulSMP!", NamedTextColor.BLUE)))
                        for(i in 0..5){
                            val x = Random.nextInt(-40, 40)
                            val z = Random.nextInt(-40, 40)
                            val y = Location(
                                it.world,
                                it.location.blockX.plus(x).toDouble(),
                                0.0,
                                it.location.blockZ.plus(z).toDouble()
                            ).toHighestLocation().blockY
                            val location = Location(
                                it.world,
                                it.location.blockX.plus(x).toDouble(),
                                y.toDouble(),
                                it.location.blockZ.plus(z).toDouble()
                            )
                            val color = Color.fromRGB(Random.nextInt(255), Random.nextInt(255), Random.nextInt(255))
                            Firework(color, 1, 2, true).spawn(location)
                            LootChest.spawnSilvesterChest(it)
                    }
                        if(timer == -10) {
                            timer =0
                            silvester= false
                            cancel()
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 20)
    }
}



