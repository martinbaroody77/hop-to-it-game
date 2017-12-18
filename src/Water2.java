
/**
 * Water object that moves to the right.
 * 
 * @author (Martin Baroody) //Jonathan Chow had to fix an error in this class
 * @version (2014-11-18)
 */
public class Water2 extends Platform
{
    public Water2(int x1, int y1) //There will be an array of Water2 objects therefore the position will vary.
    {
        super(50, 50, "Graphics/water.png", 5, 1, true, x1, y1);     
    }
}
