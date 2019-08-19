package View;

import java.awt.BorderLayout;
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

public class PopupViewDataExFive extends JFrame {

	public PopupViewDataExFive(ArrayList<String[]> data, int numberOfColumns) {
		
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
		
		for (int i = 0; i < numberOfColumns; i++) {
			JPanel column = new JPanel();
			column.setLayout(new GridLayout(data.size(), 1));
			for (String[] line : data) {
				if (i < line.length) {
					System.out.println(i + " - " + line[i]);
					JLabel label = new JLabel(line[i].replace("", " "));
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
