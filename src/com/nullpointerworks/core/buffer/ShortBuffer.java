/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core.buffer;

import com.nullpointerworks.core.buffer.abstracts.AbstractBuffer;

/**
 * a byte buffer for 8-bit graphics display<br>
 * <br>
 * Regular color palette for 8-bit is:<br>
 * Bit  : 7  6  5  4  3  2  1  0<br>
 * Data : R  R  R  G  G  G  B  B<br>
 */
public class ShortBuffer extends AbstractBuffer<ShortBuffer>
{
	protected short[] pixels;

	public ShortBuffer(int w, int h) 
	{
		super(w, h);
		createBuffer(w,h);
	}
	
	public ShortBuffer(int w, int h, short clear) 
	{
		super(w,h);
		createBuffer(w,h);
		clear(clear);
	}
	
	protected void createBuffer(int w, int h)
	{
		setBuffer(w,h);
		pixels = new short[length];
	}
	
	/**
	 * resize the buffer from its original size.<br>
	 * this may lead to data loss if the new width<br>
	 * and/or height are less than the original dimensions
	 */
	public void resize(int w, int h)
	{
		resize(w,h,(short)0);
	}

	/**
	 * resize the buffer from its original size and<br>
	 * clear it with the given color.<br>
	 * this may lead to data loss if the new width<br>
	 * and/or height are less than the original dimensions
	 */
	public void resize(int w, int h, short value)
	{
		int bw = width, bh = height;
		int bindex = 0, bstride = bw;
		int index = 0, stride = w;
		
		short[] buff = pixels;
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
    public void plot(short[] c)
    {
    	for (int i=0, l=c.length; i<l; i++)
    	{
    		pixels[i] = c[i];
    	}
    }
    
    /*
     * plot a single pixel to the canvas
     */
	public void plot(int i, short c)
	{
		pixels[i] = c;
	}
	
    /*
     * plot a single pixel to the canvas
     */
	public void plot(int x, int y, short c)
	{
		int i = x + y*width;
		pixels[i] = c;
	}
	
	/*
	 * 
	 */
	public void clear(short c) 
	{
		for (int i=0, l=pixels.length; i<l; i++)
			pixels[i] = c;
	}
	
	/*
	 * returns all pixels in the texture
	 */
	public short[] content() 
	{
		return pixels;
	}
	
	/**
	 * get the color from the buffer with an index
	 */
	public short grab(int i)
	{
		return pixels[i];
	}

	/*
	 * get the color from a texture
	 */
	public short grab(int x, int y)
	{
		return pixels[x + y*width];
	}
	
	/*
	 * get the color from a texture using u,v and w values<br>
	 */
	public short grab(float u, float v, float w)
	{
		float sc = 0.999f*w;
		int x = (int)(sc*u*width);
		int y = (int)(sc*v*height);
		return pixels[x + y*width];
	}
	
	/**
	 * get a clean copy of this buffer
	 */
	public ShortBuffer copy()
	{
		ShortBuffer buff = new ShortBuffer(width,height);
		buff.plot(this.content());
		return buff;
	}
}
