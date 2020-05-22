package svg.element.text;

import svg.element.BaseElement;
import svg.element.Element;

//-----------------------------------------------------------------------------

/**
 * SVG text elements. How handled yet -- added for completeness.
 * @author cambolbro
 */
public class Text extends BaseElement
{

	//-------------------------------------------------------------------------

	public Text()
	{
		super("Text");
	}

	//-------------------------------------------------------------------------

	@Override
	public Element newInstance()
	{
		return new Text();
	}

	//-------------------------------------------------------------------------

	@Override
	public boolean load(final String expr)
	{
		try
		{
			throw new Exception("SVG text loading not implemented yet.");
		} 
		catch (Exception e)
		{
			// ...
		}
		return false;
	}

	//-------------------------------------------------------------------------

	@Override
	public void setBounds()
	{
		System.out.println("Text.setBounds() not implemented yet.");
	}

	//-------------------------------------------------------------------------

	@Override
	public void render()
	{
		// ...
	}

	//-------------------------------------------------------------------------

}
