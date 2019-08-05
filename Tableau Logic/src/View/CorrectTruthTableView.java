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

public class CorrectTruthTableView extends JPanel{
	
	public CorrectTruthTableView(TruthTable truthTable) throws Exception{
		
		this.setLayout(new FlowLayout());
		
		int numberOfRows = truthTable.getNumberOfRows();
		int numberOfColumns = truthTable.getNumberOfColumns();
		int numberOfLiterals = truthTable.getNumberOfLiterals();
		
		// We build a JPanel for each column of the truth table
		for (int i = 0; i < numberOfColumns; i++) {
			
			JPanel column = new JPanel();
			column.setLayout(new BoxLayout(column, BoxLayout.Y_AXIS));
			column.setBorder(BorderFactory.createEtchedBorder());
			
			// The first line of each column is a Literal or a Formula
			if (i < truthTable.getLiterals().size()) {
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
			else {
				JLabel form = new JLabel(truthTable.getNode(i - numberOfLiterals).toString().
						substring(4, truthTable.getNode(i - numberOfLiterals).toString().length() - 1));
				form.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
				JPanel formPanel = new JPanel(new FlowLayout());
				formPanel.add(form);
				column.add(formPanel);
			}
			
			// For the rest of the lines we only give a JLabel with the correct answer
			for (int j = 0; j < numberOfRows; j++) {
				JLabel value = new JLabel(truthTable.getTable()[j][i]);
				value.setPreferredSize(new Dimension(50,25));
				value.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
				value.setHorizontalAlignment(JLabel.CENTER);
				JPanel valuePanel = new JPanel(new FlowLayout());
				valuePanel.add(value);
				column.add(valuePanel);
			}
			this.add(column);
		}		
	}
}
