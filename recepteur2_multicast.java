package recepteur;

import java.io.File;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class recepteur2_multicast {

	public static void main(String argv []) {
		int port = 5000;
		String host = "224.1.2.3";
		try {
			DatagramPacket packet;
			InetAddress groupeAdress = InetAddress.getByName(host);
			MulticastSocket socket = new MulticastSocket(port);
			socket.joinGroup(groupeAdress);
			byte[] data = new byte[100];
			packet = new DatagramPacket(data, data.length);
			System.out. println("recepteur 1 en attente:");
			socket.receive(packet);
			String fileName = new String(packet.getData(),0,packet.getLength());
			System.out. println("Le fichier "+fileName+" vient de : "+packet.getAddress()+"."+ packet.getPort());
			data = new byte[1000];
			File fichier = new File ("C:\\Users\\yasmine\\Desktop\\TP3\\"+fileName);
			FileOutputStream fileOutputStream = new FileOutputStream(fichier);
			packet = new DatagramPacket(data, data.length);
			while (packet.getLength()!=0) {
				socket.receive(packet);
				byte[] file = packet.getData();
				fileOutputStream.write(file);
			}
			System.out. println("Le fichier "+fileName+" vient de : "+packet.getAddress()+"."+ packet.getPort());
			
		}catch (Exception e) { 
		
			System.err.println("Err :"+e);}
	}
	
}
