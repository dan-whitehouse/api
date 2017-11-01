package org.ricone.api.controller.extension;

import org.ricone.api.component.config.model.App;
import org.ricone.api.security.DecodedToken;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public class MetaData {

    public static final String LEA_LOCAL_ID_KEY = "leaId";

    private App app;
    private DecodedToken token;
    private Pageable paging;
    private Map<String, String> headers;

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public DecodedToken getToken() {
        return token;
    }

    public void setToken(DecodedToken token) {
        this.token = token;
    }

    public Pageable getPaging() {
        return paging;
    }

    public void setPaging(Pageable paging) {
        this.paging = paging;
    }

    public Map<String, String> getHeaders() { return headers; }

    public void setHeaders(Map<String, String> headers) { this.headers = headers; }
}
