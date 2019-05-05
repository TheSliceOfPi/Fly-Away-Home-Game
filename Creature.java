import java.awt.image.BufferedImage;

public abstract class Creature
{
    protected FlyWorld world;
    protected GridLocation location;
    protected BufferedImage image;
    
    public abstract boolean isPredator();

     /**
     * @return GridLocation the location of the Creature
     */
    public GridLocation getLocation()
    {
	return location;
    }

     /**
     * @return BufferedImage the image of the Creature
     */
   public BufferedImage getImage()
    {
	return image;
	}
    
    
}
