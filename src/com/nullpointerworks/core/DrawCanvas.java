/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core;

import java.awt.Component;

/**
 * A rendering interface that provides a handle for a Window to display.
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public interface DrawCanvas
{
	/**
	 * Returns the width in pixels of the drawing canvas.
	 * @return the width in pixels
	 * @since 1.0.0
	 */
	int width();
	
	/**
	 * Returns the height in pixels of the drawing canvas.
	 * @return the height in pixels
	 * @since 1.0.0
	 */
	int height();
	
	/**
	 * Swap the content of the provided array with the content held by the drawing canvas. The swapped content will be displayed when new frames are requested. Each integer is an ARGB color at 8 bit depth.
	 * @param pixels - an integer array for the same size as the rendering surface
	 * @since 1.0.0
	 */
	void swap(int[] pixels);
	
	/**
	 * Returns a java.awt.Component to which the canvas is drawing on. It can be added as a UI element to the {@code JFrame} in the window.
	 * @return the component for the window to display
	 * @since 1.0.0
	 */
	Component component();
}
 