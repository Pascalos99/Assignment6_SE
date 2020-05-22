package main.decorators.shapes;

import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

import main.decorators.DecoratorGraphics2D;
import svg.element.BaseElement;
import svg.element.shape.Polyline;
import svg.element.shape.Shape;
import svg.element.style.Style;

public class DecoratorGraphics2DPolyline extends DecoratorGraphics2D
{
	public DecoratorGraphics2DPolyline(final Polyline base, final Graphics2D g2d)
	{
		super("polyline", base, null, g2d);
	}

	@Override
	public void render()
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
		
		if (((Shape)component).strokeColour() != null)
		{
			super.render();  // set shape styles
			graphics2D.setPaint(((Shape)component).strokeColour());
			graphics2D.draw(path);
		}
	}

	@Override
	public DecoratorGraphics2D createDecoratorInstance(BaseElement base, Style style, Graphics2D g2d) {
		return new DecoratorGraphics2DPolyline((Polyline)base, g2d);
	}

}
