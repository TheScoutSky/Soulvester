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
import org.bukkit.loot.LootContext
import org.bukkit.loot.LootTables
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
            chest.setLootTable(Bukkit.getLootTable(LootTables.BASTION_TREASURE.key));
            chest.update();
            chests.add(player)
        }
    }

}