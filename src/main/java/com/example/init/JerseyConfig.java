package com.example.init;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.example.controller.SetupController;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("com.example.controller");
        
    }
}