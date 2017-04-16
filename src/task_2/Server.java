package task_2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Socket serversocket = null;
		ObjectInputStream inputStream = null;
		ObjectOutputStream outputSteam = null;
		ServerSocket server = new ServerSocket(4444);

		while (true) {
			// receive
			serversocket = server.accept();
			inputStream = new ObjectInputStream(serversocket.getInputStream());
			Agent agent = (Agent) inputStream.readObject();
			System.out.print("Id: " + agent.showId() + ", Name: " + agent.showName());
			System.out.println(", From: " + agent.showIP());
			// send back
			agent.setId(456);
			agent.setName("Bob");
			agent.setTask("HELLO WORLD");
			outputSteam = new ObjectOutputStream(serversocket.getOutputStream());
			outputSteam.writeObject(agent);
			// clean
			inputStream.close();
			outputSteam.close();
			serversocket.close();

//			Socket clientSocket = new Socket("localhost", 1234);
//			outputSteam = new ObjectOutputStream(clientSocket.getOutputStream());
//			outputSteam.writeObject(agent);

		}
	}

}
