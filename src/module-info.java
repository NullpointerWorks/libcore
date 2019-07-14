/**
 * @version 1.0.0
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
