package exp.nullpointerworks.core.window;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JWindow;

/**
 * @author Michiel Drost - nullpointerworks
 */
public class BackWindow extends JWindow implements MouseMotionListener
{
	private static final long serialVersionUID = 1L;
	
	private Robot bot = null;
	private int wndX = 0;
	private int wndY = 0;
	
	public BackWindow()
	{
		this.setOpacity(0.005f);
		this.addMouseMotionListener(this);
		this.setAlwaysOnTop(true);
		
		try
		{
			bot = new Robot();
		}
		catch (AWTException e) 
		{
			//Log.err("Backdrop window robot not astablished. Mouse interception may not work as desired!");
			e.printStackTrace();
		}
	}
	
	public void setMouseLocation(int x, int y)
	{
		wndX = x;
		wndY = y;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) 
	{
		mouseMoved(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		bot.mouseMove(wndX,  wndY);
	}
}
