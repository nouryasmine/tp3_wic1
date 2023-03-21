package emetteur;

import java.io.*;
import java.net.*;

public class emetteur_multicast {
	public static void main(String argv []){ 
		int port = 5000;
		String host = "224.1.2.3";
		byte[] reponse = new byte[10000];
		try {
			InetAddress groupeAdress = InetAddress.getByName(host);
			MulticastSocket socket = new MulticastSocket(port);
			DatagramPacket packet;
			String fileName = "C:\\Users\\yasmine\\eclipse-workspace\\tp3\\src\\emetteur\\server.txt";
		    File file = new File(fileName);
		    byte[] name = file.getName().getBytes();
		    packet = new DatagramPacket(name, name.length, groupeAdress, port);
			socket.send(packet);
			byte[] data = new byte[1000];
			FileInputStream input = new FileInputStream(file);
			while(input.read(data) != -1) {
				packet = new DatagramPacket(data, data.length, groupeAdress, port);
				socket.send(packet);
			}
			packet = new DatagramPacket(data, 0, groupeAdress, port);
			socket.send(packet);
			System.out.println("fichier est envoye");
		}catch (Exception e) { 
			System.err.println("Err :"+e);}
		}
}
