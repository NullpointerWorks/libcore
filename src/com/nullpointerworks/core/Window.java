/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core;

import static com.nullpointerworks.core.WindowMode.*;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.nullpointerworks.core.buffer.IntBuffer;
import com.nullpointerworks.core.input.KeyboardInput;
import com.nullpointerworks.core.input.MouseInput;

/**
 * A container for a {@code javax.swing.JFrame} which serves as the primary window 
 * handler. The Window does not extend any other subclass. To gain access to 
 * the underlying frame use; {@code window.getFrame();}
 * 
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public class Window
{
	private int width 			= 800;
	private int height 			= 600;
	private String title		= "";
	private boolean show 		= false;
	private Monitor monitor		= null;
	
	private JFrame jf_frame;
	private IntBuffer icon;
	private DrawCanvas canvas;
	private MouseInput mi		= null;
	private KeyboardInput ki	= null;
	
	/**
	 * Create a window with the given internal dimensions. 
	 * By default, this window will have no title, be set 
	 * in windowed mode and appear on monitor ID 1.
	 * @param width - the width of the display
	 * @param height - the height of the display
	 * @since 1.0.0
	 */
	public Window(int width, int height)
	{
		this(width,height,"",WINDOWED,Monitor.getDisplay(0));
	}
	
	/**
	 * Create a window with the given internal dimensions 
	 * and title. By default, this window will be in windowed 
	 * mode and appear on monitor ID 1.
	 * @param width - the width of the display
	 * @param height - the height of the display
	 * @param title - the window title
	 * @since 1.0.0
	 */
	public Window(int width, int height, String title)
	{
		this(width,height,title,WINDOWED,Monitor.getDisplay(0));
	}
	
	/**
	 * Create a window with the given internal dimensions, 
	 * title and windowing mode. By default, this window 
	 * will appear on monitor ID 1.
	 * @param width - the width of the display
	 * @param height - the height of the display
	 * @param title - the window title
	 * @param mode - the windowing mode
	 * @since 1.0.0
	 */
	public Window(int width, int height, String title, WindowMode mode)
	{
		this(width,height,title,mode,Monitor.getDisplay(0));
	}
	
	/**
	 * Create a window with the given internal dimensions, 
	 * title and target monitor. By default, this window 
	 * will be set in windowed mode.
	 * @param width - the width of the display
	 * @param height - the height of the display
	 * @param title - the window title
	 * @param monitor - the target monitor
	 * @since 1.0.0
	 */
	public Window(int width, int height, String title, Monitor monitor)
	{
		this(width,height,title,WINDOWED,monitor);
	}
	
	/**
	 * Create a window with the dimensions of the target monitor. 
	 * This constructor is meant for fullscreen type modes only. 
	 * When a non-fullscreen type mode is used, the dimensions 
	 * will default to 800 by 600 pixels.
	 * @param title - the window title
	 * @param mode - the windowing mode
	 * @param monitor - the target monitor
	 * @since 1.0.0
	 */
	public Window(String title, WindowMode mode, Monitor monitor)
	{
		if (mode != BORDERLESSFULL || mode != FULLSCREEN)
		{
			this.width=800;
			this.height=600;
		}
		
		this.width=0;
		this.height=0;
		this.title=title;
		this.monitor=monitor;
		create(mode);
	}
	
	/**
	 * Create a window with the given internal dimensions, 
	 * title, windowing mode and target monitor.
	 * @param width - the width of the display
	 * @param height - the height of the display
	 * @param title - the window title
	 * @param mode - the windowing mode
	 * @param monitor - the target monitor
	 * @since 1.0.0
	 */
	public Window(int width, int height, String title, WindowMode mode, Monitor monitor)
	{
		this.width=width;
		this.height=height;
		this.title=title;
		this.monitor=monitor;
		create(mode);
	}
	
	/**
	 * Swap the integer content of the given array to the 
	 * display buffer. It's up to the user to make sure that 
	 * the length of the array matches the area of the display.
	 * Each integer is assumed to follow ARGB order at a bit 
	 * depth of 8 bits.
	 * @param pixels - the array of integers that represent colors
	 * @since 1.0.0
	 */
	public void swap(int[] pixels)
	{
		canvas.swap(pixels);
	}
	
	/**
	 * Delegate to display the internal {@code JFrame} object. Set 
	 * true to make the window appear, false to hide it.
	 * @param show - to show, or not to show
	 * @since 1.0.0
	 */
	public void setVisible(boolean show)
	{
		this.show = show;
		jf_frame.setVisible(show);
	}
	
	/**
	 * Set the windowing mode for this window.
	 * @param mode - determines if a window will be framed and 
	 * what dimensions it might take 
	 * @since 1.0.0
	 */
	public void setWindowMode(WindowMode mode)
	{
		create(mode);
	}
	
	/**
	 * This method sets a {@code DrawCanvas} object as the primary 
	 * recipient to render to. When creating a new window, this 
	 * class creates a {@code PanelCanvas} instance as the primary 
	 * rendering recipient. Use this method only if you want 
	 * the window to utilize non-standard rendering code.
	 * @param canvas - the drawing canvas to be used in this window.
	 * @since 1.0.0
	 * @see DrawCanvas
	 * @see PanelCanvas
	 */
	public void setDrawCanvas(DrawCanvas canvas)
	{
		if (canvas!=null)
		{
			canvas.component().setEnabled(false);
			if (jf_frame!=null)
			{
				jf_frame.remove(canvas.component());
			}
		}
		this.canvas=canvas;
		
		addInputDevice(mi);
		
		width = canvas.width();
		height = canvas.height();
		
		jf_frame.add(canvas.component());
		jf_frame.pack();
		jf_frame.validate();
	}
	
	/**
	 * Provide an {@code IntBuffer} object that contains the ARGB image 
	 * data to be set as the icon for the window. No icon will 
	 * be set if the input image is null.
	 * @param image - the integer buffer to be used
	 * @since 1.0.0
	 */
	public void setIcon(IntBuffer image)
	{
		if (image==null) return;
		if (icon!=null) icon.free();
		icon = image.copy();
		InputStream stream = toInputStream(icon);
		setIcon(stream);
	}
	
	/**
	 * Provide an input stream linking to image data to be used 
	 * as the icon for the window.
	 * @param stream - input stream linking to the image data
	 * @since 1.0.0
	 */
	public void setIcon(InputStream stream)
	{
		try
		{
			ImageIcon icon = new ImageIcon(ImageIO.read(stream));
			jf_frame.setIconImage(icon.getImage());
			stream.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Add a mouse input to the window to read user input. The
	 * {@code MouseInput} will be attached as a listener to the DrawCanvas.
	 * This input device will be moved to another canvas when 
	 * swapping to another canvas.
	 * @param mouseinput - the mouse input object
	 * @since 1.0.0
	 */
	public void addInputDevice(MouseInput mouseinput)
	{
		if (mouseinput==null) return;
		this.mi=mouseinput;
		Component c = canvas.component();
		c.addMouseListener(mi);
		c.addMouseMotionListener(mi);
		c.addMouseWheelListener(mi);
	}
	
	/**
	 * Add a keyboard input to the window to read user input. The
	 * {@code KeyboardInput} will be added as a listener to the internal 
	 * frame. This input device will be moved to another frame when
	 * creating a new window.
	 * @param keyboardinput - the keyboard input object
	 * @since 1.0.0
	 */
	public void addInputDevice(KeyboardInput keyboardinput)
	{
		if (keyboardinput==null) return;
		this.ki=keyboardinput;
		jf_frame.addKeyListener(ki);
	}
	
	/**
	 * Add a {@code java.awt.event.WindowListener} to the internal frame. 
	 * When creating a new window, all window listeners will be 
	 * added to the new window. This method is intended to be used
	 * by the libnpw.game library which has a {@code LoopAdapter} that 
	 * track the state of the application and inform the window.
	 * @param windowlistener - the window listener to be added
	 * @since 1.0.0
	 */
	public void addWindowListener(WindowListener windowlistener)
	{
		jf_frame.addWindowListener(windowlistener);
	}
	
	// =============================================================
	
	/**
	 * Returns the {@code DrawCanvas} rendering surface.
	 * @return the {@code DrawCanvas} rendering surface
	 * @since 1.0.0
	 */
	public DrawCanvas getDrawCanvas() {return canvas;}
	
	/**
	 * Returns the {@code JFrame} which is the primary windowing container.
	 * @return the {@code JFrame} which is the primary windowing container
	 * @since 1.0.0
	 */
	public JFrame getFrame() {return jf_frame;}
	
	/**
	 * Returns the {@code MouseInput} object attached to the window.
	 * @return the {@code MouseInput} object attached to the window
	 * @since 1.0.0
	 */
	public MouseInput getMouse() {return mi;}
	
	/**
	 * Returns the {@code KeyboardInput} object attached to the window.
	 * @return the {@code KeyboardInput} object attached to the window
	 * @since 1.0.0
	 */
	public KeyboardInput getKeyboard() {return ki;}
	
	/**
	 * Returns the internal rendering width of the window.
	 * @return the internal rendering width of the window
	 * @since 1.0.0
	 */
	public int getWidth() {return width;}
	
	/**
	 * Returns the internal rendering height of the window.
	 * @return the internal rendering height of the window
	 * @since 1.0.0
	 */
	public int getHeight() {return height;}
	
	/**
	 * Returns the title of the window.
	 * @return the title of the window
	 * @since 1.0.0
	 */
	public String getTitle() {return title;}
	
	// =============================================================
	
	/*
	 * create a new window with the selected attributes, modes and dimension
	 */
	private void create(WindowMode mode)
	{
		if (canvas!=null)
		{
			canvas.component().setEnabled(false);
			if (jf_frame!=null)
			{
				jf_frame.remove(canvas.component());
			}
		}
		
		WindowListener[] wls = null;
		
		if (jf_frame!=null)
		{
			wls = jf_frame.getWindowListeners();
			jf_frame.setVisible(false);
			jf_frame.dispose();
		}
		
		/*
		 * setup window
		 */
		jf_frame 	= new JFrame();
		jf_frame.setFocusTraversalKeysEnabled(false);
		jf_frame.setResizable(false);
		jf_frame.setTitle(title);
		if (wls!=null) for (WindowListener wl : wls) jf_frame.addWindowListener(wl);
		setIcon(icon);
		windowmode(mode);
		addInputDevice(ki);
		jf_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		canvas 		= new PanelCanvas(width,height);
		setDrawCanvas(canvas);
		jf_frame.pack();
		jf_frame.setVisible(show);
	}
	
	/*
	 * Set the windowing mode
	 */
	private void windowmode(WindowMode m)
	{
		jf_frame.setVisible(false);
		
		if (m == WINDOWED)
		{
			jf_frame.setUndecorated(false);
			Point p = monitor.getGraphicsEnvironment().getCenterPoint();
			jf_frame.setLocation(p.x - (width>>1), p.y - (height>>1) );
		}
		
		if (m == BORDERLESS)
		{
			jf_frame.setUndecorated(true);
			Point p = monitor.getGraphicsEnvironment().getCenterPoint();
			jf_frame.setLocation(p.x - (width>>1), p.y - (height>>1) );
		}
		
		if (m == BORDERLESSFULL)
		{
			jf_frame.setUndecorated(true);
			Rectangle r = monitor.getGraphicsDevice().getDefaultConfiguration().getBounds();
			jf_frame.setLocation(r.x,r.y);
			jf_frame.setLocationRelativeTo(null);
			width = monitor.getWidth();
			height = monitor.getHeight();
		}
		
		if (m == FULLSCREEN)
		{
			if (monitor.getGraphicsDevice().isFullScreenSupported())
			{
				jf_frame.setUndecorated(true);
				Rectangle r = monitor.getGraphicsDevice().getDefaultConfiguration().getBounds();
				jf_frame.setLocation(r.x,r.y);
				jf_frame.setLocationRelativeTo(null);
				monitor.getGraphicsDevice().setFullScreenWindow(jf_frame);
				width = monitor.getWidth();
				height = monitor.getHeight();
			}
			else
			{
				windowmode(WindowMode.WINDOWED);
			}
		}
	}
	
	/*
	 * Returns a AWT BufferedImage object from the given Core IntBuffer object
	 */
	private BufferedImage toBufferedImage(IntBuffer ib)
	{
		int w = ib.getWidth();
		int h = ib.getHeight();
		BufferedImage bi = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
		int[] px = ((DataBufferInt)bi.getRaster().getDataBuffer()).getData();
		int[] sc = ib.content();
		for (int l = w*h - 1; l>=0; l--) px[l] = sc[l];
		return bi;
	}
	
	/*
	 * Returns a java.io InputStream object from the given Core IntBuffer object
	 */
	private InputStream toInputStream(IntBuffer ib)
	{
		BufferedImage bi = toBufferedImage(ib);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
        try 
        {
			ImageIO.write(bi, "png", os);
		} 
        catch (IOException e) 
        {
			e.printStackTrace();
		}
		return new ByteArrayInputStream(os.toByteArray());
	}
}
