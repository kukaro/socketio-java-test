package com.example.socketiotest.socket;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static java.lang.StringTemplate.STR;

@RequiredArgsConstructor
@Component
@Slf4j
public class SocketRunner implements CommandLineRunner {

    private final SocketIOServer socketIOServer;

    @Override
    public void run(String... args) throws Exception {
        socketIOServer.addConnectListener(client -> {
            log.info("#client connected");
        });
        socketIOServer.addDisconnectListener(client -> {
            log.info("#client disconnected");
        });
        socketIOServer.addEventListener("join", Message.class, (client, data, ackSender) -> {
            String socketId = client.getSessionId().toString();
            log.info(STR."#ON JOIN roomName : \{data.roomName()}, socketId : \{socketId}");
            client.joinRoom(data.roomName());
            socketIOServer.getRoomOperations(data.roomName()).sendEvent("recMsg", new Message(STR."\{socketId} : \{data.roomName()}\n", null));
//            client.sendEvent("recMsg", new Message(STR."\{socketId} : \{data.roomName()}", null));
        });
        socketIOServer.addEventListener("msg", Message.class, (client, data, ackSender) -> {
            log.info("#ON MSG");
            String socketId = client.getSessionId().toString();
            socketIOServer.getRoomOperations(data.roomName()).sendEvent("recMsg", new Message(STR."\{socketId} : \{data.comment()}\n", null));
        });
        socketIOServer.start();
    }

}

record Message(
        String comment,
        String roomName
) {
}