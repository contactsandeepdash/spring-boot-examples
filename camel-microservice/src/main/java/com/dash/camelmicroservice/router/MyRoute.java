package com.dash.camelmicroservice.router;

import java.time.LocalDateTime;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

// disable @component if router is not needed
// @Component
public class MyRoute extends RouteBuilder {

    @Autowired
    CurrentTimeBean currentTimeBean;

    @Autowired
    SimpleLoggingProcessingComponent simpleLoggingProcessingComponent;

    @Override
    public void configure() throws Exception {
        from("timer:my-first-timer")
        // .log("${body}")
        // .transform().constant("My constant message")
        // .log("${body}")
        // .transform().constant("current time is " + LocalDateTime.now())
        // .bean("getCurrentTimeBean")
        // .bean(currentTimeBean)
        // .log("${body}")

        // .bean(simpleLoggingProcessingComponent)
        // .log("${body}")

        // .process(new SimpleLoggingProcessor())
        .log("${body}")

        // processing - does not change the body of the message
        // transformation - change the body of the message

        .to("log:my-first-timer");
        
    }
}

@Component
class CurrentTimeBean {
    public String getCurrentTime() {
        return "current time is : " + LocalDateTime.now(); 
    }
}

@Component
@Slf4j
class SimpleLoggingProcessingComponent {
    public void process(String message) {
        log.info("SimpleLoggingProcessingComponent {}", message);
    }
}