/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core.buffer;

public class DoubleBuffer extends AbstractBuffer<DoubleBuffer>
{
	protected double[] pixels;
	
	public DoubleBuffer(int w, int h) 
	{
		super(w, h);
		pixels = new double[length];
	}
	
	public DoubleBuffer(int w, int h, double clear) 
	{
		super(w, h);
		pixels = new double[length];
		clear(clear);
	}
	
	protected void createBuffer(int w, int h)
	{
		setBuffer(w,h);
		pixels = new double[length];
	}
	
	/**
	 * resize the buffer from its original size.<br>
	 * this may lead to data loss if the new width<br>
	 * and/or height are less than the original dimensions
	 */
	public void resize(int w, int h)
	{
		resize(w,h,0d);
	}

	/**
	 * resize the buffer from its original size and<br>
	 * clear it with the given color.<br>
	 * this may lead to data loss if the new width<br>
	 * and/or height are less than the original dimensions
	 */
	public void resize(int w, int h, double value)
	{
		int bw = width, bh = height;
		int bindex = 0, bstride = bw;
		int index = 0, stride = w;
		
		double[] buff = pixels;
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
     * copy over an float[] with colors to draw
     */
    public void plot(double[] c)
    {
    	for (int i=0, l=c.length; i<l; i++)
    	{
    		pixels[i] = c[i];
    	}
    }
    
    /*
     * plot a single pixel to the canvas
     */
	public void plot(int i, double c)
	{
		pixels[i] = c;
	}
	
    /*
     * plot a single pixel to the canvas
     */
	public void plot(int x, int y, double c)
	{
		int i = x + y*width;
		pixels[i] = c;
	}

	/*
	 * 
	 */
	public void clear(double c) 
	{
		for (int i=0, l=pixels.length; i<l; i++)
			pixels[i] = c;
	}

	/*
	 * returns all floats in the texture
	 */
	public double[] content() 
	{
		return pixels;
	}
	
	/**
	 * get the color from the buffer with an index
	 */
	public double grab(int i)
	{
		return pixels[i];
	}
	
	/*
	 * get the color from a texture
	 */
	public double grab(int x, int y)
	{
		return pixels[x + y*width];
	}
	
	/*
	 * get the float color from the texture using u,v and w values<br>
	 */
	public double grab(float u, float v, float w)
	{
		float sc = 0.999f*w;
		int x = (int)(sc*u*width);
		int y = (int)(sc*v*height);
		return pixels[x + y*width];
	}
	
	/**
	 * get a clean copy of this buffer
	 */
	public DoubleBuffer copy()
	{
		DoubleBuffer buff = new DoubleBuffer(width,height);
		buff.plot(this.content());
		return buff;
	}
}
