import java.io.*;
import java.net.*;

public class Server
{
    static final int PORT = 2000;

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
			
				DataInputStream is = new DataInputStream(socket.getInputStream());
                DataOutputStream os = new DataOutputStream(socket.getOutputStream());

				Thread connection = new Thread(new Connection(socket, is, os));
				connection.start();

				System.out.println("A client has connected...");
			}
		}
		catch(Exception e)
		{	
			System.out.println(e);
		}
	}

	private class Connection extends Thread
	{
		private Socket socket;
		private DataInputStream is;
		private DataOutputStream os;
	    
		public Connection(Socket socket, DataInputStream is, DataOutputStream os)
		{
			this.socket = socket;
			this.is = is;
			this.os = os;
		}

		public void run()
		{
			while(true)
			{
				try
				{
					System.out.println("" + is.readInt());
					os.writeInt(555);
				}
				catch(Exception e)
				{
					System.out.println("Trouble processing messages on server: " + e);
				}
			}
		}
			
		
	}

}