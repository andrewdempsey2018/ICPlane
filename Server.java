import java.io.*;
import java.net.*;

public class Server
{
    static final int PORT = 2000;
	private Thread connection;

	public Server()
	{
		System.out.println("Server has started...");
	}

	public void runProgram()
	{
		try
		{
			ServerSocket ss = new ServerSocket(PORT);

			while (true)
			{
				// wait for a connection request
				Socket socket = ss.accept();
				connection = new Thread(new MessagesFromTransMitter(socket)); 
				connection.start(); 
			} 
		}
		catch (Exception e) 
		{ 
			System.out.println("Trouble with a connection " + e); 
		}
		
	}

	private class MessagesFromTransMitter implements Runnable
    {
		private Socket socket;

		private DataInputStream is;

		public MessagesFromTransMitter(Socket aSocket)
		{
			socket = aSocket;
		}

		public void run ()
		{
			try
			{
                is = new DataInputStream(socket.getInputStream());
				int valueFromTransmitter = 0;

				while(socket.isConnected())
			    {
			        valueFromTransmitter = is.readInt();
				}

				socket.close(); 
			} 
			catch(Exception e) 
			{ 
				System.out.println("Trouble with a connection " + e); 
			} 
		}
	}
}