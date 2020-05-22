package main.decorators.shapes;

import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

import main.decorators.DecoratorGraphics2D;
import svg.element.BaseElement;
import svg.element.shape.Circle;
import svg.element.shape.Polygon;
import svg.element.shape.Polyline;
import svg.element.shape.Shape;
import svg.element.style.Style;

public class DecoratorGraphics2DPolygon extends DecoratorGraphics2D
{
	public DecoratorGraphics2DPolygon(final Polygon base, final Graphics2D g2d)
	{
		super("polygon", base, null, g2d);
	}

	@Override
	public void render()  //Graphics2D g2d)
	{
		final GeneralPath path = new GeneralPath();
		for (int n = 0; n < ((Polyline)component).points().size(); n++)
		{
			final Point2D.Double pt = ((Polyline)component).points().get(n);
			if (n == 0)
				path.moveTo((float)pt.x, (float)pt.y);
			else
				path.lineTo((float)pt.x, (float)pt.y);
		}
		path.closePath();
		
		if (((Shape)component).fillColour() != null)
		{
			graphics2D.setPaint(((Shape)component).fillColour());
			graphics2D.fill(path);
		}
	
		if (((Shape)component).strokeColour() != null)
		{
			super.render();  //graphics2D);  // set shape styles
			graphics2D.setPaint(((Shape)component).strokeColour());
			graphics2D.draw(path);
		}
	}

	@Override
	public DecoratorGraphics2D createDecoratorInstance(BaseElement base, Style style, Graphics2D g2d) {
		return new DecoratorGraphics2DPolygon((Polygon)base, g2d);
	}

}
