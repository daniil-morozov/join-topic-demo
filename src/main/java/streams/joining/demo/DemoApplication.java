package streams.joining.demo;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.kafka.support.serializer.JsonSerde;
import streams.joining.demo.event.RawEvent;
import streams.joining.demo.event.Profile;

import java.util.Properties;


public class DemoApplication {

	public static void main(String[] args) {
		StreamsBuilder builder = new StreamsBuilder();

		KStream<String, RawEvent> rawEvent = builder
				.stream(Constants.EVENT_TOPIC,
						Consumed.with(Serdes.String(), new JsonSerde<>(RawEvent.class)));

		KTable<String, Profile> profileData = builder
				.table(
						Constants.PROFILE_TOPIC,
						Consumed.with(Serdes.String(), new JsonSerde<>(Profile.class)));

		rawEvent
				.join(profileData, (event, userData) -> {
					event.enrich(userData);
					return event;
				})
				.to(Constants.ENRICHED_TOPIC);

		KStream<String, RawEvent> enrichedEvent = builder
				.stream(Constants.ENRICHED_TOPIC,
						Consumed.with(Serdes.String(), new JsonSerde<>(RawEvent.class)));
		enrichedEvent.foreach((key, value) -> System.out.println(
				"| key = " + key + " | " + value + " |"));

		KafkaStreams streams = new KafkaStreams(builder.build(), getStreamSettings());
		streams.start();
	}


	private static Properties getProducerProps() {
		Properties props = new Properties();
		props.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG,
				"localhost:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringSerializer");
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				"org.springframework.kafka.support.serializer.JsonSerializer");

		return props;
	}

	private static Properties getStreamSettings() {
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, Constants.CONSUMER_GROUP);
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, "0");
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, JsonSerde.class);


		return props;
	}

}
