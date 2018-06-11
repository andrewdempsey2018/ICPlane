import java.io.*;
import java.net.*;

public class Server
{
    static final int PORT = 2000;
	Connection connection;

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
				// wait for a connection request
				Socket socket = ss.accept();
				
				

				connection = new Connection(socket); 
				connection.start();
				System.out.println("A client has connected...");
            } 
		}
		catch (Exception e) 
		{ 
			System.out.println("Trouble with a connection " + e); 
		}
		
	}

	public class Connection extends Thread
    {
		private Socket socket;
		private DataOutputStream os;
		private DataInputStream is;
		private boolean running;

		private int value;

		public Connection(Socket aSocket)
		{
			
			socket = aSocket;

			try
			{
				os = new DataOutputStream(socket.getOutputStream());
				is = new DataInputStream(socket.getInputStream());
			}
			catch(Exception e)
			{
				value = 0;
				running = true;
			}
		}
	
		public void run()
		{
			while(running)
			{
				try
				{
					value = is.readInt();
					os.writeInt(value);
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
			
			try
			{
				os.close();
				is.close();
				socket.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
}