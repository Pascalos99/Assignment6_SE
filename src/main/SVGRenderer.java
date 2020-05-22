package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import main.decorators.Decorator;
import svg.SVG;
import svg.SVGParser;
import svg.element.Element;
import svg.element.shape.Shape;
import svg.element.style.Style;
import view.SVGView;
import static main.decorators.DecoratorGraphics2D.shapeDecorators;
import static main.decorators.DecoratorGraphics2D.styleDecorators;;

//-----------------------------------------------------------------------------

/**
 * Class for rendering SVG file contents to a Graphics2D canvas.
 * For KEN1520 week 6 assignment.
 * @author cambolbro
 */
public class SVGRenderer
{
	private final SVGParser parser;
	private final SVGView   view;

	//-------------------------------------------------------------------------

	public SVGRenderer(final SVGParser parser, final SVGView view)
	{
		this.parser = parser;
		this.view = view;
	}
	
	//-------------------------------------------------------------------------

	public void render()
	{
		final Graphics2D g2d = view.graphics2D();
			
     	g2d.setPaint(Color.white);
       	g2d.fillRect(0, 0, view.getWidth(), view.getHeight());

       	final SVG svg = parser.svg();

       	// Prepare an image to render to
       	final BufferedImage img = new BufferedImage(view.getWidth(), view.getHeight(), BufferedImage.TYPE_INT_ARGB);
       	Graphics2D g2dImage = (Graphics2D)img.getGraphics();
       	g2dImage.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       	g2dImage.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
       	
       	g2d.setPaint(new Color(255, 127, 0));

       	RenderBuilder rb = new RenderBuilder(g2dImage);
       	for (Element element : svg.elements())
       		rb.drawElement(element);
       	rb.close();
   	   	
       	if (!view.zoom())
       	{
       		// Just draw elements
       		g2d.drawImage(img, 0, 0, null);
       	}
       	else
       	{
       		// Draw to image then scale to window
      	   	final Rectangle2D.Double bounds = svg.bounds();
      	   	final double sw = svg.maxStrokeWidht();
      	   	final double svgWidth  = bounds.getX() + bounds.getWidth()  + sw;
      	   	final double svgHeight = bounds.getY() + bounds.getHeight() + sw;
      	   	
    	   	final double scale = (svgHeight == 0) ? 1 : (view.getHeight() / svgHeight);
       	   	
      	   	final int sx = (int)(svgWidth + 0.5);
      	   	final int sy = (int)(svgHeight + 0.5);
      	   	
      	   	final int dx = (int)(svgWidth  * scale + 0.5);
      	   	final int dy = (int)(svgHeight * scale + 0.5);
      	   
      	   	g2d.drawImage(img, 0, 0, dx, dy, 0, 0, sx, sy, null);
       	}
	}
	
	//-------------------------------------------------------------------------

	static class RenderBuilder {
		private final Graphics2D g2d;
		private boolean open = true;
		public RenderBuilder(final Graphics2D g) {
			g2d = g;
		}
		
		public boolean drawElement(Element element) {
			if (!open) return false;
			draw(element);
			return true;
		}
		
		private void draw(Element element) {
			Decorator shape_decorator = null;
			Decorator style_decorator = null;
			Shape shape = (Shape)element;
			for (int i=0; i < shapeDecorators.length; i++)
				if (shape.label().equals(shapeDecorators[i].decorator_label)) {
					shape_decorator = shapeDecorators[i].createDecoratorInstance(shape, null, g2d);
					break;
				}
			for (Style style : shape.styles())
				for (int i=0; i < styleDecorators.length; i++)
					if (style.label().equals(styleDecorators[i].decorator_label))
						style_decorator = styleDecorators[i].
							createDecoratorInstance(style_decorator, style, g2d);
			if (style_decorator != null) style_decorator.render();
			if (shape_decorator != null) shape_decorator.render();
		}
		
		public void close() {
			open = false;
		}
	}
	
}
