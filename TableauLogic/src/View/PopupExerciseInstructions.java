package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class PopupExerciseInstructions extends JFrame {
	
	public PopupExerciseInstructions(Exercise exercise) {
		
		// We give the frame a BorderLayout
		this.setLayout(new BorderLayout());
		
		// We add the title and the instructions of the current exercise to this frame
		JLabel title = new JLabel(exercise.title, JLabel.CENTER);
		title.setFont(new Font("Serif", Font.PLAIN, 35));
		title.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 5));
		JTextArea instructions = new JTextArea(exercise.instructions);
		instructions.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		instructions.setFont(new Font("Serif", Font.PLAIN, 18));
		instructions.setEditable(false);
		instructions.setLineWrap(true);
		instructions.setWrapStyleWord(true);
		this.add(title, BorderLayout.NORTH);
		this.add(instructions, BorderLayout.CENTER);
		
		// We add a button for the user so that he can click on it to
		// close this frame and go for the exercise
		JButton button = new JButton("Go for it !");
		button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	CloseFrame();
		    }
		});
		this.add(button, BorderLayout.SOUTH);
		
		// We set up the characteristics of the frame
		Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) (Math.round(screenDimensions.width * 0.80));
		int height = (int) (Math.round(screenDimensions.height * 0.80));
		this.setPreferredSize(new Dimension(width, height));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	public void CloseFrame(){
		super.dispose();
	}
	
}
