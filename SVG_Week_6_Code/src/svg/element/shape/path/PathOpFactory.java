package svg.element.shape.path;

import java.util.ArrayList;
import java.util.List;

//-----------------------------------------------------------------------------

/**
 * Class that holds a factory method for creating new SVG path operations. 
 * @author cambolbro
 */
public class PathOpFactory
{
	// List of concrete classes to be instantiated
	private final static List<PathOp> prototypes = new ArrayList<PathOp>();
	{
		// Path operation prototypes
		prototypes.add(new MoveTo());
		prototypes.add(new LineTo());
		prototypes.add(new QuadTo());
		prototypes.add(new CubicTo());
		prototypes.add(new Close());
	}
	
	// Singleton occurrence of this class
	private static PathOpFactory singleton = null;
	
	//-------------------------------------------------------------------------

	/**
	 * Private constructor: only this class can contruct itself.
	 */
	private PathOpFactory() 
	{
		// Nothing to do...
	}
	
	//-------------------------------------------------------------------------

	public static PathOpFactory get()
	{
		if (singleton == null)
			singleton = new PathOpFactory();  // lazy initialisation
		return singleton;
	}
	
	public List<PathOp> prototypes()
	{
		return prototypes;
	}
	
	//-------------------------------------------------------------------------

	/**
	 * @param label Element type to make.
	 * @return New element of specified type, with fields unset.
	 */
	public PathOp makePathOp(final char label)
	{
		final char labelUpper = Character.toUpperCase(label);
		
		// Find the appropriate prototype
		PathOp prototype = null;
		for (PathOp prototypeN : prototypes)
			if (Character.toUpperCase(prototypeN.label()) == labelUpper)
			{
				prototype = prototypeN;
				break;
			}
		
		if (prototype == null)
		{
			System.out.println("* Failed to find prototype for PathOp " + label + ".");
			return null;
		}

		return prototype.newInstance();  // return an unset clone
	}
	
	//-------------------------------------------------------------------------

}
