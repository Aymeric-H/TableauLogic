package Model;

public class DoubleImply extends TwoFormulasOp{

	/*
	 * one => first child formula of the operator
	 * two => second child formula of the operator
	 */
	public DoubleImply(Formula one, Formula two, boolean value){
		super(one, two, value);
		this.name = "<->";
	}
	
	public Formula clone(){
		return new DoubleImply(this.formulaOne.clone(), this.formulaTwo.clone(), this.value);
	}
	
	/*
	 * Function which transforms the current Double Implication into
	 * an Or operator with an And one which contains both children and
	 * another And which contains the Negation of both children
	 * Also apply the transformation rules on both children
	 */
	public Formula applyRule(){
		Or result = new Or(new And(this.formulaOne, this.formulaTwo, true), new And(new Negation(this.formulaOne, true), new Negation(this.formulaTwo, true), true), true);
		result.formulaOne = result.formulaOne.applyRule();
		result.formulaTwo = result.formulaTwo.applyRule();
		return result;
	}
	
	public boolean evaluate(){
		if ((this.formulaOne.evaluate() && this.formulaTwo.evaluate()) 
				|| (!this.formulaOne.evaluate() && !this.formulaTwo.evaluate())) {
			return true;
		}
		else{
			return false;
		}
	}
	
}
