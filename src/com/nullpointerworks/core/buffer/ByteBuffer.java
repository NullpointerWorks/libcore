/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core.buffer;

/**
 * a byte buffer for 8-bit graphics display<br>
 * <br>
 * Regular color palette for 8-bit is:<br>
 * Bit  : 7  6  5  4  3  2  1  0<br>
 * Data : R  R  R  G  G  G  B  B<br>
 */
public class ByteBuffer extends AbstractBuffer<ByteBuffer>
{
	protected byte[] pixels;

	public ByteBuffer(int w, int h) 
	{
		super(w, h);
		createBuffer(w,h);
	}
	
	public ByteBuffer(int w, int h, byte clear) 
	{
		super(w,h);
		createBuffer(w,h);
		clear(clear);
	}
	
	protected void createBuffer(int w, int h)
	{
		setBuffer(w,h);
		pixels = new byte[length];
	}
	
	/**
	 * resize the buffer from its original size.<br>
	 * this may lead to data loss if the new width<br>
	 * and/or height are less than the original dimensions
	 */
	public void resize(int w, int h)
	{
		resize(w,h,(byte)0);
	}

	/**
	 * resize the buffer from its original size and<br>
	 * clear it with the given color.<br>
	 * this may lead to data loss if the new width<br>
	 * and/or height are less than the original dimensions
	 */
	public void resize(int w, int h, byte value)
	{
		int bw = width, bh = height;
		int bindex = 0, bstride = bw;
		int index = 0, stride = w;
		
		byte[] buff = pixels;
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
    public void plot(byte[] c)
    {
    	for (int i=0, l=c.length; i<l; i++)
    	{
    		pixels[i] = c[i];
    	}
    }
    
    /*
     * plot a single pixel to the canvas
     */
	public void plot(int i, byte c)
	{
		pixels[i] = c;
	}
	
    /*
     * plot a single pixel to the canvas
     */
	public void plot(int x, int y, byte c)
	{
		int i = x + y*width;
		pixels[i] = c;
	}
	
	/*
	 * 
	 */
	public void clear(byte c) 
	{
		for (int i=0, l=pixels.length; i<l; i++)
			pixels[i] = c;
	}
	
	/*
	 * returns all pixels in the texture
	 */
	public byte[] content() 
	{
		return pixels;
	}
	
	/**
	 * get the color from the buffer with an index
	 */
	public byte grab(int i)
	{
		return pixels[i];
	}

	/*
	 * get the color from a texture
	 */
	public byte grab(int x, int y)
	{
		return pixels[x + y*width];
	}
	
	/*
	 * get the color from a texture using u,v and w values<br>
	 */
	public byte grab(float u, float v, float w)
	{
		float sc = 0.999f*w;
		int x = (int)(sc*u*width);
		int y = (int)(sc*v*height);
		return pixels[x + y*width];
	}
	
	/**
	 * get a clean copy of this buffer
	 */
	public ByteBuffer copy()
	{
		ByteBuffer buff = new ByteBuffer(width,height);
		buff.plot(this.content());
		return buff;
	}
}
