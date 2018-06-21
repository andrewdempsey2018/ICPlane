import java.io.*;
import java.net.*;

public class Server
{
    static final int PORT = 2000;

	//DataOutputStream serverOs = new DataOutputStream(socket.getOutputStream());

	public Server()
	{
		System.out.println("Server has started...");
    }

	public void runProgram()
	{
		try
		{
			ServerSocket ss = new ServerSocket(PORT);

			while(true)
			{
				Socket socket = ss.accept();
				System.out.println("Transmitter connected");
			
				DataInputStream is = new DataInputStream(socket.getInputStream());
                DataOutputStream os = new DataOutputStream(socket.getOutputStream());

				Socket socket2 = ss.accept();
				System.out.println("Plane connected");
			
				DataInputStream is2 = new DataInputStream(socket2.getInputStream());
                DataOutputStream os2 = new DataOutputStream(socket2.getOutputStream());

				ConnectionPair connection = new ConnectionPair(socket, is, os, socket2, is2, os2);
				connection.start();

            }
		}
		catch(Exception e)
		{	
			System.out.println(e);
		}
	}

	private class ConnectionPair extends Thread
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
			while(true)
			{
				try
				{
					os2.writeInt(is.readInt());
				
				}
				catch(Exception e)
				{
					System.out.println("Trouble processing messages on server: " + e);
				}
			}
		}
			
		
	}

}