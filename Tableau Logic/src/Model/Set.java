package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Set {

	private Set firstChild, secondChild;
	
	private int height;
	
	private ArrayList<Formula> formulas;
	
	private String lastRuleApplied;
	
	private boolean contradiction;
	
	/*
	 * formulas => the String to transform into a set of Formulas
	 */
	public Set(String formulas) throws Exception{
		this.formulas = new ArrayList<Formula>();
		String[] forms = formulas.split(", ");
		for (String s : forms) {
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
			TreeMaker tree = new TreeMaker(s);
			//Don't forget syntaxAnalysis method can throw an Exception
			try {
				Formula f = tree.syntaxAnalysis();
				f.setValue(value);
				this.formulas.add(f);	
			} catch (Exception e) {
				throw e;
			}
		}
		this.firstChild = null;
		this.secondChild = null;
		this.height = 1;
		this.lastRuleApplied = "";
		this.contradiction = false;
	}
	
	/*
	 * formulas => a list of Formulas already formed
	 */
	public Set(ArrayList<Formula> formulas){
		this.formulas = formulas;
		this.firstChild = null;
		this.secondChild = null;
		this.height = 1;
		this.lastRuleApplied = "";
		this.contradiction = false;
	}
	
	/*
	 * Getter of the first child of the Set
	 */
	public Set getFirst(){
		return this.firstChild;
	}
	
	/*
	 * Getter of the second child of the Set
	 */
	public Set getSecond(){
		return this.secondChild;
	}
	
	/*
	 * Getter of the Formulas
	 */
	public ArrayList<Formula> getFormulas(){
		return this.formulas;
	}
	
	/*
	 * Setter for the first child
	 */
	public void setFirst(String formulas) throws Exception{
		this.firstChild = new Set(formulas);
	}
	
	/*
	 * Setter for the second child
	 */
	public void setSecond(String formulas) throws Exception{
		this.secondChild = new Set(formulas);
	}
	
	/*
	 * Getter for the height of the Set (for the representation of the Tableau)
	 */
	public int getHeight(){
		int res = this.height;
		int first = 0;
		int second = 0;
		if (this.firstChild != null) {
			first = this.firstChild.height;
		}
		if (this.secondChild != null) {
			second  = this.secondChild.height;
		}

		return res + Math.max(first, second);
	}
	
	public boolean isAlphaRule(Formula formula){
		if ((formula.value == true && (formula.name.equals("&") || formula.name.equals("~")))
				|| (formula.value == false && (formula.name.equals("v") || formula.name.equals("->") || formula.name.equals("~")))) {
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean isBetaRule(Formula formula){
		if ((formula.value == true && (formula.name.equals("v") || formula.name.equals("->")))
				|| (formula.value == false && formula.name.equals("&"))) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * Function which returns the place of the first alpha rule in the Set
	 */
	public int alphaRule(){
		int i = 0;
		for (Formula formula : this.formulas) {
			if ((formula.value == true && (formula.name.equals("&") || formula.name.equals("~")))
					|| (formula.value == false && (formula.name.equals("v") || formula.name.equals("->") || formula.name.equals("~")))) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	/*
	 * Function which returns the place of the first beta rule in the Set
	 */
	public int betaRule(){
		int i = 0;
		for (Formula formula : this.formulas) {
			if ((formula.value == true && (formula.name.equals("v") || formula.name.equals("->")))
					|| (formula.value == false && formula.name.equals("&"))) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	/*
	 * Function which allow to apply the right rule for the current Set
	 * and and then apply the right rules for its children
	 */
	public void applyRules(){
		if (!this.contradiction()) {
			int alpha = this.alphaRule();
			if (alpha != -1) {
				this.firstChild = new Set(new ArrayList<Formula>(this.formulas));
				ArrayList<Formula> formulas = this.formulas.get(alpha).applyAlphaRule();
				this.firstChild.formulas.add(formulas.get(0));
				if (formulas.size() > 1) {
					this.firstChild.formulas.add(formulas.get(1));
				}
				this.firstChild.formulas.remove(alpha);
				this.firstChild.applyRules();
				if (this.formulas.get(alpha).getValue()) {
					this.lastRuleApplied = "T" + this.formulas.get(alpha).getName();
				}
				else{
					this.lastRuleApplied = "F" + this.formulas.get(alpha).getName();
				}
				this.contradiction = this.firstChild.getContradiction();
			}
			else{
				int beta = this.betaRule();
				if (beta != -1) {
					ArrayList<Formula> formulas = this.formulas.get(beta).applyBetaRule();
					this.firstChild = new Set(new ArrayList<Formula>(this.formulas));
					this.firstChild.formulas.remove(beta);
					this.firstChild.formulas.add(formulas.get(0));
					this.firstChild.applyRules();
					this.secondChild = new Set(new ArrayList<Formula>(this.formulas));
					this.secondChild.formulas.remove(beta);
					this.secondChild.formulas.add(formulas.get(1));
					this.secondChild.applyRules();
					if (this.formulas.get(beta).getValue()) {
						this.lastRuleApplied = "T" + this.formulas.get(beta).getName();
					}
					else{
						this.lastRuleApplied = "F" + this.formulas.get(beta).getName();
					}
					this.contradiction = this.firstChild.getContradiction() && this.secondChild.getContradiction();
				}
			}
		}
		else{
			this.contradiction = true;
		}
	}
	
	/*
	 * Function which allow to apply only one Rule (the right one)
	 * and no more (for the resolution modes that are step by step)
	 */
	public void applyRule(){
		if (!this.contradiction()) {
			int alpha = this.alphaRule();
			if (alpha != -1) {
				this.firstChild = new Set(new ArrayList<Formula>(this.formulas));
				ArrayList<Formula> formulas = this.formulas.get(alpha).applyAlphaRule();
				this.firstChild.formulas.add(formulas.get(0));
				if (formulas.size() > 1) {
					this.firstChild.formulas.add(formulas.get(1));
				}
				this.firstChild.formulas.remove(alpha);
				if (this.formulas.get(alpha).getValue()) {
					this.lastRuleApplied = "T" + this.formulas.get(alpha).getName();
				}
				else{
					this.lastRuleApplied = "F" + this.formulas.get(alpha).getName();
				}
				this.contradiction = this.firstChild.getContradiction();
			}
			else{
				int beta = this.betaRule();
				if (beta != -1) {
					ArrayList<Formula> formulas = this.formulas.get(beta).applyBetaRule();
					this.firstChild = new Set(new ArrayList<Formula>(this.formulas));
					this.firstChild.formulas.remove(beta);
					this.firstChild.formulas.add(formulas.get(0));
					this.secondChild = new Set(new ArrayList<Formula>(this.formulas));
					this.secondChild.formulas.remove(beta);
					this.secondChild.formulas.add(formulas.get(1));
					if (this.formulas.get(beta).getValue()) {
						this.lastRuleApplied = "T" + this.formulas.get(beta).getName();
					}
					else{
						this.lastRuleApplied = "F" + this.formulas.get(beta).getName();
					}
					this.contradiction = this.firstChild.getContradiction() && this.secondChild.getContradiction();
				}
			}
		}
		else{
			this.contradiction = true;
		}
	}
	
	public void applyRule(int index) throws Exception{
		if (!this.contradiction()) {
			Formula formula = this.formulas.get(index);
			if (this.isAlphaRule(formula)) {
				this.firstChild = new Set(new ArrayList<Formula>(this.formulas));
				ArrayList<Formula> formulas = formula.applyAlphaRule();
				this.firstChild.formulas.add(formulas.get(0));
				if (formulas.size() > 1) {
					this.firstChild.formulas.add(formulas.get(1));
				}
				this.firstChild.formulas.remove(index);
				if (formula.getValue()) {
					this.lastRuleApplied = "T" + formula.getName();
				}
				else{
					this.lastRuleApplied = "F" + formula.getName();
				}
				this.contradiction = this.firstChild.getContradiction();
			}
			else if (this.isBetaRule(formula)) {
				ArrayList<Formula> formulas = formula.applyBetaRule();
				this.firstChild = new Set(new ArrayList<Formula>(this.formulas));
				this.firstChild.formulas.remove(index);
				this.firstChild.formulas.add(formulas.get(0));
				this.secondChild = new Set(new ArrayList<Formula>(this.formulas));
				this.secondChild.formulas.remove(index);
				this.secondChild.formulas.add(formulas.get(1));
				if (formula.getValue()) {
					this.lastRuleApplied = "T" + formula.getName();
				}
				else{
					this.lastRuleApplied = "F" + formula.getName();
				}
				this.contradiction = this.firstChild.getContradiction() && this.secondChild.getContradiction();
			}
			else if (!(formula instanceof Literal)){
				throw new Exception("Neither Alpha nor Beta rule !");
			}
		}
		else {
			this.contradiction = true;
		}
	}
	
	/*
	 * Function which returns true if this Set and its children are valid
	 */
	public boolean validity(){
		if (this.firstChild != null) {
			boolean firstValid = this.firstChild.validity();
			if (this.secondChild != null) {
				boolean secondValid = this.secondChild.validity();
				return firstValid && secondValid;
			}
			return firstValid;
		}
		return !this.contradiction();
	}
	
	/*
	 * Function which returns true if there is a contradiction
	 * in the current Set
	 */
	public boolean contradiction(){
		for (int i = 0; i < this.formulas.size(); i++) {
			for (int j = i+1; j < this.formulas.size(); j++) {
				if (this.formulas.get(i) instanceof Literal && this.formulas.get(j) instanceof Literal
					&&	this.formulas.get(i).getName().equals(this.formulas.get(j).getName())
					&& this.formulas.get(i).getValue() != this.formulas.get(j).getValue()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean allLiterals(){
		for (Formula formula : this.formulas) {
			if (!(formula instanceof Literal)) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Getter for the contradiction boolean of the class
	 */
	public boolean getContradiction(){
		return this.contradiction;
	}
	
	public void updateContradiction(){
		if (this.firstChild != null) {
			this.firstChild.updateContradiction();
			if (this.secondChild != null) {
				this.secondChild.updateContradiction();
				this.contradiction = this.firstChild.getContradiction() && this.secondChild.getContradiction();
			}
			else{
				this.contradiction = this.firstChild.getContradiction();
			}
		}
		else {
			this.contradiction = this.contradiction();
		}
	}
	
	public HashMap<String, Integer> getOperators(){
		HashMap<String, Integer> operatorsMap = new HashMap<String, Integer>();
		int index = 0;
		for (Formula formula : this.formulas) {
			if (! (formula instanceof Literal)) {
				operatorsMap.put(formula.name, index);
			}
			index ++;
		}
		return operatorsMap;
	}
	
	public Set clone(Tableau tableau){
		ArrayList<Formula> formulas = new ArrayList<Formula>();
		for (Formula f : this.formulas) {
			formulas.add(f.clone());
		}
		Set set = new Set(formulas);
		set.contradiction = this.contradiction;
		set.lastRuleApplied = new String(this.lastRuleApplied);
		if (this.firstChild != null) {
			set.firstChild = this.firstChild.clone(tableau);
		}
		if (this.secondChild != null) {
			set.secondChild = this.secondChild.clone(tableau);
		}
		if (tableau.accessibleSets.containsValue(this)) {
			for (Coordinates coords : tableau.accessibleSets.keySet()) {
				if (tableau.accessibleSets.get(coords).equals(this)) {
					tableau.accessibleSets.replace(coords, this, set);
				}
			}
		}
		return set;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "{ " + this.formulas.toString().substring(1, this.formulas.toString().length() - 1) + " }  " + this.lastRuleApplied;
	}
	
}
