package View;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PopupViewChoseOperator extends JFrame{

	public String set;
	public Collection<String> operators;
	public ArrayList<JButton> buttons;
	public JPanel panelGeneral;
	public JPanel panelOperator;
	public JPanel panelResultAnticipation;
	public JButton checkAnticipation;
	public JTextField inputOne;
	public JTextField inputTwo;
	public JLabel labelInfo;
	
	public PopupViewChoseOperator(String set, Collection<String> operators){
		this.set = set;
		this.operators = operators;
		this.buttons = new ArrayList<JButton>();
		
		//Panel which combines everything
		this.panelGeneral = new JPanel();
		panelGeneral.setLayout(new BoxLayout(panelGeneral, BoxLayout.Y_AXIS));
		
		//Panel with the current Set information
		JPanel panelInfo = new JPanel();
		this.labelInfo = new JLabel(set);
		panelInfo.add(labelInfo);
		
		//Panel for the choice of the operation to do
		this.panelOperator = new JPanel();
		panelOperator.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JLabel label = new JLabel(this.set);
		panelOperator.add(label);
		for (String operator : operators) {
			JButton button = new JButton(operator);
			panelOperator.add(button);
			this.buttons.add(button);
		}
		
		//Panel for the anticipation of the result of the operation
		this.panelResultAnticipation = new JPanel();
		JLabel labelInputOne = new JLabel("First Child : ");
		this.inputOne = new JTextField();
		inputOne.setPreferredSize(new Dimension(250, 25));
		JLabel labelInputTwo = new JLabel("Second Child : ");
		this.inputTwo = new JTextField();
		inputTwo.setPreferredSize(new Dimension(250, 25));
		this.checkAnticipation = new JButton("Check it");
		
		JPanel labels = new JPanel();
		labels.setLayout(new BoxLayout(labels, BoxLayout.Y_AXIS));
		labels.add(labelInputOne);
		labels.add(Box.createRigidArea(new Dimension(0, 10)));
		labels.add(labelInputTwo);
		JPanel inputs = new JPanel();
		inputs.setLayout(new BoxLayout(inputs, BoxLayout.Y_AXIS));
		inputs.add(inputOne);
		inputs.add(Box.createRigidArea(new Dimension(0, 10)));
		inputs.add(inputTwo);
		panelResultAnticipation.add(labels);
		panelResultAnticipation.add(inputs);
		panelResultAnticipation.add(this.checkAnticipation);
		
		panelGeneral.add(panelInfo);
		panelGeneral.add(panelOperator);
		
		this.setPreferredSize(new Dimension(700, 200));
		this.setLayout(new GridBagLayout());
		this.add(panelGeneral);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
}
