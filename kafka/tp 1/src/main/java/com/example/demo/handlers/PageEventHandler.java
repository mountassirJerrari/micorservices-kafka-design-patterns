package com.example.demo.handlers;

import com.example.demo.events.PageEvent;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Component
public class PageEventHandler {
    @Bean
    public Consumer<PageEvent> pageEventConsumer() {
        return (input) -> {

            System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiii");
            System.out.println(input.date().toString());

        };
    }

    @Bean
    public Supplier<PageEvent> pageEventSupplier() {
        return () -> {

           return  new PageEvent(Math.random()>0.5?"p1":"p2" , Math.random()>0.5?"u1":"u2",new Date(),12+ new Random().nextInt(1000));

        };
    }


    @Bean
    public Function<KStream<String, PageEvent>, KStream<String, Long>> kStream(){
        return (stream)->
                stream
                        .map((k,v)->new KeyValue<>(v.name(), 0L))
                        .groupByKey(Grouped.with(Serdes.String(), Serdes.Long()))
                        .windowedBy(TimeWindows.of(Duration.ofSeconds(5)))
                        //.aggregate(()->0.0, (k,v,total)->total+v, Materialized.as("total-store"))
                        .count(Materialized.as("count-store"))
                        .toStream()
                        .map((k,v)->new KeyValue<>(k.key(), v))
                ;

    }





}
