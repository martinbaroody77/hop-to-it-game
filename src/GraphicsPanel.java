import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.*;
/**
 * Displays the actions in Game on the screen.
 * 
 * @author (Martin Baroody and Jonathan Chow) 
 * @version (November 1st 2014)
 */
public class GraphicsPanel extends JPanel
{ 
    private GamePanel game; //Allows the graphics to interact with the basic game setup.
    
    public GraphicsPanel(GamePanel gameVar){ //Sets the background color and preferred size of the panel.  
        game = gameVar;
        
        setBackground(new Color(75, 127, 196));
        setPreferredSize(new Dimension(750, 700));
    }
    
    public void paintComponent(Graphics g){ //Allows things to be drawn.
        super.paintComponent(g);
        
        game.draw(g);
    }
}