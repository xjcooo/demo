package org.xjc.demo.io.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

/**
 * 阻塞io客户端
 * Created by xjc on 2019-1-16.
 */
@Slf4j
public class SocketClient {

    Socket socket = null;

    public SocketClient() throws IOException {
        socket = new Socket("127.0.0.1", 8080);
    }

    public void sendMessage(String message) {

        InputStream is = null;
        OutputStream os = null;
        try {
            os = socket.getOutputStream();
            is = socket.getInputStream();

            os.write(message.getBytes());
            socket.shutdownOutput();

            // 从服务器读取信息，需要先把字节流转换为容易读取的字符流
            BufferedReader bis = new BufferedReader(new InputStreamReader(is));
            String response = bis.readLine();
            log.info("send to server:[{}], response:[{}]", message, response);
            socket.shutdownInput();

        } catch (IOException e) {
            log.error("发送消息失败", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error("inputStream failed to close", e);
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    log.error("outputStream failed to close");
                }
            }
        }
    }

    public void close() throws IOException {
        if (socket != null) {
            socket.close();
        }
    }

}
