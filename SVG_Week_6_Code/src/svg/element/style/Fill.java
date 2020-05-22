package svg.element.style;

import java.awt.Color;

import svg.SVGParser;

//-----------------------------------------------------------------------------

/**
 * SVG fill colour property.
 * @author cambolbro
 */
public class Fill extends Style
{
	// Format:  fill="rgb(255,0,0)"
	
	private Color colour = null;  //new Color(0, 0, 0);

	//-------------------------------------------------------------------------
	
	public Fill()
	{
		super("fill");
	}
	
	//-------------------------------------------------------------------------

	public Color colour()
	{
		return colour;
	}

	//-------------------------------------------------------------------------

	@Override
	public Style newInstance()
	{
		return new Fill();
	}
	
	//-------------------------------------------------------------------------

	@Override
	public boolean load(final String expr)
	{
		boolean okay = true;
		
		final int pos = expr.indexOf("fill=");
		if (pos != -1)
		{
			final String result = SVGParser.extractStringAt(expr, pos+7);
			if (result == null)
				return false;
			
			if (result.equals("transparent"))
				colour = null;
			else if (result.equals("red"))
				colour = new Color(255, 0, 0);
			else if (result.equals("green"))
				colour = new Color(0, 175, 0);
			else if (result.equals("blue"))
				colour = new Color(0, 0, 255);
			else if (result.equals("white"))
				colour = new Color(255, 255, 255);
			else if (result.equals("black"))
				colour = new Color(0, 0, 0);
			else if (result.equals("orange"))
				colour = new Color(255, 175, 0);
			else if (result.equals("yellow"))
				colour = new Color(255, 240, 0);
		}
		
		return okay;
	}
	
	//-------------------------------------------------------------------------

	@Override
	public void render()
	{
		// Shape.fillColour is set in load.
	}

	//-------------------------------------------------------------------------

}
