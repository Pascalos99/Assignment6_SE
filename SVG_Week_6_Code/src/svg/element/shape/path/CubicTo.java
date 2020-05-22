package svg.element.shape.path;

import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import svg.SVGParser;

//-----------------------------------------------------------------------------

/**
 * SVG path cubic curve to operation.
 * @author cambolbro
 */
public class CubicTo extends PathOp
{
	// Format: 
	//   C 100 100 200 200 300 100
    //   c 100 100 200 200 300 100
	
	private double x1 = 0;
	private double y1 = 0;
	private double x2 = 0;
	private double y2 = 0;
	private double x3 = 0;
	private double y3 = 0;

	//-------------------------------------------------------------------------
	
	public CubicTo()
	{
		super('C');
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

	public double x3()
	{
		return x3;
	}

	public double y3()
	{
		return y3;
	}

	//-------------------------------------------------------------------------

	@Override
	public PathOp newInstance()
	{
		return new CubicTo();
	}
	
	//-------------------------------------------------------------------------

	@Override
	public Rectangle2D.Double bounds()
	{
		final double x = Math.min(x1, Math.min(x2, x3));
		final double y = Math.min(y1, Math.min(y2, y3));
		final double width  = Math.max(x1, Math.max(x2, x3)) - x;
		final double height = Math.max(y1, Math.max(y2, y3)) - y;

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

		while (c < expr.length() && SVGParser.isNumeric(expr.charAt(c)))
			c++;
			
		while (c < expr.length() && !SVGParser.isNumeric(expr.charAt(c)))
			c++;

		final Double resultX3 = SVGParser.extractDoubleAt(expr, c);
		if (resultX3 == null)
		{
			System.out.println("* Failed to read X3 from " + expr + ".");
			return false;
		}
		x3 = resultX3.doubleValue();
			
		while (c < expr.length() && SVGParser.isNumeric(expr.charAt(c)))
			c++;
			
		while (c < expr.length() && !SVGParser.isNumeric(expr.charAt(c)))
			c++;
			
		final Double resultY3 = SVGParser.extractDoubleAt(expr, c);
		if (resultY3 == null)
		{
			System.out.println("* Failed to read Y3 from " + expr + ".");
			return false;
		}
		y3 = resultY3.doubleValue();
		
		return true;
	}
	
	//-------------------------------------------------------------------------

	@Override
	public String toString()
	{
		final StringBuilder sb = new StringBuilder();
		
		sb.append(label + ": x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2 + ", x3=" + x3 + ", y3=" + y3);
				
		return sb.toString();

	}
	
	//-------------------------------------------------------------------------

	@Override
	public void apply(final GeneralPath path)
	{
		path.curveTo(x1, y1, x2, y2, x3, y3);
	}
	
	//-------------------------------------------------------------------------

}
