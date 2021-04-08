/*
 * This is free and unencumbered software released into the public domain.
 * <http://unlicense.org/>
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.core.buffer.concurrency;

import com.nullpointerworks.core.buffer.IntBuffer;

/**
 * A thread safe implementation of the {@code IntBuffer}.
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public class SyncIntBuffer extends IntBuffer
{
	private SyncIntBuffer lock = this;

	/**
	 * Creates a buffer with the given dimensions. Each 
	 * element of this buffer will be defaulted to false.
	 * @param width - the width of the buffer
	 * @param height - the height of the buffer
	 * @since 1.0.0
	 */
	public SyncIntBuffer(int width, int height) 
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
	public SyncIntBuffer(int width, int height, int init) 
	{
		super(width, height, init);
	}

	// ==========================================
	
	public void clear(int value) 
	{
		synchronized (lock)
		{
			super.clear(value);
		}
	}
	
	public int[] content() 
	{
		synchronized (lock)
		{
			return super.content();
		}
	}
	
	public SyncIntBuffer copy()
	{
		synchronized (lock)
		{
			SyncIntBuffer buff = new SyncIntBuffer(width,height);
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
	
	public int grab(int index)
	{
		synchronized (lock)
		{
			return super.grab(index);
		}
	}
	
	public int grab(int x, int y)
	{
		synchronized (lock)
		{
			return super.grab(x, y);
		}
	}
	
	public int grab(float u, float v, float w)
	{
		synchronized (lock)
		{
			return super.grab(u,v,w);
		}
	}
	
	// ==========================================
	
	public void plot(int[] values)
	{
		synchronized (lock)
		{
			super.plot(values);
		}
	}
	
	public void plot(int index, int value)
	{
		synchronized (lock)
		{
			super.plot(index, value);
		}
	}
	
	public void plot(int x, int y, int value)
	{
		synchronized (lock)
		{
			super.plot(x, y, value);
		}
	}
}