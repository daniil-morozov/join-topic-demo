package streams.joining.demo.event;

public class EnrichedEvent extends RawEvent {
    private final String objectid;
    private final Long timestamp;
    private final Double latitude;
    private final Double longitude;
    private final String ownerId;
    private final String trackerId;

    public EnrichedEvent() {
        objectid = "";
        timestamp = 0L;
        latitude = 0.0;
        longitude = 0.0;
        ownerId = "";
        trackerId = "";
    }

    public EnrichedEvent(RawEvent event, Profile profile) {
        objectid = event.getObjectid();
        timestamp = event.getTimestamp();
        latitude = event.getLatitude();
        longitude = event.getLongitude();
        ownerId = profile.getOwnerId();
        trackerId = profile.getTrackerId();
    }

    public String getObjectid() {
        return objectid;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getTrackerId() {
        return trackerId;
    }

    @Override
    public String toString() {
        return "EnrichedEvent{" +
                "objectid='" + objectid + '\'' +
                ", timestamp=" + timestamp +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", ownerId='" + ownerId + '\'' +
                ", trackerId='" + trackerId + '\'' +
                '}';
    }
}
