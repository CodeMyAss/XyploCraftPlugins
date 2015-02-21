package xyplocraft.net.bano.permissions;

import net.nanon.networkutilities.perm.PermissionsCore;
import net.nanon.networkutilities.perm.Rank;

public class PermissionsHandler extends PermissionsCore {
	
	private Rank guest;
	private Rank member;
	private Rank moderator;
	private Rank architect;
	private Rank developer;
	private Rank administrator;
	private Rank founder;
	
	public PermissionsHandler() {
		super();
	}
	
	public void initPermissions() {
		guest = new Rank("Guest");
		member = new Rank("Member");
		moderator = new Rank("Moderator");
		architect = new Rank("Architect");
		developer = new Rank("Developer");
		administrator = new Rank("Admin");
		founder = new Rank("Founder");
		
		addRank(guest);
		addRank(member);
		addRank(moderator);
		addRank(architect);
		addRank(developer);
		addRank(administrator);
		addRank(founder);
	}
}
