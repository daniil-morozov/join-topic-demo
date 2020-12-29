package streams.joining.demo.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import streams.joining.demo.Constants;
import streams.joining.demo.event.RawEvent;

import java.util.Properties;
import java.util.UUID;

public class EventProducer {

    private static final String KEY = UUID.randomUUID().toString();
    private final KafkaProducer<String, RawEvent> producer;

    private EventProducer(Properties props) {
        producer = new KafkaProducer<>(props);
    }

    public static EventProducer createSampleProducer(Properties props) {
        return new EventProducer(props);
    }

    @Override
    protected void finalize() {
        producer.close();
    }

    public void send(RawEvent event) {
        producer.send(new ProducerRecord<>(
            Constants.EVENT_TOPIC,
            KEY,
            event));
    }
}
