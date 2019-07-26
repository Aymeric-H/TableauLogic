package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Literal;
import Model.TruthTable;
import Model.Formula;

public class TruthTableView extends JPanel{

	public JTextField[][] answers;
	public JButton check;
	
	public TruthTableView(TruthTable truthTable) throws Exception{
		
		int numberOfRows = truthTable.getNumberOfRows();
		int numberOfColumns = truthTable.getNumberOfColumns();
		int numberOfLiterals = truthTable.getNumberOfLiterals();
		
		this.answers = new JTextField[numberOfRows][numberOfColumns - numberOfLiterals];
		this.setLayout(new FlowLayout());
		
		for (int i = 0; i < numberOfColumns; i++) {
			
			JPanel column = new JPanel();
			column.setLayout(new BoxLayout(column, BoxLayout.Y_AXIS));
			column.setBorder(BorderFactory.createEtchedBorder());
			
			//First line of the column => Literal/Formula
			if (i < numberOfLiterals) {
				Literal literal;
				try {
					literal = truthTable.getLiteral(i);
					JLabel lit = new JLabel(literal.getName());
					lit.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
					JPanel litPanel = new JPanel(new FlowLayout());
					litPanel.add(lit);
					column.add(litPanel);
				} catch (Exception e) {
					throw e;
				}
				
				//Rest of the lines => value of the element
				for (int j = 0; j < numberOfRows; j++) {
					JLabel value = new JLabel(truthTable.getTable()[j][i]);
					value.setPreferredSize(new Dimension(50,25));
					value.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
					value.setHorizontalAlignment(JLabel.CENTER);
					JPanel valuePanel = new JPanel(new FlowLayout());
					valuePanel.add(value);
					column.add(valuePanel);
				}
			}
			else {
				JLabel form = new JLabel(truthTable.getNode(i - numberOfLiterals).toString().
						substring(4, truthTable.getNode(i - numberOfLiterals).toString().length() - 1));
				form.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
				JPanel formPanel = new JPanel(new FlowLayout());
				formPanel.add(form);
				column.add(formPanel);
				
				//Rest of the lines => value of the element
				for (int j = 0; j < numberOfRows; j++) {
					JTextField answer = new JTextField();
					this.answers[j][i - numberOfLiterals] = answer;
					answer.setPreferredSize(new Dimension(50, 22));
					JPanel answerPanel = new JPanel(new FlowLayout());
					answerPanel.add(answer);
					answerPanel.setBorder(BorderFactory.createEmptyBorder(2, 0, 1, 0));
					answer.setHorizontalAlignment(JTextField.CENTER);
					column.add(answerPanel);
				}
			}
			this.add(column);
		}
		
		this.check = new JButton();
		this.add(this.check);
	}	
}
