import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Transmitter extends JFrame
{
    private Socket socket;
	static final int PORT = 2000;
	private String address;
	private DataOutputStream os;
	private DataInputStream is;
	private JButton button1;

	private int rudderPosition = 5;
	private int elevatorPosition = 14;

    public Transmitter()
	{
		WaitForInternet();

		setLayout(null);
		setSize(480, 240);
		setTitle("ICPlane Transmitter");
        addWindowListener(new CloseProgram());

		button1 = new JButton("Button");
		button1.addActionListener(new ButtonWatcher());
		button1.setBounds(20, 50, 64, 32);

		add(button1);

		addKeyListener(new KeyList());
		
		try
		{
			address = new String(Files.readAllBytes(Paths.get("targetip.txt")));
			socket = new Socket(address, PORT);
			os = new DataOutputStream(socket.getOutputStream());
			is = new DataInputStream(socket.getInputStream());

			System.out.println("Transmitter ready...");
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}
	
	public void runProgram()
	{
		setVisible(true);

		while(socket.isConnected())
		{
			try
			{
				System.out.println("server said: " + is.readInt());
			}
			catch(Exception e)
			{
				System.out.println("trouble on transmitter: " + e);
			}
		}
	}

    private class CloseProgram extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            System.exit(0);
            
            try
            {
				os.writeInt(19); //close socket on server
			    socket.close();
            }
            catch(IOException ioe)
            {
                System.out.println(ioe);
            }
        }
    }

    private class ButtonWatcher implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{

			if(e.getSource() == button1)
			{
			}

		}
	}

	private class KeyList extends KeyAdapter
    {
        public void keyPressed(KeyEvent k)
		{ 
			if(k.getKeyCode() == KeyEvent.VK_A)
			{
				if(rudderPosition > 1)
				{
					rudderPosition--;
					ModifyRudderPosition(rudderPosition);
				}
			}

			if(k.getKeyCode() == KeyEvent.VK_D)
			{
				if(rudderPosition < 9)
				{
					rudderPosition++;
					ModifyRudderPosition(rudderPosition);
				}
			}

			if(k.getKeyCode() == KeyEvent.VK_W)
			{
				rudderPosition = 5;
				ModifyRudderPosition(rudderPosition);
			}
	
			if(k.getKeyCode() == KeyEvent.VK_J)
			{
				if(elevatorPosition > 10)
				{
					elevatorPosition--;
					ModifyElevatorPosition(elevatorPosition);
				}
			}

			if(k.getKeyCode() == KeyEvent.VK_L)
			{
				if(elevatorPosition < 18)
				{
					elevatorPosition++;
					ModifyElevatorPosition(elevatorPosition);
				}
			}

			if(k.getKeyCode() == KeyEvent.VK_I)
			{
				elevatorPosition = 14;
				ModifyElevatorPosition(elevatorPosition);
			}
		} 
	}

	/**
    * Modifies the position of a servo 
    * @param  position  an integer that denotes the position of the rudder
    */
	private void ModifyRudderPosition(int position)
	{
		System.out.println("Rudder position = " + position);
		
		try
		{
			os.writeInt(position);
		}
		catch(Exception e)
		{
			System.out.println("Problem moving rudder: " + e);
		}
	}

	private void ModifyElevatorPosition(int position)
	{
		System.out.println("Elevator position = " + position);
		
		try
		{
			os.writeInt(position);
		}
		catch(Exception e)
		{
			System.out.println("Problem moving elevator: " + e);
		}
	}

	/**
    * Delays the running of the program in order to allow
	* the host system time to establish an internet connection
    */
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