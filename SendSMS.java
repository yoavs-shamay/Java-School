import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;

public class SendSMS implements Command{
	private JFrame frame;
	private JPanel selectPanel,writeMessagePanel;
	private JLabel sc,ss,m;
	private JComboBox selectClass,selectStudent;
	private JButton select,send;
	private int phone;
	private JTextArea message;
	
	public void exec() {
		frame = new JFrame("select student");
		selectPanel = new JPanel();
		sc = new JLabel("select class:");
		String[] s = {"select class","a","b","c","d","e","f"};
		selectClass = new JComboBox(s);
		selectClass.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
		        int state = ie.getStateChange();
		        if(state == ItemEvent.SELECTED) {
		        	JComboBox cb = (JComboBox)ie.getItemSelectable();
	                String s = (String) cb.getSelectedItem();
	                if(s.equals("select class")) {
	                	selectPanel.remove(selectStudent);
	                	ss.setVisible(false);
	                	select.setVisible(false);
	                	return;
	                }
	                ConnectToDatabase ctd = new ConnectToDatabase();
	                selectPanel.remove(selectStudent);
	                ss.setVisible(true);
	                select.setVisible(true);
	                Vector v = ctd.getStudents(s);
	                selectStudent = new JComboBox(v);
	                selectPanel.add(selectStudent);
	                selectPanel.repaint();
	                selectPanel.validate();
		        }
			}
		});
		ss = new JLabel("select student:");
		ss.setVisible(false);
		selectStudent = new JComboBox();
		selectStudent.setVisible(false);
		select = new JButton("select");
		select.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				ConnectToDatabase ctd = new ConnectToDatabase();
				phone = ctd.getPhoneNumber((String)selectClass.getSelectedItem(), (String)selectStudent.getSelectedItem());
				System.out.println(phone);
				writeMessagePanel = new JPanel();
				m = new JLabel("enter message:");
				message = new JTextArea(5, 5);
				send = new JButton("send");
				send.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						SendSMSToStudent.sendSMS(phone, message.getText());
					}
				});
				writeMessagePanel.add(m);
				writeMessagePanel.add(message);
				writeMessagePanel.add(send);
				frame.setVisible(false);
				frame.getContentPane().removeAll();
				frame.getContentPane().add(writeMessagePanel);
				frame.setVisible(true);
			}
		});
		select.setVisible(false);
		selectPanel.add(sc);
		selectPanel.add(selectClass);
		selectPanel.add(ss);
		selectPanel.add(select);
		frame.add(selectPanel);
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
}