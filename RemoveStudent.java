import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

public class RemoveStudent implements Command{
	private JFrame selectWindow;
	private JPanel panel;
	private JComboBox selectClass,selectStudent;
	private JLabel sc,ss;
	private JButton remove;
	
	public void exec() {
		selectWindow = new JFrame("select student");
		panel = new JPanel();
		sc = new JLabel("select class");
		String[] classes = {"select class","a","b","c","d","e","f"};
		selectClass = new JComboBox(classes);
		selectClass.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				int state = ie.getStateChange();
		        if(state == ItemEvent.SELECTED) {
		        	JComboBox cb = (JComboBox)ie.getItemSelectable();
	                String s = (String) cb.getSelectedItem();
	                if(s.equals("select class")) {
	                	panel.remove(selectStudent);
	                	ss.setVisible(false);
	                	remove.setVisible(false);
	                	return;
	                }
	                ConnectToDatabase ctd = new ConnectToDatabase();
	                panel.remove(selectStudent);
	                ss.setVisible(true);
	                remove.setVisible(true);
	                Vector v = ctd.getStudents(s);
	                selectStudent = new JComboBox(v);
	                panel.add(selectStudent);
	                panel.repaint();
	                panel.validate();
		        }
			}
		});
		ss =new JLabel("select student");
		ss.setVisible(false);
		selectStudent = new JComboBox();
		remove = new JButton("remove");
		remove.setVisible(false);
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		panel.add(sc);
		panel.add(selectClass);
		panel.add(ss);
		panel.add(remove);
		selectWindow.add(panel);
		selectWindow.setSize(300, 300);
		selectWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		selectWindow.setVisible(true);
	}
}
