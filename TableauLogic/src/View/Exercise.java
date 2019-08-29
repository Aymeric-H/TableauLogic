package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class Exercise extends JPanel {

	public JTextField inputExpressions;
	public JButton dealExpressions;
	public JButton reset;
	public JButton goBack;
	public String title;
	public JPanel exerciseTop;
	public String instructions;
	
	public Exercise() {
		
		this.setLayout(new BorderLayout());
		JLabel inputLabel = new JLabel("Input : ");
		this.inputExpressions = new JTextField();
		this.inputExpressions.setPreferredSize(new Dimension(500, 27));
		this.inputExpressions.setMargin(new Insets(5, 2, 5, 2));
		this.dealExpressions = new JButton();
		this.reset = new JButton();
		this.exerciseTop = new JPanel(new FlowLayout());
		exerciseTop.add(inputLabel);
		exerciseTop.add(this.inputExpressions);
		exerciseTop.add(this.dealExpressions);
		exerciseTop.add(this.reset);
		this.goBack = new JButton();
		
		this.add(exerciseTop, BorderLayout.NORTH);
		this.add(goBack,BorderLayout.SOUTH);
		
	}
	
}
