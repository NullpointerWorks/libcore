/*
 * This is free and unencumbered software released into the public domain.
 * <http://unlicense.org/>
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.core;

/**
 * This enumeration contains three modes for a libnpw-core Window. 
 * Items include; WINDOWED, BORDERLESS, FULLSCREEN and BORDERLESSFULL mode.
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public enum WindowMode
{
	/**
	 * Make a window display with a surrounding frame. The 
	 * layout and design of the frame is determined by 
	 * the host OS.
	 * @since 1.0.0
	 */
	WINDOWED,
	/**
	 * Make a window appear without a surrounding frame. 
	 * This means there's no interface to close the window 
	 * other than forcing the main thread to stop. Be sure 
	 * to have a way to do this in your application.
	 * @since 1.0.0
	 */
	BORDERLESS,
	/**
	 * Make a window appear without a surrounding frame 
	 * and have the dimensions to fit the target monitor.
	 * Since there's no frame, there's no interface to 
	 * close the window other than forcing the main thread 
	 * to stop. Be sure to have a way to do this in your 
	 * application. Fullscreen mode support depends on
	 * the host PC's graphics card. Some PC's may not
	 * allow for fullscreen.
	 * @since 1.0.0
	 */
	FULLSCREEN,
	/**
	 * A blend of fullscreen and borderless mode. Make 
	 * a window appear without a surrounding frame and 
	 * have the dimensions to fit the target monitor.
	 * Since there's no frame, there's no interface to 
	 * close the window other than forcing the main thread 
	 * to stop. Be sure to have a way to do this in your 
	 * application. Since this mode does not require graphics 
	 * card support directly, it's often faster established 
	 * than fullscreen mode.
	 * @since 1.0.0
	 */
	BORDERLESSFULL
}
