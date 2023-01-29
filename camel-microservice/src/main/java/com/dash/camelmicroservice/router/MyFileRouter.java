package com.dash.camelmicroservice.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

// @Component
public class MyFileRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:/Users/sandeepdash/Desktop/source")
        // .log("${body}")
        .to("file:/Users/sandeepdash/Desktop/target");
        
    }
    
}
