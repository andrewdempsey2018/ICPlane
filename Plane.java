import java.io.*;
import java.net.*;

public class Plane
{
    private Socket socket;
	static final int PORT = 2000;
	static final String ADDRESS = "192.168.1.3";
	private DataInputStream is;

	Runtime runTime = Runtime.getRuntime();

	public Plane()
	{
        try
		{
			socket = new Socket(ADDRESS, PORT);
			is = new DataInputStream(socket.getInputStream());
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}

	public void start()
	{
        int valueFromServer = 0;

		try
		{
			while(socket.isConnected())
			{
				valueFromServer = is.readInt();

				if(valueFromServer == 1)
				{
			        runTime.exec(new String[] {"bash", "-c", "echo 1=+10 > /dev/servoblaster"});
				}

				if(valueFromServer == 2)
				{
					runTime.exec(new String[] {"bash", "-c", "echo 1=50% > /dev/servoblaster"});
				}

				if(valueFromServer == 3)
				{
					runTime.exec(new String[] {"bash", "-c", "echo 1=-10 > /dev/servoblaster"});
				}

                if(valueFromServer == 4)
				{
					runTime.exec(new String[] {"bash", "-c", "echo 3=+10 > /dev/servoblaster"});
				}

				if(valueFromServer == 5)
				{
					runTime.exec(new String[] {"bash", "-c", "echo 3=50% > /dev/servoblaster"});
				}

				if(valueFromServer == 6)
				{
					runTime.exec(new String[] {"bash", "-c", "echo 3=-10 > /dev/servoblaster"});
				}

				if(valueFromServer == 7)
				{
					try
			        {
				        socket.close();
			        }
			        catch(IOException ioe)
			        {
				        System.out.println(ioe);
			        }
				}
			}
		}
		catch(Exception e)
		{
		}
	}
}