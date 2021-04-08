/*
 * This is free and unencumbered software released into the public domain.
 * <http://unlicense.org/>
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.core.buffer.concurrency;

import com.nullpointerworks.core.buffer.LongBuffer;

/**
 * A thread safe implementation of the {@code LongBuffer}.
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public class SyncLongBuffer extends LongBuffer
{
	private SyncLongBuffer lock = this;

	/**
	 * Creates a buffer with the given dimensions. Each 
	 * element of this buffer will be defaulted to false.
	 * @param width - the width of the buffer
	 * @param height - the height of the buffer
	 * @since 1.0.0
	 */
	public SyncLongBuffer(int width, int height) 
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
	public SyncLongBuffer(int width, int height, long init) 
	{
		super(width, height, init);
	}

	// ==========================================
	
	public void clear(long value) 
	{
		synchronized (lock)
		{
			super.clear(value);
		}
	}
	
	public long[] content() 
	{
		synchronized (lock)
		{
			return super.content();
		}
	}
	
	public SyncLongBuffer copy()
	{
		synchronized (lock)
		{
			SyncLongBuffer buff = new SyncLongBuffer(width,height);
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
	
	public long grab(int index)
	{
		synchronized (lock)
		{
			return super.grab(index);
		}
	}
	
	public long grab(int x, int y)
	{
		synchronized (lock)
		{
			return super.grab(x, y);
		}
	}
	
	public long grab(float u, float v, float w)
	{
		synchronized (lock)
		{
			return super.grab(u,v,w);
		}
	}
	
	// ==========================================
	
	public void plot(long[] values)
	{
		synchronized (lock)
		{
			super.plot(values);
		}
	}
	
	public void plot(int index, long value)
	{
		synchronized (lock)
		{
			super.plot(index, value);
		}
	}
	
	public void plot(int x, int y, long value)
	{
		synchronized (lock)
		{
			super.plot(x, y, value);
		}
	}
}