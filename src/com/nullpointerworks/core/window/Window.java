/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core.window;

import java.awt.Component;
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

import com.nullpointerworks.core.Monitor;
import com.nullpointerworks.core.buffer.IntBuffer;
import com.nullpointerworks.core.input.KeyboardInput;
import com.nullpointerworks.core.input.MouseInput;

/**
 * A container for a javax.swing JFrame which serves as the primary window 
 * handler. The Window does not extend any other subclass. To gain access to 
 * the underlying JFrame use;
 * <pre>
 *       window.getFrame();
 * </pre>
 * 
 * @author Michiel Drost - nullpointerworks
 */
public class Window
{
	private int width 			= 640;
	private int height 			= 480;
	private String title		= "";
	private WindowMode mode 	= WindowMode.WINDOWED;
	private boolean show 		= false;
	
	private JFrame jf_frame;
	private IntBuffer icon;
	private DrawCanvas canvas;
	private MouseInput mi		= null;
	private KeyboardInput ki	= null;
	
	/**
	 * 
	 */
	public Window(int w, int h)
	{
		this(w,h,"",WindowMode.WINDOWED,Monitor.getDisplay(0));
	}
	
	/**
	 * 
	 */
	public Window(int w, int h, String t)
	{
		this(w,h,t,WindowMode.WINDOWED,Monitor.getDisplay(0));
	}
	
	/**
	 * 
	 */
	public Window(int w, int h, String t, WindowMode m)
	{
		this(w,h,t,m,Monitor.getDisplay(0));
	}
	
	/**
	 * 
	 */
	public Window(int w, int h, String t, Monitor d)
	{
		this(w,h,t,WindowMode.WINDOWED,d);
	}
	
	/**
	 * 
	 */
	public Window(int w, int h, String t, WindowMode m, Monitor display)
	{
		width 	= w;
		height 	= h;
		title 	= t;
		mode	= m;
		
		create(mode);
	}

	public void swap(int[] p)
	{
		canvas.swap(p);
	}
	
	// =============================================================
	
	/**
	 * 
	 */
	public void setVisible(boolean show)
	{
		this.show = show;
		jf_frame.setVisible(show);
	}
	
	/**
	 * 
	 */
	public void setWindowMode(WindowMode mode)
	{
		this.mode = mode;
		create(mode);
	}
	
	/**
	 * 
	 */
	public void setDrawCanvas(DrawCanvas dc)
	{
		if (canvas!=null)
		{
			canvas.component().setEnabled(false);
			if (jf_frame!=null)
			{
				jf_frame.remove(canvas.component());
			}
		}
		canvas = dc;
		
		addInputDevice(mi);

		width = canvas.width();
		height = canvas.height();
		
		jf_frame.add(canvas.component());
		jf_frame.pack();
		jf_frame.validate();
	}
	
	/**
	 * Set an IntBuffer as the icon for the JFrame
	 * @throws IOException if an error occurs during reading. 
	 */
	public void setIcon(IntBuffer image)
	{
		if (image==null) return;
		if (icon!=null) icon.free();
		icon = image.copy();
		
		InputStream stream = toInputStream(icon);
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
	 * 
	 */
	public void addInputDevice(MouseInput mi)
	{
		if (mi==null) return;
		this.mi=mi;
		Component c = canvas.component();
		c.addMouseListener(mi);
		c.addMouseMotionListener(mi);
		c.addMouseWheelListener(mi);
	}
	
	/**
	 * 
	 */
	public void addInputDevice(KeyboardInput ki)
	{
		if (ki==null) return;
		this.ki=ki;
		jf_frame.addKeyListener(ki);
	}
	
	/**
	 * 
	 */
	public void addWindowListener(WindowListener wl)
	{
		jf_frame.addWindowListener(wl);
	}
	
	// =============================================================
	
	/**
	 * 
	 */
	public DrawCanvas getDrawCanvas() {return canvas;}
	
	/**
	 * 
	 */
	public JFrame getFrame() {return jf_frame;}
	
	/**
	 * 
	 */
	public MouseInput getMouse() {return mi;}
	
	/**
	 * 
	 */
	public KeyboardInput getKeyboard() {return ki;}
	
	/**
	 * 
	 */
	public int getWidth() {return width;}
	
	/**
	 * 
	 */
	public int getHeight() {return height;}
	
	/**
	 * 
	 */
	public String geTitle() {return title;}
	
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
		
		if (jf_frame!=null)
		{
			jf_frame.setVisible(false);
			jf_frame.dispose();
		}
		
		jf_frame 	= new JFrame();
		canvas 		= new PanelCanvas(width,height);
		
		setIcon(icon);
		windowmode(mode);
		addInputDevice(ki);
		jf_frame.setTitle(title);
		jf_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf_frame.setFocusTraversalKeysEnabled(false);
		jf_frame.setResizable(false);
		setDrawCanvas(canvas);
		jf_frame.setLocationRelativeTo(null);
		jf_frame.setVisible(show);
	}
	
	/**
	 * Set the windowing mode
	 */
	private void windowmode(WindowMode m)
	{
		jf_frame.setVisible(false);
		switch(m)
		{
		case WINDOWED: 
			jf_frame.setUndecorated(false);
			break;
		case BORDERLESS: 
			jf_frame.setUndecorated(true);
			break;
		case FULLSCREEN: 
			jf_frame.setUndecorated(true);
			break;
		}
	}
	
	/**
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
	
	/**
	 * Returns a java.io InputStream object from the given Core IntBuffer object
	 */
	private InputStream toInputStream(IntBuffer ib)
	{
		BufferedImage bi = toBufferedImage(ib);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
        try 
        {
			javax.imageio.ImageIO.write(bi, "png", os);
		} 
        catch (IOException e) 
        {
			e.printStackTrace();
		}
		return new ByteArrayInputStream(os.toByteArray());
	}
}
