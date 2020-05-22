package svg.element.shape.path;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import svg.SVGParser;
import svg.element.Element;
import svg.element.shape.Shape;

//-----------------------------------------------------------------------------

/**
 * SVG path shape.
 * @author cambolbro
 */
public class Path extends Shape
{
	// Format: 
	//    <path d="M 100 100 L 300 50 zM 100 100 l 300 50 l 300 250 Z"
    //          fill="red" stroke="blue" stroke-width="3" />
	
	private final List<PathOp> ops = new ArrayList<PathOp>();
	
	private double pathLength = 0;  // optional author-specified estimate of path length

	//-------------------------------------------------------------------------
	
	public Path()
	{
		super("path");
	}
	
	//-------------------------------------------------------------------------

	public List<PathOp> ops()
	{
		return ops;
	}
	
	public double pathLength()
	{
		return pathLength;
	}

	//-------------------------------------------------------------------------

	@Override
	public Element newInstance()
	{
		return new Path();
	}
	
	//-------------------------------------------------------------------------

	@Override
	public void setBounds()
	{
		double x0 =  10000;
		double y0 =  10000;
		double x1 = -10000;
		double y1 = -10000;
		
		for (PathOp op : ops)
		{
			final Rectangle2D.Double bound = op.bounds();
			
			if (bound == null)
				continue;  // op has no bounds
			
			if (bound.x < x0)
				x0 = bound.x;
			if (bound.y < y0)
				y0 = bound.y;
			
			final double x = bound.x + bound.width; 
			final double y = bound.y + bound.height; 
			
			if (x > x1)
				x1 = x;
			if (y > y1)
				y1 = y;
		}
			
		bounds.setRect(x0, y0, x1-x0, y1-y0);
	}
	
	//-------------------------------------------------------------------------

	@Override
	public boolean load(final String expr)
	{
		boolean okay = true;

		if (!super.load(expr))
			return false;

		ops.clear();

		// Load path ops from expression
		if (expr.contains(" d=\""))
		{
			// Contains path data
			final int pos = expr.indexOf(" d=\"");
			final String str = SVGParser.extractStringAt(expr, pos+3);
			if (str == null)
			{
				System.out.println("* Failed to extract string from: " + expr.substring(pos+3));
				return false;
			}
			
			int c = 0;
			while (c < str.length())
			{
				final char ch = str.charAt(c);
				if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z')
				{
					// Is (probably) a path op label
					final PathOp op = PathOpFactory.get().makePathOp(ch);
					if (op == null)
					{
						System.out.println("* Couldn't find path op with label: " + op);
						return false;
					}
					
					op.load(str.substring(c));
					ops.add(op);
				}
				c++;
			}
		}
		
		return okay;
	}
	
	//-------------------------------------------------------------------------

	@Override
	public String toString()
	{
		final StringBuilder sb = new StringBuilder();
		
		sb.append(label() + ":");
		for (PathOp op : ops)
			sb.append(" [" + op + "]" + (op.absolute() ? "*" : ""));
			
		return sb.toString();
	}
	
	//-------------------------------------------------------------------------

	@Override
	public void render()
	{
		// ...
	}

	//-------------------------------------------------------------------------

}
