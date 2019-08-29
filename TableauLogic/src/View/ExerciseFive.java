package View;

import javax.swing.JButton;

public class ExerciseFive extends ExerciseWithData {

	public JButton undo;
	
	public ExerciseFive() {
		super();
		this.title = "Constructing a Full Tableau";
		this.instructions = "Here you can train your ability to build a Tableau freely (the way you want). You can enter an expression you want to deal with into the program. This expression must be like : T [A & B ], F [A v B ], etc. The program will do the first step by applying the first rule on the root node. You can then choose which node to expand further and which tableau rule to apply first T&, Tv, T>, T~ or F&, Fv, F>, or F~. At each step you are free to develop the Tableau the way you want, without any restrictions. Also,  you will be able to undo your actions if you think you have not selected the best option. This exercise allows you to understand the importance of applying Alpha-rules before Beta-rules. The important thing to notice is that at each step you have to anticipate the result of applying one of the tableau rules on a node. You would need to guess what will be the child(ren) node(s) to obtain the next step (the next choice of the rule to apply on a node).";
		
		this.undo = new JButton();
		this.exerciseTop.add(this.undo);
	}
	
}
