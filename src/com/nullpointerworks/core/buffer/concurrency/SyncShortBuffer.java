/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core.buffer.concurrency;

import com.nullpointerworks.core.buffer.ShortBuffer;

public class SyncShortBuffer extends ShortBuffer
{
	private SyncShortBuffer lock = this;
	
	public SyncShortBuffer(int w, int h) 
	{
		super(w, h);
	}
	
	public SyncShortBuffer(int w, int h, short i) 
	{
		super(w, h, i);
	}
	
	public void resize(int w, int h)
	{
		resize(w,h,(short)0);
	}
	
	public void resize(int w, int h, short value)
	{
		synchronized (lock)
		{
			super.resize(w, h, value);
		}
	}

	// ==========================================
	
	public void clear(short c) 
	{
		synchronized (lock)
		{
			super.clear(c);
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
	
	public short grab(int i)
	{
		synchronized (lock)
		{
			return super.grab(i);
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
	
	public void plot(short[] c)
	{
		synchronized (lock)
		{
			super.plot(c);
		}
	}
	
	public void plot(int i, short c)
	{
		synchronized (lock)
		{
			super.plot(i, c);
		}
	}
	
	public void plot(int x, int y, short c)
	{
		synchronized (lock)
		{
			super.plot(x, y, c);
		}
	}
}