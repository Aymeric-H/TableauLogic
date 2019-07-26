package Model;

import java.util.ArrayList;

public class Or extends TwoFormulasOp{

	/*
	 * one => first child formula of the operator
	 * two => second child formula of the operator
	 */
	public Or(Formula one, Formula two, boolean value){
		super(one, two, value);
		this.name = "v";
	}
	
	public Formula clone(){
		return new Or(this.formulaOne.clone(), this.formulaTwo.clone(), this.value);
	}
	
	/*
	 * Function which apply the transformation rules 
	 * on the current Or operator children
	 */
	public Formula applyRule(){
		this.formulaOne = this.formulaOne.applyRule();
		this.formulaTwo = this.formulaTwo.applyRule();
		return this;
	}
	
	/*
	 * Function which returns a list (for each child) of the Literals contained in the branches
	 */
	public ArrayList<ArrayList<Literal>> getLiterals(){
		ArrayList<ArrayList<Literal>> listRes = new ArrayList<ArrayList<Literal>>();
		listRes.addAll(this.formulaOne.getLiterals());
		listRes.addAll(this.formulaTwo.getLiterals());
		return listRes;
	}
	
	public ArrayList<Formula> applyAlphaRule(){
		ArrayList<Formula> formulas = new ArrayList<Formula>();
		Formula res1 = this.formulaOne.clone();
		res1.setValue(!res1.getValue());
		Formula res2 = this.formulaTwo.clone();
		res2.setValue(!res2.getValue());
		formulas.add(res1);
		formulas.add(res2);
		return formulas;
	}
	
	public ArrayList<Formula> applyBetaRule(){
		ArrayList<Formula> formulas = new ArrayList<Formula>();
		formulas.add(this.formulaOne.clone());
		formulas.add(this.formulaTwo.clone());
		return formulas;
	}
	
	public boolean evaluate(){
		return this.formulaOne.evaluate() || this.formulaTwo.evaluate();
	}
	
}
