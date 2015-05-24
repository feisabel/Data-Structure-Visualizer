import java.awt.*;
import java.awt.geom.*;
public class NodeDesigner {

    private int diameter;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;
    
    /**
     * Create a new circle at default position with default color.
     */
    public NodeDesigner()
    {
        diameter = 30;
        xPosition = 20;
        yPosition = 60;
        color = "blue";
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
        erase();
        isVisible = false;
    }
        /**
     * Move the circle horizontally by 'distance' pixels.
     */
    public void moveHorizontal(int distance)
    {
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the circle vertically by 'distance' pixels.
     */
    public void moveVertical(int distance)
    {
        erase();
        yPosition += distance;
        draw();
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
	    	erase();
	        diameter = newDiameter;
	        draw();
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
            MyCanvas canvas = MyCanvas.getCanvas();
            canvas.draw(this, color, new Ellipse2D.Double(xPosition, yPosition, 
                                                          diameter, diameter));
            canvas.wait(10);
            try {
				canvas.wait(10);
			} catch (Exception e) { //ECLIPSE QUEM FEZ ISSO, SÓ ACEITEI NA VIDA
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    /**
     * Erase the circle on screen.
     */
    private void erase()
    {
        if(isVisible) {
            MyCanvas canvas = MyCanvas.getCanvas();
            canvas.erase();
        }
    }
}
