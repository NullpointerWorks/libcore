/*
 * This is free and unencumbered software released into the public domain.
 * <http://unlicense.org/>
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.core.input;

import java.awt.event.MouseEvent;

/**
 * A container of event codes for common mouse even triggers.
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public class Mouse 
{
	/**
	 * Indicates no button has been pressed.
	 * @since 1.0.0
	 */
	public static final int NONE	= MouseEvent.NOBUTTON;
	
	/**
	 * Indicates the left mouse button has been pressed.
	 * @since 1.0.0
	 */
	public static final int LEFT 	= MouseEvent.BUTTON1;
	
	/**
	 * Indicates the middle mouse button has been pressed.
	 * @since 1.0.0
	 */
	public static final int MIDDLE 	= MouseEvent.BUTTON2;
	
	/**
	 * Indicates the right mouse button has been pressed.
	 * @since 1.0.0
	 */
	public static final int RIGHT 	= MouseEvent.BUTTON3;
}
