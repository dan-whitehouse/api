package org.ricone.api.core.request.info.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "api",
        "db",
        "config"
})
public class Info {

    @JsonProperty("api")
    private Api api;
    @JsonProperty("db")
    private Db db;
    @JsonProperty("config")
    private Config config;

    @JsonProperty("api")
    public Api getApi() {
        return api;
    }

    @JsonProperty("api")
    public void setApi(Api api) {
        this.api = api;
    }

    @JsonProperty("db")
    public Db getDb() {
        return db;
    }

    @JsonProperty("db")
    public void setDb(Db db) {
        this.db = db;
    }

    @JsonProperty("config")
    public Config getConfig() {
        return config;
    }

    @JsonProperty("config")
    public void setConfig(Config config) {
        this.config = config;
    }

}