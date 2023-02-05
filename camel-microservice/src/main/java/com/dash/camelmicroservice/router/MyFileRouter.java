package com.dash.camelmicroservice.router;

import java.util.Map;

import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

// https://camel.apache.org/components/3.20.x/languages/simple-language.html
// https://camel.apache.org/components/3.20.x/index.html

// @Component
public class MyFileRouter extends RouteBuilder {

    @Autowired
    DeciderBean deciderBean;

    @Override
    public void configure() throws Exception {
        from("file:/Users/sandeepdash/Desktop/source")
                .routeId("files-input-route")
                .transform().body(String.class)
                .choice()
                    .when(simple("${file:ext} == 'xml'"))
                        .log("XML File")
                        // .when(simple("${body} contains 'USD'"))
                    .when(method(deciderBean))
                        .log("Not a XML file; but, contains USD")
                    .otherwise()
                        .log("not a XML File")
                .end()
                // .to("direct:log-file-values")
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

@Component
@Slf4j
class DeciderBean {

    public boolean isThisConditionMet(@Body String body, @Headers Map<String, String> headers,
            @ExchangeProperties Map<String, String> exchangeProperties) {
        log.info("DeciderBean \n{} \n-----------\n{} \n-----------\n{}", body, headers, exchangeProperties);
        return true;
    }
}
