import java.io.*;
import java.net.*;

public class Server
{

    private ServerSocket serverSocket;
	private Socket socket;
    private DataInputStream is;
		
	static final int PORT = 2000;

	Runtime runTime = Runtime.getRuntime();

    public Server()
	{
        try
		{
			serverSocket = new ServerSocket(PORT);
		}
		catch(IOException e)
		{
			System.out.println(e);
		}

		/*try
		{
        }
        catch(Exception e)
		{
            System.out.println(e);
		}*/
	}
	
	public void run()
	{
		try
		{
			socket = serverSocket.accept();
			is = new DataInputStream(socket.getInputStream());

            int valueFromClient = 0;
			

			while(socket.isConnected())
			{
			    
			    valueFromClient = is.readInt();
				
                if(valueFromClient == 1)
				{
					System.out.println("Elevator++");
					//runTime.exec("sudo echo 1=-10 > /dev/servoblaster");
					runTime.exec(new String[] {"bash", "-c", "echo 1=+10 > /dev/servoblaster"});
				}

				if(valueFromClient == 3)
				{
					System.out.println("Elevator--");
					//runTime.exec("echo 1=-10 > /dev/servoblaster");
					runTime.exec(new String[] {"bash", "-c", "echo 1=-10 > /dev/servoblaster"});
				}

				if(valueFromClient == 4)
				{
					System.out.println("Rudder++");
					//runTime.exec("echo 3=+10 > /dev/servoblaster");
					runTime.exec(new String[] {"bash", "-c", "echo 3=+10 > /dev/servoblaster"});
				}

                if(valueFromClient == 6)
				{
					System.out.println("Rudder--");
					//runTime.exec("echo 3=-10 > /dev/servoblaster");
					runTime.exec(new String[] {"bash", "-c", "echo 3=-10 > /dev/servoblaster"});
				}

				/*if(valueFromClient == 5)
				{
					System.out.println("Rudder position is Center");
					runTime.exec("gpio pwm 24 152 && gpio pwm 1 200");
				}
				if(valueFromClient == 6)
				{
					System.out.println("Rudder position is Right");
					runTime.exec("gpio pwm 24 200 && gpio pwm 1 152");
				}*/

				if(valueFromClient == 7)
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
		catch(IOException e)
		{
			System.out.println(e);
		}

		//statusLabel.setText("Client has disconnected");
		
	}

    /*public class CloseProgram extends WindowAdapter
    {
        public void windowClosing (WindowEvent e)
        {
			try
			{
				socket.close();
			}
			catch(IOException ioe)
			{
				System.out.println(ioe);
			}
            System.exit(0);
        }
    }*/
}