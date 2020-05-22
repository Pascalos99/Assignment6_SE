package svg.element.shape;

import svg.element.Element;

//-----------------------------------------------------------------------------

/**
 * SVG polygon shape.
 * @author cambolbro
 */
public class Polygon extends Polyline
{

	//-------------------------------------------------------------------------
	
	public Polygon()
	{
		super("polygon");  // load using Polyline.load()
	}
	
	//-------------------------------------------------------------------------

	@Override
	public Element newInstance()
	{
		return new Polygon();
	}

	//-------------------------------------------------------------------------

	@Override
	public void render()
	{
		// ...
	}
	
	//-------------------------------------------------------------------------

}
