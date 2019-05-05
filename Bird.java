import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Random;

public class Bird extends Predator
{
    private static final String imgFile = "bird.jpg";
    public Bird(GridLocation loc, FlyWorld fw)
    {
        super();   
	this.location=loc;
	this.world=fw;

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
     * Generates a list of <strong>ALL</strong> possible legal moves<br>
     * for a Bird.<br>
     * You should select all possible grid locations from<br>
     * the <strong>world</strong> based on the following restrictions<br>
     * Bird can fly to any  space in world but<br>
     * 1. Can not move off the grid<br>
     * 2. Can not move onto a square that already has a Predator on it<br>
     * 
     *
     * @return ArrayList<GridLocation> a list of legal grid locations from<br>
     * the <strong>world</strong> that the bird can move to
     */

    public ArrayList<GridLocation> generateLegalMoves()
    {
        ArrayList <GridLocation> generateLegalMoves=new ArrayList<GridLocation>();
	for (int i=0;i<world.getNumRows();i++)
	    {
		for(int j=0;j<world.getNumCols();j++)
		    {
			if(world.isValidLoc(i,j) && world.getLocation(i,j).hasPredator()==false) //Any GridLocation without a Predator
			    {
				generateLegalMoves.add(world.getLocation(i,j));
			    }
		    }
	    }
	
	return generateLegalMoves; 
    }
    /**
     * This method helps determine if a bird is in a location<br>
     * where it can eat a fly or not. A bird can eat the fly if it<br>
     * is on the same square.<br>
     * 
     *
     * @return boolean true if the fly can be eaten, false otherwise
     */ 
    public boolean eatsFly()
    {
        
       	if (location.equals(world.getFlyLocation()))
		    {
			return true;
		    }
	    
	return false;

    }   
}
