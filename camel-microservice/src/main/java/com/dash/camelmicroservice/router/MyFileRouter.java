package com.dash.camelmicroservice.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


// https://camel.apache.org/components/3.20.x/languages/simple-language.html
// https://camel.apache.org/components/3.20.x/index.html

@Component
public class MyFileRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:/Users/sandeepdash/Desktop/source")
        .routeId("files-input-route")
        .transform().body(String.class)
        .choice()
            .when(simple("${file:ext} ends with 'xml'"))
                .log("XML File")
                .when(simple("${body} contains 'USD'"))
                    .log("Not a XML file; but, contains USD")
            .otherwise()
                .log("not a XML File")
        .end()
        .to("direct:log-file-values")
        .to("file:/Users/sandeepdash/Desktop/target");
        

        // direct will be used as a function and reusable
        from("direct:log-file-values")
        .log("${messageHistory} : ${headers.CamelFileAbsolute}")
        .log("${file:absolute.path} : ${file:name} : ${file:name.ext} : ${file:name.noext} : ${file:onlyname} : ${file:onlyname.noext}")
        .log("${file:parent} : ${file:path} : ${file:absolute} : ${file:size} : ${file:modified}")
        .log("${routeId} : ${camelId} : \n${body}");


        // note: in the log below 2 routes will be seen
        // Started files-input-route (file:///Users/sandeepdash/Desktop/source)
        // Started route7 (direct://log-file-values)
    }
    
}
