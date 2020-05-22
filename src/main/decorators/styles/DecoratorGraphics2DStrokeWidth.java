package main.decorators.styles;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

import main.decorators.Decorator;
import main.decorators.DecoratorGraphics2D;
import svg.element.BaseElement;
import svg.element.style.StrokeWidth;
import svg.element.style.Style;

public class DecoratorGraphics2DStrokeWidth extends DecoratorGraphics2D
{
	public DecoratorGraphics2DStrokeWidth(final BaseElement base, final StrokeWidth style, final Graphics2D g2d)
	{
		super("stroke-width", base, style, g2d);
	}

	@Override
	public void render()  //Graphics2D g2d)
	{
		if (component != null) component.render();
		final float width = (float)((StrokeWidth)style).width();
		graphics2D.setStroke(new BasicStroke(width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
	}

	@Override
	public DecoratorGraphics2D createDecoratorInstance(BaseElement base, Style style, Graphics2D g2d) {
		return new DecoratorGraphics2DStrokeWidth(base, (StrokeWidth)style, g2d);
	}

}
