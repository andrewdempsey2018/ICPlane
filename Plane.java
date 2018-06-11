import java.io.*;
import java.net.*;

public class Plane
{
    private Socket socket;
	static final int PORT = 2000;
	static final String ADDRESS = "127.0.0.1";
	private DataInputStream is;

	private DataOutputStream os; //123

	Runtime runTime = Runtime.getRuntime();

	public Plane()
	{

		System.out.println("Plane ready...");
        try
		{
			socket = new Socket(ADDRESS, PORT);
			is = new DataInputStream(socket.getInputStream());

			os = new DataOutputStream(socket.getOutputStream());
os.writeInt(999);
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

			valueFromServer = is.readInt();

			System.out.println("val from server= " + valueFromServer);

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

				if(valueFromServer == 8)
				{
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