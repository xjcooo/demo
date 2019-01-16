package org.xjc.demo.io.nio;

import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by xjc on 2019-1-16.
 */
@Slf4j
public class NioClientDemo {

    public static void main(String[] args) {
        new Thread(new NioClient(null, 8080)).start();
    }

    static class NioClient implements Runnable {

        private String ip;
        private int port;
        private Selector selector;
        private SocketChannel socketChannel;
        private volatile boolean stop;

        public NioClient(String ip, int port) {
            this.ip = StringUtil.isNullOrEmpty(ip) ? "127.0.0.1" : ip;
            this.port = port;
            try {
                selector = Selector.open();
                socketChannel = SocketChannel.open();
                socketChannel.configureBlocking(false);
            } catch (IOException e) {
                log.error("create nio client error", e);
                System.exit(1);
            }
        }

        @Override
        public void run() {
            try {
                doConnect();
            } catch (IOException e) {
                log.error("connect server error", e);
                System.exit(1);
            }
            while (!stop) {
                try {
                    selector.select(1000);
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    SelectionKey key = null;
                    while (iterator.hasNext()) {
                        key = iterator.next();
                        iterator.remove();
                        try {
                            // handle key
                            handleOutput(key);
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
                if (selector != null) {
                    try {
                        selector.close();
                    } catch (IOException e) {
                        log.error("close selector error", e);
                    }
                }
            }
        }

        private void handleOutput(SelectionKey key) throws IOException {
            if (key.isValid()) {
                SocketChannel sc = (SocketChannel) key.channel();
                if (key.isConnectable()) {
                    if (sc.finishConnect()){
                        sc.register(selector, SelectionKey.OP_READ);
                        doWrite(sc);
                    } else {
                        System.exit(1);
                    }
                }
                if (key.isReadable()) {
                    ByteBuffer bb = ByteBuffer.allocate(1024);
                    int readBytes = sc.read(bb);
                    if (readBytes > 0) {
                        bb.flip();
                        byte[] bytes = new byte[bb.remaining()];
                        bb.get(bytes);
                        String body = new String(bytes, "UTF-8");
                        log.info("response message : {}", body);
                    } else if (readBytes < 0) {
                        key.cancel();
                        sc.close();
                    } else {
                    }
                }
                this.stop = true;
            }
        }

        private void doConnect() throws IOException {
            // 如果直接连接成功,则注册到多路复用器上,发送请求,读应答
            if (socketChannel.connect(new InetSocketAddress(ip, port))){
                socketChannel.register(selector, SelectionKey.OP_READ);
//                doWrite(socketChannel);
            } else {
                socketChannel.register(selector, SelectionKey.OP_CONNECT);
            }
        }

        private void doWrite(SocketChannel socketChannel) throws IOException {
            byte[] bytes = "login:xjcooo".getBytes("UTF-8");
            ByteBuffer bb = ByteBuffer.allocate(bytes.length);
            bb.put(bytes);
            bb.flip();
            socketChannel.write(bb);
            if (!bb.hasRemaining()){
                log.info("send message success");
            }
        }

        public void stop() {
            this.stop = true;
        }
    }
}
