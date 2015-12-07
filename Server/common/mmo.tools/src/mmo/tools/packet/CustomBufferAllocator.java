package mmo.tools.packet;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.apache.mina.core.buffer.AbstractIoBuffer;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.IoBufferAllocator;

public class CustomBufferAllocator implements IoBufferAllocator {

	public IoBuffer allocate(int capacity, boolean direct) {
		return wrap(allocateNioBuffer(capacity, direct));
	}

	public ByteBuffer allocateNioBuffer(int capacity, boolean direct) {
		ByteBuffer nioBuffer;
		if (direct) {
			nioBuffer = ByteBuffer.allocateDirect(capacity);
		} else {
			nioBuffer = ByteBuffer.allocate(capacity);
		}
		return nioBuffer;
	}

	public IoBuffer wrap(ByteBuffer nioBuffer) {
		return new CustomBuffer(nioBuffer);
	}

	public void dispose() {
		// Do nothing
	}

	private class CustomBuffer extends AbstractIoBuffer {
		private ByteBuffer buf;

		protected CustomBuffer(ByteBuffer buf) {
			super(CustomBufferAllocator.this, buf.capacity());
			this.buf = buf;
			buf.order(ByteOrder.BIG_ENDIAN);
		}

		protected CustomBuffer(CustomBuffer parent, ByteBuffer buf) {
			super(parent);
			this.buf = buf;
		}

		@Override
		public ByteBuffer buf() {
			return buf;
		}

		@Override
		protected void buf(ByteBuffer buf) {
			this.buf = buf;
		}

		@Override
		protected IoBuffer duplicate0() {
			return new CustomBuffer(this, this.buf.duplicate());
		}

		@Override
		protected IoBuffer slice0() {
			return new CustomBuffer(this, this.buf.slice());
		}

		@Override
		protected IoBuffer asReadOnlyBuffer0() {
			return new CustomBuffer(this, this.buf.asReadOnlyBuffer());
		}

		@Override
		public byte[] array() {
			return buf.array();
		}

		@Override
		public int arrayOffset() {
			return buf.arrayOffset();
		}

		@Override
		public boolean hasArray() {
			return buf.hasArray();
		}

		@Override
		public void free() {
		}
	}
}
