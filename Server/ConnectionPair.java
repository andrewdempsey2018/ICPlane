public class ConnectionPair extends Thread
{
	public Socket socket;
	public Socket socket2;
	public DataInputStream is;
	public DataOutputStream os;

	public DataInputStream is2;
	public DataOutputStream os2;
    
	public ConnectionPair(Socket socket, DataInputStream is, DataOutputStream os, Socket socket2, DataInputStream is2, DataOutputStream os2)
	{
	    this.socket = socket;
		this.is = is;
		this.os = os;

		this.socket2 = socket2;
		this.is2 = is2;
		this.os2 = os2;
	}

	public void run()
	{
	

		//while(runPair)
		//{
		try
		{
			messageFromServer = is.readInt();
			os2.writeInt(messageFromServer);

		    if(messageFromServer == 999)
			{
				os.close();
				is.close();
				os2.close();
				is2.close();
				socket.close();
				socket2.close();
				//runPair = false;
				messageFromServer = 0;
			}
				
				}
				catch(Exception e)
				{
					System.out.println("Trouble processing messages on server: " + e);
				}
			//}

			//try
			//{
				
				
			//	connection.wait();
			//}
			//catch(Exception e)
			//{
			//	System.out.println("trouble closing sockets" + e);
			//}
		}
			
		
}