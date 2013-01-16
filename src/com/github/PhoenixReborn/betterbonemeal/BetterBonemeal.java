//---------------------------------------------------------------
// Minecraft plugin: BetterBonemeal
// Author: Michael Scanlon (polaris120990 / PhoenixReborn)
//---------------------------------------------------------------

package com.github.PhoenixReborn.betterbonemeal;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BetterBonemeal extends JavaPlugin implements Listener
{
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	public void onDisable()
	{
		
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e)
	{
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		{
			if(e.getPlayer().hasPermission("betterbonemeal.use") || e.getPlayer().isOp()) //Checks if player has permission or is OP.
			{
				if(e.getItem() != null && e.getItem().getData().getData() == 15) //Checks to see if the item in hand is bonemeal.
				{
					if(e.getClickedBlock().getType().equals(Material.SUGAR_CANE_BLOCK)) //Checks if player is right clicking on sugar.
					{
						Block sugar = e.getClickedBlock(); //Create a variable named sugar to represent the block in order to avoid confusion.
						boolean used = false; //This will be used to determine whether or not we need to remove bonemeal from the players inventory.
						while(sugar.getRelative(BlockFace.DOWN).getType().equals(Material.SUGAR_CANE_BLOCK))
						{
							sugar = sugar.getRelative(BlockFace.DOWN); //Set the sugar block to the block of sugar cane at the bottom.
						}
						for(int i = 0; i < 2; i++) //This loop stacks sugarcane on top until it reaches a height of 3.
						{
							if(sugar.getRelative(BlockFace.UP).getType().equals(Material.AIR))
							{
								sugar.getRelative(BlockFace.UP).setType(Material.SUGAR_CANE_BLOCK);
								used = true;
							}
							sugar = sugar.getRelative(BlockFace.UP);
						}
						if(used == true)
						{
							if(e.getItem().getAmount() == 1)
							{
								e.getPlayer().setItemInHand(null);
							}
							else
							{
								e.getItem().setAmount(e.getItem().getAmount() - 1); //Remove 1 bonemeal from the players inventory.
							}
						}
					}
					
					if(e.getClickedBlock().getType().equals(Material.CACTUS)) //Checks if the player is right clicking cactus.
					{
						// This if statement does the same as the statement above except with cactus instead of sugarcane.
						Block cactus = e.getClickedBlock();
						boolean used = false;
						while(cactus.getRelative(BlockFace.DOWN).getType().equals(Material.CACTUS))
						{
							cactus = cactus.getRelative(BlockFace.DOWN);
						}
						for(int i = 0; i < 2; i++)
						{
							if(cactus.getRelative(BlockFace.UP).getType().equals(Material.AIR))
							{
								cactus.getRelative(BlockFace.UP).setType(Material.CACTUS);
								used = true;
							}
							cactus = cactus.getRelative(BlockFace.UP);
						}
						if(used == true)
						{
							if(e.getItem().getAmount() == 1)
							{
								e.getPlayer().setItemInHand(null);
							}
							else
							{
								e.getItem().setAmount(e.getItem().getAmount() - 1); //Remove 1 bonemeal from the players inventory.
							}
						}
					}
				}
			}
		}
	}
}
