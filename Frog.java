import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Random;

/**
 * Handles display, movement, and fly eating capabalities for frogs
 */
public class Frog extends Predator
{
    private static final String imgFile = "frog.jpg";

    /**
     * Creates a new Frog object.<br>
     * The image file for a frog is frog.jpg<br>
     *
     * @param loc a GridLocation
     * @param fw the FlyWorld the frog is in
     */
    public Frog(GridLocation loc, FlyWorld fw)
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
     * for a frog.<br>
     * You should select all possible grid locations from<br>
     * the <strong>world</strong> based on the following restrictions<br>
     * Frogs can move one space in any of the four cardinal directions but<br>
     * 1. Can not move off the grid<br>
     * 2. Can not move onto a square that already has frog on it<br>
     * GridLocation has a method to help you determine if there is a frog<br>
     * on a location or not.<br>
     *
     * @return ArrayList<GridLocation> a list of legal grid locations from<br>
     * the <strong>world</strong> that the frog can move to
     */
    @Override
    public ArrayList<GridLocation> generateLegalMoves()
    {
        ArrayList <GridLocation> generateLegalMoves=new ArrayList<GridLocation>();

        if (world.isValidLoc(location.getRow()+1,location.getColumn()))
		    {
			GridLocation nRow1= world.getLocation(location.getRow()+1,location.getColumn());
		       	if ( nRow1.hasPredator()==false)
			    {
				generateLegalMoves.add(nRow1);
			    }
		    }
	if (world.isValidLoc(location.getRow()-1,location.getColumn()))
		    {
			GridLocation nRow2= world.getLocation(location.getRow()-1,location.getColumn());
		       	if ( nRow2.hasPredator()==false)
			    {
				generateLegalMoves.add(nRow2);
			    }
		    }
	if (world.isValidLoc(location.getRow(),location.getColumn()+1))
		    {
			GridLocation nCol1= world.getLocation(location.getRow(),location.getColumn()+1);
			if ( nCol1.hasPredator()==false)
			    {
				generateLegalMoves.add(nCol1);
			    }
		    }
		    
	if (world.isValidLoc(location.getRow(),location.getColumn()-1))
		    {
			GridLocation nCol2= world.getLocation(location.getRow(),location.getColumn()-1);
			if ( nCol2.hasPredator()==false)
			    {
				generateLegalMoves.add(nCol2);
			    }
		    }


	return generateLegalMoves; 
    }

    /**
     * This method helps determine if a frog is in a location<br>
     * where it can eat a fly or not. A frog can eat the fly if it<br>
     * is on the same square as the fly or 1 spaces away in<br>
     * one of the cardinal directions
     *
     * @return boolean true if the fly can be eaten, false otherwise
     */ 
    public boolean eatsFly()
    {
	
	if (generateLegalMoves().indexOf(world.getFlyLocation()) != -1 || location.equals(world.getFlyLocation()))
	    {
		return true;
	    }
	else
	    {
		return false;
	    } 
    }   
}
