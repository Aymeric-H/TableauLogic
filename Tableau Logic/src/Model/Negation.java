package Model;

import java.util.ArrayList;

public class Negation extends OneFormulaOp{

	/*
	 * one => first child formula of the Negation operator
	 */
	public Negation(Formula one, boolean value){
		super(one, value);
		this.name = "~";
	}
	
	public Formula clone(){
		return new Negation(this.formula.clone(), this.value);
	}
	
	/*
	 * Function which apply the transformation rules on the 
	 * current formula and returns it
	 */
	public Formula applyRule(){
		//If we got a Literal we change its value and return it
		if (this.formula instanceof Literal) {
			this.formula = new Literal(this.formula.name, !this.formula.getValue());
		}
		//If we got a double Negation we return the formula which was affected
		else if (this.formula instanceof Negation) {
			return ((Negation) this.formula).getFormula();
		}
		//If we got an And operator we create an Or one with the Negation of both children and return it
		else if (this.formula instanceof And) {
			this.formula = new Or(new Negation(((And) this.formula).getFormulaOne(), true), new Negation(((And) this.formula).getFormulaTwo(), true), true);
		}
		//If we got an Or operator we create an And one with the Negation of both children and return it
		else if (this.formula instanceof Or) {
			this.formula = new And(new Negation(((Or) this.formula).getFormulaOne(), true), new Negation(((Or) this.formula).getFormulaTwo(), true), true);
		}
		//If we got an Imply operator we create an And one with the first child and the negation of the second one then return it
		else if (this.formula instanceof Imply) {
			this.formula = new And(((Imply) this.formula).getFormulaOne(), new Negation(((Imply) this.formula).getFormulaTwo(), true), true);
		}
		//Else, we got a DoubleImply operator and create an Or one with an And operator which contains both children formula and
		//another And operator which contains the Negation of both children formula
		else{
			this.formula = new Or(new And(new Negation(((DoubleImply) this.formula).getFormulaOne(), true), ((DoubleImply) this.formula).getFormulaTwo(), true),
					new And(((DoubleImply) this.formula).getFormulaOne(), new Negation(((DoubleImply) this.formula).getFormulaTwo(), true), true), true);
		}
		//We also need to apply rules to the child
		this.formula = this.formula.applyRule();
		return this.formula;
	}
	
	public ArrayList<Formula> applyAlphaRule(){
		ArrayList<Formula> formulas = new ArrayList<Formula>();
		Formula res = this.formula.clone();
		if (this.value) {
			res.setValue(!res.getValue());
		}
		formulas.add(res);
		return formulas;
	}
	
	public boolean evaluate(){
		return !this.formula.evaluate();
	}
	
}
