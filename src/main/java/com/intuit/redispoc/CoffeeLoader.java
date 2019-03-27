package com.intuit.redispoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.connection.stream.StreamReadOptions;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.connection.stream.StringRecord;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.StreamReceiver;
import org.springframework.data.redis.stream.Subscription;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class CoffeeLoader {
//    private final ReactiveRedisConnectionFactory factory;
//    private final ReactiveRedisOperations<String, Coffee> coffeeOps;


    @Autowired
    RedisConnectionFactory connectionFactory;

    StreamListener<String, MapRecord<String, String, String>> streamListener = new ExampleStreamListener();


    @Autowired
    RedisTemplate<String, String> redisTemplate;

//    public CoffeeLoader(ReactiveRedisConnectionFactory factory, ReactiveRedisOperations<String, Coffee> coffeeOps, RedisTemplate<String, String> template) {
////        this.factory = factory;
////        this.coffeeOps = coffeeOps;
//        this.template = template;
////        this.connectionFactory = connectionFactory;
////    }

    @PostConstruct
    public void loadData() {
        System.out.println("herere also--------------");

        Map testMap = new HashMap<String, String>();
        testMap.put("testmsg1", "testmsg1");


        StreamMessageListenerContainer<String, MapRecord<String, String, String>> container = StreamMessageListenerContainer.create(connectionFactory);

//        container.register()





        Subscription subscription = container.receive(StreamOffset.fromStart("mystream"), streamListener);
        Subscription subscription1 = container.receive(StreamOffset.fromStart("my-stream"), streamListener);
//        Subscription subscription2 = container.receive(StreamOffset.fromStart("my-stream-one"), streamListener);
//        Subscription subscription3 = container.receive(StreamOffset.fromStart("my-stream-two"), streamListener);
//        Subscription subscription4 = container.receive(StreamOffset.fromStart("my-stream-three"), streamListener);
//        Subscription subscription5 = container.receive(StreamOffset.fromStart("my-stream-four"), streamListener);
//        Subscription subscription6 = container.receive(StreamOffset.fromStart("my-stream-five"), streamListener);

        for(int i=0; i<100; i++) {
            container.receive(StreamOffset.fromStart("my-stream-" + i), streamListener);
        }

        System.out.println("subscription-status---------------" + subscription.isActive());

        container.start();

        for(int i=0; i<100; i++) {
            RecordId recordId = redisTemplate.opsForStream().add(StreamRecords.string(testMap).withStreamKey("my-stream-" + i));
            System.out.println("----------Record value------------" + recordId.getValue());
        }

        System.out.println("subscription-status---------------" + subscription.isActive());
//




        StringRecord records = StreamRecords.string(testMap).withStreamKey("mystream");
        StringRecord records1 = StreamRecords.string(testMap).withStreamKey("my-stream");

        StringRecord records2 = StreamRecords.string(testMap).withStreamKey("my-stream-one");
        StringRecord records3 = StreamRecords.string(testMap).withStreamKey("my-stream-two");
        StringRecord records4 = StreamRecords.string(testMap).withStreamKey("my-stream-three");
        StringRecord records5 = StreamRecords.string(testMap).withStreamKey("my-stream-four");
        StringRecord records6 = StreamRecords.string(testMap).withStreamKey("my-stream-five");


        RecordId recordId = redisTemplate.opsForStream().add(records);
        RecordId recordId1 = redisTemplate.opsForStream().add(records1);
        RecordId recordId2 = redisTemplate.opsForStream().add(records2);
        RecordId recordId3 = redisTemplate.opsForStream().add(records3);
        RecordId recordId4 = redisTemplate.opsForStream().add(records4);
        RecordId recordId5 = redisTemplate.opsForStream().add(records5);
        RecordId recordId6 = redisTemplate.opsForStream().add(records6);



        System.out.println("----------Record value------------" + recordId.getValue());
        System.out.println("----------Record value------------" + recordId1.getValue());
        System.out.println("----------Record value------------" + recordId2.getValue());
        System.out.println("----------Record value------------" + recordId3.getValue());
        System.out.println("----------Record value------------" + recordId4.getValue());
        System.out.println("----------Record value------------" + recordId5.getValue());
        System.out.println("----------Record value------------" + recordId6.getValue());


//        List<MapRecord<String, Object, Object>> read = template.opsForStream().read(StreamReadOptions.empty(), StreamOffset.fromStart("mystream"));
//
//        System.out.println("-----read size-----" + read.size());




//        StreamReceiver.StreamReceiverOptions<String, MapRecord<String, String, String>> options = StreamReceiver.StreamReceiverOptions.builder().pollTimeout(Duration.ofMillis(100))
//                .build();
//        StreamReceiver<String, MapRecord<String, String, String>> receiver = StreamReceiver.create(factory, options);
//
//        Flux<MapRecord<String, String, String>> messages = receiver.receive(StreamOffset.fromStart("mystream"));
//
//        messages.subscribe(System.out::println);
//
//        RecordId recordId1 = template.opsForStream().add(records);
//        System.out.println("----------Record value------------" + recordId1.getValue());



//        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, MapRecord<String, String, String>> containerOptions = StreamMessageListenerContainer.StreamMessageListenerContainerOptions.builder().pollTimeout(Duration.ofMillis(100)).build();






//        factory.getReactiveConnection().serverCommands().flushAll().thenMany(
//                Flux.just("Jet Black Redis", "Darth Redis", "Black Alert Redis")
//                        .map(name -> new Coffee(UUID.randomUUID().toString(), name))
//                        .flatMap(coffee -> coffeeOps.opsForValue().set(coffee.getId(), coffee)))
//                .thenMany(coffeeOps.keys("*")
//                        .flatMap(coffeeOps.opsForValue()::get))
//                .subscribe(System.out::println);
    }
}
