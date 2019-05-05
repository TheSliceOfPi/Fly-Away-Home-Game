import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Contains several methods that aid in the<br>
 * display and movement of Mosca
 */
public class Fly extends Creature
{
    private static final String imgFile = "fly.jpg";
    

    
    /**
     * Creates a new Fly object.<br>
     * The image file for a fly is fly.jpg<br>
     *
     * @param loc a GridLocation
     * @param fw the FlyWorld the fly is in
     */
    public Fly(GridLocation location, FlyWorld world)
    {
        super();
	this.location=location;
	this.world=world;

	// Sets the image instance variable
	try
	{
	    
	    image = ImageIO.read(new File(imgFile));
	}
	catch (IOException ioe)
	{
	    System.out.println("Unable to read image file: " + imgFile);
	    System.exit(0);
	}

	// Puts the fly on the GridLocation so image displays
	location.setCreature(this);
    }


    /**
     * @return boolean, always false, Mosca is not a predator
     */
    @Override
    public boolean isPredator()
    {
	return false;
    }
    
    /**
     * This method <strong>updates</strong> the fly's location in<br>
     * the <strong>world</strong><br>
     * The fly can move in one of the four cardinal (N, S, E, W) directions.<br>
     * You need to make sure that the <strong>updated</strong> location<br>
     * is within the bounds of the world before<br>
     * changing the location of the fly<br>
     *
     * @param direction one of the four cardinal directions<br>
     *        Given as constants in FlyWorldGUI<br><br>
     *        They are:<br>
     *        FlyWorldGUI.NORTH<br>
     *        FlyWorldGUI.SOUTH<br>
     *        FlyWorldGUI.EAST<br>
     *        FlyWorldGUI.WEST<br>
     */
    public void update(int direction)
    {
	//GridLocation moved;
	int i=0;
	int j=0;
	if (direction==FlyWorldGUI.NORTH)
	    {
		i=-1;
		
	    }
	else if (direction==FlyWorldGUI.SOUTH)
	    {
		i=1;
	    }
	else if (direction==FlyWorldGUI.EAST)
	    {
		j=1;
	    }
	else
	    {
		j=-1;
	    }

	if (world.isValidLoc(location.getRow()+i,location.getColumn()+j))
	    {
	       location.removeCreature();
	       location= world.getLocation(location.getRow()+i,location.getColumn()+j);
	      
	       location.setCreature(this);
	    }
        
    }
}
