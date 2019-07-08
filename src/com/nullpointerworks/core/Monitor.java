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
 * Monitor device class provides access the JVM's graphics 
 * environment, which in turn provides access to all connected monitors.
 * @author Michiel Drost - nullpointerworks
 */
public class Monitor 
{
	private static Monitor[] de = null;
	
	/**
	 * Returns a Monitor object with the details of the selected display device.
	 * @param id 
	 * @return a Monitor object with the details of the selected display device
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
			d.width = d.dm.getWidth();
			d.height = d.dm.getHeight();
			de[i] = d;
		}
		return de;
	}
	
	private static int clamp(int lower, int x, int upper)
	{
		int x1 = (x<lower)?lower:x;
		return (x1<upper)?x1:upper;
	}
	
	// ======================================
	
	public GraphicsEnvironment ge;
	public GraphicsDevice gd;
	public DisplayMode dm;
	public int width, height, deviceID;
}
