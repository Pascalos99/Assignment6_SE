package svg.element.shape;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import svg.element.BaseElement;
import svg.element.style.Fill;
import svg.element.style.Stroke;
import svg.element.style.StrokeWidth;
import svg.element.style.Style;
import svg.element.style.StyleFactory;

//-----------------------------------------------------------------------------

/**
 * Base class for SVG shapes. Shapes are scoped by <angled brackets>.
 * @author cambolbro
 */
public abstract class Shape extends BaseElement
{
	private final List<Style> styles = new ArrayList<Style>();
		
	protected Color fillColour = null;
	protected Color strokeColour = null;
	
	//-------------------------------------------------------------------------

	public Shape(final String label)
	{
		super(label);
	}

	//-------------------------------------------------------------------------

	public List<Style> styles()
	{
		return styles;
	}

	public Rectangle2D.Double bounds()
	{
		return bounds;
	}

	public Color fillColour()
	{
		return fillColour;
	}
	
	public Color strokeColour()
	{
		return strokeColour;
	}
		
	//-------------------------------------------------------------------------

	@Override
	public boolean load(final String expr)
	{
		boolean okay = true;
		
		// Load painting styles for shape
		styles.clear();
		for (Style proto : StyleFactory.get().prototypes())
		{
			final String str = proto.label() + "=";
			final int pos = str.indexOf(str);
			if (pos != -1)
			{
				final String sub = expr.substring(pos);
				final Style style = StyleFactory.get().makeStyle(proto.label());
				style.load(sub);
				styles.add(style);
				
				if (proto.label().equals("fill"))
					fillColour = ((Fill)style).colour();  // store the fill colour
				
				if (proto.label().equals("stroke"))
					strokeColour = ((Stroke)style).colour();  // store the stroke colour
			}
		}
		
		return okay;
	}

	//-------------------------------------------------------------------------

	@Override
	public double strokeWidth()
	{
		for (Style style : styles)
			if (style.label().equals("stroke-width"))
				return ((StrokeWidth)style).width();
		return 0;  // no stroke width specified
	}
	
	//-------------------------------------------------------------------------

//	@Override
//	public void render()
//	{
//		for (Style style : styles)
//			style.render();
//	}
	
	//-------------------------------------------------------------------------

}
