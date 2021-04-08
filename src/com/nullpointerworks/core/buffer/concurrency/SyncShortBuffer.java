/*
 * This is free and unencumbered software released into the public domain.
 * <http://unlicense.org/>
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.core.buffer.concurrency;

import com.nullpointerworks.core.buffer.ShortBuffer;

/**
 * A thread safe implementation of the {@code ShortBuffer}.
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public class SyncShortBuffer extends ShortBuffer
{
	private SyncShortBuffer lock = this;

	/**
	 * Creates a buffer with the given dimensions. Each 
	 * element of this buffer will be defaulted to false.
	 * @param width - the width of the buffer
	 * @param height - the height of the buffer
	 * @since 1.0.0
	 */
	public SyncShortBuffer(int width, int height) 
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
	public SyncShortBuffer(int width, int height, short init) 
	{
		super(width, height, init);
	}

	// ==========================================
	
	public void clear(byte value) 
	{
		synchronized (lock)
		{
			super.clear(value);
		}
	}
	
	public short[] content() 
	{
		synchronized (lock)
		{
			return super.content();
		}
	}
	
	public SyncShortBuffer copy()
	{
		synchronized (lock)
		{
			SyncShortBuffer buff = new SyncShortBuffer(width,height);
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
	
	public short grab(int index)
	{
		synchronized (lock)
		{
			return super.grab(index);
		}
	}
	
	public short grab(int x, int y)
	{
		synchronized (lock)
		{
			return super.grab(x, y);
		}
	}
	
	public short grab(float u, float v, float w)
	{
		synchronized (lock)
		{
			return super.grab(u,v,w);
		}
	}
	
	// ==========================================
	
	public void plot(short[] values)
	{
		synchronized (lock)
		{
			super.plot(values);
		}
	}
	
	public void plot(int index, short value)
	{
		synchronized (lock)
		{
			super.plot(index, value);
		}
	}
	
	public void plot(int x, int y, short value)
	{
		synchronized (lock)
		{
			super.plot(x, y, value);
		}
	}
}