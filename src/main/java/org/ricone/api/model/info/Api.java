package org.ricone.api.model.info;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "version",
        "status",
        "started",
        "uptime"
})
public class Api {

    @JsonProperty("version")
    private String version;
    @JsonProperty("status")
    private String status;
    @JsonProperty("started")
    private String started;
    @JsonProperty("uptime")
    private Double uptime;

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
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

}