package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import org.w3c.dom.css.RGBColor;

public class MenuExercisePanel extends JPanel {
	
	public MenuExercisePanel(Exercise exercise, String image) {
		
		this.setLayout(new BorderLayout());
		JLabel exLabel = new JLabel(exercise.title, JLabel.CENTER);
		exLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		exLabel.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
		ImageIcon icon = new ImageIcon(this.getClass().getResource(image));
		Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();
		ImageIcon resizedIcon = new ImageIcon(icon.getImage().getScaledInstance(screenDimensions.width/4, screenDimensions.height/4, Image.SCALE_SMOOTH));
		JLabel labelIcon = new JLabel(resizedIcon);
		this.add(exLabel, BorderLayout.NORTH);
		this.add(labelIcon, BorderLayout.CENTER);
		
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	}
	
}
