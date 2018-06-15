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
			
				DataInputStream is = new DataInputStream(socket.getInputStream());
                DataOutputStream os = new DataOutputStream(socket.getOutputStream());

				Thread connection1 = new Thread(new Connection(socket, is, os));
				connection1.start();

                //***

				System.out.println("A client has connected...");

				socket = ss.accept();
			
				is = new DataInputStream(socket.getInputStream());
                os = new DataOutputStream(socket.getOutputStream());

				Thread connection2 = new Thread(new Connection(socket, is, os));
				connection2.start();

				System.out.println("A client has connected...");

				while(true)
				{
					connection2.os.writeInt(1);
				}
			}
		}
		catch(Exception e)
		{	
			System.out.println(e);
		}
	}

	private class Connection extends Thread
	{
		public Socket socket;
		public DataInputStream is;
		public DataOutputStream os;
	    
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
					System.out.println("trans said: " + is.readInt());
					//System.out.println("trans said: " + is.readInt());
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