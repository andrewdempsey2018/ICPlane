import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Plane
{
    private Socket socket;
	static final int PORT = 2000;
	private String address;
	private DataInputStream is;

	private DataOutputStream os;

	Runtime runTime = Runtime.getRuntime();

	public Plane()
	{
        System.out.println("Plane ready...");

        try
		{
			address = new String(Files.readAllBytes(Paths.get("targetip.txt")));
			socket = new Socket(address, PORT);
			is = new DataInputStream(socket.getInputStream());

			os = new DataOutputStream(socket.getOutputStream());
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}

	public void runProgram()
	{
        try
		{
			int valueFromServer = 0;

			while(socket.isConnected())
			{
                valueFromServer = is.readInt();

				if(valueFromServer == 1)
				{
					System.out.println("elevator up: +10");
			        runTime.exec(new String[] {"bash", "-c", "echo 1=+10 > /dev/servoblaster"});
				}

				if(valueFromServer == 2)
				{
					System.out.println("elevator center: 50%");
					runTime.exec(new String[] {"bash", "-c", "echo 1=50% > /dev/servoblaster"});
				}

				if(valueFromServer == 3)
				{
					System.out.println("elevator down: -10");
					runTime.exec(new String[] {"bash", "-c", "echo 1=-10 > /dev/servoblaster"});
				}

                if(valueFromServer == 4)
				{
					System.out.println("rudder left: +10");
					runTime.exec(new String[] {"bash", "-c", "echo 3=+10 > /dev/servoblaster"});
				}

				if(valueFromServer == 5)
				{
					System.out.println("rudder center: 50%");
					runTime.exec(new String[] {"bash", "-c", "echo 3=50% > /dev/servoblaster"});
				}

				if(valueFromServer == 6)
				{
					System.out.println("rudder right: -10");
					runTime.exec(new String[] {"bash", "-c", "echo 3=-10 > /dev/servoblaster"});
				}

				if(valueFromServer == 19)
				{
					try
			        {
				        socket.close();
			        }
			        catch(IOException ioe)
			        {
				        System.out.println(ioe);
			        }

					System.out.println("shutting down airplane...");
					runTime.exec("sudo shutdown -h now");
				}

			}
		}
		catch(Exception e)
		{
			System.out.println("Connection lost " + e);
		}
	}
}