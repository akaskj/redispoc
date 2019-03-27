package com.intuit.redispoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.connection.stream.StringRecord;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CoffeeController {
//    private final ReactiveRedisOperations<String, Coffee> coffeeOps;

//    CoffeeController(ReactiveRedisOperations<String, Coffee> coffeeOps) {
//        this.coffeeOps = coffeeOps;
//    }

    @Autowired
    RedisTemplate<String, String> redisTemplate;


    @GetMapping("/coffees")
    public boolean all() {

        Map testMap = new HashMap<String, String>();
        testMap.put("testmsgController", "testmsgController");

        StringRecord records = StreamRecords.string(testMap).withStreamKey("mystream");
        RecordId recordId = redisTemplate.opsForStream().add(records);
        System.out.println("----------Record value------------" + recordId.getValue());


        return true;
    }
}
