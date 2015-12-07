package com.huayi.doupo.logic.core.filter.netty;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class Encrypt {

	// zip处理的缓冲区大小
	protected final static int BUFFER_SIZE = 1024;

	// zip压缩并添加数据头
	public static byte[] doZip(byte[] src) throws IOException {
		return doZip(src, 0, src.length);
	}

	public static byte[] doZip(byte[] src, int off, int len) throws IOException {

		Deflater deflater = new Deflater();
		deflater.setInput(src, off, len);
		deflater.finish();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		baos.write(len >> 24);
		baos.write(len >> 16);
		baos.write(len >> 8);
		baos.write(len >> 0);

		byte[] buffer = new byte[BUFFER_SIZE];
		while (!deflater.finished()) {
			baos.write(buffer, 0, deflater.deflate(buffer));
		}

		byte[] dst = baos.toByteArray();
		baos.close();

		return dst;
	}

	// zip解压缩并移除数据头
	public static byte[] unZip(byte[] src) throws IOException, DataFormatException {
		return unZip(src, 0, src.length);
	}

	public static byte[] unZip(byte[] src, int off, int len) throws IOException, DataFormatException {

		Inflater uncompressor = new Inflater();
		uncompressor.setInput(src, off + 4, len - 4);
		uncompressor.finished();

		int length = (src[off] << 24 & 0xFF000000) | (src[off + 1] << 16 & 0xFF0000) | (src[off + 2] << 8 & 0xFF00) | (src[off + 3] << 0 & 0xFF);

		ByteArrayOutputStream baos = new ByteArrayOutputStream(length);

		byte[] buffer = new byte[BUFFER_SIZE];
		while (!uncompressor.finished()) {
			baos.write(buffer, 0, uncompressor.inflate(buffer));
		}

		byte[] dst = baos.toByteArray();
		baos.close();

		return dst;
	}

	// 异或数据
	public static byte[] doXor(byte[] src) {
		return doXor(src, 0, src.length, new Random().nextInt());
	}

	public static byte[] doXor(byte[] src, int rnd) {
		return doXor(src, 0, src.length, rnd);
	}

	public static byte[] doXor(byte[] src, int off, int len) {
		return doXor(src, off, len, new Random().nextInt());
	}

	public static byte[] doXor(byte[] src, int off, int len, int rnd) {
		int dstLen = (4 + len + (4 - 1)) / 4 * 4;
		int diff = len & 0x00000003;
		if (diff != 0) {
			diff = 4 - diff;
		}
		byte xor0 = (byte) (rnd >> 24);
		byte xor1 = (byte) (rnd >> 16);
		byte xor2 = (byte) (rnd >> 8 & 0xFC | diff);
		byte xor3 = (byte) (rnd >> 0);
		byte[] dst = new byte[dstLen];
		dst[dstLen - 1] = 0;
		dst[dstLen - 2] = 0;
		dst[dstLen - 3] = 0;
		dst[dstLen - 4] = 0;
		System.arraycopy(src, off, dst, 4, len);
		dst[0] = xor0;
		dst[1] = xor1;
		dst[2] = xor2;
		dst[3] = xor3;
		for (int i = 4; i < dstLen; i += 4) {
			dst[i + 0] ^= xor0 += 0x1F;
			dst[i + 1] ^= xor1 += 0x3D;
			dst[i + 2] ^= xor2 += 0x5B;
			dst[i + 3] ^= xor3 += 0x79;
		}
		return dst;
	}

	// 异或数据
	public static byte[] unXor(byte[] src) {
		return unXor(src, 0, src.length);
	}

	public static byte[] unXor(byte[] src, int srcOff, int srcLen) {
		byte xor0 = src[srcOff + 0];
		byte xor1 = src[srcOff + 1];
		byte xor2 = src[srcOff + 2];
		byte xor3 = src[srcOff + 3];
		int diff = xor2 & 0x03;
		int algLen = srcLen - 4;
		int dstLen = algLen - diff;
		byte[] alg = new byte[algLen];
		System.arraycopy(src, srcOff + 4, alg, 0, algLen);
		for (int i = 0; i < algLen; i += 4) {
			alg[i + 0] ^= xor0 += 0x1F;
			alg[i + 1] ^= xor1 += 0x3D;
			alg[i + 2] ^= xor2 += 0x5B;
			alg[i + 3] ^= xor3 += 0x79;
		}
		// TODO 优化此处
		byte[] dst = new byte[dstLen];
		System.arraycopy(alg, 0, dst, 0, dstLen);
		return dst;
	}

	// 加密接口
	public static byte[] encrypt(byte[] src) throws IOException {
		return doXor(doZip(src));
	}

	public static byte[] encrypt(byte[] src, int rnd) throws IOException {
		return doXor(doZip(src), rnd);
	}

	// 解密接口
	public static byte[] decrypt(byte[] src, int off, int len) throws IOException, DataFormatException {
		return unZip(unXor(src, off, len));
	}

	public static void printBytes(byte[] bytes) {
		for (int i = 0; i < bytes.length; ++i) {
			if (i > 0 && (i & 0xF) == 0) {
				System.out.printf("\n%02X ", bytes[i]);
			} else {
				System.out.printf("%02X ", bytes[i]);
			}
		}
		System.out.println();
	}

	/**
	 * @param argssjl
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
	}

}
