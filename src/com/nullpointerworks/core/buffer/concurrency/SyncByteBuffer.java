/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core.buffer.concurrency;

import com.nullpointerworks.core.buffer.ByteBuffer;

/**
 * A thread safe implementation of the {@code ByteBuffer}.
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public class SyncByteBuffer extends ByteBuffer
{
	private SyncByteBuffer lock = this;

	/**
	 * Creates a buffer with the given dimensions. Each 
	 * element of this buffer will be defaulted to false.
	 * @param width - the width of the buffer
	 * @param height - the height of the buffer
	 * @since 1.0.0
	 */
	public SyncByteBuffer(int width, int height) 
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
	public SyncByteBuffer(int width, int height, byte init) 
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
	
	public byte[] content() 
	{
		synchronized (lock)
		{
			return super.content();
		}
	}
	
	public SyncByteBuffer copy()
	{
		synchronized (lock)
		{
			SyncByteBuffer buff = new SyncByteBuffer(width,height);
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
	
	public byte grab(int index)
	{
		synchronized (lock)
		{
			return super.grab(index);
		}
	}
	
	public byte grab(int x, int y)
	{
		synchronized (lock)
		{
			return super.grab(x, y);
		}
	}
	
	public byte grab(float u, float v, float w)
	{
		synchronized (lock)
		{
			return super.grab(u,v,w);
		}
	}
	
	// ==========================================
	
	public void plot(byte[] values)
	{
		synchronized (lock)
		{
			super.plot(values);
		}
	}
	
	public void plot(int index, byte value)
	{
		synchronized (lock)
		{
			super.plot(index, value);
		}
	}
	
	public void plot(int x, int y, byte value)
	{
		synchronized (lock)
		{
			super.plot(x, y, value);
		}
	}
}