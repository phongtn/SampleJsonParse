package com.wind.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author PhongTn
 * @since 1.0 at 21/07/2017 14:51
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class LiveShow implements Serializable {

    @JsonProperty("showid")
    public String showid;
    @JsonProperty("time")
    public String time;
    @JsonProperty("provider")
    public int provider;
    @JsonProperty("sponser")
    public String sponser;

    @JsonCreator
    public LiveShow(@JsonProperty("showid") String showid,
                    @JsonProperty("time") String time,
                    @JsonProperty("provider") int provider,
                    @JsonProperty("sponser") String sponser) {
        super();
        this.showid = showid;
        this.time = time;
        this.provider = provider;
        this.sponser = sponser;
    }
}
