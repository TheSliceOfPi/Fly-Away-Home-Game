import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Random;

public class Spider extends Predator
{
     private static final String imgFile = "spider.jpg";
    public Spider(GridLocation loc, FlyWorld fw)
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
     * for a Spider.<br>
     * You should select all possible grid locations from<br>
     * the <strong>world</strong> based on the following restrictions<br>
     * Spider can crawl one space in any of  the four cardinal directions<br>
     * towards Fly but
     * 1. Can not move off the grid<br>
     * 2. Can not move onto a square that already has a Predator on it<br>
     * 
     *
     * @return ArrayList<GridLocation> a list of legal grid locations from<br>
     * the <strong>world</strong> that the spider can move to
     */

    public ArrayList<GridLocation> generateLegalMoves()
    {
        ArrayList <GridLocation> generateLegalMoves=new ArrayList<GridLocation>();
	GridLocation moscaLoc=world.getFlyLocation();
	GridLocation moveCol=null; //Initialize default possibilities
	GridLocation moveRow=null;

	//Determines vertical moves to get closer to mosca
	if (location.getColumn()<moscaLoc.getColumn())
	    {
		moveCol=world.getLocation(location.getRow(),location.getColumn()+1);
		if(world.isValidLoc(moveCol.getRow(),moveCol.getColumn()) && moveCol.hasPredator()==false)
		    {  
		        generateLegalMoves.add(moveCol); 
		    }	 
	    }
      	else if (location.getColumn()>moscaLoc.getColumn())
	    {
	        moveCol=world.getLocation(location.getRow(),location.getColumn()-1);	        
		if(world.isValidLoc(moveCol.getRow(),moveCol.getColumn()) && moveCol.hasPredator()==false)
		    {  
		        generateLegalMoves.add(moveCol); 
		    }
	    }

	//Determines horizontal moves to get close to mosca
	if (location.getRow()<moscaLoc.getRow())
	    {
		moveRow=world.getLocation(location.getRow()+1,location.getColumn());
		if(world.isValidLoc(moveRow.getRow(),moveRow.getColumn()) && moveRow.hasPredator()==false)
		    {  
			generateLegalMoves.add(moveRow); 
		    }
	    }
      	else if (location.getRow()>moscaLoc.getRow())
	    {
	        moveRow=world.getLocation(location.getRow()-1,location.getColumn());
		if(world.isValidLoc(moveRow.getRow(),moveRow.getColumn()) && moveRow.hasPredator()==false)
		    {  
			generateLegalMoves.add(moveRow); 
		    }
	    }

		    

        
	    
		
	    
		    
	    
	
	return generateLegalMoves; 
    }
    /**
     * This method helps determine if a spider is in a location<br>
     * where it can eat a fly or not. A spider can eat the fly if it<br>
     * is on the same square as the fly.<br>
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
