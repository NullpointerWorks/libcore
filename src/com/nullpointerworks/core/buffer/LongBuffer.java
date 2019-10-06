/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core.buffer;

/**
 * A buffer implementation that contains a {@code long} array.
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public class LongBuffer extends AbstractBuffer<LongBuffer>
{
	protected long[] values;
	
	/**
	 * Creates a buffer with the given dimensions. Each 
	 * element of this buffer will be defaulted to 0.
	 * @param width - the width of the buffer
	 * @param height - the height of the buffer
	 * @since 1.0.0
	 */
	public LongBuffer(int width, int height) 
	{
		createBuffer(width,height);
	}

	/**
	 * Creates a buffer with the given dimensions and 
	 * initial value.
	 * @param width - the width of the buffer
	 * @param height - the height of the buffer
	 * @param init - the initial value
	 * @since 1.0.0
	 */
	public LongBuffer(int width, int height, long init) 
	{
		createBuffer(width,height);
		clear(init);
	}
	
	/**
	 * Creates a buffer with the given dimensions.
	 * @param width - the width of the buffer
	 * @param height - the height of the buffer
	 * @since 1.0.0
	 */
	protected void createBuffer(int width, int height)
	{
		setBuffer(width,height);
		values = new long[length];
	}
	
	/**
     * Copies the content of the given array onto the buffer.
     * @param array - the array of values to be copied
	 * @since 1.0.0
     */
    public void plot(long[] array)
    {
    	for (int i=0, l=array.length; i<l; i++)
    	{
    		values[i] = array[i];
    	}
    }

    /**
     * Places the given value onto the buffer at the 
     * specified index. This method has no boundary detection. 
     * It is up to the user to make sure the index is within 
     * bounds.
	 * @param index - the index in the buffer
	 * @param value - the value to be placed
	 * @since 1.0.0
     */
    public void plot(int index, long value)
	{
		values[index] = value;
	}
	
    /**
     * Places the given value onto the buffer at the specified 
     * coordinates. This method has no boundary detection. 
     * It is up to the user to make sure the index is within 
     * bounds.
	 * @param x - the location along the width of the buffer
	 * @param y - the location along the height of the buffer
	 * @param value - the value to be placed
	 * @since 1.0.0
     */
	public void plot(int x, int y, long value)
	{
		int i = x + y*width;
		values[i] = value;
	}

	/**
	 * Set the value of the entire buffer to the given value.
	 * @param value - the value to clear the buffer with
	 * @since 1.0.0
	 */
	public void clear(long value) 
	{
		for (int l=length-1; l>=0; l--) values[l] = value;
	}

	/**
	 * Returns the array content of the buffer.
	 * @return the array content of the buffer
	 * @since 1.0.0
	 */
	public long[] content() 
	{
		return values;
	}

	/**
	 * Take a value from the buffer at the specified index. This 
	 * method has no boundary detection. It is up to the user to 
	 * make sure the index is within bounds.
	 * @param index - an index in the buffer array
	 * @return the value from the buffer at the specified index
	 * @since 1.0.0
	 */
	public long grab(int index)
	{
		return values[index];
	}
	
	/**
	 * Take a value from the buffer at the specified coordinates.
	 * This method has no boundary detection. It is up to the user 
	 * to make sure the index is within bounds.
	 * @param x - the location along the width of the buffer
	 * @param y - the location along the height of the buffer
	 * @return the value from the buffer at the specified coordinates
	 * @since 1.0.0
	 */
	public long grab(int x, int y)
	{
		return values[x + y*width];
	}
	
	/**
	 * Take a value from the buffer at the specified UV mapping 
	 * coordinates. The parameter W is a depth scaling value 
	 * that can be kept at 1 if no depth correction is performed.
	 * This method has no boundary detection. It is up to the user 
	 * to make sure the index is within bounds.
	 * @param u - a factor [0-1] along the width of the buffer
	 * @param v - a factor [0-1] along the height of the buffer
	 * @param w - the depth scaling parameter
	 * @return the value from the buffer at the found coordinates
	 * @since 1.0.0
	 */
	public long grab(float u, float v, float w)
	{
		float sc = 0.999f*w;
		int x = (int)(sc*u*width);
		int y = (int)(sc*v*height);
		return values[x + y*width];
	}
	
	@Override
	public void free()
	{
		values = null;
	}

	@Override
	public LongBuffer copy()
	{
		LongBuffer buff = new LongBuffer(width,height);
		buff.plot(this.content());
		return buff;
	}
}
