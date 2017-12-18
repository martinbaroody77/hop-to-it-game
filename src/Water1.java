
/**
 * A water object that moves to the left.
 * 
 * @author (Martin Baroody) //Jonathan Chow had to fix an error in this class
 * @version (2014-11-18)
 */
public class Water1 extends Platform
{
    public Water1(int x1, int y1) //There will be several of these objects thus their position will vary.
    {
        super(50, 50, "Graphics/water.png", 5, -1, true, x1, y1);     
    }
}
