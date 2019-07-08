/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core.buffer.concurrency;

import com.nullpointerworks.core.buffer.ByteBuffer;

public class SyncByteBuffer extends ByteBuffer
{
	private SyncByteBuffer lock = this;
	
	public SyncByteBuffer(int w, int h) 
	{
		super(w, h);
	}
	
	public SyncByteBuffer(int w, int h, byte i) 
	{
		super(w, h, i);
	}
	
	public void resize(int w, int h)
	{
		resize(w,h,(byte)0);
	}
	
	public void resize(int w, int h, byte value)
	{
		synchronized (lock)
		{
			super.resize(w, h, value);
		}
	}

	// ==========================================
	
	public void clear(byte c) 
	{
		synchronized (lock)
		{
			super.clear(c);
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
	
	public byte grab(int i)
	{
		synchronized (lock)
		{
			return super.grab(i);
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
	
	public void plot(byte[] c)
	{
		synchronized (lock)
		{
			super.plot(c);
		}
	}
	
	public void plot(int i, byte c)
	{
		synchronized (lock)
		{
			super.plot(i, c);
		}
	}
	
	public void plot(int x, int y, byte c)
	{
		synchronized (lock)
		{
			super.plot(x, y, c);
		}
	}
}