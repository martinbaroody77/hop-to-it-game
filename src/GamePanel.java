import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * Test for platform movement.
 * 
 * @author (Martin Baroody and Jonathan Chow) 
 * @version (November 1st 2014)
 */
public class GamePanel implements ActionListener
{
    private Timer timer; 
    private Platform[] platforms; //Stores all the platforms that will be in the game.
    private Checkpoint[] checkpoints; /*There will be only 2 checkpoints; 1 where the frog starts and another where the frog is 
    supposed to finish.*/
    private int screenWidth, screenHeight;
    private GraphicsPanel display; //Graphics stuff, allows the game and the graphics to interact.
    private JFrame mainFrame; //Makes graphics things easier to manage.  
    private HopToIt hopToIt; //Allows GamePanel to interact with HopToIt.
    private boolean checkingCollision;
   
    public void actionPerformed(ActionEvent e){ //This is what will happen with every tick of the timer.
      if (! checkingCollision) /*The reason checkingCollsion exists is because there used to be an error in which if the frog died
      at a level higher than 1, then the JOptionPane was displayed multiple times.  I realized this was because the timer was still 
      running in the background of the option pane, so now, the timer will only run when checkingCollsion is false.  More on this
      later.*/
      {
          checkingCollision = true;
          for(int i = 0; i < platforms.length; i++){
              platforms[i].advance(); //Advances all the platforms, one by one.
          }
        
          display.repaint(); //Repaints everything after these modifications have been made.  
          
          hopToIt.collision(); /*Checks if a collision occurred.  Also, when you lose, this happens in collsion(), so the JOptionPane
          pops up here.  checkingCollsion is still true at this point and the timer will only restart after checkingCollsion is false.
          As a result, this allows the JOptionPane to run without the timer and prevents the aforementioned bug from occurring. */
          checkingCollision = false; 
      }
    }
    
    public GamePanel(int scrWidth, int scrHeight, JFrame frame){
        hopToIt = new HopToIt();
        mainFrame = frame;
        display = null;
        screenWidth = scrWidth;
        screenHeight = scrHeight;
        checkingCollision = false;
        
        platforms = new Platform[132];
        checkpoints = new Checkpoint[2];
        
        //Row 1
        checkpoints[0] = new Checkpoint(0, 650); /*Puts the first checkpoint at the bottom of the screen.  This is where the frog will
        start.*/
        
        int count = 0;
        //The following algorithm initializes all the platforms.  
        for(int i = 0; i < 132; i += 22){
            //Row Right
            platforms[i+0] = new Log(0, (600-(count*100)));
            platforms[i+1] = new Log(150, (600-(count*100)));
            platforms[i+2] = new Water2(300, (600-(count*100)));
            platforms[i+3] = new Water2(350, (600-(count*100)));
            platforms[i+4] = new Water2(400, (600-(count*100)));
            platforms[i+5] = new Log(450, (600-(count*100)));
            platforms[i+6] = new Turtle(600, (600-(count*100)));
            platforms[i+7] = new Water2(700, (600-(count*100)));
            platforms[i+8] = new Log(750, (600-(count*100)));
            platforms[i+9] = new Log(900, (600-(count*100)));
            
            //Row Left
            platforms[i+10] = new Lilypad(0, (550-(count*100)));
            platforms[i+11] = new Lilypad(100, (550-(count*100)));
            platforms[i+12] = new Lilypad(200, (550-(count*100)));
            platforms[i+13] = new Water1(300, (550-(count*100)));
            platforms[i+14] = new Crocodile(350, (550-(count*100)));
            platforms[i+15] = new Lilypad(500, (550-(count*100)));
            platforms[i+16] = new Water1(600, (550-(count*100)));
            platforms[i+17] = new Water1(650, (550-(count*100)));
            platforms[i+18] = new Lilypad(700, (550-(count*100)));
            platforms[i+19] = new Lilypad(800, (550-(count*100)));
            platforms[i+20] = new Lilypad(900, (550-(count*100)));
            platforms[i+21] = new Water1(1000, (550-(count*100)));
            
            count++; //This effectively increases the row number every time.  
        }
        
        //Row 4
        checkpoints[1] = new Checkpoint(0, 0); //This is the checkpoint where the frog is supposed to end up.  
    }
    
    public void startGame(int ms){
        paintScreen();
        timer = new Timer(ms, this); //Ms is the rate at which the timer ticks in milliseconds.
        timer.start();
    }    
    
    public void draw(Graphics g){ //Draws all the platforms and checkpoints.
        for(int i = 0; i < platforms.length; i++){
            platforms[i].draw(g);
        }
        
        for(int i = 0; i < checkpoints.length; i++){
            checkpoints[i].draw(g);
        }
    }
    
    public Platform[] getPlatform(){
        return platforms; //This will be used in HopToIt, when we're looking for collision detection with a platform.  
    }
    
    public void paintScreen(){ //Graphics stuff.
        if(display != null){
            mainFrame.remove(display);
            mainFrame.setContentPane(new JPanel());
            mainFrame.pack();
            mainFrame.validate();
            mainFrame.repaint();
        }
            
        display = new GraphicsPanel(this);
        mainFrame.setContentPane(display);
        mainFrame.pack();
        mainFrame.validate();
        mainFrame.repaint();
    }
}
