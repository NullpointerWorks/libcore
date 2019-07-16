/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
/**
 * @version 1.0.1
 * @author Michiel Drost - Nullpointer Works
 */
module libnpw.core
{
	requires java.base;
	requires transitive java.desktop;
	exports com.nullpointerworks.core;
	exports com.nullpointerworks.core.window;
	exports com.nullpointerworks.core.buffer;
	exports com.nullpointerworks.core.buffer.concurrency;
	exports com.nullpointerworks.core.input;
}
