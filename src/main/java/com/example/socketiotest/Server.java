package com.example.socketiotest;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;

public class Server {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9092);

        SocketIOServer server = new SocketIOServer(config);
        server.addEventListener("message", String.class, (client, data, ackRequest) -> {
            // 여기서 클라이언트로부터 받은 메시지를 처리합니다.
            System.out.println("Received message: " + data);
            // 클라이언트에 응답을 보낼 수 있습니다.
            client.sendEvent("message", "Message received: " + data);
        });

        server.start();
        System.out.println("Server started on port 9092");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            server.stop();
            System.out.println("Server stopped");
        }));
    }
}