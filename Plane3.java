import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Plane3 extends JFrame
{
    private Socket socket;
	static final int PORT = 2000;
	static final String ADDRESS = "127.0.0.1";
	private DataOutputStream os;
	private DataInputStream is;
	private JButton elevatorUpButton, elevatorDownButton, elevatorCenterButton, rudderLeftButton, rudderRightButton, rudderCenterButton, shutdownButton;
	private int id;

    public Plane3(int id)
	{
		System.out.println("Plane3 ready...");

		setLayout(null);
		setSize(480, 240);
		setTitle("ICPlane Plane3");
        addWindowListener(new CloseProgram());

		elevatorUpButton = new JButton("Up");
		elevatorUpButton.addActionListener(new ButtonWatcher());
		elevatorUpButton.setBounds(20, 50, 64, 32);

		elevatorCenterButton = new JButton("Center");
		elevatorCenterButton.addActionListener(new ButtonWatcher());
		elevatorCenterButton.setBounds(94, 50, 64, 32);

		elevatorDownButton = new JButton("Down");
		elevatorDownButton.addActionListener(new ButtonWatcher());
		elevatorDownButton.setBounds(168, 50, 64, 32);

		rudderLeftButton = new JButton("Left");
		rudderLeftButton.addActionListener(new ButtonWatcher());
		rudderLeftButton.setBounds(20, 120, 64, 32);

		rudderCenterButton = new JButton("Center");
		rudderCenterButton.addActionListener(new ButtonWatcher());
		rudderCenterButton.setBounds(94, 120, 64, 32);

		rudderRightButton = new JButton("Right");
		rudderRightButton.addActionListener(new ButtonWatcher());
		rudderRightButton.setBounds(168, 120, 64, 32);

		shutdownButton = new JButton("off");
		shutdownButton.addActionListener(new ButtonWatcher());
		shutdownButton.setBounds(94, 162, 64, 32);

		/*add(elevatorUpButton);
		add(elevatorCenterButton);
		add(elevatorDownButton);

		add(shutdownButton);

		add(rudderLeftButton);
		add(rudderCenterButton);
		add(rudderRightButton);*/

        try
		{
			socket = new Socket(ADDRESS, PORT);
			os = new DataOutputStream(socket.getOutputStream());
			is = new DataInputStream(socket.getInputStream());
		}
		catch(IOException e)
		{
			System.out.println(e);
		}

		this.id = id;
	}
	
	public void runProgram()
	{
		setVisible(true);

		while(true)
		{
			try
			{
				System.out.println("server said: " + is.readInt());

				if(is.readInt() == 1)
				{
					System.out.println("hello");
				}
			}
			catch(Exception e)
			{
				System.out.println("trouble on Plane3: " + e);
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
				os.writeInt(7); //close socket on server
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

			if(e.getSource() == elevatorUpButton)
			{
				try
				{
					os.writeInt(1);
				}
				catch(IOException ioe)
				{
					System.out.println(ioe);
				}
			}

			if(e.getSource() == elevatorCenterButton)
			{
				try
				{
				    os.writeInt(2);
				}
				catch(IOException ioe)
				{
					System.out.println(ioe);
				}
			}

			if(e.getSource() == elevatorDownButton)
			{
				try
				{
				    os.writeInt(3);
				}
				catch(IOException ioe)
				{
					System.out.println(ioe);
				}
			}
			
			if(e.getSource() == rudderLeftButton)
			{
				try
				{
				    os.writeInt(4);
				}
				catch(IOException ioe)
				{
					System.out.println(ioe);
				}
			}

			if(e.getSource() == rudderCenterButton)
			{
				try
				{
				    os.writeInt(5);
				}
				catch(IOException ioe)
				{
					System.out.println(ioe);
				}
			}

			if(e.getSource() == rudderRightButton)
			{
				try
				{
				    os.writeInt(6);
				}
				catch(IOException ioe)
				{
					System.out.println(ioe);
				}
			}

			if(e.getSource() == shutdownButton)
			{
				try
				{
				    os.writeInt(8);
				}
				catch(IOException ioe)
				{
					System.out.println(ioe);
				}
			}
		}
	}

	public int getId()
	{
		return id;
	}

}