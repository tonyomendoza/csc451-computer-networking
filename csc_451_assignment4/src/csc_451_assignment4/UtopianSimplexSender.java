/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc_451_assignment4;
import java.util.*;
import java.io.*;
import java.net.*;

/**
 *
 * @author Tony Mendoza
 */
class UtopianSimplexSender {
   static final int size = 1; //1024;
   public static void main(String args[]) /*throws Exception*/ {
      int serverPort = 2102;				// room number
      String serverName = "localhost";
      System.out.println("Connecting to "+serverName+" "+serverPort);
      System.out.println("Terminate sending with ctrl-D (ctrl-z on windows)");
      Scanner keyboard = new Scanner(System.in);
      try {
          DatagramSocket clientSocket = new DatagramSocket();
          InetAddress ipAddress = InetAddress.getByName(serverName);
          byte[] sendData = new byte[size];
          byte[] receiveData = new byte[size];
          while(keyboard.hasNext()) {
             String sentence = keyboard.nextLine();
             //System.out.println("FROM USER:"+sentence);
             for (int i=0; i<sentence.length(); i+=size) {
                 String s = sentence.substring(i, i+size);
                 sendData = sentence.substring(i, i+size).getBytes();
                 DatagramPacket sendPacket = new DatagramPacket(sendData, size, ipAddress, serverPort);
                 clientSocket.send(sendPacket);
             }
             
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);       // make empty datagram
            clientSocket.receive(receivePacket);                                                                                        // receive datagram 
            String returnMsg = new String(receivePacket.getData());
            System.out.print(returnMsg);
          }
          System.out.println("");
          clientSocket.close();
      } catch(Exception e){ e.printStackTrace(); }
   }
}