import java.io.*;
import java.net.*;

public class Server
{
    static final int PORT = 2000;
    ConnectionPair connection;
	private int messageFromServer = 0;

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

				connection = new ConnectionPair(socket, is, os, socket2, is2, os2);
				connection.start();

            }
		}
		catch(Exception e)
		{	
			System.out.println(e);
		}
	}
}