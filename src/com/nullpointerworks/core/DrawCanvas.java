/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core;

import java.awt.Component;

/**
 * Drawing interface for a libnpw.core window. 
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public interface DrawCanvas
{
	/**
	 * Returns the width of the drawing canvas.
	 * @return the width of the drawing canvas
	 * @since 1.0.0
	 */
	int width();
	
	/**
	 * Returns the height of the drawing canvas.
	 * @return the height of the drawing canvas
	 * @since 1.0.0
	 */
	int height();
	
	/**
	 * Returns the component that will be added 
	 * to the {@code JFrame} in the window.
	 * @return the component for the window to display
	 * @since 1.0.0
	 */
	Component component();
	
	/**
	 * The given array of integers represent the 
	 * colors to be rendered. Each integer is 
	 * an ARGB at 8 bit depth.
	 * @param pixels - an integer array for the same size as the rendering surface
	 * @since 1.0.0
	 */
	void swap(int[] pixels);
}
 