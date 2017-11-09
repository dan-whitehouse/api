package org.ricone.api.xPress.model;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "xAppProvisioningInfo"
})
public class XAppProvisioning implements Serializable {

    @JsonProperty("xAppProvisioningInfo")
    private List<XAppProvisioningInfo> xAppProvisioningInfo = new ArrayList<>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
    private final static long serialVersionUID = 1602976204588614880L;

    public XAppProvisioning() {
    }

    public XAppProvisioning(List<XAppProvisioningInfo> xAppProvisioningInfo) {
        super();
        this.xAppProvisioningInfo = xAppProvisioningInfo;
    }

    @JsonProperty("xAppProvisioningInfo")
    public List<XAppProvisioningInfo> getXAppProvisioningInfo() {
        return xAppProvisioningInfo;
    }

    @JsonProperty("xAppProvisioningInfo")
    public void setXAppProvisioningInfo(List<XAppProvisioningInfo> xAppProvisioningInfo) {
        this.xAppProvisioningInfo = xAppProvisioningInfo;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}