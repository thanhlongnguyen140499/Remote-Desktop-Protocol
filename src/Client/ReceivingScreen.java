package Client;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Image;
import sun.security.util.Length;

public class ReceivingScreen extends Thread {

	public InputStream inInputStream = null;
	private JPanel panel = null;
	Image image1 = null;
	private boolean continueLoop = true;

	public ReceivingScreen(InputStream in, JPanel cPanel) {
		// TODO Auto-generated constructor stub

		inInputStream = in;
		panel = cPanel;
		start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (continueLoop) {
				byte[] bytes = new byte[1024 * 1024];
				int count = 0;
				do {
					count += inInputStream.read(bytes, count, bytes.length - count);
				} while (!(count > 4 && bytes[count - 2] == (byte) -1) && bytes[count - 1] == (byte) -39);

				image1 = ImageIO.read(new ByteArrayInputStream(bytes));
				image1 = image1.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_FAST);

				// Draw the received screenshots

				Graphics graphics = panel.getGraphics();
				graphics.drawImage(image1, 0, 0, panel.getWidth(), panel.getHeight(), panel);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.run();
	}
}
