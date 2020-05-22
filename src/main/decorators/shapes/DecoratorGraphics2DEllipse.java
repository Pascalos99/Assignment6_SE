package main.decorators.shapes;

import java.awt.Graphics2D;

import main.decorators.DecoratorGraphics2D;
import svg.element.BaseElement;
import svg.element.shape.Ellipse;
import svg.element.shape.Shape;
import svg.element.style.Style;

public class DecoratorGraphics2DEllipse extends DecoratorGraphics2D
{
	public DecoratorGraphics2DEllipse(final Ellipse base, final Graphics2D g2d)
	{
		super("ellipse", base, null, g2d);
	}

	@Override
	public void render()  //Graphics2D g2d)
	{
		final int x = (int)(((Ellipse)component).cx() - ((Ellipse)component).rx() + 0.5);
		final int y = (int)(((Ellipse)component).cy() - ((Ellipse)component).ry() + 0.5);
		final int diameterX = (int)(((Ellipse)component).rx() * 2 + 0.5);
		final int diameterY = (int)(((Ellipse)component).ry() * 2 + 0.5);
	
		if (((Shape)component).fillColour() != null)
		{
			graphics2D.setPaint(((Shape)component).fillColour());
			graphics2D.fillArc(x, y, diameterX, diameterY, 0, 360);
		}

		if (((Shape)component).strokeColour() != null)
		{
			super.render();  //graphics2D);  // set shape styles
			graphics2D.setPaint(((Shape)component).strokeColour());
			graphics2D.drawArc(x, y, diameterX, diameterY, 0, 360);
		}
	}

	@Override
	public DecoratorGraphics2D createDecoratorInstance(BaseElement base, Style style, Graphics2D g2d) {
		return new DecoratorGraphics2DEllipse((Ellipse)base, g2d);
	}

}
