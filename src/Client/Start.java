package Client;

import java.net.Socket;

import javax.swing.JOptionPane;

import sun.text.normalizer.ICUBinary.Authenticate;


public class Start {
	
	static String port = "5555";
	
	public static void main(String[] args) {
		try {
			// TODO Auto-generated method stub
			String ip = JOptionPane.showInputDialog("Please Enter server ip");
			new Start().initialize(ip, Integer.parseInt(port));
		} catch (Exception e) {
			// TODO: handle exception
		}
		 
	}
	
	public static void initialize(String ip, int port) {
		// TODO Auto-generated method stub
		try {
			Socket sc = new Socket(ip, port);
			System.out.println("Connecting to the server");
			Client.Authenticate frame1 = new Client.Authenticate(sc);
			frame1.setSize(200,200);
			frame1.setLocation(0,0);
			//frame1.setVisible(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
