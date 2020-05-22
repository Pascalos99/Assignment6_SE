package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import main.decorators.Decorator;
import main.decorators.shapes.DecoratorGraphics2DCircle;
import main.decorators.shapes.DecoratorGraphics2DEllipse;
import main.decorators.shapes.DecoratorGraphics2DLine;
import main.decorators.shapes.DecoratorGraphics2DPath;
import main.decorators.shapes.DecoratorGraphics2DPolygon;
import main.decorators.shapes.DecoratorGraphics2DPolyline;
import main.decorators.shapes.DecoratorGraphics2DRect;
import main.decorators.styles.DecoratorGraphics2DStrokeWidth;
import svg.SVG;
import svg.SVGParser;
import svg.element.Element;
import svg.element.shape.Circle;
import svg.element.shape.Rect;
import svg.element.shape.Line;
import svg.element.shape.Ellipse;
import svg.element.shape.Polyline;
import svg.element.shape.Polygon;
import svg.element.shape.path.Path;
import svg.element.shape.Shape;
import svg.element.style.StrokeWidth;
import svg.element.style.Style;
import view.SVGView;

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
       	g2d.drawString("Draw SVG contents here.", 10, 20);

       	// **
       	// ** TODO: Draw SVG contents here.
       	// **
   	   	
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

}
