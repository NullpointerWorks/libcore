/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2017-2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core.buffer;

/**
 * Buffer object abstract for all libnpw-core buffers.
 */
abstract class AbstractBuffer<B>
{
	protected int width = 0;
	protected int height = 0;
	protected int length = 0;
	
	public AbstractBuffer(int w, int h)
	{
		setBuffer(w,h);
	}
	
	protected void setBuffer(int w, int h)
	{
		width 		= w;
		height 		= h;
		length		= w*h;
	}
	
    public int getWidth()
    {return width;}
    
    public int getHeight()
    {return height;}
    
    public int getLength()
    {return length;}
    
    public abstract B copy();

    public abstract void free();
    
}
