package org.xjc.demo.io.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 阻塞io服务端
 *
 * 未实现使用线程池来做伪异步
 * Created by xjc on 2019-1-15.
 */
@Slf4j
public class SocketServer {

    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080);
            log.info("The socket server started in the port:{}", 8080);
            Socket socket = null;
            while (true) {
                socket = serverSocket.accept();
                final Socket finalSocket = socket;
                new Thread(new Runnable() {
                    public void run() {
                        dealSocket(finalSocket);
                    }
                }).start();
            }
        } catch (IOException e) {
            log.error("启动socket服务异常", e);
        } finally {
            if (serverSocket != null) {
                log.info("The server is closing...");
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    log.error("The server failed to close");
                }
            }
        }
    }

    private static void dealSocket(Socket socket) {
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            String currentTime = null;
            String body = null;
            while (true) {
                body = reader.readLine();
                if (body == null) {
                    break;
                }
                log.info("socket body is [{}]", body);
                if (body.startsWith("login")) {
                    writer.println("hello," + body.replace("login:", ""));
                } else {
                    writer.println("message:" + body);
                }
            }
        } catch (IOException e) {
            log.error("parsing socket error", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error("socket读取器关闭异常");
                }
            }
            if (writer != null)
                writer.close();
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    log.error("socket关闭异常");
                }
            }
        }
    }

}
