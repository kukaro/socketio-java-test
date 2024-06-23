package com.example.socketiotest.socket;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SocketConfig {

    private final WebSocketAddMappingSupporter mappingSupporter;

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setPort(8081);
        config.setOrigin("*");
        SocketIOServer server = new SocketIOServer(config);
        mappingSupporter.addListeners(server);
        return server;
    }

}
