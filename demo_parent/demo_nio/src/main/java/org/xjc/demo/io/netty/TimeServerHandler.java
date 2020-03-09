package org.xjc.demo.io.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;

/**
 * Created by xjc on 2019-1-16.
 */
@Slf4j
public class TimeServerHandler extends SimpleChannelInboundHandler {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("remote ip:{}, message:{}", ctx.channel().remoteAddress(), msg.toString());
        ctx.writeAndFlush("receiver message:" + msg.toString());
    }

    /**
     * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("RemoteAddress : {} active !", ctx.channel().remoteAddress());
        ctx.writeAndFlush( "Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\r\n");
        super.channelActive(ctx);
    }
}
