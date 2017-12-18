

import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.*;
/**
 * Class of the Checkpoint object, where the Frog must land.
 * 
 * @author (Martin Baroody) 
 * @version (2014-11-18)
 */
public class Checkpoint //I didn't make this a platform because when using polymorphism to move the platforms it wouldn't work for this.
{
    private int width, height;
    private int x, y;
    private BufferedImage img;
    
    public Checkpoint(int x1, int y1)
    {
        width = 750;
        height = 50;
        x = x1; //We will have two Checkpoint objects in the game therefore we must implement a variable, not a fixed value, at the moment.
        y = y1;
        
        File file = new File("Graphics/checkpoint.png");
        try
        {
            img = ImageIO.read(file);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        //There is a similar block in platform, this just stores checkpoint.png as an image.
    }
    
    public void draw(Graphics g)
    {
        g.drawImage(img, x, y, width, height, null); //Draws the checkpoint.
    }
    
    public int getxCoordinate()
    {
        return x; //Returns the x coordinate of the checkpoint.
    }
    
    public int getyCoordinate()
    {
        return y; //Returns the y-coordinate of the checkpoint.
    }    
}
