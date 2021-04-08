/**
 * This is free and unencumbered software released into the public domain.
 * <http://unlicense.org/>
 * Nullpointer Works (2021)
 * @version 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
module libnpw.core
{
	requires transitive java.desktop;
	exports com.nullpointerworks.core;
	exports com.nullpointerworks.core.buffer;
	exports com.nullpointerworks.core.buffer.concurrency;
	exports com.nullpointerworks.core.input;
}
