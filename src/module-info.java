module libcore
{
	requires java.base;
	requires transitive java.desktop;
	exports com.nullpointerworks.core;
	exports com.nullpointerworks.core.buffer;
	exports com.nullpointerworks.core.buffer.concurrency;
	exports com.nullpointerworks.core.input;
}
