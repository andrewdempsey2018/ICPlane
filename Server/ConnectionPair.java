import java.io.*;
import java.net.*;

public class ConnectionPair extends Thread
{
	public Socket socket;
	public Socket socket2;
	public DataInputStream is;
	public DataOutputStream os;

	public DataInputStream is2;
	public DataOutputStream os2;
    
	public ConnectionPair(Socket socket, DataInputStream is, DataOutputStream os, Socket socket2, DataInputStream is2, DataOutputStream os2)
	{
	    this.socket = socket;
		this.is = is;
		this.os = os;

		this.socket2 = socket2;
		this.is2 = is2;
		this.os2 = os2;
	}

	public void run()
	{
	    try
		{
			os.writeInt(is2.readInt());
			os2.writeInt(is.readInt());
        }
        catch(Exception e)
		{
		    System.out.println("Trouble processing messages on server: " + e);
		}
	}
			
		
}