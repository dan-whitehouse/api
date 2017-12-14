package org.ricone.api.xPress.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlTransient;

@JsonIgnoreProperties({"districtId"})
@XmlTransient
public abstract class XWrapper {

    @JsonIgnore
    @XmlTransient
    private String districtId;

    @JsonIgnore
    @XmlTransient
    public String getDistrictId() { return districtId; }
    @JsonIgnore
    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }
}
