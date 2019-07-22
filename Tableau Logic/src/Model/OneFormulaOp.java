package Model;

import java.util.ArrayList;

public abstract class OneFormulaOp extends Formula{

	protected Formula formula;
	
	/*
	 * formula => the operator child
	 */
	public OneFormulaOp(Formula formula, boolean value){
		this.formula = formula;
		this.value = value;
	}
	
	/*
	 * Setter for the child formula of the operator
	 */
	public void setFormula(Formula form){
		this.formula = form;
	}
	
	/*
	 * Function which returns the current height of the formula (Node)
	 * Useful for the representation of the tree
	 */
	public int height(){
		return this.formula.height() + 1;
	}
	
	/*
	 * Function which returns the operator child formula
	 */
	public Formula getFormula(){
		return this.formula;
	}
	
	/*
	 * Function which return null as all the operators working with only
	 * one child formula are dealt with before the validation step
	 */
	public ArrayList<ArrayList<Literal>> getLiterals(){
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String child;
		if (this.formula == null) {
			child = null;
		}
		else if (this.formula instanceof Literal) {
			if (!this.formula.getValue()) {
				child = "~" + this.formula.name;
			}
			else{
				child = this.formula.name;
			}
		}
		else{
			child = "( " + this.formula.toString().substring(4,this.formula.toString().length() - 2) + " )";
		}
		if (this.value) {
			return "T [ " + this.name + child  + " ]";
		}
		else{
			return "F [ " + this.name + child + " ]";
		}
	}
	
}
