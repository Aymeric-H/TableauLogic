package View;
import Model.*;
import Model.Set;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import structure5.*;


/*
 * A Viewer for Binary Trees.
 */
public class BinaryTreeView<E> extends JPanel {

    /* The tree currently being display */
    protected Formula formula;

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
    public BinaryTreeView(Formula formula, int width, int height, int x, int y) {

        //Initialize drawing colors, border, opacity.
        setBackground(Color.white);
        setForeground(Color.black);

        // Create window and make it so hitting the close icon
        // will terminate the program
        JFrame f = new JFrame("Formula as a Tree View");
        f.setLocation(x, y);
        f.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
        
        f.getContentPane().add(this, BorderLayout.CENTER);
        f.setSize(new Dimension(width, height));
        f.setVisible(true);
        
        // install initial tree.
        setTree(formula);
    }
    
    public BinaryTreeView(Formula formula){
    	//Initialize drawing colors, border, opacity.
        setBackground(Color.white);
        setForeground(Color.black);
        // install initial tree.
        setTree(formula);
    }

    /*
     * Set the display to show the given tree.
     */ 
    public void setTree(Formula formula) {
        this.formula = formula;
        maxHeight = formula.height();
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
                            int y, int yStep, Formula formula) {
    	
    	String s = formula.getName();
    	
    	if (formula instanceof Literal && !formula.getValue()) {
			s = "~" + s;
		}
    	
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        int width = fm.stringWidth(s);
        int height = fm.getHeight();

        int xSep = Math.min((maxX - minX)/8, 10);

        g.drawString(s, (minX + maxX)/2 - width/2, y + yStep/2);
        if (formula instanceof TwoFormulasOp) {
        	if (((TwoFormulasOp) formula).getFormulaOne() != null) {
        		// if left tree not empty, draw line to it and recursively
        		// draw that tree
        		g.drawLine((minX + maxX)/2 - xSep, y + yStep/2 + 5,
        				(minX + (minX + maxX)/2) / 2, 
        				y + yStep + yStep/2 - height);
        		drawTree(g, minX, (minX + maxX)/2, y + yStep, yStep, ((TwoFormulasOp) formula).getFormulaOne());
            }
            if (((TwoFormulasOp) formula).getFormulaTwo() != null) {
            	// same thing for right subtree.
                g.drawLine((minX + maxX)/2 + xSep, y + yStep/2 + 5,
                		(maxX + (minX + maxX)/2) / 2, 
                        y + yStep + yStep/2 - height);
                drawTree(g, (minX + maxX)/2, maxX, y + yStep, yStep, ((TwoFormulasOp) formula).getFormulaTwo());
            }
		}
        else if (formula instanceof OneFormulaOp) {
			if (((OneFormulaOp) formula).getFormula() != null) {
				g.drawLine((minX + maxX)/2, y + yStep/2 + 5,
                        ((minX + maxX)/2), 
                        y + yStep + yStep/2 - height);
				drawTree(g, minX, maxX, y + yStep, yStep, ((OneFormulaOp) formula).getFormula());
			}
		}
        
    }


    /*
     * Paint method unherited from the Swing library. Just
     * calls drawTree whenever the window needs to be repainted.
     */
    protected void paintComponent(Graphics g) {
    	//clears the background
    	super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        maxHeight = Math.max(formula.height(), maxHeight);
        int treeHeight = maxHeight;

        drawTree(g, 0, width, 0, height / (3 * treeHeight), formula);

    }
}