package xyplocraft.net.hub.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.bukkit.ChatColor;

public class Sockets
{

	public static String[] getBungeeSocket(int port) throws Exception
	{
		Socket socket = new Socket();
		socket.setSoTimeout(100);
		socket.connect(new InetSocketAddress("127.0.0.1", port), 100);
		
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		DataInputStream in = new DataInputStream(socket.getInputStream());
		
		out.write(0xFE);
		
		int b;
		StringBuffer str = new StringBuffer();
		while ((b = in .read()) != -1)
		{
			if (b != 0 && b > 16 && b != 255 && b != 23 && b != 24)
			{
				str.append((char) b);
			}
		}
			 
		String[] data = str.toString().split(String.valueOf(ChatColor.COLOR_CHAR));
		String serverMotd = data[0];
			 
		socket.close();
			 
		System.out.println(String.format(serverMotd));
		
		return data;
	}

}
