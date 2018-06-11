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
			
				DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

				Thread connection = new Thread(new Connection(socket, dis, dos));
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
		private DataOutputStream os;
	    private DataInputStream is;
			
		public Connection(Socket aSocket, DataInputStream dis, DataOutputStream dos)
		{
			socket = aSocket;
			os = dos;
			is = dis;
		}

		public void run()
		{
			try
			{
				//openStreams();
				processMessages();
				closeStreams();
				socket.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
			
		/*private void openStreams() throws IOException
	    {
		    is = new DataInputStream(socket.getInputStream());
		    os = new DataOutputStream(socket.getOutputStream());
	    }*/

		private void closeStreams() throws IOException
	    {    
		    is.close();
		    os.close();
	    }

		private void processMessages() throws IOException
	    {
		    int value = 0;

		    while(value != 7) //value 7 from transmitter = socket close
		    {
			    value = is.readInt();
			    os.writeInt(value);
			    System.out.println(value);
		    }
	    }
	}

}