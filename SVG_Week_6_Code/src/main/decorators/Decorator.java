package main.decorators;

import svg.element.BaseElement;
import svg.element.style.Style;

public abstract class Decorator extends BaseElement
{
	protected final BaseElement component; 
	protected final Style style;
	
	public Decorator(final BaseElement base, final Style style)
	{
		super("Decorator");  // keep the BaseElement constructor happy
		this.component = base;
		this.style = style;
	}

}
