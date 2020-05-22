package main.decorators.shapes;

import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import main.decorators.DecoratorGraphics2D;
import svg.element.shape.Shape;
import svg.element.shape.path.Path;
import svg.element.shape.path.PathOp;

public class DecoratorGraphics2DPath extends DecoratorGraphics2D
{
	public DecoratorGraphics2DPath(final Path base, final Graphics2D g2d)
	{
		super(base, null, g2d);
	}

	@Override
	public void render()  //Graphics2D g2d)
	{
		final GeneralPath path = new GeneralPath();
		for (PathOp op : ((Path)component).ops())
			op.apply(path);
		
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

}
