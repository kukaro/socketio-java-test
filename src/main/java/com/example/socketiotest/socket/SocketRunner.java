package com.example.socketiotest.socket;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class SocketRunner implements CommandLineRunner {

    private final SocketIOServer socketIOServer;

    @Override
    public void run(String... args) throws Exception {
        socketIOServer.addConnectListener(client -> {
            log.info("client connected");
            client.sendEvent("recMsg", "Hello, " + client.getSessionId());
        });
        socketIOServer.addDisconnectListener(client -> {
            log.info("client disconnected");
        });
        socketIOServer.addEventListener("join", Object.class, (client, data, ackSender) -> {
            System.out.println(data);
            client.sendEvent("chat", data);
        });
        socketIOServer.addEventListener("hi", String.class, (client, data, ackSender) -> {
            System.out.println(data);
            client.sendEvent("chat", data);
        });
        socketIOServer.start();
    }

}
