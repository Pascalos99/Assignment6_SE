package main.decorators.shapes;

import java.awt.Graphics2D;

import main.decorators.DecoratorGraphics2D;
import svg.element.BaseElement;
import svg.element.shape.Circle;
import svg.element.shape.Shape;
import svg.element.style.Style;

public class DecoratorGraphics2DCircle extends DecoratorGraphics2D
{
	public DecoratorGraphics2DCircle(final Circle base, final Graphics2D g2d)
	{
		super("circle", base, null, g2d);
	}

	@Override
	public void render()  //Graphics2D g2d)
	{
		final int x = (int)(((Circle)component).cx() - ((Circle)component).r() + 0.5);
		final int y = (int)(((Circle)component).cy() - ((Circle)component).r() + 0.5);
		final int diameter = (int)(((Circle)component).r() * 2 + 0.5);
	
		if (((Shape)component).fillColour() != null)
		{
			graphics2D.setPaint(((Shape)component).fillColour());
			graphics2D.fillArc(x, y, diameter, diameter, 0, 360);
		}

		if (((Shape)component).strokeColour() != null)
		{
			super.render();  //graphics2D);  // set shape styles
			graphics2D.setPaint(((Shape)component).strokeColour());
			graphics2D.drawArc(x, y, diameter, diameter, 0, 360);
		}
	}

	@Override
	public DecoratorGraphics2D createDecoratorInstance(BaseElement base, Style style, Graphics2D g2d) {
		return new DecoratorGraphics2DCircle((Circle)base, g2d);
	}

}
