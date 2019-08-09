package View;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Literal;
import Model.TruthTable;

public class OneTruthTableLineView extends JPanel{

	public JButton check;
	public JTextField answers[];
	
	public OneTruthTableLineView(TruthTable truthTable) throws Exception{
		
		int numberOfColumns = truthTable.getNumberOfColumns();
		int numberOfLiterals = truthTable.getNumberOfLiterals();
		this.answers = new JTextField[numberOfColumns];
		
		this.setLayout(new FlowLayout());
		
		// We build a JPanel for each column of the truth table
		for (int i = 0; i < answers.length; i++) {
			
			JPanel column = new JPanel();
			column.setLayout(new BoxLayout(column, BoxLayout.Y_AXIS));
			column.setBorder(BorderFactory.createEtchedBorder());
			
			// The first line is for each column the corresponding Literal/Formula
			//In this case a Literal
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
			}
			//In this one a Formula
			else {
				JLabel form = new JLabel(truthTable.getNode(i - numberOfLiterals).getFormulaString());
				form.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
				JPanel formPanel = new JPanel(new FlowLayout());
				formPanel.add(form);
				column.add(formPanel);
			}
			
			// The second line is a JTextField to let the user give his answer
			JTextField answer = new JTextField();
			this.answers[i] = answer;
			answer.setPreferredSize(new Dimension(50, 22));
			JPanel answerPanel = new JPanel(new FlowLayout());
			answerPanel.add(answer);
			answerPanel.setBorder(BorderFactory.createEmptyBorder(2, 0, 1, 0));
			answer.setHorizontalAlignment(JTextField.CENTER);
			column.add(answerPanel);
			
			this.add(column);
		}
		
		this.check = new JButton("Check");
		this.add(this.check);		
	
	}
	
}
