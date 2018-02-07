package org.ricone.api.core.request.validation.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"districts"})
public class ValidationResponse {

    @JsonProperty("districts")
    private Districts districts;

    public ValidationResponse() {
    }

    public ValidationResponse(Districts districts) {
        super();
        this.districts = districts;
    }

    @JsonProperty("districts")
    public Districts getDistricts() {
        return districts;
    }

    @JsonProperty("districts")
    public void setDistricts(Districts districts) {
        this.districts = districts;
    }
}