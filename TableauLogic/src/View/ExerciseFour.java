package View;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class ExerciseFour extends Exercise {

	public JButton giveExpression;
	public JRadioButton modeOneStep;
	public JRadioButton modeDepthFirst;
	public JRadioButton modeBreadthFirst;
	public ButtonGroup modeSelector;
	public JButton nextStep;
	
	public ExerciseFour() {
		super();
		this.title = "Exploring different ways to construct a Tableau";
		this.instructions = "In this exercise you can ask the program to build a Tableau from an expression you need to write yourself. This expression must be like : T [ A & B ], F [ A v C ], etc. You would have the choice to get the Tableau in one go or to build it step by step. If you choose the first option, the program will build the Tableau directly after you have clicked the « Deal with it » button. Otherwise, you can use the program to get the Tableau in a step by step way. You can choose to get it done with the Depth-First method which corresponds to building the Tableau by developing a branch entirely before moving to another one. Alternatively, you can choose the Breadth-First method which corresponds to building the Tableau level of nodes by level of nodes. Finally, when you get the full Tableau, the program will also notify is the Tableau has a contradiction or not – a contradiction in a node.";
		
		this.giveExpression = new JButton();
		this.modeOneStep = new JRadioButton("In One Go");
		this.modeDepthFirst = new JRadioButton("Depth-First");
		this.modeBreadthFirst = new JRadioButton("Breadth-First");
		this.modeSelector = new ButtonGroup();
		
		this.exerciseTop.add(this.giveExpression);
		this.modeSelector.add(this.modeOneStep);
		this.modeSelector.add(this.modeDepthFirst);
		this.modeSelector.add(this.modeBreadthFirst);
		this.modeSelector.setSelected(this.modeOneStep.getModel(), true);
		this.nextStep = new JButton();
		
		this.exerciseTop.add(modeOneStep);
		this.exerciseTop.add(modeDepthFirst);
		this.exerciseTop.add(modeBreadthFirst);
		this.exerciseTop.add(nextStep);
	}
	
}
