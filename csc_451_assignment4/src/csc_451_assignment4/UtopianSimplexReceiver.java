/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc_451_assignment4;
import java.io.*;
import java.net.*;

/**
 *
 * @author Tony Mendoza
 */

class UtopianSimplexReciever {
   static final int size = 1024;
   public static void main(String args[]) /*throws Exception*/ {
      int serverPort = 2102;                            // room number
      String serverName = "localhost";
      try  {
         DatagramSocket serverSocket = new DatagramSocket(null);
         InetSocketAddress address = new InetSocketAddress(serverName, serverPort);
         serverSocket.bind(address);
         byte[] receiveData = new byte[size];
         byte[] sendData = new byte[size];
         System.out.println("("+address.getHostName()+")SERVER is at "+ address.getAddress().getHostAddress());
         while(true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String( receivePacket.getData());

            InetAddress ipAddress = receivePacket.getAddress();
            System.out.println("("+ipAddress.getHostAddress()+")RECEIVED: " + sentence);
            int port = receivePacket.getPort();                                                            //  get port number used by sender
            String newSentence = new String( receivePacket.getData()); 
            sendData = newSentence.getBytes();         
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);         // make datagram
            serverSocket.send(sendPacket);                                                                // send back datagram to sender (so he knows I got something from him
         }
      } catch (Exception e){ e.printStackTrace(); }
   }
}