package com.intuit.redispoc;

import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.stream.StreamListener;

class ExampleStreamListener implements StreamListener<String, MapRecord<String, String, String>> {

	@Override
	public void onMessage(MapRecord<String, String, String> message) {

		System.out.println("MessageId: " + message.getId());
		System.out.println("Stream: " + message.getStream());
		System.out.println("Body: " + message.getValue());
	}
}