/*
 * This is free and unencumbered software released into the public domain.
 * <http://unlicense.org/>
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.core.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * Input device for a mouse. Implements {@code java.awt.event}'s {@code MouseListener}, 
 * {@code MouseMotionListener} and {@code MouseWheelListener}.
 * It keeps track of mouse buttons and the scrolling wheel.
 * interface.
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public class MouseInput 
implements MouseListener, MouseMotionListener, MouseWheelListener
{
	private final int NUM_BUTTONS = 32;
	private boolean[] c_buttons;
	private boolean[] p_buttons;
	
	private int mouse_x, mouse_y, scroll, prescroll;
	private float inv_scale;
	
	/**
	 * Creates a new {@code MouseInput} instance that 
	 * handles up to 32 different input codes. 
	 * @since 1.0.0
	 */
	public MouseInput()
	{
		c_buttons = new boolean[NUM_BUTTONS];
		p_buttons = new boolean[NUM_BUTTONS];
		
		mouse_x = 0;
		mouse_y = 0;
		scroll = 0;
		prescroll = 0;
		inv_scale = 1f;
	}
	
	/**
	 * Input registers need to be updated after each input 
	 * cycle in a typical game loop. This method shifts
	 * the current input register to the backup register.
	 * @since 1.0.0
	 */
	public void update()
	{
		scroll = prescroll;
		prescroll = 0;
		for (int l=NUM_BUTTONS-1; l>=0; l--)
		{
			p_buttons[l] = c_buttons[l];
		}
	}
	
	/**
	 * Returns true if the given input code is present in the primary register.
	 * @param id - the input code
	 * @return true if the given input code is present in the primary register
	 * @since 1.0.0
	 */
	public boolean isClicked(int id)
	{
		id = clamp(0, id, NUM_BUTTONS);
		return c_buttons[id];
	}
	
	/**
	 * Returns true if the given input code is no longer present in the 
	 * primary register, but is still present in the backup register.
	 * @param id - the input code
	 * @return true if the given input code is no longer present in the 
	 * primary register, but is still present in the backup register
	 * @since 1.0.0
	 */
	public boolean isClickedUp(int id)
	{
		id = clamp(0, id, NUM_BUTTONS);
		return !c_buttons[id] && p_buttons[id];
	}
	
	/**
	 * Returns true if the given input code is present in the primary 
	 * register, but is not yet present in the backup register.
	 * @param id - the input code
	 * @return true if the given input code is present in the primary 
	 * register, but is not yet present in the backup register
	 * @since 1.0.0
	 */
	public boolean isClickedDown(int id)
	{
		id = clamp(0, id, NUM_BUTTONS);
		return c_buttons[id] && !p_buttons[id];
	}
	
	/**
	 * Returns true if the mouse is scrolling.
	 * @return true if the mouse is scrolling
	 * @since 1.0.0
	 */
	public boolean isScrolling() 
	{
		return scroll != 0;
	}
	
	/**
	 * Returns the mouse X position in the host component.
	 * @return the mouse X position in the host component
	 * @since 1.0.0
	 */
	public int getMouseX() 
	{
		return mouse_x;
	}
	
	/**
	 * Returns the mouse Y position in the host component.
	 * @return the mouse Y position in the host component
	 * @since 1.0.0
	 */
	public int getMouseY() 
	{
		return mouse_y;
	}
	
	/**
	 * Returns the mouse scrolling steps performed in the host component.
	 * @return the mouse scrolling steps performed in the host component
	 * @since 1.0.0
	 */
	public int getScroll() 
	{
		return scroll;
	}
	
	/**
	 * Set the mouse movement scaling factor. This is useful when the mouse 
	 * needs to cover a larger or smaller area. The scaling multiplier may 
	 * not be less or equal to 0.
	 * @param scale - the scaling multiplier
	 * @since 1.0.0
	 */
	public void setScale(float scale)
	{
		if (scale <= 0f) scale = 0.0001f;
		inv_scale = 1f / scale;
	}
	
	private int rnd(float x) 
	{
		return (int)(x+0.5f);
	}
	
	private int clamp(int min, int x, int max) 
	{
		if (x<min)return min;
		return (x>max)?max:x;
	}
	
	// ==============================================
	// MOUSE MOTION
	// ==============================================
	
	@Override
	public void mouseMoved(MouseEvent e) 
	{
		mouse_x = rnd(e.getX()*inv_scale);
		mouse_y = rnd(e.getY()*inv_scale);
	}

	@Override
	public void mouseDragged(MouseEvent e) 
	{
		mouseMoved(e);
	}
	
	// ==============================================
	// MOUSE WHEEL
	// ==============================================
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) 
	{
		int sc = e.getWheelRotation();
		prescroll += e.getScrollAmount() * sc;
	}
	
	// ==============================================
	// MOUSE BUTTON
	// ==============================================
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		c_buttons[e.getButton()] = true;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) 
	{
		c_buttons[e.getButton()] = false;
	}
	
	@Override
	public void mouseEntered(MouseEvent e) 
	{
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) 
	{
		
	}
}
