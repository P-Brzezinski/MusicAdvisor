package advisor.api;

import advisor.api.requests.NewReleaseRequest;

public class RequestsHandler {

    private NewReleaseRequest newReleaseRequest;

    public RequestsHandler() {
        this.newReleaseRequest = new NewReleaseRequest();
    }

    public void showNewReleases(){
        newReleaseRequest.showNewReleases();
    }
}
