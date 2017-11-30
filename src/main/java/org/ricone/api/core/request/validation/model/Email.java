package org.ricone.api.core.request.validation.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "total",
    "percent"
})
public class Email {

    @JsonProperty("total")
    private Integer total;
    @JsonProperty("percent")
    private String percent;

    public Email() {
    }

    public Email(Integer total, String percent) {
        super();
        this.total = total;
        this.percent = percent;
    }

    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Integer total) {
        this.total = total;
    }

    @JsonProperty("percent")
    public String getPercent() {
        return percent;
    }

    @JsonProperty("percent")
    public void setPercent(String percent) {
        this.percent = percent;
    }
}