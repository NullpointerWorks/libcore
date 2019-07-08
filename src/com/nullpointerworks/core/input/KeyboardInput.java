/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Michiel Drost - nullpointerworks
 */
public class KeyboardInput implements KeyListener
{
	private final int NUM_KEYS = 1024;
	private boolean[] c_keys;
	private boolean[] p_keys;
	
	public KeyboardInput()
	{
		c_keys = new boolean[NUM_KEYS];
		p_keys = new boolean[NUM_KEYS];
	}
	
	public void update()
	{
		for (int l=NUM_KEYS-1; l>=0; l--)
		{
			p_keys[l] = c_keys[l];
			//c_keys[l] = false;
		}
	}
	
	public boolean isKey(int code)
	{
		code = clamp(0, code, NUM_KEYS);
		return c_keys[code];
	}
	
	public boolean isKeyDown(int code)
	{
		code = clamp(0, code, NUM_KEYS);
		return c_keys[code] && !p_keys[code];
	}
	
	public boolean isKeyUp(int code)
	{
		code = clamp(0, code, NUM_KEYS);
		return !c_keys[code] && p_keys[code];
	}
	
	private int clamp(int min, int x, int max) 
	{
		if (x<min)return min;
		return (x>max)?max:x;
	}
	
	// ==============================================
	// KEYBOARD BUTTON
	// ==============================================
	
	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		c_keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		c_keys[e.getKeyCode()] = false;
	}
	
}
