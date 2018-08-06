import java.io.*;
import java.net.*;

public class Server
{
    static final int PORT = 2000;
    ConnectionPair connection;

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
				System.out.println("A client has connected, waiting for another to create a pair...");
			
				DataInputStream is = new DataInputStream(socket.getInputStream());
                DataOutputStream os = new DataOutputStream(socket.getOutputStream());

				Socket socket2 = ss.accept();
				System.out.println("Another client has connected, now pairing clients...");
			
				DataInputStream is2 = new DataInputStream(socket2.getInputStream());
                DataOutputStream os2 = new DataOutputStream(socket2.getOutputStream());

				connection = new ConnectionPair(socket, is, os, socket2, is2, os2);
				connection.start();

				System.out.println("new connection pair created, messages can now be exchanged");

            }
		}
		catch(Exception e)
		{	
			System.out.println(e);
		}
	}
}