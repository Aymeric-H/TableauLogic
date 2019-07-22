package Model;

import java.util.ArrayList;

public class Literal extends Formula{
	
	/*
	 * Name => the String representation of the Literal
	 * Value => a boolean telling if the Literal is True or False
	 */
	public Literal(String name, boolean value){
		this.value = value;
		this.name = name;
	}
	
	/*
	 * Function which returns the current height of the formula (Node)
	 * Useful for the representation of the tree
	 */
	public int height(){
		return 1;
	}
	
	/*
	 * Function which returns a new list with the current Literal
	 */
	public ArrayList<ArrayList<Literal>> getLiterals(){
		ArrayList<ArrayList<Literal>> listRes = new ArrayList<ArrayList<Literal>>();
		ArrayList<Literal> literal = new ArrayList<Literal>();
		literal.add(this);
		listRes.add(literal);
		return listRes;
	}
	
	/*
	 * Function which returns the current Literal as there is no
	 * possible transformation
	 */
	public Formula applyRule(){
		return this;
	}
	
	public Formula clone(){
		return new Literal(this.name, this.value);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		if (this.value) {
			return "T [ " + this.name + " ]";
		}
		else{
			return "F [ " + this.name + " ]";
		}
	}
	
}
