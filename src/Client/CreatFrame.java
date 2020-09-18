package Client;

import java.awt.BorderLayout;
import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.net.Socket;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import com.sun.prism.paint.Color;

public class CreatFrame extends Thread{

	String width = "", height = "";
	
	private JFrame frame = new JFrame();
	private JDesktopPane desktopPane = new JDesktopPane();
	private Socket cSocket = null;
	private JInternalFrame internFrame = new JInternalFrame("Server screen", true, true, true);
	private JPanel cPanel = new JPanel();
	
	public CreatFrame(Socket cSocket, String width, String height) {
		// TODO Auto-generated constructor stub
		this.width = width;
		this.height = height;
		this.cSocket = cSocket;
		start();
	}
	
	public void drawGUI() {
		frame.add(desktopPane, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		
		internFrame.setLayout(new BorderLayout());
		internFrame.getContentPane().add(cPanel, BorderLayout.CENTER);
		internFrame.setSize(100,100);
		
		desktopPane.add(internFrame);
		
		try {
			internFrame.setMaximum(true);
		} catch (PropertyVetoException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		// This allows to handle KeyListener events
		cPanel.setFocusable(true);
		internFrame.setVisible(true);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		InputStream in = null;
		drawGUI();
		try {
			in = cSocket.getInputStream();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		new ReceivingScreen(in, cPanel);
		new SendEvents(cSocket, cPanel, width, height);
		super.run();
	}
	
	//public static void main(String[] args) {
		//Socket cSocket = new Socket();
		//CreatFrame creatFrame = new CreatFrame(cSocket, "100", "100");
	//}
}
 