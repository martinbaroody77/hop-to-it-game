
/**
 * A log object.  Inherits from platform.
 * 
 * @author (Martin Baroody) 
 * @version (2014-11-18)
 */
public class Log extends Platform
{
    public Log(int x1, int y1) //I will make an array of platforms therefore the x and y coordinates will vary
    {
        super(150, 50, "Graphics/log.png", 5, 1, false, x1, y1);       
    }
}
