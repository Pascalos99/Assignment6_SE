package main.decorators;

import java.awt.Graphics2D;

import main.decorators.shapes.DecoratorGraphics2DCircle;
import main.decorators.shapes.DecoratorGraphics2DEllipse;
import main.decorators.shapes.DecoratorGraphics2DLine;
import main.decorators.shapes.DecoratorGraphics2DPath;
import main.decorators.shapes.DecoratorGraphics2DPolygon;
import main.decorators.shapes.DecoratorGraphics2DPolyline;
import main.decorators.shapes.DecoratorGraphics2DRect;
import main.decorators.styles.DecoratorGraphics2DStrokeWidth;
import svg.element.BaseElement;
import svg.element.Element;
import svg.element.style.Style;

public abstract class DecoratorGraphics2D extends Decorator
{
	final protected Graphics2D graphics2D;
	public final String decorator_label;
	
	public final static DecoratorGraphics2D[] styleDecorators = {
			new DecoratorGraphics2DStrokeWidth(null, null, null)
	};
	public static final DecoratorGraphics2D[] shapeDecorators = {
			new DecoratorGraphics2DCircle(null, null),
			new DecoratorGraphics2DEllipse(null, null),
			new DecoratorGraphics2DLine(null, null),
			new DecoratorGraphics2DPath(null, null),
			new DecoratorGraphics2DPolygon(null, null),
			new DecoratorGraphics2DPolyline(null, null),
			new DecoratorGraphics2DRect(null, null)
	};
	
	public DecoratorGraphics2D(final String label, final BaseElement base, final Style style, final Graphics2D g2d)
	{
		super(base, style);
		this.graphics2D = g2d;
		decorator_label = label;
	}

	@Override
	public Element newInstance()
	{
		return component.newInstance();
	}

	@Override
	public boolean load(String expr)
	{
		return component.load(expr);
	}

	@Override
	public void render()  //Graphics2D g2d)
	{
		component.render();  //g2d);
	}

	@Override
	public void setBounds()
	{
		component.setBounds();
		
	}
	
	public abstract DecoratorGraphics2D createDecoratorInstance(BaseElement base, Style style, Graphics2D g2d);

}
