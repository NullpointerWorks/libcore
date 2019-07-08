/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core.window;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JPanel;

/**
 * @author Michiel Drost - nullpointerworks
 */
public class PanelCanvas extends JPanel implements DrawCanvas
{
	private static final long serialVersionUID = 8800843681463260252L;

	private int width = 0;
	private int height = 0;
	private BufferedImage bi;
	private int[] pixels;
	
	public PanelCanvas(int w, int h) 
	{
		width = w;
		height = h;
		setSize(w, h);
		setPreferredSize(getSize());
		setMaximumSize(getSize());
		setMinimumSize(getSize());
		
		bi = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		pixels = getContent(bi);
	}

	@Override
	public int width()
	{
		return width;
	}

	@Override
	public int height()
	{
		return height;
	}
	
	@Override
	public Component component()
	{
		return this;
	}
	
	@Override
	public void swap(int[] p)
	{
		if (p.length != pixels.length) return;
		int l=pixels.length-1;
		for (;l>=0;l--)
		{
			pixels[l] = p[l];
		}
		paint(this.getGraphics());
	}
	
	@Override
	public void paint(Graphics g) 
	{         
		g.drawImage(bi, 0, 0, width, height, null);
	}
	
	/*
	 * Returns the integer array content form the given AWT BufferedImage
	 */
	private int[] getContent(BufferedImage bi)
	{
		return ((DataBufferInt)bi.getRaster().getDataBuffer()).getData();
	}
}