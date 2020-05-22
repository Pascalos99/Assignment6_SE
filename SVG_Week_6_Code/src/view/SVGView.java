package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

//-----------------------------------------------------------------------------

/**
 * Main view for SVGReader app.
 * For KEN1520 week 6 assignment.
 * @author cambolbro
 */
public class SVGView extends JPanel
{
	private static final long serialVersionUID = 1L;

	private boolean zoom = false;
	
	//-------------------------------------------------------------------------

	public boolean zoom()
	{
		return zoom;
	}
	
	public void setZoom(final boolean set)
	{
		zoom = set;
	}
	
	//-------------------------------------------------------------------------
    
    public void paint(Graphics g) 
    {
     	final Graphics2D g2d = (Graphics2D)g;

       	g2d.setPaint(Color.white);
       	g2d.fillRect(0, 0, getWidth(), getHeight());

       	g2d.setPaint(new Color(0, 127, 255));
       	g2d.drawString("Draw SVG contents here.", 10, 20);
    }
    
    //-------------------------------------------------------------------------

    public Graphics2D graphics2D()
    {
    	final Graphics2D g2d = (Graphics2D)this.getGraphics();
    
     	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    	
    	return g2d;
    }
 
    //-------------------------------------------------------------------------
    
}
