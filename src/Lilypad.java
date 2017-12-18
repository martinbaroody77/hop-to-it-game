
/**
 * Class for lilypad.
 * 
 * @author (Martin Baroody) 
 * @version (2014-11-18)
 */
public class Lilypad extends Platform
{
    public Lilypad(int x1, int y1) //Since there will be an array of these platforms the x and y coordinates will vary.
    {
        super(100, 50, "Graphics/lilypad.png", 5, -1, false, x1, y1);     
    }
}
