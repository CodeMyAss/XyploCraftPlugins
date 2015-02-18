package xyplocraft.net.kitpvp.listeners;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import xyplocraft.net.kitpvp.Kitpvp;
import xyplocraft.net.kitpvp.managers.SupplyDropManager;
import xyplocraft.net.kitpvp.utils.Ints;
import xyplocraft.net.kitpvp.utils.Scoreboards;

@SuppressWarnings("deprecation")
public class SupplyDropInteractListener implements Listener
{
	Kitpvp main = Kitpvp.plugin;
	  
	@EventHandler(priority=EventPriority.HIGHEST)
	  public void onPlayerInteract(PlayerInteractEvent e)
	  {
	    if (e.getAction() == Action.RIGHT_CLICK_BLOCK)
	    {
	      Player p = e.getPlayer();
	      Block facing = p.getTargetBlock(null, 200);
	      if (facing.getType() == Material.BEACON)
	      {
	        String item = null;
	        Random random = new Random();
	        boolean bool = random.nextBoolean();
	        if (bool)
	        {
	          Random r = new Random();
	          int rint = r.nextInt(5);
	          
	          ItemStack is = null;
	          switch (rint)
	          {
	          case 0: 
	            is = new ItemStack(Material.BOW);
	            is.addEnchantment(Enchantment.ARROW_DAMAGE, 3);
	            is.addEnchantment(Enchantment.ARROW_FIRE, 1);
	            is.addEnchantment(Enchantment.ARROW_INFINITE, 1);
	            if (!p.getInventory().contains(Material.ARROW)) {
	              p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.ARROW, 1) });
	            }
	            item = "Bow with enchantments";
	            break;
	          case 1: 
	            short sh = 16425;
	            is = new ItemStack(Material.POTION, 1, (byte)sh);
	            item = "Strength Potion";
	            break;
	          case 2: 
	            is = new ItemStack(Material.BOW);
	            is.addEnchantment(Enchantment.ARROW_DAMAGE, 3);
	            is.addEnchantment(Enchantment.ARROW_FIRE, 1);
	            if (!p.getInventory().contains(Material.ARROW)) {
	              p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.ARROW, 1) });
	            }
	            item = "Bow with enchantments";
	            break;
	          case 3: 
	            is = new ItemStack(322);
	            item = "Gold Apple";
	            break;
	          case 4: 
	            is = new ItemStack(Material.IRON_SWORD);
	            is.addEnchantment(Enchantment.DURABILITY, 1);
	            is.addEnchantment(Enchantment.KNOCKBACK, 1);
	            is.addEnchantment(Enchantment.DAMAGE_ALL, 2);
	            item = "Sword with enchantments";
	            break;
	          case 5: 
	            is = new ItemStack(Material.BOW);
	            is.addEnchantment(Enchantment.ARROW_DAMAGE, 3);
	            is.addEnchantment(Enchantment.ARROW_FIRE, 1);
	            if (!p.getInventory().contains(Material.ARROW)) {
	              p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.ARROW, 1) });
	            }
	            item = "Bow with enchantments";
	          }
	          if (is != null)
	          {
	            p.getInventory().addItem(new ItemStack[] { is });
	            p.updateInventory();
	          }
	        }
	        else
	        {
	          Random r = new Random();
	          int rint = r.nextInt(4);
	          
	          int points = 0;
	          switch (rint)
	          {
	          case 0: 
	            points = 1000;
	            item = "1000 points";
	            break;
	          case 1: 
	            points = 1000;
	            item = "1000 points";
	            break;
	          case 2: 
	            points = 2500;
	            item = "2500 points";
	            break;
	          case 3: 
	            points = 5000;
	            item = "5000 points";
	          }
	          try
	          {
	            ResultSet res = main.c.createStatement().executeQuery("SELECT * FROM `kitpvp_data` WHERE ign= '" + p.getName() + "';");
	            if (res.next())
	            {
	              int points2 = res.getInt("points");
	              points2 += points;
	              main.c.createStatement().executeUpdate("UPDATE `kitpvp_data` SET points= '" + points2 + "' WHERE ign= '" + p.getName() + "'");
	            }
	          }
	          catch (SQLException sql)
	          {
	            sql.printStackTrace();
	          }
	          Scoreboards.setupMain(p);
	        }
	        facing.setType(Material.AIR);
	        SupplyDropManager.broadcastMessage(ChatColor.AQUA + p.getName() + ChatColor.GOLD + " collected the supply crate and received:");
	        SupplyDropManager.broadcastMessage(ChatColor.YELLOW + " - 1 x " + item);
	        SupplyDropManager.enable = false;
	        Ints.supplyDropTimer = 300;
	      }
	    }
	  }

}
