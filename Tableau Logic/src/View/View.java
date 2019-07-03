package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class View extends JFrame{
	
	public JPanel main;
	
	public JButton exerciseOne;
	public JButton exerciseTwo;
	public JButton exerciseThree;
	public JButton exerciseFour;
	public JPanel exercisesPanel;
	
	public JPanel panelExerciseOne;
	public JPanel panelExerciseTwo;
	public JPanel panelExerciseThree;
	public JPanel panelExerciseFour;
	
	public JTextField inputExpressionsExOne;
	public JButton dealExpressionsExOne;
	public JButton resetExOne;
	public JButton goBackExOne;
	
	public JTextField inputExpressionsExTwo;
	public JButton dealExpressionsExTwo;
	public JButton resetExTwo;
	public JButton goBackExTwo;
	
	public JTextField inputExpressionsExThree;
	public JButton dealExpressionsExThree;
	public JButton resetExThree;
	public JButton goBackExThree;
	public JButton nextStep;
	public JRadioButton modeOneStep;
	public JRadioButton modeDepthFirst;
	public JRadioButton modeBreadthFirst;
	public ButtonGroup modeSelector;
	
	public JTextField inputExpressionsExFour;
	public JButton dealExpressionsExFour;
	public JButton resetExFour;
	public JButton undo;
	public JButton goBackExFour;
	
	public View(){
		
		//Left Top Icon
		JPanel iconPanel = new JPanel();
		ImageIcon image = new ImageIcon(this.getClass().getResource("universityLogo.gif"));
		ImageIcon resizedImage = new ImageIcon(image.getImage().getScaledInstance(250, 140, Image.SCALE_SMOOTH));
		JLabel picLabel = new JLabel(resizedImage);
		iconPanel.add(picLabel);
		
		//Mid Top Title
		JPanel titlePanel = new JPanel(new BorderLayout());
		titlePanel.setLayout(new GridBagLayout());
		titlePanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		JLabel title = new JLabel("Exercises about Tableau Logic");
		title.setFont(new Font("Serif", Font.PLAIN, 35));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		titlePanel.add(title);
		
		//Right Top Corner
		JLabel lecturer = new JLabel("Alexander BOLOTOV");
		lecturer.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		JLabel lecture = new JLabel("Formal Introduction of Tableau");
		lecture.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		JLabel year = new JLabel("First year");
		year.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		JLabel help = new JLabel("Help");
	    help.setToolTipText("<html><img src=\"" + View.class.getResource("Rules.PNG") + "\">");
	    help.setHorizontalAlignment(JLabel.CENTER);
	    help.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	    JPanel helpPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	    helpPanel.setPreferredSize(new Dimension(40, 40));
	    helpPanel.add(help);
	    helpPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    help.setOpaque(true);
	    help.setBackground(Color.MAGENTA);
	    
		JPanel rightTopCorner = new JPanel();
		rightTopCorner.setLayout(new BoxLayout(rightTopCorner, BoxLayout.Y_AXIS));
		rightTopCorner.add(lecturer);
		rightTopCorner.add(lecture);
		rightTopCorner.add(year);
		rightTopCorner.add(helpPanel);
		rightTopCorner.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		//Top Panel		
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setPreferredSize(new Dimension(1000, 180));
		topPanel.add(iconPanel, BorderLayout.LINE_START);
		topPanel.add(titlePanel, BorderLayout.CENTER);
		topPanel.add(rightTopCorner, BorderLayout.LINE_END);
		
		
		
		//List of exercises
		this.exerciseOne = new JButton();
		this.exerciseOne.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.exerciseTwo = new JButton();
		this.exerciseTwo.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.exerciseThree = new JButton();
		this.exerciseThree.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.exerciseFour = new JButton();
		this.exerciseFour.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.exercisesPanel = new JPanel();
		this.exercisesPanel.setLayout(new BoxLayout(this.exercisesPanel, BoxLayout.Y_AXIS));
		this.exercisesPanel.add(exerciseOne);
		this.exercisesPanel.add(exerciseTwo);
		this.exercisesPanel.add(exerciseThree);
		this.exercisesPanel.add(exerciseFour);
		
		
		//Main panel => where the exercises are set
		this.main = new JPanel(new BorderLayout());
		this.main.add(this.exercisesPanel, BorderLayout.CENTER);
		
		
		//Exercise 1
		JLabel inputLabelOne = new JLabel("Input : ");
		this.inputExpressionsExOne = new JTextField();
		this.inputExpressionsExOne.setPreferredSize(new Dimension(500, 27));
		this.dealExpressionsExOne = new JButton();
		this.resetExOne = new JButton();
		JPanel exerciseOneTop = new JPanel(new FlowLayout());
		exerciseOneTop.add(inputLabelOne);
		exerciseOneTop.add(this.inputExpressionsExOne);
		exerciseOneTop.add(this.dealExpressionsExOne);
		exerciseOneTop.add(this.resetExOne);
		this.goBackExOne = new JButton();
		
		this.panelExerciseOne = new JPanel(new BorderLayout());
		panelExerciseOne.add(exerciseOneTop, BorderLayout.NORTH);
		panelExerciseOne.add(goBackExOne,BorderLayout.SOUTH);
		
		
		//Exercise 2
		JLabel inputLabelTwo = new JLabel("Input : ");
		this.inputExpressionsExTwo = new JTextField();
		this.inputExpressionsExTwo.setPreferredSize(new Dimension(500, 27));
		this.dealExpressionsExTwo = new JButton();
		this.resetExTwo = new JButton();
		JPanel exerciseTwoTop = new JPanel(new FlowLayout());
		exerciseTwoTop.add(inputLabelTwo);
		exerciseTwoTop.add(this.inputExpressionsExTwo);
		exerciseTwoTop.add(this.dealExpressionsExTwo);
		exerciseTwoTop.add(this.resetExTwo);
		this.goBackExTwo = new JButton();
		
		this.panelExerciseTwo = new JPanel(new BorderLayout());
		panelExerciseTwo.add(exerciseTwoTop, BorderLayout.NORTH);
		panelExerciseTwo.add(goBackExTwo,BorderLayout.SOUTH);
		
		
		//Exercise 3
		JLabel inputLabelThree = new JLabel("Input : ");
		this.inputExpressionsExThree = new JTextField();
		this.inputExpressionsExThree.setPreferredSize(new Dimension(500, 27));
		this.dealExpressionsExThree = new JButton();
		this.resetExThree = new JButton();
		this.modeOneStep = new JRadioButton("One Step");
		this.modeDepthFirst = new JRadioButton("Depth-First");
		this.modeBreadthFirst = new JRadioButton("Breadth-First");
		this.modeSelector = new ButtonGroup();
		this.modeSelector.add(this.modeOneStep);
		this.modeSelector.add(this.modeDepthFirst);
		this.modeSelector.add(this.modeBreadthFirst);
		this.modeSelector.setSelected(this.modeOneStep.getModel(), true);
		this.nextStep = new JButton();
		JPanel exerciseThreeTop = new JPanel(new FlowLayout());
		exerciseThreeTop.add(inputLabelThree);
		exerciseThreeTop.add(this.inputExpressionsExThree);
		exerciseThreeTop.add(this.dealExpressionsExThree);
		exerciseThreeTop.add(this.resetExThree);
		exerciseThreeTop.add(this.modeOneStep);
		exerciseThreeTop.add(this.modeDepthFirst);
		exerciseThreeTop.add(this.modeBreadthFirst);
		exerciseThreeTop.add(this.nextStep);
		this.goBackExThree = new JButton();
				
		this.panelExerciseThree = new JPanel(new BorderLayout());
		panelExerciseThree.add(exerciseThreeTop, BorderLayout.NORTH);
		panelExerciseThree.add(goBackExThree,BorderLayout.SOUTH);
		
		
		//Exercise 4
		JLabel inputLabelFour = new JLabel("Input : ");
		this.inputExpressionsExFour = new JTextField();
		this.inputExpressionsExFour.setPreferredSize(new Dimension(500, 27));
		this.dealExpressionsExFour = new JButton();
		this.resetExFour = new JButton();
		this.undo = new JButton();
		JPanel exerciseFourTop = new JPanel(new FlowLayout());
		exerciseFourTop.add(inputLabelFour);
		exerciseFourTop.add(this.inputExpressionsExFour);
		exerciseFourTop.add(this.dealExpressionsExFour);
		exerciseFourTop.add(this.resetExFour);
		exerciseFourTop.add(this.undo);
		this.goBackExFour = new JButton();
				
		this.panelExerciseFour = new JPanel(new BorderLayout());
		panelExerciseFour.add(exerciseFourTop, BorderLayout.NORTH);
		panelExerciseFour.add(goBackExFour,BorderLayout.SOUTH);
		
		
		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(this.main, BorderLayout.CENTER);
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
}