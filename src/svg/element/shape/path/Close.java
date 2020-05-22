package svg.element.shape.path;

import java.awt.geom.GeneralPath;

//-----------------------------------------------------------------------------

/**
 * SVG path close operation.
 * @author cambolbro
 */
public class Close extends PathOp
{
	// Format: 
	//   Z 	
    //   z 

	//-------------------------------------------------------------------------
	
	public Close()
	{
		super('Z');
	}
	
	//-------------------------------------------------------------------------

	@Override
	public PathOp newInstance()
	{
		return new Close();
	}
	
	//-------------------------------------------------------------------------

	@Override
	public boolean load(final String expr)
	{
		// Is absolute if label is upper case
		label = expr.charAt(0); 
		absolute = (label == Character.toUpperCase(label));
		
		return true;
	}
	
	//-------------------------------------------------------------------------

	@Override
	public String toString()
	{
		final StringBuilder sb = new StringBuilder();
		
		sb.append(label);
				
		return sb.toString();

	}
	
	//-------------------------------------------------------------------------

	@Override
	public void apply(final GeneralPath path)
	{
		path.closePath();
	}
	
	//-------------------------------------------------------------------------

}
