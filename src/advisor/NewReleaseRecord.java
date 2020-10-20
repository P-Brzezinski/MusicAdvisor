package advisor;

import java.net.URI;

public class NewReleaseRecord {

    private String title;
    private String author;
    private URI uri;


    public NewReleaseRecord() {
    }

    public NewReleaseRecord(String title, String author, URI uri) {
        this.title = title;
        this.author = author;
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
}
