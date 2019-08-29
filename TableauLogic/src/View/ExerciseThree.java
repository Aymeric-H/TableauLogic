package View;

public class ExerciseThree extends ExerciseWithData {

	public ExerciseThree() {
		super();
		this.title = "Truth Table";
		this.instructions = "In this exercise you will be asked to fill in the truth table for an expression given to the program. You start with writing in the Textfield the expression you want to work with. This expression must be like T [ A & B ] or F [ A v B ]. After pressing the « Deal with it » button you will be asked to fill the blank fields with 'T' if you think this is the correct answer or 'F' otherwise. You do this for each blank field and go through the whole truth table. When this is done you can check your answers clicking the « Check » button (Details to know: the program checks the answers column by column so that if there is an error in the first column it stops the checking session and tells the user there is at least one mistake in the current column – errors are displayed in red). If there are no errors, the truth table is shown as fixed (the Textfields are replaced by labels so they can't be changed). In the other case, if there is one mistake (or more) you will be warned with a popup. You can then quit this popup and try again. You can verify answers and correct them before clicking the « Check » button again.";
	}
	
}
