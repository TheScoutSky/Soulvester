package de.theskyscout.soulvester.utils

import org.bukkit.Color
import org.bukkit.DyeColor
import org.bukkit.FireworkEffect
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.entity.Firework
import org.bukkit.inventory.meta.FireworkMeta

class Firework constructor(color: Color, amount:Int, power:Int, flicker:Boolean) {
    val color = color
    val amount = amount
    val power = power
    val flicker = flicker
    lateinit var firework:Firework
    lateinit var fireworkMeta: FireworkMeta

    fun spawn(location: Location) {
        this.firework = location.world.spawnEntity(location, EntityType.FIREWORK) as Firework
        this.fireworkMeta = this.firework.fireworkMeta
        this.fireworkMeta.power = this.power
        this.fireworkMeta.addEffect(FireworkEffect.builder().withColor(this.color).flicker(this.flicker).build())
        this.firework.fireworkMeta = this.fireworkMeta
        this.firework.remove()
        for (i in 0..this.amount) run {
            val fw = location.world.spawnEntity(location, EntityType.FIREWORK) as Firework
            fw.fireworkMeta = this.fireworkMeta
        }

    }
}