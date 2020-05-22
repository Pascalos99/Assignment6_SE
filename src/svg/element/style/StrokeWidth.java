package svg.element.style;

import svg.SVGParser;

//-----------------------------------------------------------------------------

/**
 * SVG stroke width property.
 * @author cambolbro
 */
public class StrokeWidth extends Style
{
	// Format:  stroke-width="1"
	
	private double width = 1;

	//-------------------------------------------------------------------------
	
	public StrokeWidth()
	{
		super("stroke-width");
	}
	
	//-------------------------------------------------------------------------

	public double width()
	{
		return width;
	}

	//-------------------------------------------------------------------------

	@Override
	public Style newInstance()
	{
		return new StrokeWidth();
	}
	
	//-------------------------------------------------------------------------

	@Override
	public boolean load(final String expr)
	{
		boolean okay = true;
		
		if (expr.contains("stroke-width="))
		{
			final Double result = SVGParser.extractDouble(expr, "stroke-width=");
			if (result == null)
				return false;
			width = result.doubleValue();
		}

		return okay;
	}
	
	//-------------------------------------------------------------------------

	@Override
	public void render()
	{
		// ...
	}

	//-------------------------------------------------------------------------

}
