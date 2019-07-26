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
    protected Font font = new Font("Roman", 0, 14);
    
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
        this.setPreferredSize(new Dimension(100, 100));
        JScrollPane scrollPane = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        f.getContentPane().add(scrollPane, BorderLayout.CENTER);
        f.setSize(new Dimension(width, height));
        f.setVisible(true);
        
        // install initial tree.
        setTree(tab.getRoot());
        this.tab = tab;
    }
    
    public TableauView(Tableau tab){
    	//Initialize drawing colors, border, opacity.
        setBackground(Color.white);
        setForeground(Color.black);
        // install initial tree.
        setTree(tab.getRoot());
        this.tab = tab;
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
        
        //g.setColor(Color.BLUE);
        
        if (set.getContradiction()) {
			g.setColor(Color.RED);
		}
        
        g.drawString(s, (minX + maxX)/2 - width/2, y + yStep/1);
        if (set.getFirst() != null && set.getSecond() != null) {
        	if (set.getFirst().getContradiction()) {
				g.setColor(Color.RED);
			}
        	// if left tree not empty, draw line to it and recursively
        	// draw that tree
        	g.drawLine((minX + maxX)/2 - xSep, y + yStep/1 + 5,
        			(minX + (minX + maxX - s.length()*2)/2) / 2, 
        			y + yStep + yStep/1 - height);
        	drawTree(g, minX, (minX + maxX - s.length()*2)/2, y + yStep, yStep,set.getFirst());
        	g.setColor(Color.BLACK);
        	if (set.getSecond().getContradiction()) {
				g.setColor(Color.RED);
			}
            // same thing for right subtree.
            g.drawLine((minX + maxX)/2 + xSep, y + yStep/1 + 5,
            		(maxX + (minX + maxX + s.length()*2)/2) / 2, 
               		y + yStep + yStep/1 - height);
            drawTree(g, (minX + maxX + s.length()*2)/2, maxX, y + yStep, yStep, set.getSecond());
            g.setColor(Color.BLACK);
		}
        else if (set.getFirst() != null) {
        	if (set.getFirst().contradiction()) {
				g.setColor(Color.RED);
			}
			g.drawLine((minX + maxX)/2, y + yStep/1 + 5,
					((minX + maxX)/2), 
                    y + yStep + yStep/1 - height);
			drawTree(g, minX, maxX, y + yStep, yStep, set.getFirst());
			g.setColor(Color.BLACK);
		}
        else{
        	Rectangle2D setBounds = g.getFontMetrics().getStringBounds(s, g);
        	int x1 = (minX + maxX)/2 - width/2;
        	int y1 = y + yStep/1 + (int) setBounds.getY();
        	int x2 = (int) (x1 + setBounds.getWidth());
        	int y2 = (int) (y1 + setBounds.getHeight());
        	this.tab.addAccessibleSet(new Coordinates(x1, y1, x2, y2), set);
        	//System.out.println(new Coordinates(x1, y1, x2, y2));
        	g.drawRect(x1, y1, (int)setBounds.getWidth(), (int) setBounds.getHeight());
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
        
        //8 => The param to change the height of the graph
         drawTree(g, 0, width, 0, height / (8 * treeHeight), set);

    }
    
}
