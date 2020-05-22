package main.decorators.shapes;

import java.awt.Graphics2D;

import main.decorators.DecoratorGraphics2D;
import svg.element.shape.Rect;
import svg.element.shape.Shape;

public class DecoratorGraphics2DRect extends DecoratorGraphics2D
{
	public DecoratorGraphics2DRect(final Rect base, final Graphics2D g2d)
	{
		super(base, null, g2d);
	}

	@Override
	public void render()  //Graphics2D g2d)
	{
		final int x = (int)(((Rect)component).x() + 0.5);
		final int y = (int)(((Rect)component).y() + 0.5);
		final int sx = (int)(((Rect)component).width() + 0.5);
		final int sy = (int)(((Rect)component).height() + 0.5);
		final int rx = (int)(((Rect)component).rx() + 0.5);
		final int ry = (int)(((Rect)component).ry() + 0.5);
	
		if (((Shape)component).fillColour() != null)
		{
			graphics2D.setPaint(((Shape)component).fillColour());
			if (rx == 0 && ry == 0)
				graphics2D.fillRect(x, y, sx, sy);
			else
				graphics2D.fillRoundRect(x, y, sx, sy, rx, ry);
		}

		if (((Shape)component).strokeColour() != null)
		{
			super.render();  //graphics2D);  // set shape styles
			graphics2D.setPaint(((Shape)component).strokeColour());
			if (rx == 0 && ry == 0)
				graphics2D.drawRect(x, y, sx, sy);
			else
				graphics2D.drawRoundRect(x, y, sx, sy, rx, ry);
		}
	}

}
