package streams.joining.demo.event;

public class Profile {
    private final String ownerId;
    private final String trackerId;
    private final String email;
    private final Integer gender;
    private final String file_name;
    private final Integer status;

    public Profile(String ownerId, String trackerId, String email, Integer gender, String file_name, Integer status) {
        this.ownerId = ownerId;
        this.trackerId = trackerId;
        this.email = email;
        this.gender = gender;
        this.file_name = file_name;
        this.status = status;
    }

    public Profile() {
        this("", "", "", 0, "", 0);
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getTrackerId() {
        return trackerId;
    }

    public String getEmail() {
        return email;
    }

    public Integer getGender() {
        return gender;
    }

    public String getFile_name() {
        return file_name;
    }

    public Integer getStatus() {
        return status;
    }
}
