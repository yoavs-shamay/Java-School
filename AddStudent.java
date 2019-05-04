import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AddStudent implements Command {
	private JFrame frame;
	private JPanel panel;
	private JLabel i,n,p,c;
	private JTextField id,name,phone,classroom;
	private JButton add;
	
	public void exec() {
		frame = new JFrame("add student");
		panel = new JPanel();
		i = new JLabel("id:");
		id = new JTextField(10);
		n = new JLabel("name:");
		name = new JTextField(10);
		p = new JLabel("phone:");
		phone = new JTextField(10);
		c = new JLabel("class:");
		classroom = new JTextField(10);
		add = new JButton("add");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConnectToDatabase ctd = new ConnectToDatabase();
				ctd.addStudent(Integer.parseInt(id.getText()), name.getText(), Integer.parseInt(phone.getText()), classroom.getText());
				frame.dispose();
			}
		});
		panel.add(i);
		panel.add(id);
		panel.add(n);
		panel.add(name);
		panel.add(p);
		panel.add(phone);
		panel.add(c);
		panel.add(classroom);
		panel.add(add);
		frame.add(panel);
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
}
