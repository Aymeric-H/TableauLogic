package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.print.attribute.standard.Copies;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

public class PopupViewDataExOne extends JFrame {

	
	public PopupViewDataExOne(ArrayList<String[]> mistakes, ArrayList<String> correctAnswers, int numberOfColumns) {
		
		// Main panel of the frame
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		this.add(mainPanel);
		
		JLabel title = new JLabel("Here are your general statistics for this exercise", JLabel.CENTER);
		title.setFont(new Font("Serif", Font.PLAIN, 35));
		title.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));
		mainPanel.add(title, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1,2));
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		// Panel for the correct answers
		JPanel correctAnswersPanel = new JPanel();
		correctAnswersPanel.setLayout(new BorderLayout());
		
		JLabel correctAnswersLabel = new JLabel("Your correct answers (First try)", JLabel.CENTER);
		correctAnswersLabel.setBorder(BorderFactory.createEmptyBorder(15, 5, 15, 5));
		correctAnswersLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		correctAnswersPanel.add(correctAnswersLabel, BorderLayout.NORTH);
		
		// Calculate the number of rows for the grid layout
		JPanel correctAnswersPanelCenter = new JPanel();
		int numberOfRows = 0;
		if (correctAnswers.size() % 2 == 0) {
			numberOfRows = correctAnswers.size() / 2;
		}
		else{
			numberOfRows = (correctAnswers.size() + 1) / 2;
		}
		// We display a grid Layout with two columns
		correctAnswersPanelCenter.setLayout(new GridLayout(numberOfRows, 2));
		JScrollPane scrollPaneCorrectAnswers = new JScrollPane(correctAnswersPanelCenter, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneCorrectAnswers.setBorder(BorderFactory.createEmptyBorder());
		correctAnswersPanel.add(scrollPaneCorrectAnswers, BorderLayout.CENTER);
		
		// We add the expressions with which the user made no mistakes to the panel
		// It fills the panel with one expression in the first column then one in the second and then it continues the
		// same way for each rows till there's no more expressions to display
		for (String correctAnswer : correctAnswers) {
			JLabel correctAnswerLabel = new JLabel(correctAnswer.replace("", " "));
			correctAnswerLabel.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 5));
			FontMetrics fm = correctAnswerLabel.getFontMetrics(correctAnswerLabel.getFont());
			correctAnswerLabel.setPreferredSize(new Dimension(fm.stringWidth(correctAnswer), fm.getHeight() + 35));
			correctAnswersPanelCenter.add(correctAnswerLabel);
		}
		
		centerPanel.add(correctAnswersPanel);
		
		
		// Panel for the mistakes made by the user
		JPanel mistakesPanel = new JPanel(new FlowLayout());
		mistakesPanel.setLayout(new BorderLayout());
		
		JLabel mistakesLabel = new JLabel("Your mistakes", JLabel.CENTER);
		mistakesLabel.setBorder(BorderFactory.createEmptyBorder(15, 5, 15, 5));
		mistakesLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		mistakesPanel.add(mistakesLabel, BorderLayout.NORTH);
		
		JPanel mistakesPanelCenter = new JPanel();
		mistakesPanelCenter.setLayout(new FlowLayout());
		JScrollPane scrollPaneMistakes = new JScrollPane(mistakesPanelCenter, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneMistakes.setBorder(BorderFactory.createEmptyBorder());
		mistakesPanel.add(scrollPaneMistakes, BorderLayout.CENTER);
		
		// We display the data (for the mistakes) columns by columns (the expression in the first one
		// and then the sub-formulas with the number of mistakes made by sub-formula)
		for (int i = 0; i < numberOfColumns; i++) {
			JPanel column = new JPanel();
			column.setLayout(new GridLayout(mistakes.size(), 1));
			for (String[] mistake : mistakes) {
				if (mistake.length > i) {
					JLabel mistakeLabel = new JLabel(mistake[i].replace("", " "));
					FontMetrics fm = mistakeLabel.getFontMetrics(mistakeLabel.getFont());
					mistakeLabel.setPreferredSize(new Dimension(fm.stringWidth(mistakeLabel.getText() + 30), fm.getHeight() + 35));
					column.add(mistakeLabel);
				}
				else{
					column.add(new JLabel(" "));
				}
			}
			mistakesPanelCenter.add(column);			
		}
		
		centerPanel.add(mistakesPanel);
		
		// Characteristics of the frame
		Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) (Math.round(screenDimensions.width * 0.80));
		int height = (int) (Math.round(screenDimensions.height * 0.80));
		this.setPreferredSize(new Dimension(width, height));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
}
