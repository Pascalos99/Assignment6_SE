package svg.element.style;

import java.util.ArrayList;
import java.util.List;

//-----------------------------------------------------------------------------

/**
 * Singleton class that holds a factory method for creating new SVG style objects. 
 * @author cambolbro
 */
public class StyleFactory
{
	// List of concrete classes to be instantiated
	private final static List<Style> prototypes = new ArrayList<Style>();
	{
		prototypes.add(new Stroke());
		prototypes.add(new StrokeWidth());
//		prototypes.add(new StrokeLineCap());
//		prototypes.add(new StrokeLineJoin());
		prototypes.add(new Fill());
	}
	
	// Singleton occurrence of this class
	private static StyleFactory singleton = null;
	
	//-------------------------------------------------------------------------

	/**
	 * Private constructor: only this class can construct itself.
	 */
	private StyleFactory() 
	{
		// Nothing to do...
	}
	
	//-------------------------------------------------------------------------

	public static StyleFactory get()
	{
		if (singleton == null)
			singleton = new StyleFactory();  // lazy initialisation
		return singleton;
	}
	
	public List<Style> prototypes()
	{
		return prototypes;
	}
	
	//-------------------------------------------------------------------------

	/**
	 * @param label Symbol type to make.
	 * @return New symbol of specified type, with fields unset.
	 */
	public Style makeStyle(final String label)
	{
		for (Style prototype : prototypes)
			if (prototype.label().equals(label))
				return prototype.newInstance();  // return an unset clone
		
		System.out.println("* Failed to find prototype for Style " + label + ".");
		return null;
	}
	
	//-------------------------------------------------------------------------

}
