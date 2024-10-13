package com.dbp.winproyect.chat;

import com.pusher.rest.Pusher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PusherConfig {

    @Bean
    public Pusher pusher() {
        Pusher pusher = new Pusher("1879280", "1d1d25f53b3ac07ff76f", "2ca40b6fe43348ca139e");
        pusher.setCluster("us2");
        pusher.setEncrypted(true);
        return pusher;
    }
}