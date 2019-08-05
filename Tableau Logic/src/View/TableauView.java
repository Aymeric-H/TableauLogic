package View;
import Model.*;
import Model.Set;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

import structure5.*;

public class TableauView<E> extends JPanel {
    /* The tree currently being display */
    private Set set;
    private Tableau tab;

    /* The max. height of any tree drawn so far.  This
       is used to avoid the tree jumping around when nodes
       are removed from the tree. */
    protected int maxHeight;

    /* The font for the tree nodes. */
    protected Font font = new Font("Roman", 0, 13);
    
    private Dimension graphicDimension;
    
    private int minX, maxX, minY, maxY;
    
    // We get the characteristics of the screen (for a good Size of the graphic)
    private int panelWidth;
    private int panelHeight;
    // Attribute which allows to translate the graphic if it goes with negative coordinates
    private int space;
    
    /* 
     * Create a new window with the given width and height 
     * that is showing the given tree.
     */
    public TableauView(Tableau tab, int width, int height, int x, int y) {

        //Initialize drawing colors, border, opacity.
        setBackground(Color.white);
        setForeground(Color.black);

        // Create window and make it so hitting the close icon
        // will terminate the program
        JFrame f = new JFrame("Set of expressions as a Tableau");
        f.setLocation(x, y);
        f.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
        //this.setLayout(new BorderLayout());
        //this.setPreferredSize(new Dimension(100, 100));
        JScrollPane scrollPane = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        f.getContentPane().add(scrollPane, BorderLayout.CENTER);
        f.setSize(new Dimension(width, height));
        f.setVisible(true);
        
        // install initial tree.
        setTree(tab.getRoot());
        this.tab = tab;
        this.minX = 0;
        this.maxX = 0;
        this.minY = 0;
        this.maxY = 0;
        this.panelHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.panelWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        this.space = 0;
    }
    
    public TableauView(Tableau tab){
    	//Initialize drawing colors, border, opacity.
        setBackground(Color.white);
        setForeground(Color.black);
        // install initial tree.
        setTree(tab.getRoot());
        this.tab = tab;
        this.minX = 0;
        this.maxX = 0;
        this.minY = 0;
        this.maxY = 0;
        this.panelHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.panelWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        this.space = 0;
    }
    
    /*
     * Set the display to show the given tree.
     */ 
    public void setTree(Set set) {
        this.set = set;
        maxHeight = set.getHeight();
    }

    /*
     * Invoke this method whenever you would like the window
     * to be refreshed, such as after updating the tree in some
     * way.
     */
    public void refresh() {
        paintImmediately(0,0, getWidth(), getHeight());
    }

    /*
     * Draw the contents of the tree into the given Graphics
     * context.
     * It will place all info between minX and maxX in the x-direction,
     * starting at location y in the y-direction.  Levels of the tree
     * will be separated by yStep pixels.
     */
    protected void drawTree(Graphics g, int minX, int maxX, 
                            int y, int yStep, Set set) {
    	
    	
    	String s = set.toString();
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        int width = fm.stringWidth(s);
        int height = fm.getHeight();

        int xSep = 0;//Math.min((maxX - minX)/8, 10);
        
        if (set.getContradiction()) {
			g.setColor(Color.RED);
		}
        
        if (y + yStep + height > this.maxY) {
			this.maxY = y + yStep + height;
		}
        if ((minX + maxX)/2 - width/2 - 10 < this.minX) {
			this.minX = (minX + maxX)/2 - width/2 - 10;	        
		}
        if ((minX + maxX)/2 + width/2 > this.maxX) {
			this.maxX = (minX + maxX)/2 + width/2;
		}
        if (this.minX < 0) {
        	if (this.space < Math.abs(this.minX)) {
        		//g.translate(Math.abs(this.minX) - space, 0);
			}
        	this.space = Math.abs(this.minX);
		}
        // We draw the current Set of formulas and recursively the rest of the Tableau
        g.drawString(s, (minX + maxX)/2 - width/2 + space, y + yStep/1);
        
        // Case where we have two children for the current Set
        if (set.getFirst() != null && set.getSecond() != null) {
        	if (set.getFirst().getContradiction()) {
				g.setColor(Color.RED);
			}
        	// If the left tree isn't empty, it draws the line and recursively
        	// draws the tree
        	g.drawLine((minX + maxX)/2 - xSep + space, y + yStep/1 + 5,
        			(minX + (minX + maxX - width)/2) / 2 + space, 
        			y + yStep + yStep/1 - height);
        	drawTree(g, minX, (minX + maxX - width)/2, y + yStep, yStep,set.getFirst());
        	g.setColor(Color.BLACK);
        	if (set.getSecond().getContradiction()) {
				g.setColor(Color.RED);
			}
            // same thing for right subtree.
            g.drawLine((minX + maxX)/2 + xSep + space, y + yStep/1 + 5,
            		(maxX + (minX + maxX + width)/2) / 2 + space, 
               		y + yStep + yStep/1 - height);
            drawTree(g, (minX + maxX + width)/2, maxX, y + yStep, yStep, set.getSecond());
            g.setColor(Color.BLACK);
		}
        // Case where we have only one child
        else if (set.getFirst() != null) {
        	if (set.getFirst().contradiction()) {
				g.setColor(Color.RED);
			}
			g.drawLine((minX + maxX)/2 + space, y + yStep/1 + 5,
					((minX + maxX)/2) + space, 
                    y + yStep + yStep/1 - height);
			drawTree(g, minX, maxX, y + yStep, yStep, set.getFirst());
			g.setColor(Color.BLACK);
		}
        // This part of the code allows us to collect the coordinates of the leaves
        // so that we can stock them and deal with the user interactions with the graphic
        else{
        	Rectangle2D setBounds = g.getFontMetrics().getStringBounds(s, g);
        	int x1 = (minX + maxX)/2 - width/2 + space;
        	int y1 = y + yStep/1 + (int) setBounds.getY();
        	int x2 = (int) (x1 + setBounds.getWidth()) + space - 10;
        	int y2 = (int) (y1 + setBounds.getHeight());
        	this.tab.addAccessibleSet(new Coordinates(x1, y1, x2, y2), set);
        	g.drawRect(x1 - 1, y1, (int)setBounds.getWidth(), (int) setBounds.getHeight() + 1);
        }
        
    }

    /*
     * Paint method unherited from the Swing library. Just
     * calls drawTree whenever the window needs to be repainted.
     */
    protected void paintComponent(Graphics g) {
    	//clears the background
    	super.paintComponent(g);
        int width = getWidth()*100/95;
        int height = getHeight()*95/100;
        maxHeight = Math.max(set.getHeight(), maxHeight);
        int treeHeight = maxHeight;
        
        // Height => The attribute to change when we want to modify the starting
        // height of the Tableau on the Graphic
        drawTree(g, 0, this.panelWidth, 0, this.panelHeight/10, set);

    }
    
    /*
     * (non-Javadoc)
     * @see javax.swing.JComponent#getPreferredSize()
     * Function which allows to get the size of the Graphic so that we can handle
     * its scrollable characteristic
     */
    @Override
    public Dimension getPreferredSize(){
    	return new Dimension(this.maxX - this.minX + this.space, this.maxY - this.minY);
    }
    
}
