package main.decorators.styles;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

import main.decorators.DecoratorGraphics2D;
import svg.element.style.StrokeWidth;

public class DecoratorGraphics2DStrokeWidth extends DecoratorGraphics2D
{
	public DecoratorGraphics2DStrokeWidth(final StrokeWidth style, final Graphics2D g2d)
	{
		super(null, style, g2d);
	}

	@Override
	public void render()  //Graphics2D g2d)
	{
		final float width = (float)((StrokeWidth)style).width();
		graphics2D.setStroke(new BasicStroke(width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
	}

}
