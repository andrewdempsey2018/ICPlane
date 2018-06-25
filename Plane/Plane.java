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

		WaitForInternet();

        try
		{
			address = new String(Files.readAllBytes(Paths.get("targetip.txt")));
			socket = new Socket(address, PORT);
			is = new DataInputStream(socket.getInputStream());

			os = new DataOutputStream(socket.getOutputStream());

			System.out.println("Plane ready...");
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

				//rudder

				if(valueFromServer == 1)
				{
					System.out.println("Rudder 10%");
			        runTime.exec(new String[] {"bash", "-c", "echo 1=10% > /dev/servoblaster"});
				}

				if(valueFromServer == 2)
				{
					System.out.println("Rudder 20%");
			        runTime.exec(new String[] {"bash", "-c", "echo 1=20% > /dev/servoblaster"});
				}

				if(valueFromServer == 3)
				{
					System.out.println("Rudder 30%");
			        runTime.exec(new String[] {"bash", "-c", "echo 1=30% > /dev/servoblaster"});
				}

				if(valueFromServer == 4)
				{
					System.out.println("Rudder 40%");
			        runTime.exec(new String[] {"bash", "-c", "echo 1=40% > /dev/servoblaster"});
				}

				if(valueFromServer == 5)
				{
					System.out.println("Rudder 50%");
			        runTime.exec(new String[] {"bash", "-c", "echo 1=50% > /dev/servoblaster"});
				}

				if(valueFromServer == 6)
				{
					System.out.println("Rudder 60%");
			        runTime.exec(new String[] {"bash", "-c", "echo 1=60% > /dev/servoblaster"});
				}

				if(valueFromServer == 7)
				{
					System.out.println("Rudder 70%");
			        runTime.exec(new String[] {"bash", "-c", "echo 1=70% > /dev/servoblaster"});
				}

				if(valueFromServer == 8)
				{
					System.out.println("Rudder 80%");
			        runTime.exec(new String[] {"bash", "-c", "echo 1=80% > /dev/servoblaster"});
				}

				if(valueFromServer == 9)
				{
					System.out.println("Rudder 90%");
			        runTime.exec(new String[] {"bash", "-c", "echo 1=90% > /dev/servoblaster"});
				}

				//elevator
				if(valueFromServer == 10)
				{
					System.out.println("Elevator 10%");
			        runTime.exec(new String[] {"bash", "-c", "echo 1=10% > /dev/servoblaster"});
				}

				if(valueFromServer == 11)
				{
					System.out.println("Elevator 20%");
			        runTime.exec(new String[] {"bash", "-c", "echo 1=20% > /dev/servoblaster"});
				}

				if(valueFromServer == 12)
				{
					System.out.println("Elevator 30%");
			        runTime.exec(new String[] {"bash", "-c", "echo 1=30% > /dev/servoblaster"});
				}

				if(valueFromServer == 13)
				{
					System.out.println("Elevator 40%");
			        runTime.exec(new String[] {"bash", "-c", "echo 1=40% > /dev/servoblaster"});
				}

				if(valueFromServer == 14)
				{
					System.out.println("Elevator 50%");
			        runTime.exec(new String[] {"bash", "-c", "echo 1=50% > /dev/servoblaster"});
				}

				if(valueFromServer == 15)
				{
					System.out.println("Elevator 60%");
			        runTime.exec(new String[] {"bash", "-c", "echo 1=60% > /dev/servoblaster"});
				}

				if(valueFromServer == 16)
				{
					System.out.println("Elevator 70%");
			        runTime.exec(new String[] {"bash", "-c", "echo 1=70% > /dev/servoblaster"});
				}

				if(valueFromServer == 17)
				{
					System.out.println("Elevator 80%");
			        runTime.exec(new String[] {"bash", "-c", "echo 1=80% > /dev/servoblaster"});
				}

				if(valueFromServer == 18)
				{
					System.out.println("Elevator 90%");
			        runTime.exec(new String[] {"bash", "-c", "echo 1=90% > /dev/servoblaster"});
				}

				//other functions
				
                if(valueFromServer == 100) //shutdown airplane computer
				{
					System.out.println("Received shutdown command from transmitter...");

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

				if(valueFromServer == 20)
				{
					//turn on camera
				}

				if(valueFromServer == 21)
				{
					//turn off camera
				}

				if(valueFromServer == 22)
				{
					//?
				}

			}
		}
		catch(Exception e)
		{
			System.out.println("Connection lost " + e);
		}
	}

	private void WaitForInternet()
	{
		System.out.println("Waiting for internet connection to be established...");

		try
		{
			Thread.sleep(30000);
		}	
		catch(Exception e)
		{
		}
	}
}