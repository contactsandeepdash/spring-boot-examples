package com.dash.camelmicroservice.router.patterns;

import java.util.List;
import java.util.Map;

import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
public class EipPatternsRouter extends RouteBuilder {

    @Autowired
    SplitterComponent splitter;

    @Autowired
    DynamicRouterBean dynamicRouterBean;

    @Override
    public void configure() throws Exception {

        // to get tracing
        getContext().setTracing(true);


        // ensure no messages are lost - configure a dead letter queue
        errorHandler(deadLetterChannel("activemq:dead-letter-queue"));

        // pipeline
        // content based routing - choice()
        // multicast
        // split
        // aggregation

        // from("timer: multicast?period=10000")
        // .multicast()
        // .to("log: something1", "log: something2", "log: something3");

        // in to: it can be lg, mq, kafka, rest etc.
        // and each will get a copy of the message
// --------------------------------------------------------------------------------
        // Note 1: To send split data to active-mq
        // from("file:/Users/sandeepdash/Desktop/source/csv")
        // .unmarshal().csv()
        // .split(body())
        // // .to("log:split-files");
        // .to("activemq:spring_boot_activemq_split_queue");

        // // Note 2: To send split data of a text file to active-mq
        // // copy single line csv data
        // from("file:/Users/sandeepdash/Desktop/source/csv")
        // .transform().body(String.class)
        // .split(body())
        // // .to("log:split-files");
        // .to("activemq:spring_boot_activemq_split_queue");

        // Note 3: To send split data of a text file to active-mq (by a component)
        // copy single line csv data
        // from("file:/Users/sandeepdash/Desktop/source/csv")
        //         .transform().body(String.class)
        //         .split(method(splitter))
        //         // .to("log:split-files");
        //         .to("activemq:spring_boot_activemq_split_queue");
// --------------------------------------------------------------------------------
        // aggregate
        // messages => aggregate => endpoint
        // from("file:/Users/sandeepdash/Desktop/source/aggregate")
        // .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
        // .aggregate(simple("${body.to}"), new ArrayListAggregationStrategy())
        // .completionSize(3)
        // .to("log:aggregate-json");
// --------------------------------------------------------------------------------
        // routing slip
        String routingSlip = "direct:endpoint1,direct:endpoint2";
        // String routingSlip = "direct:endpoint1,direct:endpoint2,direct:endpoint3";

        // from("timer:routingSlip?period=10000")
        // from("timer:routingSlip?period={{timePeriod}}")     // entry made in applicaiton.yml
        // .transform().constant("My Message is Hardcoded")
        // .routingSlip(simple(routingSlip));

        // --------------------------------------------------------------------------------
        // Dynamic Routing

        // Step 1, Step 2, Step 3

        // from("timer:dynamicRouting?period={{timePeriod}}")
        from("timer:dynamicRouting?period={{timePeriod}}")
        .transform().constant("My Message is Hardcoded")
        .dynamicRouter(method(dynamicRouterBean));

        // //Endpoint1
        // //Endpoint2
        // //Endpoint3

        

        from("direct:endpoint1")
        .wireTap("log:wire-tap") //additional endpoint along with to... like phone tapping
        // .to("log:directendpoint1");
        .to("{{endpoint-for-logging}}");    // entry made in applicaiton.yml

        from("direct:endpoint2")
        .to("log:directendpoint2");

        from("direct:endpoint3")
        .to("log:directendpoint3");

    }

}

@Component
@Slf4j
class SplitterComponent {
    public List<String> splitInput(String body) {
        log.info(body);
        return List.of("abc", "def", "ghi");
    }
}

@Component
@Slf4j
class DynamicRouterBean {

    int invocations;

    public String decideTheNextEndpoint(
            @ExchangeProperties Map<String, String> properties,
            @Headers Map<String, String> headers,
            @Body String body) {
        log.info("{} {} {}", properties, headers, body);

        invocations++;

        if (invocations % 3 == 0)
            return "direct:endpoint1";

        if (invocations % 3 == 1)
            return "direct:endpoint2,direct:endpoint3";

        return null;

    }
}