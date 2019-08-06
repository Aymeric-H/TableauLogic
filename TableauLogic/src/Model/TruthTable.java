package Model;

import java.awt.FlowLayout;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TruthTable {

	private ArrayList<Formula> nodes;
	private HashMap<Literal, Integer> literals;
	private String[][] table;
	
	/*
	 * Constructor of the class
	 * input => the given expression to deal with
	 */
	public TruthTable(String input) throws Exception{
		this.nodes = new ArrayList<Formula>();
		this.literals = new HashMap<Literal, Integer>();
		String[] formulas = input.split(", ");
		for (String s : formulas) {
			boolean value = true;
			//If we have a syntax like "T[ ... ]" we have to deal with the T/F 
			//before dealing with the formulas that are inside the brackets
			if (s.charAt(0) == 'F') {
				value = false;
				s = s.substring(4, s.length() - 2);
			}
			else if (s.charAt(0) == 'T') {
				s = s.substring(4, s.length() - 2);
			}
			/*else{
				throw new Exception("Please enter a formula of this form : T [ ... ] !");
			}*/
			TreeMaker tree = new TreeMaker(s);
			//Don't forget syntaxAnalysis method can throw an Exception
			try {
				Formula f = tree.syntaxAnalysis();
				f.setValue(value);	
				this.initializeArrays(f);
			} catch (Exception e) {
				throw e;
			}
		}
		this.table = new String[(int) Math.pow(2, this.literals.size())][this.nodes.size() + this.literals.size()];
		this.initializeTable();
	}
	
	/*
	 * Function which initializes the class arrays
	 */
	public void initializeArrays(Formula formula){
		if (formula instanceof Literal) {
			boolean isAlreadyIn = false;
			for (Literal literal : this.literals.keySet()) {
				if (literal.name.equals(formula.name)) {
					isAlreadyIn = true;
				}
			}
			if (!isAlreadyIn) {
				this.literals.put((Literal)formula, this.literals.size());
			}
		}
		else{
			this.nodes.add(0, formula);
			if (formula instanceof OneFormulaOp) {
				this.initializeArrays(((OneFormulaOp)formula).getFormula());
			}
			else {
				this.initializeArrays(((TwoFormulasOp)formula).getFormulaOne());
				this.initializeArrays(((TwoFormulasOp)formula).getFormulaTwo());
			}
		}
	}

	/*
	 * Function which initializes the truth table
	 */
	public void initializeTable(){
		int numberOfValInARow = this.table.length / 2;
		for (int i = 0; i < this.literals.size(); i++) {
			String tmpVal = "T";
			int cpt = 0;
			for (int j = 0; j < this.table.length; j++) {
				if (cpt < numberOfValInARow) {
					this.table[j][i] = tmpVal;
					cpt ++;
				}
				else {
					if (tmpVal.equals("T")) {
						tmpVal = "F";
					}
					else{
						tmpVal = "T";
					}
					this.table[j][i] = tmpVal;
					cpt = 1;
				}
			}
			numberOfValInARow /= 2;
		}
	}

	/*
	 * Getter for the array of formulas of the expression (each formula is a step in the truth table)
	 */
	public ArrayList<Formula> getNodes() {
		return nodes;
	}

	/*
	 * Setter for the array of formulas
	 */
	public void setNodes(ArrayList<Formula> nodes) {
		this.nodes = nodes;
	}

	/*
	 * Getter for the Literals of the expression
	 */
	public HashMap<Literal, Integer> getLiterals() {
		return literals;
	}

	/*
	 * Setter for the Literals of the expression
	 */
	public void setLiterals(HashMap<Literal, Integer> literals) {
		this.literals = literals;
	}
	
	/*
	 * Getter for the values of the truth table
	 */
	public String[][] getTable(){
		return this.table;
	}

	/*
	 * Function which allows to get the String version of the truth table
	 */
	public String tableToString() {
		String truthTable = "";
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[0].length; j++) {
				truthTable += this.table[i][j];
				if (j < table[0].length - 1) {
					truthTable += ", ";
				}
			}
			truthTable += "\n";
		}
		return truthTable;
	}

	/*
	 * Setter for the truth table
	 */
	public void setTable(String[][] table) {
		this.table = table;
	}
	
	/*
	 * Function which allows to build the truth table
	 */
	public void buildTable() throws Exception{
		for (int i = 0; i < table.length; i++) {
			for (int j = this.literals.size(); j < table[0].length; j++) {
				Formula currentFormula = this.nodes.get(j - this.literals.size()).clone();
				try {
					this.setLiteralsValue(currentFormula, i);
				} catch (Exception e) {
					throw e;
				}
				if (currentFormula.evaluate()) {
					this.table[i][j] = "T";
				}
				else{
					this.table[i][j] = "F";
				}
			}
		}
	}
	
	/*
	 * Function which allows to set the value of each Literal considering the current line of the truth table
	 * formula => the formula to which we set the value of each Literal
	 * line => the current line of the truth table
	 */
	public void setLiteralsValue(Formula formula, int line) throws Exception{
		if (formula instanceof Literal) {
			int index = -1;
			for (Literal literal : this.literals.keySet()) {
				if (literal.name.equals(formula.name)) {
					index = this.literals.get(literal);
				}
			}
			if (index == -1) {
				throw new Exception("Literal not found !");
			}
			if (this.table[line][index].equals("F")) {
				((Literal)formula).setValue(false);
			}
		}
		else if (formula instanceof OneFormulaOp) {
				this.setLiteralsValue(((OneFormulaOp)formula).formula, line);
		}
		else{
			this.setLiteralsValue(((TwoFormulasOp)formula).formulaOne, line);
			this.setLiteralsValue(((TwoFormulasOp)formula).formulaTwo, line);
		}
	}
	
	/*
	 * Function which allows to check all the user's answers
	 * answers => the user's answers
	 */
	public boolean checkAnswers(String[][] answers){
		for (int i = 0; i < answers.length; i++) {
			for (int j = 0; j < answers[0].length; j++) {
				if (!answers[i][j].equals(this.table[i][j + this.literals.size()])) {
					return false;
				}
			}
		}
		return true;
	}
	
	public ArrayList<Integer> checkColumn(String[] answers, int column){
		ArrayList<Integer> mistakesIndex = new ArrayList<Integer>();
		for (int i = 0; i < answers.length; i++) {
			if (!answers[i].equals(this.table[i][column + this.literals.size()])) {
				mistakesIndex.add(i);
			}
		}
		return mistakesIndex;
	}
	
	/*
	 * Function which allows to compare the answers of the user with the truth table
	 * and returns whether they are true or false
	 * answers => the line of the truth table filled in by the user
	 */
	public boolean checkAnswersForLine(String[] answers) throws Exception{
		//We build the table
		try {
			this.buildTable();
		} catch (Exception e) {
			throw e;
		}
		//Then we look for the right line of the table
		for (int i = 0; i < this.getNumberOfRows(); i++) {
			boolean isThisLine = true;
			for (int j = 0; j < this.getNumberOfLiterals(); j++) {
				//When we find this line we compare the answers with the values in the truth table
				if (!this.table[i][j].equals(answers[j])) {
					isThisLine = false;
				}
			}
			if (isThisLine) {
				for (int k = this.getNumberOfLiterals(); k < this.getNumberOfColumns(); k++) {
					if (!this.table[i][k].equals(answers[k])) {
						return false;
					}
				}
				return true;
			}
		}
		throw new Exception("Line not found !");
	}

	/*
	 * Function which allows to get one of the Literals of the truth table thanks to its index
	 * i => the index of the wanted Literal
	 */
	public Literal getLiteral(int i) throws Exception{
		for (Literal literal : this.literals.keySet()) {
			if (this.literals.get(literal) == i) {
				return literal;
			}
		}
		throw new Exception("No such Literal !");
	}
	
	/*
	 * Function which returns the number of Rows in the truth table
	 */
	public int getNumberOfRows(){
		return this.table.length;
	}
	
	/*
	 * Function which returns the number of columns in the truth table
	 */
	public int getNumberOfColumns(){
		return this.table[0].length;
	}
	
	/*
	 * Function which returns the number of literals in the truth table
	 */
	public int getNumberOfLiterals(){
		return this.literals.size();
	}
	
	/*
	 * Function which allows to get one node/step of the truth table thanks to its index
	 * i => the index of the wanted node/step
	 */
	public Formula getNode(int i){
		return this.nodes.get(i);
	}
	
}
