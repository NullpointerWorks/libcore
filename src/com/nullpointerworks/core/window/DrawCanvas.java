/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core.window;

import java.awt.Component;

/**
 * @author Michiel Drost - nullpointerworks
 */
public interface DrawCanvas
{
	int width();
	int height();
	Component component();
	void swap(int[] p);
}
