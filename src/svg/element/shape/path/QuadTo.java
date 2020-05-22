package svg.element.shape.path;

import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import svg.SVGParser;

//-----------------------------------------------------------------------------

/**
 * SVG path quadratic curve to operation.
 * @author cambolbro
 */
public class QuadTo extends PathOp
{
	// Format: 
	//   Q 100 100 200 200
    //   q 100 100 200 200
	
	private double x1 = 0;
	private double y1 = 0;
	private double x2 = 0;
	private double y2 = 0;

	//-------------------------------------------------------------------------
	
	public QuadTo()
	{
		super('Q');
	}
	
	//-------------------------------------------------------------------------

	public double x1()
	{
		return x1;
	}

	public double y1()
	{
		return y1;
	}

	public double x2()
	{
		return x2;
	}

	public double y2()
	{
		return y2;
	}

	//-------------------------------------------------------------------------

	@Override
	public PathOp newInstance()
	{
		return new QuadTo();
	}
	
	//-------------------------------------------------------------------------

	@Override
	public Rectangle2D.Double bounds()
	{
		final double x = Math.min(x1, x2);
		final double y = Math.min(y1, y2);
		final double width  = Math.max(x1, x2) - x;
		final double height = Math.max(y1, y2) - y;

		return new Rectangle2D.Double(x, y, width, height);
	}

	//-------------------------------------------------------------------------

	@Override
	public boolean load(final String expr)
	{
//		System.out.println("+ Loading " + label + ": " + expr + "...");
		
		// Is absolute if label is upper case
		label = expr.charAt(0); 
		absolute = (label == Character.toUpperCase(label));
		
		int c = 1;
		
		final Double resultX1 = SVGParser.extractDoubleAt(expr, c);
		if (resultX1 == null)
		{
			System.out.println("* Failed to read X1 from " + expr + ".");
			return false;
		}
		x1 = resultX1.doubleValue();
			
		while (c < expr.length() && SVGParser.isNumeric(expr.charAt(c)))
			c++;
			
		while (c < expr.length() && !SVGParser.isNumeric(expr.charAt(c)))
			c++;
			
		final Double resultY1 = SVGParser.extractDoubleAt(expr, c);
		if (resultY1 == null)
		{
			System.out.println("* Failed to read Y1 from " + expr + ".");
			return false;
		}
		y1 = resultY1.doubleValue();

		while (c < expr.length() && SVGParser.isNumeric(expr.charAt(c)))
			c++;
			
		while (c < expr.length() && !SVGParser.isNumeric(expr.charAt(c)))
			c++;

		final Double resultX2 = SVGParser.extractDoubleAt(expr, c);
		if (resultX2 == null)
		{
			System.out.println("* Failed to read X2 from " + expr + ".");
			return false;
		}
		x2 = resultX2.doubleValue();
			
		while (c < expr.length() && SVGParser.isNumeric(expr.charAt(c)))
			c++;
			
		while (c < expr.length() && !SVGParser.isNumeric(expr.charAt(c)))
			c++;
			
		final Double resultY2 = SVGParser.extractDoubleAt(expr, c);
		if (resultY2 == null)
		{
			System.out.println("* Failed to read Y2 from " + expr + ".");
			return false;
		}
		y2 = resultY2.doubleValue();
		
		return true;
	}
	
	//-------------------------------------------------------------------------

	@Override
	public String toString()
	{
		final StringBuilder sb = new StringBuilder();
		
		sb.append(label + ": x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2);
				
		return sb.toString();

	}
	
	//-------------------------------------------------------------------------

	@Override
	public void apply(final GeneralPath path)
	{
		path.quadTo(x1, y1, x2, y2);
	}
	
	//-------------------------------------------------------------------------

}
