package org.xjc.demo.io.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 *
 *
 * Created by xjc on 2019-1-16.
 */
@Slf4j
public class NioServerDemo {

    public static void main(String[] args) {
        int port = 8080;

        new Thread(new NioServer(port)).start();
    }

    /**
     * 初始化多路复用器/绑定监听端口
     */
    static class NioServer implements Runnable {
        private Selector selector;
        private volatile boolean stop;

        public NioServer(int port) {
            try {
                ServerSocketChannel channel = ServerSocketChannel.open();
                channel.socket().bind(new InetSocketAddress(port));
                channel.configureBlocking(false);

                selector = Selector.open();
                channel.register(selector, SelectionKey.OP_ACCEPT);
                log.info("nio server started.");
            } catch (IOException e) {
                log.error("non-blocking socket init error.", e);
                System.exit(1);
            }
        }

        @Override
        public void run() {
            while (!stop) {
                try {
                    selector.select(1000);
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    SelectionKey key = null;
                    while (iterator.hasNext()) {
                        key = iterator.next();
                        iterator.remove();
                        // handle key
                        try {
                            handleInput(key);
                        } catch (Exception e) {
                            if (key != null) {
                                key.cancel();
                                if (key.channel() != null)
                                    key.channel().close();
                            }
                        }
                    }
                } catch (IOException e) {
                    log.error("handle selector error", e);
                }
            }
            if (selector != null) {
                try {
                    selector.close();
                } catch (IOException e) {
                    log.error("close selector error", e);
                }
            }
        }

        private void handleInput(SelectionKey key) throws IOException {
            if (key.isValid()) {
                if (key.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                }
                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer bb = ByteBuffer.allocate(1024);
                    int readBytes = channel.read(bb);
                    if (readBytes > 0) {
                        bb.flip();
                        byte[] bytes = new byte[bb.remaining()];
                        String body = new String(bytes, "UTF-8");
                        log.info("receiver message : {}", body);
                        String response = body.startsWith("login") ? "hello," + body.replace("login:", "") : body;
                        log.info("response message : {}", response);
                        doWrite(channel, response);
                    } else if (readBytes < 0) {
                        key.cancel();
                        channel.close();
                    } else {
                        doWrite(channel, "message is empty.");
                    }
                }
            }
        }

        private void doWrite(SocketChannel channel, String response) throws IOException {
            if (response != null && response.trim().length() > 0) {
                ByteBuffer write = ByteBuffer.allocate(response.getBytes().length);
                write.put(response.getBytes());
                write.flip();
                channel.write(write);
            }
        }

        public void stop() {
            this.stop = true;
        }
    }

}
