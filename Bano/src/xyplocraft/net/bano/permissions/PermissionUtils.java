package xyplocraft.net.bano.permissions;

import java.util.HashMap;
import java.util.UUID;

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

	public void registerClass()
	{
		/* We still need to register our Permission listeners and commands.*/
		main.getPm().registerEvents(new PermissionPlayerQuitEventListener(), main);
		main.getPm().registerEvents(new PermissionPlayerLoginEventListener(), main);
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
		if(getPlayerAttachments().containsKey(uuid))
		{
			return true;
		}
		return false;
	}
	
}
