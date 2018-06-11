import java.io.*;
import java.net.*;

public class Server
{
    static final int PORT = 2000;
		
	ServerSocket ss;
	Socket socket;

	private DataOutputStream os;
	private DataInputStream is;

	public Server()
	{
		System.out.println("Server has started...");

		try
		{
			ss = new ServerSocket(PORT);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
}

	public void runProgram()
	{
		try
		{
			socket = ss.accept();
			openStreams();
			processMessages();
			closeStreams();
		}
		catch(IOException ioe)
		{	
			System.out.println(ioe);
		}
	}

	private void openStreams() throws IOException
	{
		is = new DataInputStream(socket.getInputStream());
		os = new DataOutputStream(socket.getOutputStream());
	}

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