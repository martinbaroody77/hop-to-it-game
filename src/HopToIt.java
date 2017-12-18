import java.awt.*;
import javax.swing.*;
/**
 * The driver class, puts everything together into a game.
 * 
 * @author (Martin Baroody and Jonathan Chow) 
 * @version (November 1st 2014)
 */
public class HopToIt
{
    //We had to make the instance variables static so that they could be referred to in the main method.  
    static JFrame frame;
    static Frog frog;
    static GamePanel game;
    static Platform[] platform;
    static int interval; //Modifies the interval at which the platforms move.
    static int level; //Holds the current level the user is on.
    static int frogInterval; //Interval at which the frog moves horizontally when it's eiding on a platform.  

    
    public static void main(String[] args){
        frame = new JFrame("HopToIt");
        platform = new Platform[132];
        
        frog = new Frog(); //Thw HopToIt class and the Frog class interact heavily with each other.
        
        
        level = 1;
        interval = 50;
        frogInterval = 5;
        game = new GamePanel(750, 700, frame);
        
        game.startGame(interval);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        frame.add(frog);
        
        frame.pack();
        frame.setResizable(false); //Do not allow re-sizing
        frame.setVisible(true); //Make frame visible
        frame.setLocationRelativeTo(null); //Open in centre of window
    }
    
   
    
    public void collision()
    {   
        //This algorithm checks if a collision has occurred and acts accordingly depending on whether the platform is dangerous or not.
    
        int frogLoc = frog.getYLoc();
        
        if(frogLoc != 650 && frogLoc != 0){ //If the frog isn't at one of the checkpoints.
            if(frogLoc % 100 == 0){ 
                /*We set the rows of platforms so that the ones divisible by 100 go to the right and the ones not divisible 
                by 100 (the ones ending in 50) move to the left).*/
                frog.drift(frogInterval, 1);
            }
            else{
                frog.drift(frogInterval, -1);
            }
        }
        
        if(frog.getXLoc() < -25 || frog.getXLoc() > 725 || frog.getYLoc() > 650){ //If the frog is out of bounds then you lose.
            lose();
            
            frog.reset(); //Resets the frog back to the bottom platform.
            
        }
        
        platform = game.getPlatform(); //The platform array now holds all the game's platforms as initialized in the GamePanel.
        
        //This is the collision detection algorithm.  Determines which platform the frog is on.
        for(int i = 0; i < platform.length; i++){ //Cycles through all the platforms.
            if(frog.getYLoc() == platform[i].getYLoc()){ 
                //Checks first to see if they're on the same yLoc, in which case there's a chance that this is the platform we're looking for.
                if(frog.getXLoc() >= platform[i].getXLoc()){ //Narrows the search down even more.
                    if(frog.getXLoc() <= platform[i].getXLoc()+platform[i].getWidth()){ 
                        //For the frog to be on a platform it has to be enveloped within the x dimensions of the platform.  
                        if(platform[i].getDangerous() == true){ //If the frog is on a dangerous platform it dies.
                            lose();
                            
                            frog.reset();
                            
                            break;
                        }
                    }
                }
            }
        }
        
    }

    public void checkpointReached(){ 
        if(frog.getYLoc() == 0){ //When yLoc is zero it means it reached the upper checkpoint, whose yLoc is 0.
            interval += 10; //Increases platform speed.
            level++;
            frogInterval += 0.5; //Increases the frog's speed when it's pulled along by non-dangerous platforms.
            
            levelUp(); 
            
            game.startGame(interval); //Starts the game at the higher interval.  
            //The frog is reset, reframed, and refocused.
            frog.reset();

            frame.add(frog);
            
            frog.requestFocusInWindow();
        }
    }
    
    public void levelUp(){
        JOptionPane.showMessageDialog(null, "Level Up! You're now on level " + level + "!");
    }
    
    public void lose(){
        JOptionPane.showMessageDialog(null, "You Lose! You got to level " + level + "!");
        
    }
}
