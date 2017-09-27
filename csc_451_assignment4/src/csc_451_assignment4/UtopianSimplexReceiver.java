/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//lily 
package csc_451_assignment4;


import java.io.*;
import java.net.*;

class UtopianSimplexReciever 
{
	static final int size = 1024;
	public static void main(String args[]) throws IOException
	{
		int serverPort = 2102; // room number
		String serverName = "localhost";
		try 
		{
			DatagramSocket serverSocket = new DatagramSocket(null);
			InetSocketAddress address = new InetSocketAddress(serverName, serverPort);
			serverSocket.bind(address);
			byte[] receiveData = new byte[size];
			byte[] sendData = new byte[size];	//originally commented
			System.out.println("("+address.getHostName()+")SERVER is at "+ address.getAddress().getHostAddress());
			while(true) 
			{
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
				String sentence = new String( receivePacket.getData());
				InetAddress ipAddress = receivePacket.getAddress();
				System.out.println("("+ipAddress.getHostAddress()+")RECEIVED: " + sentence);
				
				//Send data
				InetAddress ipAddress2 = receivePacket.getAddress();
				int port = receivePacket.getPort();
				String sentence2 = new String(receivePacket.getData());
				
				sendData = sentence2.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port); // make datagram
				serverSocket.send(sendPacket); // send back datagram to sender (so he knows I got something from him
				System.out.println();
				
			}
			
		} catch (Exception e){ e.printStackTrace(); }
	}
}

/*

 -InetAddress ipAddress = receivePacket.getAddress(); // find IP address of sender
 int port = receivePacket.getPort(); //  get port number used by sender
 String sentence = new String( receivePacket.getData()); 
 sendData = sentence.getBytes();         
 DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);         // make datagram
 serverSocket.send(sendPacket);  // send back datagram to sender (so he knows I got something from him

*/