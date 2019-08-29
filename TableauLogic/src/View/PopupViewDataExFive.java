package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PopupViewDataExFive extends JFrame {

	public PopupViewDataExFive(ArrayList<String[]> data) {
		
		// Main panel of the frame
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		this.add(mainPanel);
		
		JLabel title = new JLabel("Here are your general statistics for this exercise", JLabel.CENTER);
		title.setFont(new Font("Serif", Font.PLAIN, 35));
		title.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));
		mainPanel.add(title, BorderLayout.NORTH);
		
		
		JPanel dataPanel = new JPanel(new FlowLayout());
		JScrollPane scrollPane = new JScrollPane(dataPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		// First column of data (the Set)
		JPanel firstColumn = new JPanel();
		firstColumn.setLayout(new BoxLayout(firstColumn, BoxLayout.Y_AXIS));
		JLabel legendColumnOne = new JLabel("Set");
		legendColumnOne.setFont(new Font("Serif", Font.PLAIN, 22));
		legendColumnOne.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		firstColumn.add(legendColumnOne);
		
		// Second columns of data (the number of mistakes in predictions)
		JPanel secondColumn = new JPanel();
		secondColumn.setLayout(new BoxLayout(secondColumn, BoxLayout.Y_AXIS));
		JLabel legendColumnTwo = new JLabel("Number of Mistakes (predictions)");
		legendColumnTwo.setFont(new Font("Serif", Font.PLAIN, 22));
		legendColumnTwo.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		secondColumn.add(legendColumnTwo);
		
		// Third column of data (number of Beta-rules applied before Alpha-rules)
		JPanel thirdColumn = new JPanel();
		thirdColumn.setLayout(new BoxLayout(thirdColumn, BoxLayout.Y_AXIS));
		JLabel legendColumnThree = new JLabel("Number of Beta-Rules applied before Alpha-Rules");
		legendColumnThree.setFont(new Font("Serif", Font.PLAIN, 22));
		legendColumnThree.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		thirdColumn.add(legendColumnThree);
		
		for (String[] d : data) {
			JLabel dataFirstColum = new JLabel(d[0].replace("", " "));
			dataFirstColum.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			firstColumn.add(dataFirstColum);
		
			JLabel dataSecondColum = new JLabel(d[1].replace("", " "));
			dataSecondColum.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			secondColumn.add(dataSecondColum);
			
			JLabel dataThirdColum = new JLabel(d[2].replace("", " "));
			dataThirdColum.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			thirdColumn.add(dataThirdColum);
		}
		
		dataPanel.add(firstColumn);
		dataPanel.add(secondColumn);
		dataPanel.add(thirdColumn);
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		
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
