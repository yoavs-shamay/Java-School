import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MainFrame extends JFrame implements ActionListener{

	private JPanel imagePanel;
	private JPanel buttonsPanel;
	private JButton sms,addStudent,removeStudent;
	private Hashtable ht;
	
	public MainFrame() {
		setLayout(new GridLayout(2, 1));
		setTitle("My School");
		imagePanel = new JPanel();
		imagePanel.setSize(300,500);
		try {
			int w = imagePanel.getWidth();
			int h = imagePanel.getHeight();
			BufferedImage im=ImageIO.read(new File("school.jpg"));
			BufferedImage img = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = img.createGraphics();
			g2.setBackground(UIManager.getColor("Panel.background"));
			g2.clearRect(0, 0, w, h);
	        double xScale = (double)w/im.getWidth();
	        double yScale = (double)h/im.getHeight();
	        double scale = Math.min(xScale, yScale);
	        int width  = (int)(scale*im.getWidth());
	        int height = (int)(scale*im.getHeight());
	        g2.drawImage(im, 20, 20, width, height, null);
	        g2.dispose();
			JLabel l = new JLabel(new ImageIcon(img));
			imagePanel.add(l);
		} catch (IOException e) {
			e.printStackTrace();
		}
		buttonsPanel = new JPanel();
		buttonsPanel.setSize(300,100);
		sms = new JButton("send SMS");
		sms.addActionListener(this);
		addStudent = new JButton("add student");
		addStudent.addActionListener(this);
		removeStudent = new JButton("remove student");
		removeStudent.addActionListener(this);
		buttonsPanel.add(sms);
		buttonsPanel.add(addStudent);
		buttonsPanel.add(removeStudent);
		add(imagePanel);
		add(buttonsPanel);
		ht = new Hashtable();
		ht.put("send SMS",new SendSMS());
		ht.put("add student", new AddStudent());
		ht.put("remove student", new RemoveStudent());
		setSize(600,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				MainFrame mf = new MainFrame();
			}
		});
	}
	
	public void actionPerformed(ActionEvent ae) {
		((Command) ht.get(ae.getActionCommand())).exec();
	}
	
}
