package pt.com.admendes.bookstore.model;

public class Volume {
    private String id;
    private String selfLink;
    private VolumeInfo volumeInfo;

    public Volume (String id, String selfLink, VolumeInfo volumeInfo) {
        this.id = id;
        this.selfLink = selfLink;
        this.volumeInfo = volumeInfo;
    }

    public String getId() {
     return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSelfLink() {
        return this.selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public VolumeInfo getVolumeInfo() {
        return this.volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    @Override
    public String toString() {
        return "Volume{" +
                "id='" + id + '\'' +
                ", selfLink='" + selfLink + '\'' +
                ", volumeInfo=" + volumeInfo +
                '}';
    }
}
