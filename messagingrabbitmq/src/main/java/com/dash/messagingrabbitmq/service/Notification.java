package com.dash.messagingrabbitmq.service;

import java.io.Serializable;

import lombok.Data;

@Data
public class Notification implements Serializable {
    
    private String notificationType;
    private String msg;

}
