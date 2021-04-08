/*
 * This is free and unencumbered software released into the public domain.
 * <http://unlicense.org/>
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.core.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Input device for a keyboard. Implements {@code java.awt.event.KeyListener} 
 * interface. 
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public class KeyboardInput implements KeyListener
{
	private final int NUM_KEYS = 1024;
	private boolean[] c_keys;
	private boolean[] p_keys;
	
	/**
	 * Creates a new {@code KeyboardInput} instance that 
	 * handles up to 1024 different key codes. 
	 * @since 1.0.0
	 */
	public KeyboardInput()
	{
		c_keys = new boolean[NUM_KEYS];
		p_keys = new boolean[NUM_KEYS];
	}
	
	/**
	 * Input registers need to be updated after each input 
	 * cycle in a typical game loop. This method shifts
	 * the current input register to the backup register.
	 * @since 1.0.0
	 */
	public void update()
	{
		for (int l=NUM_KEYS-1; l>=0; l--)
		{
			p_keys[l] = c_keys[l];
		}
	}
	
	/**
	 * Returns true is the given key code is set active in
	 * the key register. 
	 * @return true is the given key code is set active in 
	 * the key register
	 * @param code - the input key code
	 * @since 1.0.0
	 */
	public boolean isKey(int code)
	{
		code = clamp(0, code, NUM_KEYS-1);
		return c_keys[code];
	}
	
	/**
	 * Returns true is the given key code is set active in
	 * the key register, but not in the backup register.
	 * @return true is the given key code is set active in 
	 * the key register, but not in the backup register
	 * @param code - the input key code
	 * @since 1.0.0
	 */
	public boolean isKeyDown(int code)
	{
		code = clamp(0, code, NUM_KEYS-1);
		return c_keys[code] && !p_keys[code];
	}
	
	/**
	 * Returns true is the given key code is no longer active in
	 * the key register, but is active in the backup register.
	 * @return true is the given key code is no longer active in
	 * the key register, but is active in the backup register
	 * @param code - the input key code
	 * @since 1.0.0
	 */
	public boolean isKeyUp(int code)
	{
		code = clamp(0, code, NUM_KEYS-1);
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
