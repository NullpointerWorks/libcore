/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
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
