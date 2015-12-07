package com.huayi.doupo.logic.core.filter.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ConcurrentHashMap;

public class ChannelStateMap {

	private static ConcurrentHashMap<Channel, EncryptHeader> chcMap = new ConcurrentHashMap<Channel, EncryptHeader>();

	public static void add(ChannelHandlerContext ctx, EncryptHeader e) {
		if (!chcMap.containsKey(ctx.channel())) {
			chcMap.put(ctx.channel(), e);
		}
	}

	public static void remove(ChannelHandlerContext ctx) {
		if (chcMap.containsKey(ctx.channel())) {
			chcMap.remove(ctx.channel());
		}
	}

	public static void clear() {
		chcMap.clear();
	}

	public static boolean containsKey(ChannelHandlerContext ctx) {
		return chcMap.containsKey(ctx.channel());
	}

	public static EncryptHeader get(ChannelHandlerContext ctx) {
		if (chcMap.containsKey(ctx.channel())) {
			return chcMap.get(ctx.channel());
		}
		return null;
	}

}
