/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core.buffer.concurrency;

import com.nullpointerworks.core.buffer.IntBuffer;

public class SyncIntBuffer extends IntBuffer
{
	private SyncIntBuffer lock = this;
	
	public SyncIntBuffer(int w, int h) 
	{
		super(w, h);
	}
	
	public SyncIntBuffer(int w, int h, int i) 
	{
		super(w, h, i);
	}
	
	public void resize(int w, int h)
	{
		resize(w,h,0);
	}
	
	public void resize(int w, int h, int value)
	{
		synchronized (lock)
		{
			super.resize(w, h, value);
		}
	}

	// ==========================================
	
	public void clear(int c) 
	{
		synchronized (lock)
		{
			super.clear(c);
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
	
	public int grab(int i)
	{
		synchronized (lock)
		{
			return super.grab(i);
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
	
	public void plot(int[] c)
	{
		synchronized (lock)
		{
			super.plot(c);
		}
	}
	
	public void plot(int i, int c)
	{
		synchronized (lock)
		{
			super.plot(i, c);
		}
	}
	
	public void plot(int x, int y, int c)
	{
		synchronized (lock)
		{
			super.plot(x, y, c);
		}
	}
}