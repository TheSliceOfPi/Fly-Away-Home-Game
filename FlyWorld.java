import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Random;

/**
 * Contains information about the world (i.e., the grid of squares)<br>
 * and handles most of the game play work that is NOT GUI specific
 */
public class FlyWorld
{
    protected int numRows;
    protected int numCols;

    protected GridLocation [][] world;

    protected GridLocation start;
    protected GridLocation goal;

    protected ArrayList<Predator> predators;
    
    protected Fly mosca;

    /**
     * Reads a file containing information about<br>
     * the grid setup.  Initializes the grid<br>
     * and other instance variables for use by<br>
     * FlyWorldGUI and other pieces of code.
     *
     *@param fileName the file containing the world grid information
     */
    public FlyWorld(String fileName)
    {
	File file= new File(fileName);

	Scanner input = null;
	try
	    {
		input = new Scanner(file);
	    }
	catch(FileNotFoundException fnfe)
	    {
		System.out.println("File not found!");
		System.exit(1);
	    }     
 
	this.numRows=input.nextInt();
	this.numCols=input.nextInt();
        this.world= new GridLocation[numRows][numCols];
	this.predators=new ArrayList<Predator>();
	int i=-1;
	while (input.hasNextLine())
	    {
		String line=input.nextLine();
		Scanner lineScanner= new Scanner(line);
		lineScanner.useDelimiter("");
		
		while (lineScanner.hasNext()) //Checks rest of the file, line by line.
		    {
			i++;
	    
				for(int j=0;j<numCols;j++)
				    {
					String q=lineScanner.next();
					GridLocation newGrid=new GridLocation(i,j); //Creates new Gridlocation to set up world
					if (q.equals("s")) //Start Fly
					    {
					        start=newGrid;
					        world[i][j]=start;
					       	start.setBackgroundColor(Color.GREEN);
						mosca= new Fly(start,this);
					        
					    }

					else if (q.equals("h"))//Create Goal
					    {
					        goal=newGrid;
						world[i][j]=goal;
					        goal.setBackgroundColor(Color.RED);
					    }
					else if (q.equals("f"))//Create Frog
					    {
						world[i][j]=newGrid;
					        Predator frog= new Frog(newGrid,this);
						predators.add(frog);	    
					    }
					else if (q.equals("b"))//Create Bird
					    {
						world[i][j]=newGrid;
					        Predator bird= new Bird(newGrid,this);
						predators.add(bird);
					    }
					else if (q.equals("a"))//Create Spider
					    {
						world[i][j]=newGrid;
					        Predator spider= new Spider(newGrid,this);
						predators.add(spider);
					    }
					else
					    {
					        world[i][j]=newGrid; 
					        
						}
				      
				        
				    } 
			    
			    
		    }	
		    
	    }
	//Closes Scanner
	input.close();
    }

    /**
     * @return int, the number of rows in the world
     */
    public int getNumRows()
    {
	return numRows;
    }

    /**
     * @return int, the number of columns in the world
     */
    public int getNumCols()
    { 
	return numCols;
    }

    /**
     * Determines if a specific row/column location is<br>
     * a valid location in the world (i.e., it is not out of bounds)
     *
     * @param r a row
     * @param c a column
     *
     * @return boolean
     */
    public boolean isValidLoc(int r, int c)
    {
	if (r>numRows-1 || r<0)
	    {
		return false;
	    }
	else if (c>numCols-1 || c<0)
	    {
		return false;
	    }
	else
	    {
		return true; 
	    }
    }

    /**
     * Returns a specific location based on the given row and column
     *
     * @param r the row
     * @param c the column
     *
     * @return GridLocation
     */
    public GridLocation getLocation(int r, int c)
    {
	return world[r][c];
    }

    /**
     * @return FlyWorldLocation, the location of the fly in the world
     */
    public GridLocation getFlyLocation()
    {
	return mosca.getLocation();
    }

    /**
     * Moves the fly in the given direction (if possible)
     * Checks if the fly got home or was eaten
     *
     * @param direction the direction, N,S,E,W to move
     *
     * @return int, determines the outcome of moving fly<br>
     *              there are three possibilities<br>
     *              1. fly is at home, return ATHOME (defined in FlyWorldGUI)<br>
     *              2. fly is eaten, return EATEN (defined in FlyWorldGUI)<br>
     *              3. fly not at home or eaten, return NOACTION (defined in FlyWorldGUI)
     */
    public int moveFly(int direction)
    {
	mosca.update(direction);
	GridLocation flylocation=getFlyLocation();

	if (flylocation.equals(goal))
	    {
		return FlyWorldGUI.ATHOME;	
	    }
	

	else
	    {
		for (Predator x: predators)
		    {
		        
			if (x.eatsFly()==true)
			    {
				return FlyWorldGUI.EATEN;
			    }
	    
		     }

		return FlyWorldGUI.NOACTION;
	    } 
    }

    /**
     * Moves all predators. After it moves a predator, checks if it eats fly
     *
     * @return boolean, return true if any predator eats fly, false otherwise
     */
    public boolean movePredators()
    {
	for (Predator x: predators)
	    {
		x.update();

		if (x.eatsFly()==true)
		    {
			return true;
		    }	    
		
	    }
	 return false; 
    }
   
}
		    
			
			      
