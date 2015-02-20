package xyplocraft.net.bano.permissions;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import xyplocraft.net.bano.Bano;

public class PermissionUtils
{
	
	Bano main = Bano.getPlugin();
	private static PermissionUtils pUtils = new PermissionUtils();
	
	public static PermissionUtils getPermissionUtils()
	{
		/* Returning the PermissionUtils class (this class)*/
		return pUtils;
	}
	
	public static void setPermissionUtils(PermissionUtils pUtils)
	{
		/* Setting this class (Pointless) */
		PermissionUtils.pUtils = pUtils;
	}

	@SuppressWarnings("deprecation")
	public void registerClass()
	{
		/* We still need to register our Permission listeners and commands.*/
		main.getPm().registerEvents(new PermissionPlayerQuitEventListener(), main);
		main.getPm().registerEvents(new PermissionPlayerLoginEventListener(), main);
		
		main.getCommand("perm").setExecutor(new PermissionCommands());
		
		for(Player p : Bukkit.getOnlinePlayers())
		{
			PermissionAttachment attachment = p.addAttachment(Bano.getPlugin());
			storeNewAttachment(p.getUniqueId(), attachment);
		}
		/*There we go, it's done now.*/
	}
	
	
	public static void storeNewAttachment(UUID uniqueId, PermissionAttachment attachment)
	{
		/* Storing a new player to our playerAttachments list */
		HashMap<UUID, PermissionAttachment> currentPA = getPlayerAttachments();
		currentPA.put(uniqueId, attachment);
		setPlayerAttachments(currentPA);
	}
	
	public  void removeAttachment(UUID uniqueId)
	{
		/* Removing a player from our playerAttachments list */
		HashMap<UUID, PermissionAttachment> currentPA = getPlayerAttachments();
		currentPA.remove(uniqueId);
		setPlayerAttachments(currentPA);
	}
	
	private static HashMap<UUID, PermissionAttachment> playerAttachments = new HashMap<UUID, PermissionAttachment>();
	
	public static HashMap<UUID, PermissionAttachment> getPlayerAttachments()
	{
		/* Returning all current Player Attachments */
		return playerAttachments;
	}
	
	public static void setPlayerAttachments(HashMap<UUID, PermissionAttachment> playerAttachments)
	{
		/* Setting the playerAttachments (Used in adding/removing players) */
		PermissionUtils.playerAttachments = playerAttachments;
	}
	
	public boolean clearPlayerAttachments()
	{
		/* Safely removing everyone :) */
		boolean error = false;
		try {
			for(UUID uuid : getPlayerAttachments().keySet())
			{
				pUtils.removeAttachment(uuid);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			error = true;
		}
		
		if(error)
		{
			return false;
		} else return true;
		
	}

	public boolean containsPlayerAttachment(UUID uuid)
	{
		/* Checking if a player already has an Attachment */
		if(getPlayerAttachments().containsKey(uuid))
		{
			return true;
		}
		return false;
	}
	
	public static PermissionAttachment getPlayerPermissionAttachment(UUID uuid)
	{
		/* Getting an individuals PermissionAttachment */
		if(pUtils.containsPlayerAttachment(uuid))
		{
			return getPlayerAttachments().get(uuid);
		}
		return null;
	}

	public void givePlayerPermission(UUID uniqueId, String string)
	{
		if(getPlayerPermissionAttachment(uniqueId) != null)
		{
			PermissionAttachment attachment = getPlayerPermissionAttachment(uniqueId);
			attachment.setPermission(string,true);
		}
	}
	
	public void removePlayerPermission(UUID uniqueId, String string)
	{
		if(getPlayerPermissionAttachment(uniqueId) != null)
		{
			PermissionAttachment attachment = getPlayerPermissionAttachment(uniqueId);
			attachment.unsetPermission(string);
		}
	}

	public boolean hasPermission(CommandSender sender, String string)
	{
		if(sender.getName().equalsIgnoreCase("CONSOLE") || sender.isOp())
		{
			return true;
		}
		
		Player p = (Player) sender;
		if(containsPlayerAttachment(p.getUniqueId()))
		{
			PermissionAttachment attachment = getPlayerPermissionAttachment(p.getUniqueId());
			if(attachment.getPermissions().containsKey(string))
			{
				return true;
			}
		}
		return false;
	}
	
}
