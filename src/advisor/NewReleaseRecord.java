package advisor;

import java.net.URI;

public class NewReleaseRecord {

    private String title;
    private String artist;
    private URI uri;


    public NewReleaseRecord() {
    }

    public NewReleaseRecord(String title, String artist, URI uri) {
        this.title = title;
        this.artist = artist;
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return title + "\n"
                + "[" + artist + "]\n"
                + uri;
    }
}
