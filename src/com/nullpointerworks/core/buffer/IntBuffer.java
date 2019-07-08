/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core.buffer;

import com.nullpointerworks.core.buffer.abstracts.AbstractBuffer;

public class IntBuffer extends AbstractBuffer<IntBuffer>
{
	protected int[] pixels;
	
	/**
	 * 
	 * @param width
	 * @param height
	 */
	public IntBuffer(int w, int h) 
	{
		super(w,h);
		createBuffer(w,h);
	}
	
	/**
	 * 
	 * @param width
	 * @param height
	 * @param clear
	 */
	public IntBuffer(int w, int h, int clear) 
	{
		super(w,h);
		createBuffer(w,h);
		clear(clear);
	}
	
	protected void createBuffer(int w, int h)
	{
		setBuffer(w,h);
		pixels = new int[length];
	}
	
	/**
	 * resize the buffer from its original size.<br>
	 * this may lead to data loss if the new width<br>
	 * and/or height are less than the original dimensions
	 */
	public void resize(int w, int h)
	{
		resize(w,h,0);
	}

	/**
	 * resize the buffer from its original size and<br>
	 * clear it with the given integer.<br>
	 * this may lead to data loss if the new width<br>
	 * and/or height are less than the original dimensions
	 */
	public void resize(int w, int h, int value)
	{
		int bw = width, bh = height;
		int bindex = 0, bstride = bw;
		int index = 0, stride = w;
		
		int[] buff = pixels;
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
		
		buff = null;
	}
	
	/**
	 * clear buffers for garbage collection
	 */
	public void free()
	{
		pixels = null;
	}
	
	/**
	 * all elements are set to the given value
	 */
	public void clear(int c) 
	{
		for (int i=0, l=pixels.length; i<l; i++)
			pixels[i] = c;
	}
	
	/**
	 * returns all pixels in the texture
	 */
	public int[] content() 
	{
		return pixels;
	}
	
	/**
	 * get a clean copy of this buffer
	 */
	public IntBuffer copy()
	{
		IntBuffer buff = new IntBuffer(width,height);
		buff.plot(this.content());
		return buff;
	}

	// ==========================================
	
	/**
     * copy over an int[] with colors to draw
     */
    public void plot(int[] c)
    {
    	for (int i=0, l=c.length; i<l; i++)
    	{
    		pixels[i] = c[i];
    	}
    }
    
    /**
     * plot a single pixel to the canvas
     */
	public void plot(int i, int c)
	{
		pixels[i] = c;
	}
	
    /**
     * plot a single pixel to the canvas
     */
	public void plot(int x, int y, int c)
	{
		int i = x + y*width;
		pixels[i] = c;
	}

	// ==========================================
	
	/**
	 * get the color from the buffer with an index
	 */
	public int grab(int i)
	{
		return pixels[i];
	}

	/**
	 * get the color from the buffer
	 */
	public int grab(int x, int y)
	{
		return pixels[x + y*width];
	}
	
	/**
	 * get the color from a texture using u,v and w values<br>
	 */
	public int grab(float u, float v, float w)
	{
		float sc = 0.999f*w;
		int x = (int)(sc*u*width);
		int y = (int)(sc*v*height);
		return pixels[x + y*width];
	}
}
