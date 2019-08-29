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
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
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
import javax.swing.JTextField;
import javax.xml.crypto.Data;

import Model.Coordinates;
import Model.DataHandler;
import Model.DataHandlerExTwo;
import Model.DataHandlerExFive;
import Model.DataHandlerExThree;
import Model.Set;
import Model.Tableau;
import Model.TreeMaker;
import Model.TruthTable;
import View.TableauView;
import View.TruthTableView;
import View.View;
import View.CorrectTruthTableView;
import View.Exercise;
import View.ExerciseFive;
import View.ExerciseWithData;
import View.OneTruthTableLineView;
import View.PopupExerciseInstructions;
import View.PopupViewChoseOperator;
import View.PopupViewDataExTwo;
import View.PopupViewDataExFive;
import View.PopupViewDataExThree;

public class Controler {

	private Tableau tableau;
	private View view;
	private PopupViewChoseOperator popup;
	private Stack<Tableau> stepsStack;
	private ArrayList<String> examplesTruthTable;
	private ArrayList<String> examplesTableau;

	private String beginingOfPath;
	
	public Controler(Tableau tab){
		
		this.tableau = tab;
		this.view = new View();
		this.stepsStack = new Stack<Tableau>();
		this.examplesTruthTable = new ArrayList<String>();
		this.examplesTableau = new ArrayList<String>();
		
		// We define the Data storage space (H-Drive if the user is on a University 
		// computer and the current folder if he is on his own computer)
		if (new File("H:").exists()) {
			this.beginingOfPath = "H:/";
		}
		else{
			this.beginingOfPath = "";
		}
		try {
			Files.createDirectories(Paths.get(this.beginingOfPath + "LogicAppData"));
			File indexFile = new File(this.beginingOfPath + "LogicAppData" + "/Index.txt");
			if (!indexFile.exists()) {
				indexFile.createNewFile();
				FileWriter fileWriter = new FileWriter(indexFile);
				// We set the index to 0 for each existing exercise (currently 5)
				for (int i = 0; i < 5; i++) {
					fileWriter.write("0" + System.getProperty("line.separator"));
				}
				fileWriter.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.beginingOfPath = this.beginingOfPath + "LogicAppData/";
		
		
		// Setting the menu controllers
		this.view.exOne.addMouseListener(new ActionExercise(view.exerciseOnePanel));
		this.view.exTwo.addMouseListener(new ActionExercise(view.exerciseTwoPanel, "ExamplesTruthTable.txt", this.examplesTruthTable));
		this.view.exThree.addMouseListener(new ActionExercise(view.exerciseThreePanel, "ExamplesTruthTable.txt", this.examplesTruthTable));
		this.view.exFour.addMouseListener(new ActionExercise(view.exerciseFourPanel, "ExamplesTableau.txt", this.examplesTableau));
		this.view.exFive.addMouseListener(new ActionExercise(view.exerciseFivePanel, "ExamplesTableau.txt", this.examplesTableau));
		
		
		// Initialization of all the exercises controllers
		
		// Exercise One
		// Adding the controller for the deal with it button (executing the exercise action)
		ActionDealWithExpressionsExOne actionDealWithExpressionsExOne = new ActionDealWithExpressionsExOne();
		this.view.exerciseOnePanel.dealExpressions.setAction(actionDealWithExpressionsExOne);
		
		// Adding the key listener which allows to click on the deal with it button when we press the enter button
		// with focus on the text field
		this.view.exerciseOnePanel.inputExpressions.addKeyListener(new KeyAdapterInputExpressions(this.view.exerciseOnePanel));
		
		// Adding the controller for the reset button
		ActionResetExercise actionResetExerciseOne = new ActionResetExercise(this.view.exerciseOnePanel);
		this.view.exerciseOnePanel.reset.setAction(actionResetExerciseOne);
		
		// Adding the controller for the go back (to the main menu) button
		ActionGoBack actionGoBackExOne = new ActionGoBack();
		this.view.exerciseOnePanel.goBack.setAction(actionGoBackExOne);
		
		
		// Exercise Two
		// Adding the controller for the deal with it button (executing the exercise action)
		ActionDealWithExpressionsExTwo actionDealWithExpressionsExTwo = new ActionDealWithExpressionsExTwo();
		this.view.exerciseTwoPanel.dealExpressions.setAction(actionDealWithExpressionsExTwo);
		
		// Adding the key listener which allows to click on the deal with it button when we press the enter button
		// with focus on the text field
		this.view.exerciseTwoPanel.inputExpressions.addKeyListener(new KeyAdapterInputExpressions(this.view.exerciseTwoPanel));
		
		// Adding the controller for the button input formula (which gives an input expression)
		ActionGiveExpression actionGiveExpressionExTwo = new ActionGiveExpression(2, examplesTruthTable, view.exerciseTwoPanel.inputExpressions);
		this.view.exerciseTwoPanel.giveExpression.setAction(actionGiveExpressionExTwo);
		
		// Adding the controller for the reset button
		ActionResetExercise actionResetExTwo = new ActionResetExercise(this.view.exerciseTwoPanel);
		this.view.exerciseTwoPanel.reset.setAction(actionResetExTwo);
		
		// Adding the controller to the progress button (which allows to show the data)
		ActionShowProgressExTwo actionShowProgressExTwo = new ActionShowProgressExTwo();
		this.view.exerciseTwoPanel.progress.setAction(actionShowProgressExTwo);
		
		// Adding the controller for the go back (to the main menu) button
		ActionGoBack actionGoBackExTwo = new ActionGoBack();
		this.view.exerciseTwoPanel.goBack.setAction(actionGoBackExTwo);
		
		
		// Exercise Three
		// Adding the controller for the deal with it button (executing the exercise action)
		ActionDealWithExpressionsExThree actionDealWithExpressionsExThree = new ActionDealWithExpressionsExThree();
		this.view.exerciseThreePanel.dealExpressions.setAction(actionDealWithExpressionsExThree);
		
		// Adding the key listener which allows to click on the deal with it button when we press the enter button
		// with focus on the text field
		this.view.exerciseThreePanel.inputExpressions.addKeyListener(new KeyAdapterInputExpressions(this.view.exerciseThreePanel));
		
		// Adding the controller for the button input formula (which gives an input expression)
		ActionGiveExpression actionGiveExpressionExThree = new ActionGiveExpression(3, examplesTruthTable, view.exerciseThreePanel.inputExpressions);
		this.view.exerciseThreePanel.giveExpression.setAction(actionGiveExpressionExThree);

		// Adding the controller for the reset button
		ActionResetExercise actionResetExThree = new ActionResetExercise(this.view.exerciseThreePanel);
		this.view.exerciseThreePanel.reset.setAction(actionResetExThree);
		
		// Adding the controller to the progress button (which allows to show the data)
		ActionShowProgressExThree actionShowProgressExThree = new ActionShowProgressExThree();
		this.view.exerciseThreePanel.progress.setAction(actionShowProgressExThree);
		
		// Adding the controller for the go back (to the main menu) button
		ActionGoBack actionGoBackExThree = new ActionGoBack();
		this.view.exerciseThreePanel.goBack.setAction(actionGoBackExThree);
		
		
		// Exercise Four
		// Adding the controller for the deal with it button (executing the exercise action)
		ActionDealWithExpressionsExFour actionDealWithExpressionsExFour = new ActionDealWithExpressionsExFour();
		this.view.exerciseFourPanel.dealExpressions.setAction(actionDealWithExpressionsExFour);
		
		// Adding the key listener which allows to click on the deal with it button when we press the enter button
		// with focus on the text field
		this.view.exerciseFourPanel.inputExpressions.addKeyListener(new KeyAdapterInputExpressions(this.view.exerciseFourPanel));
		
		// Adding the controller for the button input formula (which gives an input expression)
		ActionGiveExpression actionGiveExpressionExFour = new ActionGiveExpression(4, examplesTableau, view.exerciseFourPanel.inputExpressions);
		this.view.exerciseFourPanel.giveExpression.setAction(actionGiveExpressionExFour);
		
		// Adding the controller for the reset button
		ActionResetExercise actionResetExFour = new ActionResetExercise(this.view.exerciseFourPanel);
		this.view.exerciseFourPanel.reset.setAction(actionResetExFour);
		
		// Adding of the controller which allows to print the Tableau step by step
		ActionNextStepExFour actionNextStepExFour = new ActionNextStepExFour();
		this.view.exerciseFourPanel.nextStep.setAction(actionNextStepExFour);
		
		// Adding the controller for the go back (to the main menu) button
		ActionGoBack actionGoBackExFour = new ActionGoBack();
		this.view.exerciseFourPanel.goBack.setAction(actionGoBackExFour);
		
		
		// Exercise Five
		// Adding the controller for the deal with it button (executing the exercise action)
		ActionDealWithExpressionsExFive actionDealWithExpressionsExFive = new ActionDealWithExpressionsExFive();
		this.view.exerciseFivePanel.dealExpressions.setAction(actionDealWithExpressionsExFive);
		
		// Adding the key listener which allows to click on the deal with it button when we press the enter button
		// with focus on the text field		
		this.view.exerciseFivePanel.inputExpressions.addKeyListener(new KeyAdapterInputExpressions(this.view.exerciseFivePanel));
		
		// Adding the controller for the button input formula (which gives an input expression)
		ActionGiveExpression actionGiveExpressionExFive = new ActionGiveExpression(5, examplesTableau, view.exerciseFivePanel.inputExpressions);
		this.view.exerciseFivePanel.giveExpression.setAction(actionGiveExpressionExFive);
		
		// Adding the controller for the reset button
		ActionResetExercise actionResetExFive = new ActionResetExercise(this.view.exerciseFivePanel);
		this.view.exerciseFivePanel.reset.setAction(actionResetExFive);
		
		// Adding the controller which allows the user to undo his actions
		ActionUndoExFive actionUndoExFive = new ActionUndoExFive();
		this.view.exerciseFivePanel.undo.setAction(actionUndoExFive);
		this.view.exerciseFivePanel.undo.setEnabled(false);
		
		// Adding the controller to the progress button (which allows to show the data)
		ActionShowProgressExFive actionShowProgressExFive = new ActionShowProgressExFive();
		this.view.exerciseFivePanel.progress.setAction(actionShowProgressExFive);
		
		// Adding the controller for the go back (to the main menu) button
		ActionGoBack actionGoBackExFive = new ActionGoBack();
		this.view.exerciseFivePanel.goBack.setAction(actionGoBackExFive);
		
	}
	
	/*
	 * Class extending KeyAdapter which allows to execute the action of the deal with it button
	 * when the user presses "enter" on the keyboard and that the text field has the focus
	 */
	class KeyAdapterInputExpressions extends KeyAdapter {
		
		Exercise exercisePanel;
		
		public KeyAdapterInputExpressions(Exercise exercisePanel) {
			this.exercisePanel = exercisePanel;
		}
		
		@Override
	    public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				this.exercisePanel.dealExpressions.doClick();
	        }
		}
		
	}
	
	// Main Menu
	
	/*
	 * When the user clicks on the button of one exercise it removes the panel of the main menu
	 * and replaces it with the selected exercise's panel
	 * exercisePanel => the panel of the exercise selected by the user
	 */
	public class ActionExercise implements MouseListener {

		Exercise exercisePanel;
		String file;
		ArrayList<String> examples;
		
		public ActionExercise(Exercise exercisePanel) {
			this.exercisePanel = exercisePanel;
		}
		
		public ActionExercise(Exercise exercisePanel, String file, ArrayList<String> examples) {
			this.exercisePanel = exercisePanel;
			this.file = file;
			this.examples = examples;		
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("APPUYE");
			view.title.setText(this.exercisePanel.title);
			BorderLayout layout = (BorderLayout)view.main.getLayout();
			view.main.remove(layout.getLayoutComponent(BorderLayout.CENTER));
			view.main.add(this.exercisePanel, BorderLayout.CENTER);
			this.exercisePanel.inputExpressions.requestFocusInWindow();
			if (this.exercisePanel.dealExpressions.isEnabled()) {
				this.exercisePanel.reset.setEnabled(false);
			}
			if (this.file != null && this.examples.isEmpty()) {
				readFileExamples(this.file, this.examples);
			}
			view.revalidate();
			view.repaint();
			PopupExerciseInstructions popupExerciseInstructions = new PopupExerciseInstructions(this.exercisePanel);
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
	 * Function which allows to read the Files with all the examples for the given exercise
	 * file => the name of the file to read
	 * examples => the list where the examples are stored
	 */
	public void readFileExamples(String file, ArrayList<String> examples){
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(file)))) {
            // read line by line
            String line;
            while ((line = br.readLine()) != null) {
            	examples.add(line);
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
	}
	
	
	
	// Exercises Controllers
	
	/*
	 * When the user click on the "Deal with it button" it realizes the resolution of the given expression
	 */
	public class ActionDealWithExpressionsExOne extends AbstractAction implements Observer {
		
		public ActionDealWithExpressionsExOne() {
			this.putValue(Action.NAME, "Deal with it");
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			String expression = view.exerciseOnePanel.inputExpressions.getText();
			try {
				TreeMaker treeMaker = new TreeMaker(expression);
				JPanel tabView = treeMaker.syntaxAnalysis().getView();
				BorderLayout layout = (BorderLayout)view.exerciseOnePanel.getLayout();
				if (layout.getLayoutComponent(BorderLayout.CENTER) instanceof JPanel) {
					view.exerciseOnePanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
				}
				tabView.setPreferredSize(new Dimension(1000, 1000));
		        JScrollPane scrollPane = new JScrollPane(tabView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				view.exerciseOnePanel.add(scrollPane, BorderLayout.CENTER);
				view.exerciseOnePanel.dealExpressions.setEnabled(false);
				view.exerciseOnePanel.reset.setEnabled(true);
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
	 * When the user clicks on the "Deal with it" button it gives an empty line of the truth table
	 * of the given input expression
	 */
	public class ActionDealWithExpressionsExTwo extends AbstractAction implements Observer {
		
		public ActionDealWithExpressionsExTwo() {
			this.putValue(Action.NAME, "Deal with it");
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				try{
					String input = view.exerciseTwoPanel.inputExpressions.getText();
					TruthTable truthTable = new TruthTable(input);
					OneTruthTableLineView oneTruthTableLineView = new OneTruthTableLineView(truthTable);
					JScrollPane scrollPane = new JScrollPane(oneTruthTableLineView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					oneTruthTableLineView.check.addMouseListener(new CheckAnswersLineTruthTable(truthTable, oneTruthTableLineView, view, input));
					view.exerciseTwoPanel.add(scrollPane, BorderLayout.CENTER);
					view.exerciseTwoPanel.dealExpressions.setEnabled(false);
					view.exerciseTwoPanel.reset.setEnabled(true);
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
	 * It allows to check the user's answers and tell whether he's all right or not
	 */
	public class CheckAnswersLineTruthTable implements MouseListener{

		TruthTable truthTable;
		OneTruthTableLineView oneTruthTableLineView;
		View view;
		String input;
		
		public CheckAnswersLineTruthTable(TruthTable truthTable, OneTruthTableLineView oneTruthTableLineView, View view, String input) {
			this.truthTable = truthTable;
			this.oneTruthTableLineView = oneTruthTableLineView;
			this.view = view;
			this.input = input;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {		
			String[] answers = new String[this.oneTruthTableLineView.answers.length];
			for (int i = 0; i < this.oneTruthTableLineView.answers.length; i++) {
				answers[i] = this.oneTruthTableLineView.answers[i].getText();
			}
			try {
				DataHandlerExTwo dataHandlerExTwo = new DataHandlerExTwo(beginingOfPath + "DataFileExTwo.txt");
				boolean correct = false;
				if (this.truthTable.checkAnswersForLine(answers)) {
					JOptionPane.showMessageDialog(null, "Correct answer !");
					int indexOfExamples = readIndex(2);
					if (!examplesTruthTable.isEmpty() && indexOfExamples < examplesTruthTable.size() && examplesTruthTable.get(indexOfExamples).equals(this.input)){
						updateIndex(2);
					}
					correct = true;
				}
				else{
					JOptionPane.showMessageDialog(null, "Wrong anwer !");
				}
				
				dataHandlerExTwo.write(this.input, this.truthTable.getLiterals().keySet(), this.truthTable.getNodes(), answers, correct);
			
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
	 * Allows to display the progress of the user for the second exercise (in a popup)
	 */
	public class ActionShowProgressExTwo extends AbstractAction implements Observer {
		
		public ActionShowProgressExTwo() {
			this.putValue(Action.NAME, "Your Progress");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				DataHandlerExTwo dataHandlerExTwo = new DataHandlerExTwo(beginingOfPath + "DataFileExTwo.txt");
				PopupViewDataExTwo popup = new PopupViewDataExTwo(dataHandlerExTwo.getData(), dataHandlerExTwo.getMaxNumberOfColumns());
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}

		@Override
		public void update(Observable o, Object arg) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/*
	 * When the user clicks on the "Deal with it button" it gives the empty truth table of the given input
	 */
	public class ActionDealWithExpressionsExThree extends AbstractAction implements Observer {
		
		public ActionDealWithExpressionsExThree() {
			this.putValue(Action.NAME, "Deal with it");
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				try{
					String input = view.exerciseThreePanel.inputExpressions.getText();
					TruthTable truthTable = new TruthTable(input);
					TruthTableView truthTableView = new TruthTableView(truthTable);
					JScrollPane scrollPane = new JScrollPane(truthTableView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					truthTableView.check.setAction(new CheckTruthTable(truthTable, truthTableView, view, input));
					view.exerciseThreePanel.add(scrollPane, BorderLayout.CENTER);
					view.exerciseThreePanel.dealExpressions.setEnabled(false);
					view.exerciseThreePanel.reset.setEnabled(true);
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
	 * truthTable => the Truth Table object we use to handle this exercise
	 * truthTableView => the view in which the user wrote his answers
	 * view => the general view of the program
	 * input => the expression(s) given by the user at the begining of the exercise
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
			try {
				//We setup the object which will read/write our data
				DataHandlerExThree dataHandlerExThree = new DataHandlerExThree(beginingOfPath + "DataFileExThree.txt");
				dataHandlerExThree.write(this.input, this.truthTable.getNodes());
				//We collect the user answers
				int numberOfColumns = this.truthTableView.answers[0].length;
				int numberOfRows = this.truthTableView.answers.length;
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
						dataHandlerExThree.update(this.input, i, mistakesIndex.size());
						// If there's a mistake we stop the checking and tell the user he's wrong
						throw new Exception("You've made a mistake !");
					}
				}
				// If no mistake encountered we display the Truth Table as fixed
				BorderLayout layout = (BorderLayout)view.exerciseThreePanel.getLayout();
				this.view.exerciseThreePanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
				CorrectTruthTableView correctTruthTableView;
				try {
					correctTruthTableView = new CorrectTruthTableView(this.truthTable);
					JScrollPane scrollPane = new JScrollPane(correctTruthTableView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					view.exerciseThreePanel.add(scrollPane, BorderLayout.CENTER);
					// If the user succeeded in finding the right answer for the current level of formula
					// (from the file of given formulas) we increase the level of the user by One
					int indexOfExamples = readIndex(3);
					if (!examplesTruthTable.isEmpty() && indexOfExamples < examplesTruthTable.size() && examplesTruthTable.get(indexOfExamples).equals(input)) {
						indexOfExamples++;
						updateIndex(3);
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
	
	
	/*
	 * Allows to display the progress of the user for the third exercise (in a popup frame)
	 */
	public class ActionShowProgressExThree extends AbstractAction implements Observer {
		
		public ActionShowProgressExThree() {
			this.putValue(Action.NAME, "Your Progress");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				DataHandlerExThree dataHandlerExThree = new DataHandlerExThree(beginingOfPath + "DataFileExThree.txt");
				PopupViewDataExThree popup = new PopupViewDataExThree(dataHandlerExThree.getMistakes(),
						dataHandlerExThree.getExamplesOneShot(), dataHandlerExThree.getMaxNumberOfColumns());
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}

		@Override
		public void update(Observable o, Object arg) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	/*
	 * When the user clicks on the "Deal with it button" it realizes the resolution of the given expression
	 * with the method selected
	 */
	public class ActionDealWithExpressionsExFour extends AbstractAction implements Observer {
		
		public ActionDealWithExpressionsExFour() {
			this.putValue(Action.NAME, "Deal with it");
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			String expressions = view.exerciseFourPanel.inputExpressions.getText();
			try {
				tableau = new Tableau();
				tableau.setRoot(new Set(expressions));
				if (view.exerciseFourPanel.modeSelector.getSelection().equals(view.exerciseFourPanel.modeOneStep.getModel())) {
					tableau.setDevMode(0);
					tableau.applyRules();
				}
				else{
					if (view.exerciseFourPanel.modeSelector.getSelection().equals(view.exerciseFourPanel.modeDepthFirst.getModel())) {
						tableau.setDevMode(1);
					}
					else if (view.exerciseFourPanel.modeSelector.getSelection().equals(view.exerciseFourPanel.modeBreadthFirst.getModel())) {
						tableau.setDevMode(2);
					}
					try {
						tableau.applyRule();
						view.exerciseFourPanel.nextStep.setEnabled(true);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
				TableauView<String> tabView = tableau.getView();				
				BorderLayout layout = (BorderLayout)view.exerciseFourPanel.getLayout();
				if (layout.getLayoutComponent(BorderLayout.CENTER) instanceof JPanel) {
					view.exerciseFourPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
				}
				JScrollPane scrollPane = new JScrollPane(tabView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				view.exerciseFourPanel.add(scrollPane, BorderLayout.CENTER);
				view.exerciseFourPanel.dealExpressions.setEnabled(false);
				view.exerciseFourPanel.reset.setEnabled(true);
				view.revalidate();
				view.repaint();
				if (!tableau.remainingRules()) {
					view.exerciseFourPanel.nextStep.setEnabled(false);
					if (!tableau.validity()) {
						JOptionPane.showMessageDialog(null, "There is at leat one contradiction in the Tableau !");
					}
					else{
						JOptionPane.showMessageDialog(null, "The Tableau is completed !");
					}
					int indexOfExamples = readIndex(4);
					if (!examplesTableau.isEmpty() && indexOfExamples < examplesTableau.size() && examplesTableau.get(indexOfExamples).equals(expressions)){
						updateIndex(4);
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
	 * When the user click on the "Deal with it button" it realizes the resolution of the first formula
	 * of the expression(s) given by the user and let him do the rest
	 */
	public class ActionDealWithExpressionsExFive extends AbstractAction implements Observer {
		
		public ActionDealWithExpressionsExFive() {
			this.putValue(Action.NAME, "Deal with it");
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			String expressions = view.exerciseFivePanel.inputExpressions.getText();
			try {
				tableau = new Tableau();
				tableau.setRoot(new Set(expressions));
				tableau.applyRule();
				// If the Tableau had only one rule to apply in the examples given by the program we directly 
				// update the index for this exercise
				if (tableau.accessibleSets.isEmpty()) {
					int indexOfExamples = readIndex(5);
					if (!examplesTableau.isEmpty() && indexOfExamples < examplesTableau.size() && examplesTableau.get(indexOfExamples).equals(expressions)){
						updateIndex(5);
					}
				}
				JPanel tabView = tableau.getView();
				tabView.addMouseListener(new SetSelected());
				
				BorderLayout layout = (BorderLayout)view.exerciseFivePanel.getLayout();
				if (layout.getLayoutComponent(BorderLayout.CENTER) instanceof JPanel) {
					view.exerciseFivePanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
				}
		        JScrollPane scrollPane = new JScrollPane(tabView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				view.exerciseFivePanel.add(scrollPane, BorderLayout.CENTER);
				view.exerciseFivePanel.dealExpressions.setEnabled(false);
				view.exerciseFivePanel.reset.setEnabled(true);
				view.revalidate();
				view.repaint();
				
				// We link this to the data handler
				DataHandlerExFive dataHandlerExFive = new DataHandlerExFive(beginingOfPath + "DataFileExFive.txt");
				dataHandlerExFive.write(expressions, 2);
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
		// We get the coordinates of the mouse click and deal with the Set which it corresponds to
		public void mouseClicked(MouseEvent e) {
			
			Coordinates coords = tableau.getCoordinates(e.getX(), e.getY());
			Set set = tableau.getSetSelected(coords);
			
			if (set != null && !set.allLiterals()) {
				HashMap<Integer, String> operatorsMap = set.getOperators();
				
				// We create a popup view so the user can choose which operator he wants to apply on the selected Set
				popup = new PopupViewChoseOperator(set.toString(), operatorsMap.values());
				Object[] indexList = operatorsMap.keySet().toArray();
				int tmp = 0;
				
				// We give all the operators of the Set to the popup view
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
				this.popup.checkAnticipation.addMouseListener(new CheckAnticipationOperatorResult(popup, index, coords, chosenOperator));
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
	 * popup => the view in which the user wrote his answers
	 * index => the index of the operator selected by the user
	 * coords => the coordinates of the selected Set (node of the Tableau)
	 * operator => the String representation of the operator selected by the user
	 */
	public class CheckAnticipationOperatorResult implements MouseListener{

		PopupViewChoseOperator popup;
		int index;
		Coordinates coords;
		String operator;
		
		public CheckAnticipationOperatorResult(PopupViewChoseOperator popup, int index, Coordinates coords, String operator) {
			this.popup = popup;
			this.index = index;
			this.coords = coords;
			this.operator = operator;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				DataHandlerExFive dataHandlerExFive = new DataHandlerExFive(beginingOfPath + "DataFileExFive.txt");
				Set simulatedSet = tableau.simulateRuleForThisSet(coords, index);
				// The substring method is used to get only the expression (the root) and not what it added by
				// the toString() method of the Set class
				String expression = tableau.getRoot().toString().substring(1,tableau.getRoot().toString().length() - 4);
				boolean checked = false;
				// We check the answer of the user
				if (simulatedSet.getSecond() == null) {
					if (!this.popup.inputTwo.getText().equals("")) {
						dataHandlerExFive.update(expression, 1, 1);
						throw new Exception("Too many children anticipated !");
					}
					else{
						checked = simulatedSet.getFirst().isAnticipationRight(this.popup.inputOne.getText());
					}
				}
				else {
					checked = simulatedSet.getFirst().isAnticipationRight(this.popup.inputOne.getText())
					&& simulatedSet.getSecond().isAnticipationRight(this.popup.inputTwo.getText());
				}
				// If the user anticipated well we apply the rule onto the "real" Tableau
				if (checked) {
					stepsStack.push(tableau.clone());
					view.exerciseFivePanel.undo.setEnabled(true);
					tableau.applyRuleForThisSet(this.coords, this.index);
					tableau.accessibleSets.remove(this.coords);
					if (tableau.accessibleSets.isEmpty()) {
						updateIndex(5);
					}
					this.popup.dispose();
				}
				else {
					dataHandlerExFive.update(expression, 1, 1);
					throw new Exception("Wrong Anticipation !");
				}
				if (wrongRuleApplied(this.operator)) {
					dataHandlerExFive.update(expression, 2, 1);
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			tableau.updateContradictions();
			view.revalidate();
			view.repaint();
		}

		public boolean wrongRuleApplied(String operator){
			Collection<String> operators = this.popup.operators;
			if (isBetaRule(operator)) {
				for (String op : operators) {
					if (isAlphaRule(op)) {
						return true;
					}
				}
			}
			return false;
		}
		
		public boolean isAlphaRule(String operator){
			operator= operator.replace(" ", "");
			return operator.equals("T[&]") || operator.equals("F[v]") || operator.equals("F[>]") ||
					operator.equals("T[~]") || operator.equals("F[~]");
		}
		
		public boolean isBetaRule(String operator){
			operator = operator.replace(" ", "");
			return operator.equals("F[&]") || operator.equals("T[v]") || operator.equals("T[>]");
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
	 * Allows to display the progress of the user for the fifth exercise (in a popup)
	 */
	public class ActionShowProgressExFive extends AbstractAction implements Observer {
		
		public ActionShowProgressExFive() {
			this.putValue(Action.NAME, "Your Progress");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				DataHandlerExFive dataHandlerExFive = new DataHandlerExFive(beginingOfPath + "DataFileExFive.txt");
				PopupViewDataExFive popup = new PopupViewDataExFive(dataHandlerExFive.getData());
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}

		@Override
		public void update(Observable o, Object arg) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	/*
	 * Allows to give an expression from the database to the user (in the text field)
	 * when he clicks on the "Input Formula" button
	 * exercise => the number of the exercise selected by the user
	 * examples => the array of examples corresponding to the current exercise
	 * textField => the text field in which the user will get the input he wants
	 */
	public class ActionGiveExpression extends AbstractAction implements Observer{
		
		int exercise;
		ArrayList<String> examples;
		JTextField textField;
		
		public ActionGiveExpression(int exercise, ArrayList<String> examples, JTextField textField) {
			this.putValue(Action.NAME, "Input Formula");
			this.exercise = exercise;
			this.examples = examples;
			this.textField = textField;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				this.textField.setText(this.examples.get(readIndex(this.exercise)));
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "You've been through all the examples now it's your time to play !");
			}
		}

		@Override
		public void update(Observable o, Object arg) {
			
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
			view.title.setText("Reasoning based on Booleans");
			BorderLayout layout = (BorderLayout)view.main.getLayout();
			view.main.remove(layout.getLayoutComponent(BorderLayout.CENTER));
			view.main.add(view.listOfExercises, BorderLayout.CENTER);
			view.revalidate();
			view.repaint();
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			System.out.println("UPDATE");
		}	
	}
	
	
	/*
	 * Action which allows the user to reset the current exercise settings so he can
	 * practice on another example
	 * exercisePanel => the current exercise (the one selected by the user)
	 */
	public class ActionResetExercise extends AbstractAction implements Observer {

		Exercise exercisePanel;
		
		public ActionResetExercise(Exercise exercisePanel) {
			this.putValue(Action.NAME, "Reset");
			this.exercisePanel = exercisePanel;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("RESET");
			BorderLayout layout = (BorderLayout)this.exercisePanel.getLayout();
			this.exercisePanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
			this.exercisePanel.inputExpressions.setText("");
			this.exercisePanel.dealExpressions.setEnabled(true);
			this.exercisePanel.reset.setEnabled(false);
			this.exercisePanel.inputExpressions.requestFocusInWindow();
			if (this.exercisePanel instanceof ExerciseFive) {
				((ExerciseFive) this.exercisePanel).undo.setEnabled(false);
			}
			view.revalidate();
			view.repaint();
			try {
				tableau.setRoot(null);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}

		@Override
		public void update(Observable o, Object arg) {
			// TODO Auto-generated method stub
			
		}
		
	}

	
	/*
	 * Action which allows the user to develop another Set of the current Tableau
	 * considering the resolution method selected (DepthFirst -- BreadthFirst)
	 */
	public class ActionNextStepExFour extends AbstractAction implements Observer {
		
		public ActionNextStepExFour() {
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
					view.exerciseFourPanel.nextStep.setEnabled(false);
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
	
	
	/*
	 * Action which allows the user to undo his last actions, one by one,
	 * for the fourth exercise
	 */
	public class ActionUndoExFive extends AbstractAction implements Observer{
		
		public ActionUndoExFive(){
			this.putValue(Action.NAME, "Undo");
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			tableau = stepsStack.pop();
			if (stepsStack.empty()) {
				view.exerciseFivePanel.undo.setEnabled(false);
			}
			BorderLayout layout = (BorderLayout)view.exerciseFivePanel.getLayout();
			view.exerciseFivePanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
			JPanel tabView = tableau.getView();
			tabView.addMouseListener(new SetSelected());
			tabView.setPreferredSize(new Dimension(1400, 1000));
	        JScrollPane scrollPane = new JScrollPane(tabView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			view.exerciseFivePanel.add(scrollPane, BorderLayout.CENTER);
			view.revalidate();
			view.repaint();
		}

		@Override
		public void update(Observable o, Object arg) {
			System.out.println("UPDATE");
		}
		
	}

	
	/*
	 * Function which allows to read the index of the next expression to give to the user
	 * for the current exercise (the one selected by the user)
	 * exercise => the number of the exercise to deal with
	 */
	public int readIndex(int exercise) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(this.beginingOfPath + "Index.txt")))) {
            // read line by line
            String line = br.readLine();
            int cpt = 1;
            while (line != null) {
				if (cpt == exercise) {
					return Integer.valueOf(line);
				}
				cpt++;
				line = br.readLine();
			}
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        throw new Exception("This exercise doesn't exist !");
	}
	
	
	/*
	 * Function which allows to update the index of the next expression to give to the user
	 * for the current exercise (the one selected by the user)
	 * exercise => the number of the exercise to deal with
	 */
	public void updateIndex(int exercise) throws IOException{
		String content = "";
		File file = new File(this.beginingOfPath + "Index.txt");
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getPath()));
		String line = bufferedReader.readLine();
		int cpt = 1;
		while (line != null) {
			if (cpt == exercise) {
				int value = Integer.valueOf(line) + 1;
				content = content + value + System.getProperty("line.separator");
			}
			else{
				content = content + line + System.getProperty("line.separator");
			}
			cpt ++;
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.write(content);
		fileWriter.close();
	}
	
}
