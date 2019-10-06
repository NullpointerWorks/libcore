/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JPanel;

/**
 * Contains a {@code BufferedImage} as a basic rendering surface. 
 * This implementation of {@code DrawCanvas} extends a {@code JPanel} 
 * and is also returned from the {@code component()} method.
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public class PanelCanvas extends JPanel implements DrawCanvas
{
	private static final long serialVersionUID = 8800843681463260252L;
	private int width = 0;
	private int height = 0;
	private BufferedImage bi;
	private int[] pixels;
	
	/**
	 * Creates a drawing surface of the given dimensions. It will 
	 * accepts colors as integers in ARGB format at 8 bit depth.
	 * @param width - the width of the drawing surface
	 * @param height - the height of the drawing surface
	 * @since 1.0.0
	 */
	public PanelCanvas(int width, int height) 
	{
		this.width=width;
		this.height=height;
		setSize(width, height);
		setPreferredSize(getSize());
		setMaximumSize(getSize());
		setMinimumSize(getSize());
		
		bi = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
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
	public void swap(int[] pix)
	{
		if (pix.length != pixels.length) return;
		int l=pixels.length-1;
		for (;l>=0;l--)
		{
			pixels[l] = pix[l];
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