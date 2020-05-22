package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import svg.SVGParser;
import view.SVGView;

//------------------------------------------------------------------

/**
 * Class for drawing SVG file contents.
 * For KEN1520 week 6 assignment.
 * @author cambolbro
 */
public class SVGApp implements ActionListener, ItemListener
{
	private static JFrame frame;
	private static SVGView view;
	private final SVGParser parser = new SVGParser();

	private SVGRenderer sVGRenderer;
	
	//--------------------------------------------------------------

	void loadSVG(final String fileName)
	{
	    try { parser.loadAndParse(fileName); } 
		catch (IOException e) { e.printStackTrace(); }
	    System.out.println(parser);
	}
	
	//--------------------------------------------------------------

	/**
	 * Launches a file chooser.
	 * @return Path of selected file, else "" if none.
	 */
	String selectFile()
	{
		File testFile = new File("./src");
		String baseFolder = "";
		try { baseFolder = testFile.getCanonicalPath(); } 
		catch (IOException e2) { e2.printStackTrace(); }
		JFileChooser dlg = new JFileChooser(baseFolder);
   
		FileNameExtensionFilter filter = new FileNameExtensionFilter("SVG Files", "svg");
		dlg.setFileFilter(filter);
		
		if (dlg.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			return dlg.getSelectedFile().getPath();
	
		return "";
	}
	
	//--------------------------------------------------------------


	@Override
	public void itemStateChanged(ItemEvent e)
	{
		JMenuItem source = (JMenuItem)(e.getSource());
		
		if (source.getText().equals("Zoom"))
		{
			view.setZoom(!view.zoom());  // toggle zoom setting
			sVGRenderer.render();
		}	
	}

	//--------------------------------------------------------------

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JMenuItem source = (JMenuItem)(e.getSource());
			
		if (source.getText().equals("Load"))
		{
			final String filePath = selectFile(); 
			if (filePath != "")
			{
				loadSVG(filePath);  // load the selected SVG file
				sVGRenderer.render();
			}
		}	
	}

	//--------------------------------------------------------------

	/**
	 * Run the app.
	 */
	void run()
	{
		frame = new JFrame();
		view = new SVGView();
		sVGRenderer = new SVGRenderer(parser, view);
		
		frame.setContentPane(view);		
		frame.setJMenuBar(new MainMenu(view, this, this));  // set menu after view created
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	    frame.setSize(600, 900);
		frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

	//--------------------------------------------------------------
	
	/**
	 * Main routine.
	 * @param args Program arguments. 
	 */
	public static void main(String[] args) 
	{	
		SVGApp app = new SVGApp();
		app.run();
	}

	//--------------------------------------------------------------

}
