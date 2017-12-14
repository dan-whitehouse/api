package org.ricone.api.xPress.model;

import com.fasterxml.jackson.annotation.*;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "xAppProvisioning"
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XAppProvisioningResponse implements Serializable {

    @JsonProperty("xAppProvisioning")
    @XmlElement(name = "xAppProvisioning")
    private XAppProvisioning xAppProvisioning;

    @JsonIgnore
    @XmlTransient
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 8154647960028044369L;

    public XAppProvisioningResponse() {
    }

    public XAppProvisioningResponse(XAppProvisioning xAppProvisioning) {
        super();
        this.xAppProvisioning = xAppProvisioning;
    }

    @JsonProperty("xAppProvisioning")
    public XAppProvisioning getXAppProvisioning() {
        return xAppProvisioning;
    }

    @JsonProperty("xAppProvisioning")
    public void setXAppProvisioning(XAppProvisioning xAppProvisioning) {
        this.xAppProvisioning = xAppProvisioning;
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