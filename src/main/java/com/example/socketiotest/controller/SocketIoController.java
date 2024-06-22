package com.example.socketiotest.controller;

import com.corundumstudio.socketio.SocketIOClient;
import com.example.socketiotest.socket.SocketController;
import com.example.socketiotest.socket.SocketMapping;
import lombok.extern.slf4j.Slf4j;

//@SocketController
@Slf4j
public class SocketIoController {
//    @SocketMapping(endpoint = "join")
    public void joinRoom(SocketIOClient client) {
        log.info("joinRoom");

    }
}
