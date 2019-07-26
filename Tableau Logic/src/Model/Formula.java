package Model;

import java.util.ArrayList;

import javax.swing.JPanel;

import View.BinaryTreeView;

public abstract class Formula {
	
	protected int height;
	protected String name;
	protected boolean value;
	
	/*
	 * Function which returns the name (String representation) of the formula
	 */
	public String getName(){
		return this.name;
	}
	
	/*
	 * Function which returns the current height of the formula (Node)
	 * Useful for the representation of the tree
	 */
	public abstract int height();
	
	/*
	 * Function which returns a list of every branch Literals
	 */
	public abstract ArrayList<ArrayList<Literal>> getLiterals();
	
	/*
	 * Function which apply the transformation rules on the current
	 * Formula and returns it
	 */
	public abstract Formula applyRule();
	
	/*
	 * Function which returns the boolean value of the Formula
	 */
	public boolean getValue(){
		return this.value;
	}
	
	/*
	 * Function which allows to set the boolean value of the Formula
	 */
	public void setValue(boolean value){
		this.value = value;
	}
	
	public ArrayList<Formula> applyAlphaRule(){
		return new ArrayList<Formula>();
	}
	
	public ArrayList<Formula> applyBetaRule(){
		return new ArrayList<>();
	}
	
	public abstract Formula clone();
	
	public JPanel getView(){
		return new BinaryTreeView<>(this);
	}
	
	public void printTree(int width, int height, int x, int y){
		BinaryTreeView<String> tree = new BinaryTreeView<String>(this, width, height, x, y);
		tree.refresh();
	}
	
	public abstract boolean evaluate();
	
}
