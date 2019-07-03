package View;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PopupView extends JFrame{

	String set;
	Set<String> operators;
	public ArrayList<JButton> buttons;
	
	public PopupView(String set, Set<String> operators){
		this.set = set;
		this.operators = operators;
		this.buttons = new ArrayList<JButton>();
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JLabel label = new JLabel(this.set);
		panel.add(label);
		for (String operator : operators) {
			JButton button = new JButton(operator);
			panel.add(button);
			this.buttons.add(button);
		}
		
		this.setLayout(new GridBagLayout());
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
}
