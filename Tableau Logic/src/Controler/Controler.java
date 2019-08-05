package Controler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Model.Coordinates;
import Model.Set;
import Model.Tableau;
import Model.TreeMaker;
import Model.TruthTable;
import View.TableauView;
import View.TruthTableView;
import View.View;
import View.CorrectTruthTableView;
import View.OneTruthTableLineView;
import View.PopupViewChoseOperator;

public class Controler {

	private Tableau tableau;
	private View view;
	private PopupViewChoseOperator popup;
	private Stack<Tableau> stepsStack;
	private ArrayList<String> examples;
	private int indexOfExamples;
	
	public Controler(Tableau tab){
		
		this.tableau = tab;
		this.view = new View();
		this.stepsStack = new Stack<Tableau>();
		this.examples = new ArrayList<String>();
		indexOfExamples = 0;
		
		// We read the file of examples for the first exercise and stock every expressions in the ArrayList we created
		this.readFileExamplesExOne();
		
		this.view.exOne.addMouseListener(new ActionExOne());
		
		//Exercise 1
		
		ActionExerciseOne actionExerciseOne = new ActionExerciseOne();
		this.view.exerciseOne.setAction(actionExerciseOne);
		
		this.view.inputExpressionsExOne.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if(e.getKeyCode() == KeyEvent.VK_ENTER){
	                view.dealExpressionsExOne.doClick();
	            }
	        }
	    });
		
		ActionDealWithExpressions actionDealWithExpressions = new ActionDealWithExpressions();
		this.view.dealExpressionsExOne.setAction(actionDealWithExpressions);
		
		ActionGiveExpression actionGiveExpression = new ActionGiveExpression();
		this.view.giveExpressionExOne.setAction(actionGiveExpression);
		
		ActionReset actionReset = new ActionReset();
		this.view.resetExOne.setAction(actionReset);
		
		ActionGoBack actionGoBack = new ActionGoBack();
		this.view.goBackExOne.setAction(actionGoBack);
		
		
		//Exercise 2
		
		ActionExerciseTwo actionExerciseTwo = new ActionExerciseTwo();
		this.view.exerciseTwo.setAction(actionExerciseTwo);
		
		this.view.inputExpressionsExTwo.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if(e.getKeyCode() == KeyEvent.VK_ENTER){
	                view.dealExpressionsExTwo.doClick();
	            }
	        }
	    });
		
		ActionDealWithExpressionsExTwo actionDealWithExpressionsExTwo = new ActionDealWithExpressionsExTwo();
		this.view.dealExpressionsExTwo.setAction(actionDealWithExpressionsExTwo);
		
		ActionResetExTwo actionResetExTwo = new ActionResetExTwo();
		this.view.resetExTwo.setAction(actionResetExTwo);
		
		ActionGoBack actionGoBackExTwo = new ActionGoBack();
		this.view.goBackExTwo.setAction(actionGoBackExTwo);
		
		
		//Exercise 3
		
		ActionExerciseThree actionExerciseThree = new ActionExerciseThree();
		this.view.exerciseThree.setAction(actionExerciseThree);
		
		this.view.inputExpressionsExThree.addKeyListener(new KeyAdapter() {
		       @Override
		       public void keyPressed(KeyEvent e) {
		           if(e.getKeyCode() == KeyEvent.VK_ENTER){
		               view.dealExpressionsExThree.doClick();
		           }
		       }
		   });
		
		ActionDealWithExpressionsExThree actionDealWithExpressionsExThree = new ActionDealWithExpressionsExThree();
		this.view.dealExpressionsExThree.setAction(actionDealWithExpressionsExThree);
		
		ActionResetExThree actionResetExThree = new ActionResetExThree();
		this.view.resetExThree.setAction(actionResetExThree);
		
		ActionNextStep actionNextStep = new ActionNextStep();
		this.view.nextStep.setAction(actionNextStep);
		
		ActionGoBack actionGoBackExThree = new ActionGoBack();
		this.view.goBackExThree.setAction(actionGoBackExThree);
		
		
		//Exercise 4
		ActionExerciseFour actionExerciseFour = new ActionExerciseFour();
		this.view.exerciseFour.setAction(actionExerciseFour);
		
		this.view.inputExpressionsExFour.addKeyListener(new KeyAdapter() {
		       @Override
		       public void keyPressed(KeyEvent e) {
		           if(e.getKeyCode() == KeyEvent.VK_ENTER){
		               view.dealExpressionsExFour.doClick();
		           }
		       }
		});
		
		ActionDealWithExpressionsExFour actionDealWithExpressionsExFour = new ActionDealWithExpressionsExFour();
		this.view.dealExpressionsExFour.setAction(actionDealWithExpressionsExFour);
		
		ActionResetExFour actionResetExFour = new ActionResetExFour();
		this.view.resetExFour.setAction(actionResetExFour);
		
		ActionUndoExFour actionUndoExFour = new ActionUndoExFour();
		this.view.undo.setAction(actionUndoExFour);
				
		ActionGoBack actionGoBackExFour = new ActionGoBack();
		this.view.goBackExFour.setAction(actionGoBackExFour);
		
		//Exercise 5
		
		ActionExerciseFive actionExerciseFive = new ActionExerciseFive();
		this.view.exerciseFive.setAction(actionExerciseFive);
		
		this.view.inputExpressionsExFive.addKeyListener(new KeyAdapter() {
		       @Override
		       public void keyPressed(KeyEvent e) {
		           if(e.getKeyCode() == KeyEvent.VK_ENTER){
		               view.dealExpressionsExFive.doClick();
		           }
		       }
		});
				
		ActionDealWithExpressionsExFive actionDealWithExpressionsExFive = new ActionDealWithExpressionsExFive();
		this.view.dealExpressionsExFive.setAction(actionDealWithExpressionsExFive);
		
		ActionResetExFive actionResetExFive = new ActionResetExFive();
		this.view.resetExFive.setAction(actionResetExFive);
		
		ActionGoBack actionGoBackExFive = new ActionGoBack();
		this.view.goBackExFive.setAction(actionGoBackExFive);
		
	}
	
	public class ActionExOne implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("APPUYE");
			BorderLayout layout = (BorderLayout)view.main.getLayout();
			view.main.remove(layout.getLayoutComponent(BorderLayout.CENTER));
			view.main.add(view.panelExerciseOne, BorderLayout.CENTER);
			view.inputExpressionsExOne.requestFocusInWindow();
			if (view.dealExpressionsExOne.isEnabled()) {
				view.resetExOne.setEnabled(false);
			}
			view.revalidate();
			view.repaint();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}		
		
	}
	
	
	/*
	 * When the user clicks on the button of the first exercise it removes the panel of the main menu
	 * and replaces it with the first exercise panel
	 */
	public class ActionExerciseOne extends AbstractAction implements Observer {
		
		public ActionExerciseOne() {
			this.putValue(Action.NAME, "Exercise 1");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("APPUYE");
			BorderLayout layout = (BorderLayout)view.main.getLayout();
			view.main.remove(layout.getLayoutComponent(BorderLayout.CENTER));
			view.main.add(view.panelExerciseOne, BorderLayout.CENTER);
			view.inputExpressionsExOne.requestFocusInWindow();
			if (view.dealExpressionsExOne.isEnabled()) {
				view.resetExOne.setEnabled(false);
			}
			view.revalidate();
			view.repaint();
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			System.out.println("UPDATE");
		}
	}
	
	/*
	 * When the user clicks on the button of the second exercise it removes the panel of the main menu
	 * and replaces it with the second exercise panel
	 */
	public class ActionExerciseTwo extends AbstractAction implements Observer {
		
		public ActionExerciseTwo() {
			this.putValue(Action.NAME, "Exercise 2");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("APPUYE");
			BorderLayout layout = (BorderLayout)view.main.getLayout();
			view.main.remove(layout.getLayoutComponent(BorderLayout.CENTER));
			view.main.add(view.panelExerciseTwo, BorderLayout.CENTER);
			view.inputExpressionsExTwo.requestFocusInWindow();
			if (view.dealExpressionsExTwo.isEnabled()) {
				view.resetExTwo.setEnabled(false);
			}
			view.revalidate();
			view.repaint();
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			System.out.println("UPDATE");
		}
	}
	
	/*
	 * When the user clicks on the button of the third exercise it removes the panel of the main menu
	 * and replaces it with the third exercise panel
	 */
	public class ActionExerciseThree extends AbstractAction implements Observer {
		
		public ActionExerciseThree() {
			this.putValue(Action.NAME, "Exercise 3");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("APPUYE");
			BorderLayout layout = (BorderLayout)view.main.getLayout();
			view.main.remove(layout.getLayoutComponent(BorderLayout.CENTER));
			view.main.add(view.panelExerciseThree, BorderLayout.CENTER);
			view.inputExpressionsExThree.requestFocusInWindow();
			if (view.dealExpressionsExThree.isEnabled()) {
				view.resetExThree.setEnabled(false);
				view.nextStep.setEnabled(false);
			}
			view.revalidate();
			view.repaint();
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			System.out.println("UPDATE");
		}
	}
	
	/*
	 * When the user clicks on the button of the fourth exercise it removes the panel of the main menu
	 * and replaces it with the fourth exercise panel
	 */
	public class ActionExerciseFour extends AbstractAction implements Observer {
		
		public ActionExerciseFour() {
			this.putValue(Action.NAME, "Exercise 4");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("APPUYE");
			BorderLayout layout = (BorderLayout)view.main.getLayout();
			view.main.remove(layout.getLayoutComponent(BorderLayout.CENTER));
			view.main.add(view.panelExerciseFour, BorderLayout.CENTER);
			view.inputExpressionsExFour.requestFocusInWindow();
			if (view.dealExpressionsExFour.isEnabled()) {
				view.resetExFour.setEnabled(false);
				view.undo.setEnabled(false);
			}
			view.revalidate();
			view.repaint();
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			System.out.println("UPDATE");
		}
	}
	
	public class ActionExerciseFive extends AbstractAction implements Observer {
		
		public ActionExerciseFive() {
			this.putValue(Action.NAME, "Exercise 5");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("APPUYE");
			BorderLayout layout = (BorderLayout)view.main.getLayout();
			view.main.remove(layout.getLayoutComponent(BorderLayout.CENTER));
			view.main.add(view.panelExerciseFive, BorderLayout.CENTER);
			view.inputExpressionsExFive.requestFocusInWindow();
			if (view.dealExpressionsExFive.isEnabled()) {
				view.resetExFive.setEnabled(false);
			}
			view.revalidate();
			view.repaint();
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			System.out.println("UPDATE");
		}
	}
	
	/*
	 * When the user clicks on the "Go back" button it makes him go back to the main menu
	 */
	public class ActionGoBack extends AbstractAction implements Observer {
		
		public ActionGoBack() {
			this.putValue(Action.NAME, "Go Back !");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("APPUYE BACK");
			BorderLayout layout = (BorderLayout)view.main.getLayout();
			view.main.remove(layout.getLayoutComponent(BorderLayout.CENTER));
			view.main.add(view.exercisesPanel, BorderLayout.CENTER);
			view.revalidate();
			view.repaint();
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			System.out.println("UPDATE");
		}	
	}
	
	/*
	 * When the user clicks on the "Deal with it button" it gives the empty truth table of the given input
	 */
	public class ActionDealWithExpressions extends AbstractAction implements Observer {
		
		public ActionDealWithExpressions() {
			this.putValue(Action.NAME, "Deal with it");
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			String expressions = view.inputExpressionsExOne.getText();
			try {
				try{
					String input = view.inputExpressionsExOne.getText();
					TruthTable truthTable = new TruthTable(input);
					TruthTableView truthTableView = new TruthTableView(truthTable);
					JScrollPane scrollPane = new JScrollPane(truthTableView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					truthTableView.check.setAction(new CheckTruthTable(truthTable, truthTableView, view, input));
					view.panelExerciseOne.add(scrollPane, BorderLayout.CENTER);
					view.dealExpressionsExOne.setEnabled(false);
					view.giveExpressionExOne.setEnabled(false);
					view.resetExOne.setEnabled(true);
					view.revalidate();
					view.repaint();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			catch (StringIndexOutOfBoundsException npe){
				JOptionPane.showMessageDialog(null, "Invalid syntax : empty expression !");
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			System.out.println("UPDATE");
		}	
	}
	
	/*
	 * Controller which allows to check the user's answers for the truth table
	 * It checks all the boolean values and tells the user if there's a mistake
	 * If everything is correct the view is updated with the truth table (as fixed)
	 */
	public class CheckTruthTable extends AbstractAction implements Observer{

		TruthTable truthTable;
		TruthTableView truthTableView;
		View view;
		String input;
		
		public CheckTruthTable(TruthTable truthTable, TruthTableView truthTableView, View view, String input) {
			this.putValue(Action.NAME, "Check answers");
			this.truthTable = truthTable;
			this.truthTableView = truthTableView;
			this.view = view;
			this.input = input;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// We check if the data file exists
			File dataFile = new File("src/DataFileExOne.txt");
			if (!dataFile.exists()) {
				System.out.println("File does not exist !");
				try {
					dataFile.createNewFile();
					FileWriter fileWriter = new FileWriter(dataFile);
					fileWriter.write('0');
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
				System.out.println("File existing !");
			}
			//We collect the user answers
			int numberOfColumns = this.truthTableView.answers[0].length;
			int numberOfRows = this.truthTableView.answers.length;
			try {
				this.truthTable.buildTable();
				// We check the answers column by column
				// We get each column
				for (int i = 0; i < numberOfColumns; i++) {
					String[] column = new String[numberOfRows];
					for (int j = 0; j < numberOfRows; j++) {
						column[j] = this.truthTableView.answers[j][i].getText();
						if (this.truthTableView.answers[j][i].getForeground().equals(Color.RED)) {
							this.truthTableView.answers[j][i].setForeground(Color.BLACK);
						}
					}
					// We compare each column with the right values (the correct Truth Table)
					ArrayList<Integer> mistakesIndex = this.truthTable.checkColumn(column, i);
					if (!mistakesIndex.isEmpty()) {
						for (Integer index: mistakesIndex) {
							this.truthTableView.answers[index][i].setForeground(Color.RED);
						}
						FileReader fileReader = new FileReader(dataFile);
						int character = (int) fileReader.read();
						fileReader.close();
						character ++;
						// If there's a mistake we stop the checking and tell the user he's wrong
						throw new Exception("You've made a mistake !");
					}
				}
				// If no mistake encountered we display the Truth Table as fixed
				BorderLayout layout = (BorderLayout)view.panelExerciseOne.getLayout();
				this.view.panelExerciseOne.remove(layout.getLayoutComponent(BorderLayout.CENTER));
				CorrectTruthTableView correctTruthTableView;
				try {
					correctTruthTableView = new CorrectTruthTableView(this.truthTable);
					JScrollPane scrollPane = new JScrollPane(correctTruthTableView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					view.panelExerciseOne.add(scrollPane, BorderLayout.CENTER);
					// If the user succeeded in finding the right answer for the current level of formula
					// (from the file of given formulas) we increase the level of the user by One
					if (examples.get(indexOfExamples).equals(input) && indexOfExamples < examples.size()-1) {
						indexOfExamples ++;
					}
					view.revalidate();
					view.repaint();
				} catch (Exception e) {
					throw e;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class ActionGiveExpression extends AbstractAction implements Observer{
		
		public ActionGiveExpression() {
			this.putValue(Action.NAME, "Give an expression");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			view.inputExpressionsExOne.setText(examples.get(indexOfExamples));
		}

		@Override
		public void update(Observable o, Object arg) {
			
		}
		
	}
	
	public void readFileExamplesExOne(){

        try (BufferedReader br = Files.newBufferedReader(Paths.get("src/Examples.txt"))) {

            // read line by line
            String line;
            while ((line = br.readLine()) != null) {
            	this.examples.add(line);
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
	}
	
	/*
	 * When the user click on the "Deal with it button" it realizes the resolution of the given expression
	 */
	public class ActionDealWithExpressionsExTwo extends AbstractAction implements Observer {
		
		public ActionDealWithExpressionsExTwo() {
			this.putValue(Action.NAME, "Deal with it");
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			String expression = view.inputExpressionsExTwo.getText();
			try {
				TreeMaker treeMaker = new TreeMaker(expression);
				JPanel tabView = treeMaker.syntaxAnalysis().getView();
				BorderLayout layout = (BorderLayout)view.panelExerciseTwo.getLayout();
				if (layout.getLayoutComponent(BorderLayout.CENTER) instanceof JPanel) {
					view.panelExerciseTwo.remove(layout.getLayoutComponent(BorderLayout.CENTER));
				}
				tabView.setPreferredSize(new Dimension(1000, 1000));
		        JScrollPane scrollPane = new JScrollPane(tabView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				view.panelExerciseTwo.add(scrollPane, BorderLayout.CENTER);
				view.dealExpressionsExTwo.setEnabled(false);
				view.resetExTwo.setEnabled(true);
				view.revalidate();
				view.repaint();
			}
			catch (StringIndexOutOfBoundsException npe){
				JOptionPane.showMessageDialog(null, "Invalid syntax : empty expression !");
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		
		@Override
		public void update(Observable arg0, Object arg1) {
			System.out.println("UPDATE");
		}	
	}
	
	/*
	 * When the user clicks on the "Deal with it button" it realizes the resolution of the given expression
	 */
	public class ActionDealWithExpressionsExThree extends AbstractAction implements Observer {
		
		public ActionDealWithExpressionsExThree() {
			this.putValue(Action.NAME, "Deal with it");
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			String expressions = view.inputExpressionsExThree.getText();
			try {
				tableau = new Tableau();
				tableau.setRoot(new Set(expressions));
				if (view.modeSelector.getSelection().equals(view.modeOneStep.getModel())) {
					tableau.setDevMode(0);
					tableau.applyRules();
				}
				else{
					if (view.modeSelector.getSelection().equals(view.modeDepthFirst.getModel())) {
						tableau.setDevMode(1);
					}
					else if (view.modeSelector.getSelection().equals(view.modeBreadthFirst.getModel())) {
						tableau.setDevMode(2);
					}
					try {
						tableau.applyRule();
						view.nextStep.setEnabled(true);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
				TableauView<String> tabView = tableau.getView();				
				BorderLayout layout = (BorderLayout)view.panelExerciseThree.getLayout();
				if (layout.getLayoutComponent(BorderLayout.CENTER) instanceof JPanel) {
					view.panelExerciseThree.remove(layout.getLayoutComponent(BorderLayout.CENTER));
				}
				JScrollPane scrollPane = new JScrollPane(tabView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				view.panelExerciseThree.add(scrollPane, BorderLayout.CENTER);
				view.dealExpressionsExThree.setEnabled(false);
				view.resetExThree.setEnabled(true);
				view.revalidate();
				view.repaint();
				if (!tableau.remainingRules()) {
					view.nextStep.setEnabled(false);
					if (!tableau.validity()) {
						JOptionPane.showMessageDialog(null, "There is at leat one contradiction in the Tableau !");
					}
					else{
						JOptionPane.showMessageDialog(null, "The Tableau is correct !");
					}
				}
			}
			catch (StringIndexOutOfBoundsException npe){
				JOptionPane.showMessageDialog(null, "Invalid syntax : empty expression !");
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		
		@Override
		public void update(Observable arg0, Object arg1) {
			System.out.println("UPDATE");
		}	
	}
	
	/*
	 * When the user click on the "Deal with it button" it realizes the resolution of the given expression
	 */
	public class ActionDealWithExpressionsExFour extends AbstractAction implements Observer {
		
		public ActionDealWithExpressionsExFour() {
			this.putValue(Action.NAME, "Deal with it");
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			String expressions = view.inputExpressionsExFour.getText();
			try {
				tableau = new Tableau();
				tableau.setRoot(new Set(expressions));
				tableau.applyRule();
				JPanel tabView = tableau.getView();
				tabView.addMouseListener(new SetSelected());
				
				BorderLayout layout = (BorderLayout)view.panelExerciseFour.getLayout();
				if (layout.getLayoutComponent(BorderLayout.CENTER) instanceof JPanel) {
					view.panelExerciseFour.remove(layout.getLayoutComponent(BorderLayout.CENTER));
				}
		        JScrollPane scrollPane = new JScrollPane(tabView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				view.panelExerciseFour.add(scrollPane, BorderLayout.CENTER);
				view.dealExpressionsExFour.setEnabled(false);
				view.resetExFour.setEnabled(true);
				view.revalidate();
				view.repaint();
			}
			catch (StringIndexOutOfBoundsException npe){
				JOptionPane.showMessageDialog(null, "Invalid syntax : empty expression !");
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			System.out.println("UPDATE");
		}	
	}
	
	/*
	 * Listener which allows to select a Set in the tree shown
	 */
	public class SetSelected implements MouseListener {
		
		public SetSelected(){
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			Coordinates coords = tableau.getCoordinates(e.getX(), e.getY());
			Set set = tableau.getSetSelected(coords);
			if (set != null && !set.allLiterals()) {
				HashMap<Integer, String> operatorsMap = set.getOperators();
				popup = new PopupViewChoseOperator(set.toString(), operatorsMap.values());
				Object[] indexList = operatorsMap.keySet().toArray();
				int tmp = 0;
				for (JButton button : popup.buttons) {
					button.addMouseListener(new ChoseOperator(popup, (int) indexList[tmp], coords));
					tmp ++;
				}
				popup.setVisible(true);
			}
			tableau.updateContradictions();
			view.revalidate();
			view.repaint();
		}
	}
	
	/*
	 * Listener which allows to select one specific operation to do in a selected Set
	 */
	public class ChoseOperator implements MouseListener{
		
		PopupViewChoseOperator popup;
		int index;
		Coordinates coords;
		
		
		public ChoseOperator(PopupViewChoseOperator popup, int index, Coordinates coords){
			this.popup = popup;
			this.index = index;
			this.coords = coords;
		}


		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				String chosenOperator = ((JButton)e.getSource()).getText();
				this.popup.panelGeneral.remove(this.popup.panelOperator);
				this.popup.panelGeneral.add(this.popup.panelResultAnticipation);
				this.popup.labelInfo.setText(this.popup.labelInfo.getText() + chosenOperator);
				this.popup.revalidate();
				this.popup.repaint();
				this.popup.checkAnticipation.addMouseListener(new CheckAnticipationOperatorResult(popup, index, coords));
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			tableau.updateContradictions();
			view.revalidate();
			view.repaint();
		}


		@Override
		public void mouseEntered(MouseEvent e) {
			
		}


		@Override
		public void mouseExited(MouseEvent e) {
			
		}


		@Override
		public void mousePressed(MouseEvent e) {
			
		}


		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
		
	}
	
	/*
	 * Listener which allows the user to anticipate the result of the operation previously selected
	 */
	public class CheckAnticipationOperatorResult implements MouseListener{

		PopupViewChoseOperator popup;
		int index;
		Coordinates coords;
		
		public CheckAnticipationOperatorResult(PopupViewChoseOperator popup, int index, Coordinates coords) {
			this.popup = popup;
			this.index = index;
			this.coords = coords;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				Set simulatedSet = tableau.simulateRuleForThisSet(coords, index);
				boolean checked = false;
				if (simulatedSet.getSecond() == null) {
					if (!this.popup.inputTwo.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Too many children anticipated !");
					}
					else{
						checked = simulatedSet.getFirst().isAnticipationRight(this.popup.inputOne.getText());
					}
				}
				else {
					checked = simulatedSet.getFirst().isAnticipationRight(this.popup.inputOne.getText())
					&& simulatedSet.getSecond().isAnticipationRight(this.popup.inputTwo.getText());
				}
				if (checked) {
					stepsStack.push(tableau.clone());
					view.undo.setEnabled(true);
					tableau.applyRuleForThisSet(this.coords, this.index);
					tableau.accessibleSets.remove(this.coords);
					this.popup.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Wrong Anticipation !");
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			tableau.updateContradictions();
			view.revalidate();
			view.repaint();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/*
	 * When the user clicks on the "Deal with it" button it gives an empty line of the truth table
	 * of the given input expression
	 */
	public class ActionDealWithExpressionsExFive extends AbstractAction implements Observer {
		
		public ActionDealWithExpressionsExFive() {
			this.putValue(Action.NAME, "Deal with it");
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				try{
					String input = view.inputExpressionsExFive.getText();
					TruthTable truthTable = new TruthTable(input);
					OneTruthTableLineView oneTruthTableLineView = new OneTruthTableLineView(truthTable);
					JScrollPane scrollPane = new JScrollPane(oneTruthTableLineView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					oneTruthTableLineView.check.addMouseListener(new CheckAnswersLineTruthTable(truthTable, oneTruthTableLineView, view));
					view.panelExerciseFive.add(scrollPane, BorderLayout.CENTER);
					view.dealExpressionsExFive.setEnabled(false);
					view.resetExFive.setEnabled(true);
					view.revalidate();
					view.repaint();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			catch (StringIndexOutOfBoundsException npe){
				JOptionPane.showMessageDialog(null, "Invalid syntax : empty expression !");
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			System.out.println("UPDATE");
		}	
	}
	
	/*
	 * Listener for the check button of the truth table line
	 * It allows to check the user's answers and tells whether he's all right or not
	 */
	public class CheckAnswersLineTruthTable implements MouseListener{

		TruthTable truthTable;
		OneTruthTableLineView oneTruthTableLineView;
		View view;
		
		public CheckAnswersLineTruthTable(TruthTable truthTable, OneTruthTableLineView oneTruthTableLineView, View view) {
			this.truthTable = truthTable;
			this.oneTruthTableLineView = oneTruthTableLineView;
			this.view = view;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			String[] answers = new String[this.oneTruthTableLineView.answers.length];
			for (int i = 0; i < this.oneTruthTableLineView.answers.length; i++) {
				answers[i] = this.oneTruthTableLineView.answers[i].getText();
			}
			try {
				if (this.truthTable.checkAnswersForLine(answers)) {
					JOptionPane.showMessageDialog(null, "Correct answer !");
				}
				else{
					JOptionPane.showMessageDialog(null, "Your answer is false !");
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/*
	 * When the user click on the button "Reset" it resets everything related to the current exercise
	 */
	public class ActionReset extends AbstractAction implements Observer {
		
		public ActionReset() {
			this.putValue(Action.NAME, "Reset");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("RESET");
			BorderLayout layout = (BorderLayout)view.panelExerciseOne.getLayout();
			view.panelExerciseOne.remove(layout.getLayoutComponent(BorderLayout.CENTER));
			view.inputExpressionsExOne.setText("");
			view.dealExpressionsExOne.setEnabled(true);
			view.giveExpressionExOne.setEnabled(true);
			view.resetExOne.setEnabled(false);
			view.inputExpressionsExOne.requestFocusInWindow();
			view.revalidate();
			view.repaint();
			try {
				tableau.setRoot(null);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			System.out.println("UPDATE");
		}
	}
	
	/*
	 * When the user click on the button "Reset" it resets everything related to the current exercise
	 */
	public class ActionResetExTwo extends AbstractAction implements Observer {
		
		public ActionResetExTwo() {
			this.putValue(Action.NAME, "Reset");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("RESET");
			BorderLayout layout = (BorderLayout)view.panelExerciseTwo.getLayout();
			view.panelExerciseTwo.remove(layout.getLayoutComponent(BorderLayout.CENTER));
			view.inputExpressionsExTwo.setText("");
			view.dealExpressionsExTwo.setEnabled(true);
			view.resetExTwo.setEnabled(false);
			view.inputExpressionsExTwo.requestFocusInWindow();
			view.revalidate();
			view.repaint();
			try {
				tableau.setRoot(null);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			System.out.println("UPDATE");
		}
	}
	
	/*
	 * When the user click on the button "Reset" it resets everything related to the current exercise
	 */
	public class ActionResetExThree extends AbstractAction implements Observer {
		
		public ActionResetExThree() {
			this.putValue(Action.NAME, "Reset");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("RESET");
			BorderLayout layout = (BorderLayout)view.panelExerciseThree.getLayout();
			view.panelExerciseThree.remove(layout.getLayoutComponent(BorderLayout.CENTER));
			view.inputExpressionsExThree.setText("");
			view.dealExpressionsExThree.setEnabled(true);
			view.resetExThree.setEnabled(false);
			view.nextStep.setEnabled(false);
			view.inputExpressionsExThree.requestFocusInWindow();
			view.revalidate();
			view.repaint();
			try {
				tableau.setRoot(null);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			System.out.println("UPDATE");
		}
	}
	
	/*
	 * When the user click on the button "Reset" it resets everything related to the current exercise
	 */
	public class ActionResetExFour extends AbstractAction implements Observer {
		
		public ActionResetExFour() {
			this.putValue(Action.NAME, "Reset");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("RESET");
			BorderLayout layout = (BorderLayout)view.panelExerciseFour.getLayout();
			view.panelExerciseFour.remove(layout.getLayoutComponent(BorderLayout.CENTER));
			view.inputExpressionsExFour.setText("");
			view.dealExpressionsExFour.setEnabled(true);
			view.resetExFour.setEnabled(false);
			view.undo.setEnabled(false);
			stepsStack.removeAllElements();
			view.inputExpressionsExFour.requestFocusInWindow();
			view.revalidate();
			view.repaint();
			try {
				tableau.setRoot(null);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			System.out.println("UPDATE");
		}
	}
	
	public class ActionResetExFive extends AbstractAction implements Observer {
		
		public ActionResetExFive() {
			this.putValue(Action.NAME, "Reset");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("RESET");
			BorderLayout layout = (BorderLayout)view.panelExerciseFive.getLayout();
			view.panelExerciseFive.remove(layout.getLayoutComponent(BorderLayout.CENTER));
			view.inputExpressionsExFive.setText("");
			view.dealExpressionsExFive.setEnabled(true);
			view.resetExFive.setEnabled(false);
			view.inputExpressionsExFive.requestFocusInWindow();
			view.revalidate();
			view.repaint();
			try {
				tableau.setRoot(null);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			System.out.println("UPDATE");
		}
	}
	
	/*
	 * Action which allows the user to undo his last action (for the exercise four)
	 */
	public class ActionUndoExFour extends AbstractAction implements Observer{
		
		public ActionUndoExFour(){
			this.putValue(Action.NAME, "Undo");
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			tableau = stepsStack.pop();
			if (stepsStack.empty()) {
				view.undo.setEnabled(false);
			}
			BorderLayout layout = (BorderLayout)view.panelExerciseFour.getLayout();
			view.panelExerciseFour.remove(layout.getLayoutComponent(BorderLayout.CENTER));
			JPanel tabView = tableau.getView();
			tabView.addMouseListener(new SetSelected());
			tabView.setPreferredSize(new Dimension(1400, 1000));
	        JScrollPane scrollPane = new JScrollPane(tabView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			view.panelExerciseFour.add(scrollPane, BorderLayout.CENTER);
			view.revalidate();
			view.repaint();
		}

		@Override
		public void update(Observable o, Object arg) {
			System.out.println("UPDATE");
		}
		
	}
	
	/*
	 * Action which allows the user to develop another Set of the current Tableau considering the resolution mode selected
	 */
	public class ActionNextStep extends AbstractAction implements Observer {
		
		public ActionNextStep() {
			this.putValue(Action.NAME, "Next");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("NEXT");
			try {
				tableau.applyRule();
				tableau.updateContradictions();
				view.revalidate();
				view.repaint();
				if (!tableau.remainingRules()) {
					view.nextStep.setEnabled(false);
					view.nextStep.setEnabled(false);
					if (tableau.getRoot().contradiction()) {
						JOptionPane.showMessageDialog(null, "There is at leat one contradiction in the Tableau !");
					}
					else{
						JOptionPane.showMessageDialog(null, "The Tableau is correct !");
					}
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
		
		@Override
		public void update(Observable arg0, Object arg1) {
			System.out.println("UPDATE");
		}
	}
	
}
