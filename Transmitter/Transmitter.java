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

	private JPanel panel;

	private int reach5toshutdown = 0;

	private boolean connectedToClient;

    public Transmitter()
	{
		panel = new JPanel();
		panel.setFocusable(true);

		setLayout(null);
		setSize(480, 240);
		setTitle("ICPlane Transmitter");

		add(panel);

        addWindowListener(new CloseProgram());

		button1 = new JButton("Button");
		button1.addActionListener(new ButtonWatcher());
		button1.setBounds(20, 50, 64, 32);

		panel.add(button1);

		panel.addKeyListener(new KeyList());
		
		try
		{
			address = new String(Files.readAllBytes(Paths.get("targetip.txt")));
			//address = "86.44.203.8";
			socket = new Socket(address, PORT);
			os = new DataOutputStream(socket.getOutputStream());
			is = new DataInputStream(socket.getInputStream());

			System.out.println("Transmitter ready...");
	
			connectedToClient = true;
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}
	
	public void runProgram()
	{
		setVisible(true);

		while(connectedToClient)
		{
			try
			{
				System.out.println("server said: " + is.readInt());
			}
			catch(Exception e)
			{
				//System.out.println("trouble on transmitter: " + e);
				System.out.println("Transmitter no longer connected to server");
				connectedToClient = false;
				cleanup();
			}
		}
	}

	private void cleanup()
	{
		try
		{
			is.close();
			os.close();
			socket.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Problem closing streams: " + ioe);
		}
	}

    private class CloseProgram extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            System.exit(0);
            
            try
            {
				os.writeInt(999); //close socket on server
				os.close();
				is.close();
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
			//center rudder
			if(k.getKeyCode() == KeyEvent.VK_W)
			{
				rudderPosition = 5;
				ModifyRudderPosition(rudderPosition);
			}
			//rudder all left
			if(k.getKeyCode() == KeyEvent.VK_Z)
			{
				rudderPosition = 2;
				ModifyRudderPosition(rudderPosition);
			}
			//rudder all right
			if(k.getKeyCode() == KeyEvent.VK_C)
			{
				rudderPosition = 8;
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
			//center elevator
			if(k.getKeyCode() == KeyEvent.VK_I)
			{
				elevatorPosition = 14;
				ModifyElevatorPosition(elevatorPosition);
			}
			
			//elevator all down
			if(k.getKeyCode() == KeyEvent.VK_B)
			{
				elevatorPosition = 11;
				ModifyElevatorPosition(elevatorPosition);
			}
			
			//elevator all up
			if(k.getKeyCode() == KeyEvent.VK_M)
			{
				elevatorPosition = 17;
				ModifyElevatorPosition(elevatorPosition);
			}

			if(k.getKeyCode() == KeyEvent.VK_SPACE)
			{
				reach5toshutdown++;

				if(reach5toshutdown >= 5)
				{
					try
					{
						os.writeInt(100); //send the shutdowen command to the plane
						Runtime.getRuntime().exec("sudo shutdown -h now");
					}
					catch(Exception e)
					{
						System.out.println("Error shutting down: " + e);
					}
                }
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

}