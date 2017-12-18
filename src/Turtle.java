
/**
 * Class for the dangerous turtle object.
 * 
 * @author (Martin Baroody) 
 * @version (2014-11-19)
 */
public class Turtle extends Platform
{
    public Turtle(int x1, int y1) //I will be making an array of these objects thus their position will vary.
    {
        super(100, 50, "Graphics/turtle.png", 5, 1, true, x1, y1);
    }
}
