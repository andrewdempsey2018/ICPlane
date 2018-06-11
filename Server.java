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

			while(true)
			{
				// wait for a connection request
				Socket socket = ss.accept();
				System.out.println("A client has connected...");
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
		private DataOutputStream os;

		public MessagesFromTransMitter(Socket aSocket)
		{
			socket = aSocket;
		}

		public void run ()
		{
			try
			{
                is = new DataInputStream(socket.getInputStream());
				os = new DataOutputStream(socket.getOutputStream());

				int valueIn = 0;
				int valueOut = 0;

				while(socket.isConnected())
			    {
			        valueIn = is.readInt();
					valueOut = valueIn;
					os.writeInt(valueOut);

					System.out.println("val in: " + valueIn + " val out: " + valueOut);
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