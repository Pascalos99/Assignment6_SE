package svg.element.shape.path;

import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

//-----------------------------------------------------------------------------

/**
 * An SVG path operation.
 * @author cambolbro
 */
public abstract class PathOp
{
	/** Single char label. Can change upper/lower case when loaded. */
	protected char label;
	
	/** Whether op is absolute or relative. */
	protected boolean absolute = true;  

	//-------------------------------------------------------------------------

	/**
	 * Note: Label will be passed in as upper case, but visible case 
	 *       will depend on whether it's absolute or relative.
	 */
	public PathOp(final char label)
	{
		this.label = label;
		this.absolute = (label == Character.toUpperCase(label));
	}
	
	//-------------------------------------------------------------------------

	public boolean absolute()
	{
		return absolute;
	}

//	public void setAbsolute(final boolean set)
//	{
//		absolute = set;
//	}

	//-------------------------------------------------------------------------

	/**
	 * @return Bounds for this path op, or null if none.
	 */
	public Rectangle2D.Double bounds()
	{
		return null;
	}
	
	//-------------------------------------------------------------------------
	
	public char label()
	{
		return absolute ? Character.toUpperCase(label) : Character.toLowerCase(label);
	}

	//-------------------------------------------------------------------------

	/**
	 * @return New element of own type.
	 */
	public abstract PathOp newInstance();
	
	/**
	 * Load this shape's data from an SVG expression.
	 * @return Whether expression is in the right foramt and data was loaded.
	 */
	public abstract boolean load(final String expr);

	/**
	 * Render this element to a Graphics2D canvas.
	 */
	public abstract void apply(final GeneralPath path);
	
	//-------------------------------------------------------------------------

}
