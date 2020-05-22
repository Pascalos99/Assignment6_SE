package svg;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import svg.element.BaseElement;
import svg.element.Element;

//-----------------------------------------------------------------------------

/**
 * Contents of an SVG file. 
 * @author cambolbro
 */
public class SVG
{
	//private final XMLHeader = null;
	//private final SVGHeader = null;

	//private double width  = 0;
	//private double height = 0;
	
	private final List<Element> elements = new ArrayList<Element>();

	private Rectangle2D.Double bounds = new Rectangle2D.Double();
	
	//-------------------------------------------------------------------------
	
	public List<Element> elements()
	{
		return elements;
	}
	
	//-------------------------------------------------------------------------

//	public double width()
//	{
//		return width;
//	}
//
//	public double height()
//	{
//		return height;
//	}
//
//	public void setWidth(final double set)
//	{
//		width = set;
//	}
//
//	public void setHeight(final double set)
//	{
//		height = set;
//	}
	
	public Rectangle2D.Double bounds()
	{
		return bounds;
	}
	
	//-------------------------------------------------------------------------

	public void clear()
	{
		elements.clear();
	}
	
	//-------------------------------------------------------------------------

	public void setBounds()
	{
		bounds = null;
		for (Element element : elements)
		{
			((BaseElement)element).setBounds();
			if (bounds == null)
			{
				bounds = new Rectangle2D.Double();
				bounds.setRect(((BaseElement)element).bounds());
			}
			else
			{	
				bounds.add(((BaseElement)element).bounds());
			}
		}
		System.out.println("Bounds are: " + bounds);
	}
	
	//-------------------------------------------------------------------------

	/**
	 * @return Maximum stroke width specified for any element.
	 */
	public double maxStrokeWidht()
	{
		double maxWidth = 0;
		for (Element element : elements)
		{
			final double sw = ((BaseElement)element).strokeWidth();
			if (sw > maxWidth)
				maxWidth = sw;
		}
		return maxWidth;
	}
	
	//-------------------------------------------------------------------------

	@Override
	public String toString()
	{
		final StringBuilder sb = new StringBuilder();
		
		sb.append(elements.size() + " elements:\n");
		for (Element element : elements)
			sb.append(element + "\n");
		
		return sb.toString();
	}

	//-------------------------------------------------------------------------
	
}
