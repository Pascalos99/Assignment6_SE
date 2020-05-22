package svg.element.style;

//-----------------------------------------------------------------------------

/**
 * Abstract base class for SVG style elements (stroke, fill, etc.).
 * @author cambolbro
 */
public abstract class Style
{
	private final String label;

	//-------------------------------------------------------------------------

	public Style(final String label)
	{
		this.label = label;
	}

	//-------------------------------------------------------------------------

	public String label()
	{
		return label;
	}
	
	//-------------------------------------------------------------------------

	public abstract Style newInstance();
	
	//-------------------------------------------------------------------------

	/**
	 * Load this element's painting properties.
	 * @return Whether expression is in the right format and data was loaded.
	 */
	public abstract boolean load(final String expr);
//	{
//		boolean okay = true;
//		
//		System.out.println(label() + " not implemented yet.");
//		// ...
//		
//		return okay;
//	}

	//-------------------------------------------------------------------------

	public abstract void render();  //Graphics2D g2d);

	//-------------------------------------------------------------------------

}
