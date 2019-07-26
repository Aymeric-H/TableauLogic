package Model;

import java.util.Arrays;
import java.util.Stack;

public class TableauMaker {

	private String expression;
	
	/*
	 * exp => the input expression to be analyzed and from which we want the tree representation
	 */
	public TableauMaker(String exp){
		this.expression = exp;
	}
	
	/*
	 * We use this call of the syntax analysis as the input is directly 
	 * given to the TreeMaker object
	 */
	public Formula syntaxAnalysis() throws Exception{
		return syntaxAnalysis(this.expression);
	}
	
	public Formula syntaxAnalysis(String form) throws Exception{
		//We need a stack to keep all the formulas we analyzed and affect them to the right operators
		Stack<Formula> stack = new Stack<Formula>();
				
		//String representation of all operators previously defined
		String[] oneOps = {"&", "v", ">", "<>"};
		String[] twoOps = {"~"};
		
		System.out.println("Formula : " + form);
		
		//Delimitation of the characteres in the input expression
		String[] splitForm = form.split(" ");
		
		//Counter for the number of unclosed parentheses
		int parenthese = 0;
				
		//If there's an expression surrounded by parentheses we need to keep it and analyze it 
		String parenthese_exp = "";
				
		for (String s : splitForm){
			/*System.out.println(s);
			if (!stack.empty()){
				System.out.println(" \n--------------------\n" + stack.peek() + " \n--------------------\n");
			}*/
			//Case where we have an expression surrounded by parentheses
			if (parenthese > 0) {
				if (s.equals(")")) {
					parenthese -= 1;
				}
				else if (s.equals("(")) {
					parenthese += 1;
				}
				//If all the parentheses have been closed we analyze the expression
				if (parenthese == 0) {
					Formula tmp = this.syntaxAnalysis(parenthese_exp.substring(1));
					parenthese_exp = "";
					//After being analyzed, the expression needs to be pushed into the stack
					//or dealt with the right operator
					if (stack.empty()) {
						stack.push(tmp);
					}
					else if (stack.peek() instanceof TwoFormulasOp) {
						TwoFormulasOp op = (TwoFormulasOp) stack.pop();
						Formula formulaOne = stack.pop();
						op.setFormulaOne(formulaOne);
						op.setFormulaTwo(tmp);
						stack.push(op);
					}
					else if (stack.peek() instanceof OneFormulaOp) {
						OneFormulaOp opOne = (OneFormulaOp) stack.pop();
						opOne.setFormula(tmp);
						Formula output = opOne;
						//If there is an operator which needs two formulas waiting we affect it the
						//new formula we've created
						if (!stack.empty() && stack.peek() instanceof TwoFormulasOp) {
							try{
								TwoFormulasOp opTwo = (TwoFormulasOp) stack.pop();
								Formula formulaOne = stack.pop();
								opTwo.setFormulaOne(formulaOne);
								opTwo.setFormulaTwo(opOne);
								output = opTwo;
							}
							catch(Exception e){
								System.out.println("Invalid Syntax : not enough Literal (twoOp)");
							}
						}
						stack.push(output);
					}
					else{
						throw new Exception("Can't get a Literal before a parenthesis expression !");
					}
				}
				//Keeping the trace of the current expression surrounded by parentheses
				else {
					parenthese_exp = parenthese_exp + " " + s;
				}
			}
			else{
				if (s.equals("(")) {
					parenthese += 1;
				}
				//Case where the character is a Literal
				else if (s.matches("[A-Z]{1}")){
					if (stack.empty()){
						stack.push(new Literal(s, true));
					}
					//We can't have two consecutive Literals in a logical expression
					else if (stack.peek() instanceof Literal) {
						throw new Exception("Invalid syntax : Can't deal with two Literals in a row");
					}
					//We need to apply the Literal to the waiting operator if there is one on the top of the stack
					else if (stack.peek() instanceof OneFormulaOp) {
						//Option 1 => push the Negation operator into the stack
						//OneFormulaOp opOne = (Negation) stack.pop();
						//opOne.setFormula(new Literal(s, true));
						//Formula output = opOne;
						//Option 2 => push directly the Literal with the changed value
						stack.pop();
						Formula output = new Literal(s, false);
						//If there is an operator which needs two formulas waiting we affect it the
						//new formula we've created
						if (!stack.empty() && stack.peek() instanceof TwoFormulasOp) {
							try{
								TwoFormulasOp opTwo = (TwoFormulasOp) stack.pop();
								Formula formulaOne = stack.pop();
								opTwo.setFormulaOne(formulaOne);
								opTwo.setFormulaTwo(output);
								output = opTwo;
							}
							catch(Exception e){
								System.out.println("Invalid Syntax : not enough Literal (twoOp)");
							}
						}
						stack.push(output);
					}
					else if (stack.peek() instanceof TwoFormulasOp) {
						try{
							TwoFormulasOp op = (TwoFormulasOp) stack.pop();
							Formula formulaOne = stack.pop();
							op.setFormulaOne(formulaOne);
							op.setFormulaTwo(new Literal(s, true));
							stack.push(op);
						}
						catch(Exception e){
							System.out.println("Invalid Syntax : not enough Literal (twoOp)");
						}
					}
				}
				//Case where the character is an operator
				//We just create it and push it into the stack
				else if (Arrays.asList(oneOps).contains(s) || Arrays.asList(twoOps).contains(s)) {
					Formula output;
					switch(s){
						case "&":
							output = new And(null, null, true);
							break;
						case "v":
							output = new Or(null, null, true);
							break;
						case ">":
							output = new Imply(null, null, true);
							break;
						case "<>":
							output = new DoubleImply(null, null, true);
							break;
						//The last case is the Negation operator
						default:
							if (!stack.isEmpty() && stack.peek() instanceof Literal) {
								throw new Exception("Invalid syntax : Can't deal with a negation if Literal waiting");
							}
							output = new Negation(null, true);
							break;
					}
					//System.out.println(output);
					stack.push(output);
				}
				else{
					throw new Exception("Invalide syntax here !");
				}
			}
		}
		//Final check on the stack
		//It should only stay the root of the tree made if the syntax is good
		if (stack.size() != 1){
			throw new Exception("Invalid Syntax : size of the stack !");
		}
		return stack.pop();

	}
}

