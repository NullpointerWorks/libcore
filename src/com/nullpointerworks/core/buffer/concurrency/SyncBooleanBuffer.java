/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core.buffer.concurrency;

import com.nullpointerworks.core.buffer.BooleanBuffer;

/**
 * A thread safe implementation of the {@code BooleanBuffer}.
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public class SyncBooleanBuffer extends BooleanBuffer
{
	private SyncBooleanBuffer lock = this;

	/**
	 * Creates a buffer with the given dimensions. Each 
	 * element of this buffer will be defaulted to false.
	 * @param width - the width of the buffer
	 * @param height - the height of the buffer
	 * @since 1.0.0
	 */
	public SyncBooleanBuffer(int width, int height) 
	{
		super(width, height);
	}

	/**
	 * Creates a buffer with the given dimensions and 
	 * initial value.
	 * @param width - the width of the buffer
	 * @param height - the height of the buffer
	 * @param init - the initial value
	 * @since 1.0.0
	 */
	public SyncBooleanBuffer(int width, int height, boolean init) 
	{
		super(width, height, init);
	}

	// ==========================================
	
	public void clear(boolean value) 
	{
		synchronized (lock)
		{
			super.clear(value);
		}
	}
	
	public boolean[] content() 
	{
		synchronized (lock)
		{
			return super.content();
		}
	}
	
	public SyncBooleanBuffer copy()
	{
		synchronized (lock)
		{
			SyncBooleanBuffer buff = new SyncBooleanBuffer(width,height);
			buff.plot(super.content());
			return buff;
		}
	}
	
	public void free()
	{
		synchronized (lock)
		{
			super.free();
		}
	}
	
	// ==========================================
	
	public boolean grab(int index)
	{
		synchronized (lock)
		{
			return super.grab(index);
		}
	}
	
	public boolean grab(int x, int y)
	{
		synchronized (lock)
		{
			return super.grab(x, y);
		}
	}
	
	public boolean grab(float u, float v, float w)
	{
		synchronized (lock)
		{
			return super.grab(u,v,w);
		}
	}
	
	// ==========================================
	
	public void plot(boolean[] values)
	{
		synchronized (lock)
		{
			super.plot(values);
		}
	}
	
	public void plot(int index, boolean value)
	{
		synchronized (lock)
		{
			super.plot(index, value);
		}
	}
	
	public void plot(int x, int y, boolean value)
	{
		synchronized (lock)
		{
			super.plot(x, y, value);
		}
	}
}