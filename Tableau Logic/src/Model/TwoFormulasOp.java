package Model;

import java.util.ArrayList;

public abstract class TwoFormulasOp extends Formula{

	protected Formula formulaOne, formulaTwo;
	
	/*
	 * one => first child formula of the operator
	 * two => second child formula of the operator
	 */
	public TwoFormulasOp(Formula one, Formula two, boolean value){
		this.formulaOne = one;
		this.formulaTwo = two;
		this.value = value;
	}
	
	/*
	 * Setter for the first child formula of the operator
	 */
	public void setFormulaOne(Formula form){
		this.formulaOne = form;
	}
	
	/*
	 * Setter for the second child formula of the operator
	 */
	public void setFormulaTwo(Formula form){
		this.formulaTwo = form;
	}
	
	/*
	 * Function which returns the current height of the formula (Node)
	 * Useful for the representation of the tree
	 */
	public int height(){
		return Math.max(this.formulaOne.height(), this.formulaTwo.height()) + 1;
	}
	
	/*
	 * Function which returns the first child formula of the operator
	 */
	public Formula getFormulaOne(){
		return this.formulaOne;
	}
	
	/*
	 * Function which return the second child formula of the operator
	 */
	public Formula getFormulaTwo(){
		return this.formulaTwo;
	}
	
	/*
	 * Function which return null by default as all the operators don't
	 * necessarily need this function
	 */
	public ArrayList<ArrayList<Literal>> getLiterals(){
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String childOne, childTwo;
		if (this.formulaOne == null) {
			childOne = null;
		}
		else if (this.formulaOne instanceof Literal) {
			if (!this.formulaOne.getValue()) {
				childOne = "~" + this.formulaOne.name;
			}
			else{
				childOne = this.formulaOne.name;
			}
		}
		else if (this.formulaOne instanceof OneFormulaOp){
			childOne = this.formulaOne.toString().substring(3, this.formulaOne.toString().length() - 2);
		}
		//Else it is a TwoFormulasOp
		else{
			childOne = "(" + this.formulaOne.toString().substring(3, this.formulaOne.toString().length() - 2) + ")";
		}
		if (this.formulaTwo == null) {
			childTwo = null;
		}
		else if (this.formulaTwo instanceof Literal) {
			if (!this.formulaTwo.getValue()) {
				childTwo = "~" + this.formulaTwo.name;
			}
			else{
				childTwo = this.formulaTwo.name;
			}
		}
		else if (this.formulaTwo instanceof OneFormulaOp){
			childTwo = this.formulaTwo.toString().substring(3, this.formulaTwo.toString().length() - 2);
		}
		//Else it is a TwoFormulasOp
		else{
			childTwo = "(" + this.formulaTwo.toString().substring(3, this.formulaTwo.toString().length() - 2) + ")";
		}
		if (this.value) {
			return "T[ " + childOne + " " + this.name +" " + childTwo + " ]";
		}
		else{
			return "F[ " + childOne + " " + this.name + " " + childTwo + " ]";
		}
	}

}
