/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core.buffer.concurrency;

import com.nullpointerworks.core.buffer.BooleanBuffer;

public class SyncBooleanBuffer extends BooleanBuffer
{
	private SyncBooleanBuffer lock = this;
	
	public SyncBooleanBuffer(int w, int h) 
	{
		super(w, h);
	}
	
	public SyncBooleanBuffer(int w, int h, boolean i) 
	{
		super(w, h, i);
	}
	
	public void resize(int w, int h)
	{
		resize(w,h,false);
	}
	
	public void resize(int w, int h, boolean value)
	{
		synchronized (lock)
		{
			super.resize(w, h, value);
		}
	}

	// ==========================================
	
	public void clear(boolean c) 
	{
		synchronized (lock)
		{
			super.clear(c);
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
	
	public boolean grab(int i)
	{
		synchronized (lock)
		{
			return super.grab(i);
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
	
	public void plot(boolean[] c)
	{
		synchronized (lock)
		{
			super.plot(c);
		}
	}
	
	public void plot(int i, boolean c)
	{
		synchronized (lock)
		{
			super.plot(i, c);
		}
	}
	
	public void plot(int x, int y, boolean c)
	{
		synchronized (lock)
		{
			super.plot(x, y, c);
		}
	}
}