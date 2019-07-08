/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core.buffer.concurrency;

import com.nullpointerworks.core.buffer.FloatBuffer;

public class SyncFloatBuffer extends FloatBuffer
{
	private SyncFloatBuffer lock = this;
	
	public SyncFloatBuffer(int w, int h) 
	{
		super(w, h);
	}
	
	public SyncFloatBuffer(int w, int h, float i) 
	{
		super(w, h, i);
	}
	
	public void resize(int w, int h)
	{
		resize(w,h,0f);
	}
	
	public void resize(int w, int h, float value)
	{
		synchronized (lock)
		{
			super.resize(w, h, value);
		}
	}

	// ==========================================
	
	public void clear(float c) 
	{
		synchronized (lock)
		{
			super.clear(c);
		}
	}
	
	public float[] content() 
	{
		synchronized (lock)
		{
			return super.content();
		}
	}
	
	public SyncFloatBuffer copy()
	{
		synchronized (lock)
		{
			SyncFloatBuffer buff = new SyncFloatBuffer(width,height);
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
	
	public float grab(int i)
	{
		synchronized (lock)
		{
			return super.grab(i);
		}
	}
	
	public float grab(int x, int y)
	{
		synchronized (lock)
		{
			return super.grab(x, y);
		}
	}
	
	public float grab(float u, float v, float w)
	{
		synchronized (lock)
		{
			return super.grab(u,v,w);
		}
	}
	
	// ==========================================
	
	public void plot(float[] c)
	{
		synchronized (lock)
		{
			super.plot(c);
		}
	}
	
	public void plot(int i, float c)
	{
		synchronized (lock)
		{
			super.plot(i, c);
		}
	}
	
	public void plot(int x, int y, float c)
	{
		synchronized (lock)
		{
			super.plot(x, y, c);
		}
	}
}