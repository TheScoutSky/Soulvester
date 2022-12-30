package de.theskyscout.soulvester.utils

import com.google.common.collect.Table
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.block.Chest
import org.bukkit.enchantments.EnchantmentTarget
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Recipe
import kotlin.random.Random

object LootChest {

    val chests = ArrayList<Player>()
    fun spawnSilvesterChest(player: Player) {
        while (!chests.contains(player)) {
            val x = Random.nextInt(-4, 4)
            val y = Random.nextInt(-2, 2)
            val z = Random.nextInt(-4, 4)
            val location = player.location.add(x.toDouble(), y.toDouble(), z.toDouble())
            val blockcheck = player.world.getBlockAt(location)
            if(blockcheck.type != Material.AIR) return
            if(blockcheck.getRelative(BlockFace.DOWN).type == Material.AIR)return
            blockcheck.type = Material.CHEST
            val chest: Chest = blockcheck.state as Chest
            val chestInv = chest.blockInventory
            for(i in 5..Random.nextInt(10,20)) {
                chestInv.setItem(Random.nextInt(0, 26), getRandomItem())
            }
            chests.add(player)
        }
    }
    fun getRandomItem():ItemStack {
        val check = false
        val extras = listOf(Material.DIAMOND, Material.IRON_BARS, Material.EMERALD, Material.GOLDEN_APPLE, Material.GOLD_INGOT, Material.SHULKER_SHELL)
        while (!check) {
            val material = Material.values()[Random.nextInt(Material.values().size)]
            val recipes = Bukkit.getServer().getRecipesFor(ItemStack(material))
            if(recipes.isNotEmpty() || extras.contains(material)) {
                var anzahl = Random.nextInt(1, 64)
                if(material.isBlock) anzahl = Random.nextInt(2, 64)
                if(extras.contains(material)) anzahl = Random.nextInt(2, 64)
                if(EnchantmentTarget.ALL.includes(material)) anzahl = 1

                return ItemStack(material, anzahl)
            }

        }

        TODO()
    }

}