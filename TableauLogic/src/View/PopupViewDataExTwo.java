package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PopupViewDataExTwo extends JFrame {

	public PopupViewDataExTwo(ArrayList<String[]> data, int numberOfColumns) {
		
		// Main panel of the frame
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		this.add(mainPanel);
		
		// Title panel
		JLabel title = new JLabel("Here are your general statistics for this exercise", JLabel.CENTER);
		title.setFont(new Font("Serif", Font.PLAIN, 35));
		title.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));
		mainPanel.add(title, BorderLayout.NORTH);
		
		// Panel for the center of the frame (data)
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new FlowLayout());
		JScrollPane scrollPane = new JScrollPane(centerPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		
		// Number of columns - 1 because the last column is for telling whether the answer is correct or not
		for (int i = 0; i < numberOfColumns - 1; i++) {
			JPanel column = new JPanel();
			column.setLayout(new GridLayout(data.size(), 1));
			for (String[] line : data) {
				if (i < line.length) {
					JLabel label = new JLabel(line[i].replace("", " "));
					if (line[line.length-1].equals("false")) {
						label.setForeground(Color.RED);
					}
					FontMetrics fm = label.getFontMetrics(label.getFont());
					label.setPreferredSize(new Dimension(fm.stringWidth(label.getText() + 30), fm.getHeight() + 35));
					column.add(label);
				}
				else {
					column.add(new JLabel(""));
				}
			}
			centerPanel.add(column);
		}
		
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
