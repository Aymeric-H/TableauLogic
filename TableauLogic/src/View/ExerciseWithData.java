package View;

import java.awt.BorderLayout;


import javax.swing.JButton;


public class ExerciseWithData  extends Exercise {
	
	public JButton giveExpression;
	public JButton progress;
	
	public ExerciseWithData() {
		super();
		this.title = "Computing a single row in the Truth Table";
		this.giveExpression = new JButton();
		this.progress = new JButton();
		this.exerciseTop.add(this.giveExpression);
		this.exerciseTop.add(this.progress);
	}
}
