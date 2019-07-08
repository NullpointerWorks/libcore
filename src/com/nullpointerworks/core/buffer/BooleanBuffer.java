/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core.buffer;

import com.nullpointerworks.core.buffer.abstracts.AbstractBuffer;

public class BooleanBuffer extends AbstractBuffer<BooleanBuffer> 
{
	protected boolean[] pixels;
	
	public BooleanBuffer(int w, int h) 
	{
		super(w, h);
		pixels = new boolean[length];
	}
	
	public BooleanBuffer(int w, int h, boolean clear) 
	{
		super(w, h);
		pixels = new boolean[length];
		clear(clear);
	}
	
	protected void createBuffer(int w, int h)
	{
		setBuffer(w,h);
		pixels = new boolean[length];
	}
	
	/**
	 * resize the buffer from its original size.<br>
	 * this may lead to data loss if the new width<br>
	 * and/or height are less than the original dimensions
	 */
	public void resize(int w, int h)
	{
		resize(w,h,false);
	}

	/**
	 * resize the buffer from its original size and<br>
	 * clear it with the given color.<br>
	 * this may lead to data loss if the new width<br>
	 * and/or height are less than the original dimensions
	 */
	public void resize(int w, int h, boolean value)
	{
		int bw = width, bh = height;
		int bindex = 0, bstride = bw;
		int index = 0, stride = w;
		
		boolean[] buff = pixels;
		createBuffer(w,h);
		clear(value);

		bh = (bh<h)?bh:h;
		bw = (bw<w)?bw:w;
		
		for (int i=0; i<bh; i++)
    	{
			for (int j=0; j<bw; j++)
	    	{
				pixels[index+j] = buff[bindex+j];
	    	}
			bindex += bstride;
			index += stride;
    	}
		buff=null;
	}
	
	/*
	 * clear buffers for garbage collection
	 */
	public void free()
	{
		pixels = null;
	}
	
	/*
     * copy over an int[] with colors to draw
     */
    public void plot(boolean[] c)
    {
    	for (int i=0, l=c.length; i<l; i++)
    	{
    		pixels[i] = c[i];
    	}
    }

    /*
     * plot a single pixel to the canvas
     */
	public void plot(int i, boolean c)
	{
		pixels[i] = c;
	}
	
    /*
     * plot a single pixel to the canvas
     */
	public void plot(int x, int y, boolean c)
	{
		int i = x + y*width;
		pixels[i] = c;
	}
	
	/*
	 * 
	 */
	public void clear(boolean c) 
	{
		for (int i=0, l=pixels.length; i<l; i++)
			pixels[i] = c;
	}
	
	/*
	 * returns all pixels in the texture
	 */
	public boolean[] content() 
	{
		return pixels;
	}
	
	/**
	 * get the color from the buffer with an index
	 */
	public boolean grab(int i)
	{
		return pixels[i];
	}
	
	/*
	 * get the color from a texture
	 */
	public boolean grab(int x, int y)
	{
		return pixels[x + y*width];
	}
	
	/*
	 * get the color from a texture using u,v and w values<br>
	 */
	public boolean grab(float u, float v, float w)
	{
		float sc = 0.999f*w;
		int x = (int)(sc*u*width);
		int y = (int)(sc*v*height);
		return pixels[x + y*width];
	}
	
	/**
	 * get a clean copy of this buffer
	 */
	public BooleanBuffer copy()
	{
		BooleanBuffer buff = new BooleanBuffer(width,height);
		buff.plot(this.content());
		return buff;
	}
}
