package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
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
import javax.swing.border.EtchedBorder;

public class View extends JFrame{
	
	// Frame main components
	public JPanel main;
	public JLabel title;
	
	// Main Menu => List of Exercises
	public JPanel listOfExercises;
	public JPanel exOne;
	public JPanel exTwo;
	public JPanel exThree;
	public JPanel exFour;
	public JPanel exFive;
	
	// Exercises => Objects
	public ExerciseOne exerciseOnePanel;
	public ExerciseTwo exerciseTwoPanel;
	public ExerciseThree exerciseThreePanel;
	public ExerciseFour exerciseFourPanel;
	public ExerciseFive exerciseFivePanel;
	
	
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
		this.title = new JLabel("Reasoning based on Booleans");
		title.setFont(new Font("Serif", Font.PLAIN, 35));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		titlePanel.add(title);
		
		
		//Right Top Corner
		JLabel lecturer = new JLabel("Alexander BOLOTOV");
		lecturer.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		JLabel lecture = new JLabel("Level Four Maths");
		lecture.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
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
		rightTopCorner.add(helpPanel);
		rightTopCorner.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		
		//Top Panel		
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setPreferredSize(new Dimension(1000, 180));
		topPanel.add(iconPanel, BorderLayout.LINE_START);
		topPanel.add(titlePanel, BorderLayout.CENTER);
		topPanel.add(rightTopCorner, BorderLayout.LINE_END);
		
		
		// Version with Panels		
		// Initialization of the exercises
		this.exerciseOnePanel = new ExerciseOne();
		this.exerciseTwoPanel = new ExerciseTwo();
		this.exerciseThreePanel = new ExerciseThree();
		this.exerciseFourPanel = new ExerciseFour();
		this.exerciseFivePanel = new ExerciseFive();
		this.listOfExercises = new JPanel(new GridLayout(2,3));
		
		
		// Adding the exercises menu panel to the main menu
		listOfExercises.add(this.exOne = new MenuExercisePanel(this.exerciseOnePanel, "IconExerciseOne.png"));
		listOfExercises.add(this.exTwo = new MenuExercisePanel(this.exerciseTwoPanel, "IconExerciseTwo.png"));
		listOfExercises.add(this.exThree = new MenuExercisePanel(this.exerciseThreePanel, "IconExerciseThree.png"));
		listOfExercises.add(this.exFour = new MenuExercisePanel(this.exerciseFourPanel, "IconExerciseFour.png"));
		listOfExercises.add(new JPanel());
		listOfExercises.add(this.exFive = new MenuExercisePanel(this.exerciseFivePanel, "IconExerciseFive.png"));
		
		
		// Giving them a background color
		Random random = new Random();
		ArrayList<Color> colors = new ArrayList<Color>();
		colors.add(Color.PINK);
		colors.add(Color.CYAN);
		colors.add(Color.YELLOW);
		colors.add(Color.MAGENTA);
		colors.add(new Color(0, 255, 34));
		Color colorOne = colors.get(random.nextInt(colors.size()));
		colors.remove(colorOne);
		Color colorTwo = colors.get(random.nextInt(colors.size()));
		
		this.exOne.setBackground(colorOne);
		this.exTwo.setBackground(colorTwo);
		this.exThree.setBackground(colorOne);
		this.exFour.setBackground(colorTwo);
		this.exFive.setBackground(colorTwo);
		
		
		// Main panel => where the exercises are set
		this.main = new JPanel(new BorderLayout());
		this.main.add(listOfExercises);
		
		
		// Adding the components to the Frame
		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(this.main, BorderLayout.CENTER);
		
		// Frame characteristics
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
}