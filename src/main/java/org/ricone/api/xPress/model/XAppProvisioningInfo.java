package org.ricone.api.xPress.model;

import com.fasterxml.jackson.annotation.*;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "@refId",
        "type",
        "loginId",
        "tempPassword",
        "tempPasswordExpiryDate"
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XAppProvisioningInfo implements Serializable {

    @JsonProperty("@refId")
    @XmlElement(name = "@refId")
    private String refId;
    @JsonProperty("type")
    @XmlElement(name = "type")
    private String type;
    @JsonProperty("loginId")
    @XmlElement(name = "loginId")
    private String loginId;
    @JsonProperty("tempPassword")
    @XmlElement(name = "tempPassword")
    private String tempPassword;
    @JsonProperty("tempPasswordExpiryDate")
    @XmlElement(name = "tempPasswordExpiryDate")
    private String tempPasswordExpiryDate;

    @JsonIgnore
    @XmlTransient
    private Map<String, Object> additionalProperties = new HashMap<>();
    private final static long serialVersionUID = 3341712217734119376L;

    public XAppProvisioningInfo() {
    }

    public XAppProvisioningInfo(String refId, String type, String loginId, String tempPassword, String tempPasswordExpiryDate) {
        super();
        this.refId = refId;
        this.type = type;
        this.loginId = loginId;
        this.tempPassword = tempPassword;
        this.tempPasswordExpiryDate = tempPasswordExpiryDate;
    }

    @JsonProperty("@refId")
    public String getRefId() {
        return refId;
    }

    @JsonProperty("@refId")
    public void setRefId(String refId) {
        this.refId = refId;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("loginId")
    public String getLoginId() {
        return loginId;
    }

    @JsonProperty("loginId")
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    @JsonProperty("tempPassword")
    public String getTempPassword() {
        return tempPassword;
    }

    @JsonProperty("tempPassword")
    public void setTempPassword(String tempPassword) {
        this.tempPassword = tempPassword;
    }

    @JsonProperty("tempPasswordExpiryDate")
    public String getTempPasswordExpiryDate() {
        return tempPasswordExpiryDate;
    }

    @JsonProperty("tempPasswordExpiryDate")
    public void setTempPasswordExpiryDate(String tempPasswordExpiryDate) {
        this.tempPasswordExpiryDate = tempPasswordExpiryDate;
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