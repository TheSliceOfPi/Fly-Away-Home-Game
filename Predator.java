import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public abstract class Predator extends Creature
{
    public Predator()
    {
	super();
    }
    
    public abstract ArrayList<GridLocation> generateLegalMoves();
    public abstract boolean eatsFly();

    /**
     * This method updates the Predator's position.<br>
     * It should randomly select one of the legal locations(if there any)<br>
     * and set the Predator's location to the chosen updated location.
     */
    public void update()
    {
	Random rand= new Random();
	if (generateLegalMoves().isEmpty()==false)
	    {
		location.removeCreature();
		int pos=rand.nextInt(generateLegalMoves().size());
	        location=generateLegalMoves().get(pos);
		location.setCreature(this);
	    }
    }
    
    /**
     * @return boolean, always true
     */
    
    public boolean isPredator()
    {
	return true;
    }
}
