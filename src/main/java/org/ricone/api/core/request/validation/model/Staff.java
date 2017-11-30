package org.ricone.api.core.request.validation.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "total",
    "email",
    "localId"
})
public class Staff {

    @JsonProperty("total")
    private Integer total;
    @JsonProperty("email")
    private Email email;
    @JsonProperty("localId")
    private LocalId localId;

    public Staff() {
    }

    public Staff(Integer total, Email email, LocalId localId) {
        super();
        this.total = total;
        this.email = email;
        this.localId = localId;
    }

    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Integer total) {
        this.total = total;
    }

    @JsonProperty("email")
    public Email getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(Email email) {
        this.email = email;
    }

    @JsonProperty("localId")
    public LocalId getLocalId() {
        return localId;
    }

    @JsonProperty("localId")
    public void setLocalId(LocalId localId) {
        this.localId = localId;
    }
}