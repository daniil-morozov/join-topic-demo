package streams.joining.demo.event;

public class RawEvent {
    private final String objectid;
    private final Long timestamp;
    private final Double latitude;
    private final Double longitude;
    private String ownerId;
    private String trackerId;

    public RawEvent(String objectid, Long timestamp, Double latitude, Double longitude) {
        this.objectid = objectid;
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public RawEvent() {
        this("", 0L, 0.0, 0.0);
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

    public void enrich(Profile profile) {
        ownerId = profile.getOwnerId();
        trackerId = profile.getTrackerId();
    }

    @Override
    public String toString() {
        return "RawEvent{" +
                "objectid='" + objectid + '\'' +
                ", timestamp=" + timestamp +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", ownerId='" + ownerId + '\'' +
                ", trackerId='" + trackerId + '\'' +
                '}';
    }
}
