/*
 * This is free and unencumbered software released into the public domain.
 * <http://unlicense.org/>
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.core.buffer.concurrency;

import com.nullpointerworks.core.buffer.DoubleBuffer;

/**
 * A thread safe implementation of the {@code DoubleBuffer}.
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public class SyncDoubleBuffer extends DoubleBuffer
{
	private SyncDoubleBuffer lock = this;

	/**
	 * Creates a buffer with the given dimensions. Each 
	 * element of this buffer will be defaulted to false.
	 * @param width - the width of the buffer
	 * @param height - the height of the buffer
	 * @since 1.0.0
	 */
	public SyncDoubleBuffer(int width, int height) 
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
	public SyncDoubleBuffer(int width, int height, double init) 
	{
		super(width, height, init);
	}

	// ==========================================
	
	public void clear(double value) 
	{
		synchronized (lock)
		{
			super.clear(value);
		}
	}
	
	public double[] content() 
	{
		synchronized (lock)
		{
			return super.content();
		}
	}
	
	public SyncDoubleBuffer copy()
	{
		synchronized (lock)
		{
			SyncDoubleBuffer buff = new SyncDoubleBuffer(width,height);
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
	
	public double grab(int index)
	{
		synchronized (lock)
		{
			return super.grab(index);
		}
	}
	
	public double grab(int x, int y)
	{
		synchronized (lock)
		{
			return super.grab(x, y);
		}
	}
	
	public double grab(float u, float v, float w)
	{
		synchronized (lock)
		{
			return super.grab(u,v,w);
		}
	}
	
	// ==========================================
	
	public void plot(double[] values)
	{
		synchronized (lock)
		{
			super.plot(values);
		}
	}
	
	public void plot(int index, double value)
	{
		synchronized (lock)
		{
			super.plot(index, value);
		}
	}
	
	public void plot(int x, int y, double value)
	{
		synchronized (lock)
		{
			super.plot(x, y, value);
		}
	}
}