package View;

public class ExerciseOne extends Exercise {

	public ExerciseOne() {
		super();
		this.title = "Logical Structure of expressions";
		this.instructions = "The aim of this exercise is to provide the user a way to learn the representation of a logical structure of given expression. The logical structure is given as a tree. You will be able to enter any kind of logical expression with Booleans & (conjunction), v (disjunction), > (implication), ~ (negation) and see how it can be translated into a tree by clicking on the « Deal with it » button once the expression is written in the Text-field provided. The program then displays a structure. The expression that you need to enter must be written in the following format: letters should be all capital, a space should be written between the characters, for example: A & B. A v ( B V C), A > (B > D), except for negation which can be used without a space as ~A.";
	}
	
}
