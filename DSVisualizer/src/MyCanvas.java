import javax.swing.*;
import java.awt.*;
import java.util.*;

public class MyCanvas extends Canvas {

    private Graphics2D graphic;
    private Image canvasImage;
    private ArrayList<NodeDesigner> objects;
	
	public MyCanvas(int width, int height){
    	setBackground (Color.BLUE); //just to know where is the limits
        setSize(width, height);
        objects = new ArrayList<NodeDesigner>();  
	}
	
	public void paint (Graphics g) {
         
    }

    public MyCanvas getCanvas()
    {
        return this;
    }

    /**
     * Set the canvas visibility and brings canvas to the front of screen
     * when made visible. This method can also be used to bring an already
     * visible canvas to the front of other windows.
     * @param visible  boolean value representing the desired visibility of
     * the canvas (true or false) 
     */
    public void setVisible(boolean visible)
    {
        if(graphic == null) {
            Dimension size = this.getSize();
            canvasImage = this.createImage(size.width, size.height);
            graphic = (Graphics2D)canvasImage.getGraphics();
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.black);
        }
    }

    public void draw(NodeDesigner referenceObject)
    {
    	objects.remove(referenceObject);   // just in case it was already there
    	objects.add(referenceObject);      // add at the end
        redraw();
    }
 
    /**
     * Erase a given shape's from the screen.
     * @param  referenceObject  the shape object to be erased 
     */
    public void erase(Object referenceObject)
    {
    	objects.remove(referenceObject);   // just in case it was already there
        redraw();
    }

    /**
     * Set the foreground color of the Canvas.
     * @param  newColor   the new color for the foreground of the Canvas 
     */
    public void setForegroundColor(String colorString)
    {
        if(colorString.equals("red")) {
            graphic.setColor(Color.red);
        }
        else if(colorString.equals("black")) {
            graphic.setColor(Color.black);
        }
        else if(colorString.equals("blue")) {
            graphic.setColor(Color.blue);
        }
        else if(colorString.equals("yellow")) {
            graphic.setColor(Color.yellow);
        }
        else if(colorString.equals("green")) {
            graphic.setColor(Color.green);
        }
        else if(colorString.equals("magenta")) {
            graphic.setColor(Color.magenta);
        }
        else if(colorString.equals("white")) {
            graphic.setColor(Color.white);
        }
        else {
            graphic.setColor(Color.black);
        }
    }

    /**
     * Wait for a specified number of milliseconds before finishing.
     * This provides an easy way to specify a small delay which can be
     * used when producing animations.
     * @param  milliseconds  the number 
     */
    public void wait(int milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        } 
        catch (Exception e)
        {
            // ignoring exception at the moment
        }
    }

    /**
     * Redraw ell shapes currently on the Canvas.
     */
    private void redraw()
    {
        erase();
        for(NodeDesigner shape : objects) {
            shape.draw();
        }
        this.repaint();
    }
       
    /**
     * Erase the whole canvas. (Does not repaint.)
     */
    public void erase()
    {
        Color original = graphic.getColor();
        Dimension size = this.getSize();
        graphic.fill(new Rectangle(0, 0, size.width, size.height));
        graphic.setColor(original);
    }

}
