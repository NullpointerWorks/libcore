/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core.buffer.concurrency;

import com.nullpointerworks.core.buffer.DoubleBuffer;

public class SyncDoubleBuffer extends DoubleBuffer
{
	private SyncDoubleBuffer lock = this;
	
	public SyncDoubleBuffer(int w, int h) 
	{
		super(w, h);
	}
	
	public SyncDoubleBuffer(int w, int h, double i) 
	{
		super(w, h, i);
	}
	
	public void resize(int w, int h)
	{
		resize(w,h,0f);
	}
	
	public void resize(int w, int h, double value)
	{
		synchronized (lock)
		{
			super.resize(w, h, value);
		}
	}

	// ==========================================
	
	public void clear(double c) 
	{
		synchronized (lock)
		{
			super.clear(c);
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
	
	public double grab(int i)
	{
		synchronized (lock)
		{
			return super.grab(i);
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
	
	public void plot(double[] c)
	{
		synchronized (lock)
		{
			super.plot(c);
		}
	}
	
	public void plot(int i, double c)
	{
		synchronized (lock)
		{
			super.plot(i, c);
		}
	}
	
	public void plot(int x, int y, double c)
	{
		synchronized (lock)
		{
			super.plot(x, y, c);
		}
	}
}