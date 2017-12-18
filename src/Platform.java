import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.*;
/**
 * The class with the attributes and images for platforms, both dangerous and non-dangerous.
 * 
 * @author (Martin Baroody and Jonathan Chow) 
 * @version (2014-10-26)
 */
public class Platform
{
    protected int width, height;
    protected BufferedImage platformImg;
    protected int interval; //number of pixels to advance with each time value.
    protected int direction; //either 1 or -1; -1 = left, 1 = right; I plan on multiplying by interval to move image.
    protected boolean dangerous;
    protected int x, y; //position on screen    
    
    public Platform(int widthVar, int heightVar, String imageFileVar, int intervalVar, int directionVar, boolean dangerousVar, int xVar, int yVar)
    {
        width = widthVar;
        height = heightVar;
        interval = intervalVar;
        direction = directionVar;
        dangerous = dangerousVar;
        x = xVar;
        y = yVar;
        
        File file = new File(imageFileVar);
        try
        {
            platformImg = ImageIO.read(file);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        /*This block will get the file which stores the image of the platform.  It checks to see whether a file with the name of ImageFileVar
        exists and if it does it creates a file object and reads the image stored in the file.  Of course, IOException is caught. */
    }
    
    
    public void draw(Graphics g)
    {
        g.drawImage(platformImg, x, y, width, height, null); //This draws the platform on the screen.
    }
    
    public void advance(){
        if(direction == 1){ //If you set direction to 1 then it will move to the right.  
            x += interval;
            if(x >= 900){ 
                /*If it goes past 900 px (which is past the dimensions of the screen, to the right) then it will reset at -150 (which
                is also past the dimensions of the screen, to the left however and the platform keeps moving to the right */
                x = -150;
            }
        }
        else if(direction == -1){ //If you set direction to -1 then it will move to the left.
            x -= interval;
            if(x <= -150){
                /*If the platform goes past -150 (which is past the left boundary of the screen) then instead of continuing to the left it 
                resets at 900 px, which is past the right boundary of the screen, then continues its trajectory to the left. */
                x = 900;
            }
        }
    }

    public int getXLoc(){
        return x; //Returns the x location of the frog.
    }
    
    public int getYLoc(){
        return y; //Returns the y location of the frog.
    }
    
    public boolean getDangerous(){
        return dangerous; /*Returns either true or false.  True means that it is dangerous and can kill the frog and false means that it is a 
        safe platform and if the frog lands on it it will move with the platform instead of dying. */
    }
    
    public int getDirection(){
        return direction; //Returns either 1 or -1, 1 = right and -1 = left.
    }
    
    public int getInterval(){
        return interval; //Returns the interval at which the platform moves with every tick of the timer.
    }
    
    public int getWidth(){
        return width; //Returns the width of the platform, this is necessary for collision detection.
    }
}
