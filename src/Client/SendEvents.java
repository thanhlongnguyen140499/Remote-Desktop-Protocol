package Client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JPanel;

public class SendEvents implements KeyListener, MouseMotionListener, MouseListener {
	
	private Socket socket = null;
	private JPanel panel = null;
	private PrintWriter writer = null;
	String width = "", height = "";
	double w;
	double h;
	
	public SendEvents(Socket cSocket, JPanel cPanel, String width, String height) {
		socket = cSocket;
		panel = cPanel;
		this.width = width;
		this.height = height;
		w = Double.valueOf(width.trim()).doubleValue();
		h = Double.valueOf(height.trim()).doubleValue();
		
		// Associate event listeners to the panel
		panel.addKeyListener(this);
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
		
		try {
			writer = new PrintWriter(socket.getOutputStream());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	// Override all implements
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		writer.println(Commands.PRESS_MOUSE.getAbbrev());
		int button = e.getButton();
		int xButton = 16;
		if (button == 3) {
			xButton = 4;
		}
		writer.println(xButton);
		writer.flush();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		double xScale = w/panel.getWidth();
		double yScale = h/panel.getHeight();
		writer.println(Commands.MOVE_MOUSE.getAbbrev());
		writer.println((int) (e.getX() * xScale));
		writer.println((int) (e.getY() * yScale));
		writer.flush();
	}
	@Override 
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
