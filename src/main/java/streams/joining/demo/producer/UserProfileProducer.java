package streams.joining.demo.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import streams.joining.demo.Constants;
import streams.joining.demo.event.Profile;

import java.util.Properties;
import java.util.UUID;

public class UserProfileProducer {

    private static final String KEY = UUID.randomUUID().toString();
    private final KafkaProducer<String, Profile> producer;

    private UserProfileProducer(Properties props) {
        producer = new KafkaProducer<>(props);
    }

    public static UserProfileProducer createSampleProducer(Properties props) {
        return new UserProfileProducer(props);
    }

    @Override
    protected void finalize() {
        producer.close();
    }

    public void send(Profile event) {
        producer.send(new ProducerRecord<>(
            Constants.PROFILE_TOPIC,
            KEY,
            event));
    }
}
