package org.ricone.api.core.request.info.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"status", "started", "uptime"})
public class Config {

    @JsonProperty("status")
    private String status;
    @JsonProperty("started")
    private String started;
    @JsonProperty("uptime")
    private Double uptime;

    public Config() {
    }

    public Config(String started, Double uptime) {
        this.started = started;
        this.uptime = uptime;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("started")
    public String getStarted() {
        return started;
    }

    @JsonProperty("started")
    public void setStarted(String started) {
        this.started = started;
    }

    @JsonProperty("uptime")
    public Double getUptime() {
        return uptime;
    }

    @JsonProperty("uptime")
    public void setUptime(Double uptime) {
        this.uptime = uptime;
    }

    public Config(String status) {
        this.status = status;
    }
}