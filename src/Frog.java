import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Test for frog movement.
 * 
 * @author (Jonathan Chow) 
 * @version (October 25th 2014)
 */
public class Frog extends JPanel{
    private final int JUMP;
    private ImageIcon up, down, right, left, currentImage;
    private ImageIcon upJ, downJ, rightJ, leftJ;
    private int x, y;
    private HopToIt hopToIt;
    
    public Frog(){
        setPreferredSize(new Dimension(750, 700));
        setOpaque(false);
        
        addKeyListener(new DirectionListener());
        
        JUMP = 50;
        
        x = 0;
        y = 650;
        
        hopToIt = new HopToIt();
        
        up = new ImageIcon("Graphics/Frog1U.png");
        down = new ImageIcon("Graphics/Frog1D.png");
        left = new ImageIcon("Graphics/Frog1L.png");
        right = new ImageIcon("Graphics/Frog1R.png");
        
        upJ = new ImageIcon("Graphics/Frog2U.png");
        downJ = new ImageIcon("Graphics/Frog2D.png");
        leftJ = new ImageIcon("Graphics/Frog2L.png");
        rightJ = new ImageIcon("Graphics/Frog2R.png");
        
        currentImage = up;
        
        setFocusable(true);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        currentImage.paintIcon(this, g, x, y);
    }

    public int getXLoc(){
        return x;
    }
    
    public int getYLoc(){
        return y;
    }
    
    public void drift(int s, int d){
        if(d == 1){
            x += s;
        }
        else{
            x -=s;
        }
    }
    
    public void reset(){
        x = 0;
        y = 650;
    }
    
    private class DirectionListener implements KeyListener{
        public void keyPressed(KeyEvent event){
            switch(event.getKeyCode()){
                case KeyEvent.VK_UP:
                    currentImage = upJ;
                    y -= JUMP;
                    break;
                case KeyEvent.VK_DOWN:
                    currentImage = downJ;
                    y += JUMP;
                   break;
                case KeyEvent.VK_LEFT:
                    currentImage = leftJ;
                    x -= JUMP;
                    break;
                case KeyEvent.VK_RIGHT:
                    currentImage = rightJ;
                    x += JUMP;
                    break;
            }
            repaint();
            
            hopToIt.checkpointReached();
        }

        public void keyReleased(KeyEvent event){
            switch(event.getKeyCode()){
                case KeyEvent.VK_UP:
                    currentImage = up;
                    break;
                case KeyEvent.VK_DOWN:
                    currentImage = down;
                   break;
                case KeyEvent.VK_LEFT:
                    currentImage = left;
                    break;
                case KeyEvent.VK_RIGHT:
                    currentImage = right;
                    break;
            }
            repaint();
        }
        
        public void keyTyped(KeyEvent event){}
    }
}
