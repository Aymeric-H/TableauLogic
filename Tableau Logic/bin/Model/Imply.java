package Model;

import java.util.ArrayList;

public class Imply extends TwoFormulasOp{

	/*
	 * one => first child formula of the operator
	 * two => second child formula of the operator
	 */
	public Imply(Formula one, Formula two, boolean value){
		super(one, two, value);
		this.name = "->";
	}
	
	public Formula clone(){
		return new Imply(this.formulaOne.clone(), this.formulaTwo.clone(), this.value);
	}
	
	/*
	 * Function which transforms the current Imply operator into an Or one
	 * which contains the Negation of the first child and the second one
	 * Also apply the transformation rules on both children
	 */
	public Formula applyRule(){
		Or result = new Or(new Negation(this.formulaOne, true), this.formulaTwo, true);
		result.formulaOne = result.formulaOne.applyRule();
		result.formulaTwo = result.formulaTwo.applyRule();
		return result.applyRule();
	}
	
	public ArrayList<Formula> applyAlphaRule(){
		ArrayList<Formula> formulas = new ArrayList<Formula>();
		Formula res2 = this.formulaTwo.clone();
		res2.setValue(!res2.getValue());
		formulas.add(this.formulaOne);
		formulas.add(res2);
		return formulas;
	}
	
	public ArrayList<Formula> applyBetaRule(){
		ArrayList<Formula> formulas = new ArrayList<Formula>();
		Formula res1 = this.formulaOne.clone();
		res1.setValue(!res1.getValue());
		formulas.add(res1);
		formulas.add(this.formulaTwo.clone());
		return formulas;
	}
	
	public boolean evaluate(){
		if (this.formulaOne.evaluate() && !this.formulaTwo.evaluate()) {
			return false;
		}
		else{
			return true;
		}
	}
	
}
