package main;

import java.awt.event.*;
import javax.swing.*;

import view.SVGView;

//-----------------------------------------------------------------------------

/**
 * The app's main menu.
 */
public class MainMenu extends JMenuBar 
{
	private static final long serialVersionUID = 1L;
	
	//-------------------------------------------------------------------------

	public MainMenu(final SVGView view, ActionListener al, ItemListener il)
	{
		JMenuItem menuItem;
		JCheckBoxMenuItem cbMenuItem;
		    
	    //------------------------------------
	    //	"File" menu
	    JMenu menu = new JMenu("File");
	    this.add(menu);
	    
	    menuItem = new JMenuItem("Load");
	    menuItem.addActionListener(al);
	    menu.add(menuItem);    

	    //------------------------------------
	    //	"View" menu
	    menu = new JMenu("View");
	    this.add(menu);
	    
	    cbMenuItem = new JCheckBoxMenuItem("Zoom");
	    cbMenuItem.setSelected(view.zoom());
    	cbMenuItem.addItemListener(il);
    	menu.add(cbMenuItem);
	}

	//-------------------------------------------------------------------------

}
