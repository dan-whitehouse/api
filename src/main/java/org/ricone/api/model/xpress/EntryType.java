/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.model.xpress;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "code",
        "otherCode"
})
public class EntryType {

    @JsonProperty("code")
    private String code;
    @JsonProperty("otherCode")
    private List<OtherCode> otherCode;

    public EntryType() {
        otherCode = new ArrayList<>();
    }

    public EntryType(String code, List<OtherCode> otherCode) {
        super();
        this.code = code;
        this.otherCode = otherCode;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("otherCode")
    public List<OtherCode> getOtherCode() {
        return otherCode;
    }

    @JsonProperty("otherCode")
    public void setOtherCode(List<OtherCode> otherCode) {
        this.otherCode = otherCode;
    }

    @Override
    public String toString() {
        return "EntryType{" +
                "code='" + code + '\'' +
                ", otherCode=" + otherCode +
                '}';
    }

    @JsonIgnore
    public boolean isEmptyObject()
    {
        return Stream.of(code, otherCode).allMatch(Objects::isNull);
    }
}