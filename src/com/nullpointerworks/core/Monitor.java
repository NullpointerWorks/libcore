/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

/**
 * The Monitor class provides access the JVM's graphics environment, which in turn 
 * provides access to all connected monitors.
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public class Monitor 
{
	private static Monitor[] de = null;
	
	/**
	 * Returns a Monitor object with the details of the selected display device.
	 * @param id is the monitor identity on the host OS. Provide the identity - 1.
	 * @return a Monitor object with the details of the selected display device
	 * @since 1.0.0
	 */
	public static Monitor getDisplay(int id)
	{
		if (de==null) de = getDisplay();
		id = clamp(0, id, de.length - 1);
		return de[id];
	}
	
	/**
	 * Returns an array of Monitor objects for each connected monitor.
	 * @return an array of Monitor objects for each connected monitor
	 * @since 1.0.0
	 */
	public static Monitor[] getDisplay()
	{
		if (de!=null) return de;
		GraphicsEnvironment ge;
		GraphicsDevice[] gd;
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		gd = ge.getScreenDevices();
		de = new Monitor[gd.length];
		for (int i=0,l=gd.length;i<l;i++)
		{
			Monitor d = new Monitor();
			d.deviceID = i;
			d.ge = ge;
			d.gd = gd[i];
			d.dm = d.gd.getDisplayMode();
			de[i] = d;
		}
		return de;
	}
	
	/*
	 * private clamping function. Rather this, than having a libnpw.math dependency.
	 */
	private static int clamp(int lower, int x, int upper)
	{
		int x1 = (x<lower)?lower:x;
		return (x1<upper)?x1:upper;
	}
	
	// ======================================
	
	private GraphicsEnvironment ge;
	public GraphicsDevice gd;
	public DisplayMode dm;
	public int deviceID;
	
	/**
	 * Returns the host's graphics environment associated with the monitor.
	 * @return the host's graphics environment associated with the monitor
	 * @since 1.0.0
	 */
	public GraphicsEnvironment getGraphicsEnvironment() {return ge;}
	
	/**
	 * Returns the host's graphics device associated with the monitor.
	 * @return the host's graphics device associated with the monitor
	 * @since 1.0.0
	 */
	public GraphicsDevice getGraphicsDevice() {return gd;}
	
	/**
	 * Returns the display mode for this monitor. This contains bit depth, width, height, etc.
	 * @return the display mode for this monitor
	 * @since 1.0.0
	 */
	public DisplayMode getDisplayMode() {return dm;}
	
	/**
	 * Returns the width of the display, in pixels.
	 * @return the width of the display, in pixels
	 * @since 1.0.0
	 */
	public int getWidth() {return dm.getWidth();}
	
	/**
	 * Returns the height of the display, in pixels.
	 * @return the height of the display, in pixels
	 * @since 1.0.0
	 */
	public int getHeight() {return dm.getHeight();}

	/**
	 * Returns the device ID on the host OS.
	 * @return the device ID on the host OS
	 * @since 1.0.0
	 */
	public int getDeviceID() {return deviceID;}
}
