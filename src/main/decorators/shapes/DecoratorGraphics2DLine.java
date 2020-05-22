package main.decorators.shapes;

import java.awt.Graphics2D;

import main.decorators.DecoratorGraphics2D;
import svg.element.shape.Line;
import svg.element.shape.Shape;

public class DecoratorGraphics2DLine extends DecoratorGraphics2D
{
	public DecoratorGraphics2DLine(final Line base, final Graphics2D g2d)
	{
		super(base, null, g2d);
	}

	@Override
	public void render()  //Graphics2D g2d)
	{
		final int x1 = (int)(((Line)component).x1() + 0.5);
		final int y1 = (int)(((Line)component).y1() + 0.5);
		final int x2 = (int)(((Line)component).x2() + 0.5);
		final int y2 = (int)(((Line)component).y2() + 0.5);
		
		if (((Shape)component).strokeColour() != null)
		{
			super.render();  //graphics2D);  // set shape styles
			graphics2D.setPaint(((Shape)component).strokeColour());
			graphics2D.drawLine(x1, y1, x2, y2);
		}
	}

}
