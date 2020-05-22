package svg.element;

//-----------------------------------------------------------------------------

/**
 * SVG element type.
 * @author cambolbro
 */
public interface Element
{
	/**
	 * @return Label for this element.
	 */
	public String label();
	
	/**
	 * @param other
	 * @return Comparison with other element (order in file).
	 */
	public int compare(final Element other);
	
	/**
	 * @return New element of own type.
	 */
	public Element newInstance();
	
	/**
	 * Load this element's data from an SVG expression.
	 * @return Whether expression is in the right format and data was loaded.
	 */
	public boolean load(final String expr);
	
	/**
	 * Render this element to a Graphics2D canvas.
	 */
	public abstract void render();

}
