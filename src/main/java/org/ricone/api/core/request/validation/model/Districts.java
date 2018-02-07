package org.ricone.api.core.request.validation.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"district"})
public class Districts {

    @JsonProperty("district")
    private List<District> district = new ArrayList<District>();

    public Districts() {
    }

    public Districts(List<District> district) {
        super();
        this.district = district;
    }

    @JsonProperty("district")
    public List<District> getDistrict() {
        return district;
    }

    @JsonProperty("district")
    public void setDistrict(List<District> district) {
        this.district = district;
    }
}