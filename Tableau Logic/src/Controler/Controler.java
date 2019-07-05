package Controler;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Model.Coordinates;
import Model.Set;
import Model.Tableau;
import Model.TreeMaker;
import View.TableauView;
import View.View;
import View.PopupView;

public class Controler {

	private Tableau tableau;
	private View view;
	private PopupView popup;
	private Stack<Tableau> stepsStack;
	
	public Controler(Tableau tab){
		
		this.tableau = tab;
		this.view = new View();
		this.stepsStack = new Stack<Tableau>();
		
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
	
	/*
	 * When the user click on the "Go back" button it makes him go back to the main menu
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
	 * When the user click on the "Deal with it button" it realizes the resolution of the given expression
	 */
	public class ActionDealWithExpressions extends AbstractAction implements Observer {
		
		public ActionDealWithExpressions() {
			this.putValue(Action.NAME, "Deal with it");
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			String expressions = view.inputExpressionsExOne.getText();
			try {
				tableau = new Tableau();
				tableau.setRoot(new Set(expressions));
				tableau.applyRules();
				JPanel tabView = tableau.getView();
				BorderLayout layout = (BorderLayout)view.panelExerciseOne.getLayout();
				if (layout.getLayoutComponent(BorderLayout.CENTER) instanceof JPanel) {
					view.panelExerciseOne.remove(layout.getLayoutComponent(BorderLayout.CENTER));
				}
				tabView.setPreferredSize(new Dimension(1400, 1000));
		        JScrollPane scrollPane = new JScrollPane(tabView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				view.panelExerciseOne.add(scrollPane, BorderLayout.CENTER);
				view.dealExpressionsExOne.setEnabled(false);
				view.resetExOne.setEnabled(true);
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
	 * When the user click on the "Deal with it button" it realizes the resolution of the given expression
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
						System.out.println(e.getMessage());
					}
				}
				if (!tableau.remainingRules()) {
					view.nextStep.setEnabled(false);
				}
				JPanel tabView = tableau.getView();
				BorderLayout layout = (BorderLayout)view.panelExerciseThree.getLayout();
				if (layout.getLayoutComponent(BorderLayout.CENTER) instanceof JPanel) {
					view.panelExerciseThree.remove(layout.getLayoutComponent(BorderLayout.CENTER));
				}
				tabView.setPreferredSize(new Dimension(1400, 1000));
		        JScrollPane scrollPane = new JScrollPane(tabView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				view.panelExerciseThree.add(scrollPane, BorderLayout.CENTER);
				view.dealExpressionsExThree.setEnabled(false);
				view.resetExThree.setEnabled(true);
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
				TableauView<String> tabView = tableau.getView();
				tabView.addMouseListener(new SetSelected());
				
				BorderLayout layout = (BorderLayout)view.panelExerciseFour.getLayout();
				if (layout.getLayoutComponent(BorderLayout.CENTER) instanceof JPanel) {
					view.panelExerciseFour.remove(layout.getLayoutComponent(BorderLayout.CENTER));
				}
				tabView.setPreferredSize(new Dimension(1400, 1000));
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
				System.out.println(coords);
				HashMap<String, Integer> operatorsMap = set.getOperators();
				popup = new PopupView(set.toString(), operatorsMap.keySet());
				for (JButton button : popup.buttons) {
					button.addMouseListener(new ButtonClicked(popup, operatorsMap.get(button.getText()), coords));
				}
				popup.setVisible(true);
			}
			tableau.updateContradictions();
			view.revalidate();
			view.repaint();
		}
	}
	
	/*
	 * Listener which allows to select one specific operation to do in aselected Set
	 */
	public class ButtonClicked implements MouseListener{
		
		PopupView popup;
		int index;
		Coordinates coords;
		
		
		public ButtonClicked(PopupView popup, int index, Coordinates coords){
			this.popup = popup;
			this.index = index;
			this.coords = coords;
		}


		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				stepsStack.push(tableau.clone());
				view.undo.setEnabled(true);
				tableau.applyRuleForThisSet(this.coords, this.index);
				tableau.accessibleSets.remove(this.coords);
				Controler.this.popup.dispose();
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
				if (tableau.getNumberRules() == 0) {
					view.nextStep.setEnabled(false);
				}
				view.revalidate();
				view.repaint();
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
