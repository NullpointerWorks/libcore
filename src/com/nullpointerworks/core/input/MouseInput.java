/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * @author Michiel Drost - nullpointerworks
 */
public class MouseInput implements MouseListener, MouseMotionListener, MouseWheelListener
{
	private final int NUM_BUTTONS = 5;
	private boolean[] c_buttons;
	private boolean[] p_buttons;
	
	private int mouse_x, mouse_y, scroll, prescroll;
	private float inv_scale;
	
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
	
	public void update()
	{
		scroll = prescroll;
		prescroll = 0;
		for (int l=NUM_BUTTONS-1; l>=0; l--)
		{
			p_buttons[l] = c_buttons[l];
			//c_buttons[l] = false;
		}
	}
	
	public boolean isClicked(int id)
	{
		id = clamp(0, id, NUM_BUTTONS);
		return c_buttons[id];
	}
	
	public boolean isClickedUp(int id)
	{
		id = clamp(0, id, NUM_BUTTONS);
		return !c_buttons[id] && p_buttons[id];
	}
	
	public boolean isClickedDown(int id)
	{
		id = clamp(0, id, NUM_BUTTONS);
		return c_buttons[id] && !p_buttons[id];
	}
	
	public boolean isScrolling() 
	{
		return scroll != 0;
	}
	
	public int getMouseX() 
	{
		return mouse_x;
	}

	public int getMouseY() 
	{
		return mouse_y;
	}

	public int getScroll() 
	{
		return scroll;
	}
	
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
