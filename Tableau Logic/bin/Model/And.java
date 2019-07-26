package Model;

import java.util.ArrayList;

public class And extends TwoFormulasOp{

	/*
	 * one => first child formula of the operator
	 * two => second child formula of the operator
	 */
	public And(Formula one, Formula two, boolean value){
		super(one, two, value);
		this.name = "&";
	}
	
	public Formula clone(){
		return new And(this.formulaOne.clone(), this.formulaTwo.clone(), this.value);
	}
	
	/*
	 * Function which apply the transformation rules 
	 * on the current And operator children
	 */
	public Formula applyRule(){
		this.formulaOne = this.formulaOne.applyRule();
		this.formulaTwo = this.formulaTwo.applyRule();
		return this;
	}
	
	public ArrayList<Formula> applyAlphaRule(){
		ArrayList<Formula> formulas = new ArrayList<Formula>();
		formulas.add(this.formulaOne.clone());
		formulas.add(this.formulaTwo.clone());
		return formulas;
	}
	
	public ArrayList<Formula> applyBetaRule(){
		ArrayList<Formula> formulas = new ArrayList<Formula>();
		Formula res1 = this.formulaOne.clone();
		res1.setValue(!res1.getValue());
		Formula res2 = this.formulaTwo.clone();
		res2.setValue(!res2.getValue());
		formulas.add(res1);
		formulas.add(res2);
		return formulas;
	}
	
	/*
	 * Function which returns the lists of the combinations of the two child formulas branches
	 */
	public ArrayList<ArrayList<Literal>> getLiterals(){
		ArrayList<ArrayList<Literal>> listOne = this.formulaOne.getLiterals();
		ArrayList<ArrayList<Literal>> listTwo = this.formulaTwo.getLiterals();
		ArrayList<ArrayList<Literal>> listRes = new ArrayList<ArrayList<Literal>>();
		for (ArrayList<Literal> list1 : listOne) {
			for (ArrayList<Literal> list2 : listTwo) {
				ArrayList<Literal> tmp = new ArrayList<Literal>();
				tmp.addAll(list1);
				tmp.addAll(list2);
				listRes.add(tmp);
			}
		}
		return listRes;
	}

	@Override
	public boolean evaluate() {
		return this.formulaOne.evaluate() && this.formulaTwo.evaluate();
	}
	
}
