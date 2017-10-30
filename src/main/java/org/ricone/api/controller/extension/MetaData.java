package org.ricone.api.controller.extension;

import org.ricone.api.component.config.model.App;
import org.ricone.api.security.DecodedToken;
import org.springframework.data.domain.Pageable;

public class MetaData {

    private App app;
    private DecodedToken token;
    private Pageable paging;

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
}
