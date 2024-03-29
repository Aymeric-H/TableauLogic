package Model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import View.TableauView;

public class Tableau {

	private Set root;
	private ArrayList<Set> setsToDevelop;
	/*
	 * 0 => Mode all in one Step
	 * 1 => Mode Step by Step : Depth-first
	 * 2 => Mode Step by Step : Breadth-first
	 */
	private int devMode;
	public Map<Coordinates, Set> accessibleSets;
	
	/*
	 * Tableau object constructor
	 */
	public Tableau(){
		this.root = null;
		this.setsToDevelop = new ArrayList<Set>();
		this.devMode = 0;
		this.accessibleSets = new HashMap<Coordinates, Set>();
	}
	
	/*
	 * set => The root of the Tableau
	 */
	public Tableau(Set set){
		this.root = set;
		this.setsToDevelop = new ArrayList<Set>();
		this.setsToDevelop.add(this.root);
		this.devMode = 0;
		this.accessibleSets = new HashMap<Coordinates, Set>();
	}
	
	/*
	 * Setter of the root
	 */
	public void setRoot(Set set) throws Exception{
		try {
			this.setsToDevelop = new ArrayList<Set>();
			this.root = set;
			this.setsToDevelop.add(this.root);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/*
	 * Function which allows to set the resolution mode
	 * for the Tableau
	 * mode => the mode of resolutnio selected by the user
	 */
	public void setDevMode(int mode){
		this.devMode = mode;
	}
	
	/*
	 * Function which applies each rule of each Set of the Tableau
	 * in the right order (alpha then beta rules)
	 */
	public void applyRules(){
		this.root.applyRules();
		this.setsToDevelop.remove(0);
	}
	
	/*
	 * Function which apply the next rule to apply
	 */
	public void applyRule() throws Exception{
		if (this.remainingRules()) {
			Set set = this.setsToDevelop.get(0);
			set.applyRule();
			this.setsToDevelop.remove(0);
			if (this.devMode == 1) {
				if (set.getSecond() != null && !set.getSecond().allLiterals()) {
					this.setsToDevelop.add(0, set.getSecond());
				}
				if (set.getFirst() != null && !set.getFirst().allLiterals()) {
					this.setsToDevelop.add(0, set.getFirst());
				}
			}
			else if (this.devMode == 2) {
				if (set.getFirst() != null && !set.getFirst().allLiterals()) {
					this.setsToDevelop.add(set.getFirst());
				}
				if (set.getSecond() != null && !set.getSecond().allLiterals()) {
					this.setsToDevelop.add(set.getSecond());
				}
			}
		}
		else{
			throw new Exception("No remaining rules !");
		}
	}
	
	/*
	 * Function which tells if there are still rules to apply
	 */
	public boolean remainingRules(){
		if (this.setsToDevelop.size() == 0) {
			return false;
		}
		else{
			return true;
		}
	}
	
	/*
	 * Function which allows to update the contradictions for
	 * each Set of the Tableau
	 */
	public void updateContradictions(){
		this.root.updateContradiction();
	}
	
	/*
	 * Function which returns true if the Tableau is valid
	 */
	public boolean validity(){
		return this.root.validity();
	}
	
	/*
	 * Functions which allow to print the Tableau on the screen
	 */
	public void printTableau(int width, int height, int x, int y){
		TableauView<String> tableau = new TableauView<String>(this, width, height, x, y);
		tableau.refresh();
	}
	
	/*
	 * Function which returns the number of sets that are still
	 * waiting to be handled
	 */
	public int getNumberRules(){
		return this.setsToDevelop.size();
	}
	
	/*
	 * Getter of the Tableau View (JPanel)
	 */
	public TableauView<String> getView(){
		return new TableauView<>(this);
	}
	
	public Set getRoot(){
		return this.root;
	}
	
	/*
	 * function which allows to add a Set and its Coordinates to
	 * the accessibleSet list
	 * coordinates => the Coordinates of the Set
	 * set => the Set to add
	 */
	public void addAccessibleSet(Coordinates coordinates, Set set){
		boolean alreadyIn = false;
		for (Coordinates coords : this.accessibleSets.keySet()) {
			if (coords.isOverlapping(coordinates) || coords.equals(coordinates)) {
				alreadyIn = true;
			}
		}
		if (!alreadyIn && !set.allLiterals()) {
			this.accessibleSets.put(coordinates, set);
		}
	}
	
	/*
	 * Function which returns the Coordinates of the Set clicked on
	 * x, y => the coordinates of the click
	 */
	public Coordinates getCoordinates(int x, int y){
		for (Coordinates coords : this.accessibleSets.keySet()) {
			if (x >= coords.getX1() && x <= coords.getX2() && y >= coords.getY1() && y <= coords.getY2()) {
				return coords;
			}
		}
		return null;
	}
	
	/*
	 * Function which returns the set selected
	 * coordinates => the Coordinates of the selected Set
	 */
	public Set getSetSelected(Coordinates coordinates){
		for (Coordinates coords : this.accessibleSets.keySet()) {
			if (coords != null && coordinates != null && coords.equals(coordinates)) {
				return this.accessibleSets.get(coords);
			}
		}
		return null;
	}
	
	/*
	 * Function which apply the rule selected for the Set clicked on
	 * coords => the Coordinates of the click
	 * index => index of the operator selected
	 */
	public void applyRuleForThisSet(Coordinates coords, int index) throws Exception{
		try {
			Set set  = this.getSetSelected(coords);
			set.applyRule(index);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/*
	 * Function which apply the rule selected for the Set clicked on
	 * coords => the Coordinates of the click
	 * index => index of the operator selected
	 */
	public Set simulateRuleForThisSet(Coordinates coords, int index) throws Exception{
		try {
			Set set  = this.getSetSelected(coords);
			Set simulation = set.simulateRule(index);
			return simulation;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/*
	 * Function which apply the right rule for the clicked on Set
	 * x, y => the coordinates of the click
	 */
	public Set applyRuleForThisSet(int x, int y){
		for (Coordinates coords : this.accessibleSets.keySet()) {
			if (x >= coords.getX1() && x <= coords.getX2() && y >= coords.getY1() && y <= coords.getY2()) {
				Set set = this.accessibleSets.get(coords);
				set.applyRule();
				this.accessibleSets.remove(coords);
				return set;
			}
		}
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Tableau clone(){
		Tableau tableau = new Tableau();
		HashMap<Coordinates, Set> accessibleSets = new HashMap<Coordinates, Set>();
		for (Coordinates coord : this.accessibleSets.keySet()) {
			accessibleSets.put(coord, this.accessibleSets.get(coord));
		}
		ArrayList<Set> setsToDevelop = new ArrayList<Set>();
		for (Set set : this.setsToDevelop) {
			setsToDevelop.add(set);
		}
		tableau.accessibleSets = accessibleSets;
		tableau.setsToDevelop = setsToDevelop;
		try {
			tableau.setRoot(this.root.clone(tableau));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tableau;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String res = this.root.toString() + "\n" + "|" + "\n"; 
		Set current = this.root;
		while (current.getFirst() != null) {
			res += current.getFirst().toString() + "\n" + "|" + "\n";
			/*if (this.root.getSecond() != null) {
				res += "\t - \t" + this.root.getSecond().toString();
			}*/
			current = current.getFirst();
		}
		return res;
	}
	
}
