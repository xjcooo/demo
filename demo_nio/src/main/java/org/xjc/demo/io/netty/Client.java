package org.xjc.demo.io.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by xjc on 2019-1-16.
 */
@Slf4j
public class Client {

    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class).handler(new TimeClientHandler());

        try {
            Channel ch = bootstrap.connect("localhost", 8080).sync().channel();
            // 控制台输入
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String line = in.readLine();
                if (line == null)
                    continue;

                ch.writeAndFlush(line + "\r\n");
            }
        } finally {
            group.shutdownGracefully();
        }
    }
}
