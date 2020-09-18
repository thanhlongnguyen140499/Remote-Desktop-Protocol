package Client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Authenticate extends JFrame implements ActionListener{
	
	private Socket cSocket = null;
	DataOutputStream passWordCheck = null;
	DataInputStream verification = null;
	String verifyString = "";
	JButton submitButton;
	JPanel panel;
	JLabel lb,lbPassword;
	String width = "", height = "";
	JTextField text1;
	
	public Authenticate(Socket cSocket) {
		// TODO Auto-generated constructor stub
		lbPassword = new JLabel();
		lbPassword.setText("Password");
		text1 = new JTextField(15);
		this.cSocket = cSocket;
		lb = new JLabel();
		lb.setText("");
		
		this.setLayout(new BorderLayout());
		submitButton = new JButton("Submit");
		panel = new JPanel(new GridLayout(2,1));
		
		panel.add(lbPassword);
		panel.add(text1);
		panel.add(lb);
		panel.add(submitButton);
		
		this.add(panel, BorderLayout.CENTER);
		submitButton.addActionListener(this);
		setTitle("Login Form");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String getPassword = lbPassword.getText();
		try {
			passWordCheck = new DataOutputStream(cSocket.getOutputStream());
			verification = new DataInputStream(cSocket.getInputStream());
			passWordCheck.writeUTF(getPassword);
			verifyString = verification.readUTF();
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
		if (verifyString.equals("valid")) {
			try {
				width = verification.readUTF();
				height = verification.readUTF();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			CreatFrame crFrame = new CreatFrame(cSocket,width,height);
			dispose();
		}else {
			System.out.println("Please enter valid password");
			JOptionPane.showMessageDialog(this, "Password is incorrect", "Error", JOptionPane.ERROR_MESSAGE);
			dispose();
		}
	}
	
}
