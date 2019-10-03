/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2017-2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core.buffer;

/**
 * Buffer object abstraction for all libnpw-core buffers.
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
abstract class AbstractBuffer<B>
{
	protected int width = 0;
	protected int height = 0;
	protected int length = 0;
	
	/**
	 * Sets the dimensions and area of the buffer. 
	 * @param width - the width of the buffer
	 * @param height - the height of the buffer
	 * @since 1.0.0
	 */
	protected void setBuffer(int width, int height)
	{
		this.width=width;
		this.height=height;
		length=width*height;
	}
	
	/**
	 * Returns the width of the buffer.
	 * @return the width of the buffer
	 * @since 1.0.0
	 */
    public int getWidth()
    {return width;}
    
	/**
	 * Returns the height of the buffer.
	 * @return the height of the buffer
	 * @since 1.0.0
	 */
    public int getHeight()
    {return height;}
    
	/**
	 * Returns the length of the buffer.
	 * @return the length of the buffer
	 * @since 1.0.0
	 */
    public int getLength()
    {return length;}
    
	/**
	 * All buffers have the ability to provide a reference 
	 * free copy. This returned object contain the same 
	 * values as the original.
	 * @return a reference free copy of this buffer
	 * @since 1.0.0
	 */
    public abstract B copy();
    
	/**
	 * Clears the content of this buffer, and sets all internals 
	 * to {@code null}. Garbage collection will do the rest. This 
	 * object this can be used after this method has been invoked.
	 * @since 1.0.0
	 */
    public abstract void free();
}
