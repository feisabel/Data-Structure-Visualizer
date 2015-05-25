import java.awt.*;
import java.awt.geom.*;

public class NodeDesigner {
	private MyCanvas canvas;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;
    
    /**
     * Create a new circle at default position with default color.
     */
    public NodeDesigner(MyCanvas canvas)
    {
        this.canvas = canvas;
    	diameter = 30;
        xPosition = 0;
        yPosition = 0;
        color = "white";
        isVisible = false;
    }

    /**
     * Make this circle visible. If it was already visible, do nothing.
     */
    public void makeVisible()
    {
        isVisible = true;
        draw();
    }
    
    /**
     * Make this circle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible()
    {
        canvas.erase();
        isVisible = false;
    }
        /**
     * Move the circle horizontally by 'distance' pixels.
     */
    public void moveHorizontal(int distance)
    {
        canvas.erase();
        xPosition += distance;

    }

    /**
     * Move the circle vertically by 'distance' pixels.
     */
    public void moveVertical(int distance)
    {
        canvas.erase();
        yPosition += distance;

    }

    /**
     * Slowly move the circle horizontally by 'distance' pixels.
     */
    /**
     * Change the size to the new size (in pixels). Size must be >= 0.
     */
    public void setSize(int newDiameter)
    {
        if(newDiameter > 0 ){
	    	canvas.erase();
	        diameter = newDiameter;
        }
    }

    /**
     * Change the color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor)
    {
        color = newColor;
        draw();
    }

    /**
     * Draw the circle with current specifications on screen.
     */
    public void draw()
    {
        if(isVisible) {
            canvas.draw(this);
            canvas.wait(10);
            try {
				canvas.wait(10);
			} catch (Exception e) { //ECLIPSE QUE FEZ ISSO, SÓ ACEITEI NA VIDA
				e.printStackTrace();
			}
        }
    }
}
