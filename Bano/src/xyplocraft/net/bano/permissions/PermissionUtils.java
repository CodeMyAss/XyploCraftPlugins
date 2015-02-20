package xyplocraft.net.bano.permissions;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.permissions.PermissionAttachment;

public class PermissionUtils
{
	
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

	public static void storeNewAttachment(UUID uniqueId, PermissionAttachment attachment)
	{
		/* Storing a new player to our playerAttachments list */
		HashMap<UUID, PermissionAttachment> currentPA = getPlayerAttachments();
		currentPA.put(uniqueId, attachment);
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
	
}
