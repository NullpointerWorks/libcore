/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core.buffer.concurrency;

import com.nullpointerworks.core.buffer.LongBuffer;

public class SyncLongBuffer extends LongBuffer
{
	private SyncLongBuffer lock = this;
	
	public SyncLongBuffer(int w, int h) 
	{
		super(w, h);
	}
	
	public SyncLongBuffer(int w, int h, long i) 
	{
		super(w, h, i);
	}
	
	public void resize(int w, int h)
	{
		resize(w,h,0);
	}
	
	public void resize(int w, int h, long value)
	{
		synchronized (lock)
		{
			super.resize(w, h, value);
		}
	}

	// ==========================================
	
	public void clear(long c) 
	{
		synchronized (lock)
		{
			super.clear(c);
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
			SyncLongBuffer buff = new SyncLongBuffer(width, height);
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
	
	public long grab(int i)
	{
		synchronized (lock)
		{
			return super.grab(i);
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
	
	public void plot(long[] c)
	{
		synchronized (lock)
		{
			super.plot(c);
		}
	}
	
	public void plot(int i, long c)
	{
		synchronized (lock)
		{
			super.plot(i, c);
		}
	}
	
	public void plot(int x, int y, long c)
	{
		synchronized (lock)
		{
			super.plot(x, y, c);
		}
	}
}