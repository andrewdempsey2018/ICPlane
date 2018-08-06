import java.io.*;
import java.net.*;

public class ConnectionPair extends Thread
{
	private Socket socket;
	private Socket socket2;
	private DataInputStream is;
	private DataOutputStream os;

	private DataInputStream is2;
	private DataOutputStream os2;

	private boolean isAlive;
    
	public ConnectionPair(Socket socket, DataInputStream is, DataOutputStream os, Socket socket2, DataInputStream is2, DataOutputStream os2)
	{
	    this.socket = socket;
		this.is = is;
		this.os = os;

		this.socket2 = socket2;
		this.is2 = is2;
		this.os2 = os2;
	
		isAlive = true;
	}

	public void run()
	{
		while(isAlive)
		{
			try
			{
				//os.writeInt(is2.readInt());
				os2.writeInt(is.readInt());
			}
			catch(Exception e)
			{
				System.out.println("Trouble processing messages on server: " + e);
				isAlive = false;
				cleanup();
			}
		}
	}
	
	private void cleanup()
	{
		try
		{
			is.close();
			os.close();
			is2.close();
			os2.close();
			socket.close();
			socket2.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Problem closing streams: " + ioe);
		}
	}		
		
}