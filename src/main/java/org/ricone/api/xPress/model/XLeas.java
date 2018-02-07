/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xPress.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xLea"})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XLeas {

    @JsonProperty("xLea")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @XmlElement(name = "xLea")
    private List<XLea> xLea;

    public XLeas() {
        xLea = new ArrayList<>();
    }


    public XLeas(List<XLea> xLea) {
        super();
        this.xLea = xLea;
    }

    @JsonProperty("xLea")
    public List<XLea> getXLea() {
        return xLea;
    }

    @JsonProperty("xLea")
    public void setXLea(List<XLea> xLea) {
        this.xLea = xLea;
    }

    @Override
    public String toString() {
        return "XLeas{" + "xLea=" + xLea + '}';
    }
}