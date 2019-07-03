package Model;

import java.util.ArrayList;

public class TreeAnalyzer {

	Formula root;
	
	/*
	 * formula => the input expression we want to know the validity
	 */
	public TreeAnalyzer(Formula formula){
		this.root = formula;
	}
	
	/*
	 * Function which returns wether an input expression is valid or not
	 */
	public boolean valid(){
		//Get all the branches combinations (of Literals) in the expression given
		ArrayList<ArrayList<Literal>> lists = this.root.getLiterals();
		
		//Check in every combination if there is a contradiction
		//Returns false if one is found
		for (ArrayList<Literal> list : lists) {
			for (int i = 0; i < list.size(); i++) {
				for (int j = i+1; j < list.size(); j++) {
					if (list.get(i).name.equals(list.get(j).name) && list.get(i).getValue() != list.get(j).getValue()) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	
	
}
