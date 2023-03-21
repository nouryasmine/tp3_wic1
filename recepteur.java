package recepteur;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class recepteur {
	public static void main(String arvg[])
	{ int port =2001;
	try {
		DatagramPacket packet;
		DatagramSocket socket = new DatagramSocket(port);
		byte[] reponse = new byte[101];
		packet= new DatagramPacket(reponse,reponse.length);
		System.out.println("recepteur en attente:");
		socket.receive(packet);
		String file = new String(packet.getData(),0,packet.getLength());
		InetAddress adr =packet.getAddress();
		int portt =packet.getPort();
		System.out.println("file:"+file+" de :"+packet.getAddress()+"."+packet.getPort());
		
		File newFile = new File ("C:\\Users\\yasmine\\Desktop\\TP3\\"+file);
		newFile.createNewFile();
		System.out. println("*************");
		FileOutputStream output= new FileOutputStream(newFile);
		String rep = "bien recu";
		byte[] response = rep.getBytes();
		packet=new DatagramPacket(response,response.length,adr,portt);
		socket.send(packet);
		byte[] tab= new byte [1000];
		packet= new DatagramPacket(tab,tab.length);
	    while(packet.getLength()!=0 ) {
	    	socket.receive(packet);
	    	byte[] fichier= packet.getData();
	    	output.write(fichier) ;
	    	
	    }
	    System.out. println("Le fichier "+file+" vient de : "+packet.getAddress()+"."+ packet.getPort());
	    
	}
	catch(Exception e) {
		System.out.println("Erreur:"+e);
	}
}
}