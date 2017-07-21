package com.wind.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author PhongTn
 * @since 1.0 at 21/07/2017 14:54
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class MonthlyShows implements Serializable {

    @JsonProperty("live_shows")
    public LiveShow[] live_shows;
    @JsonProperty("month")
    public String month;

    @JsonCreator
    public MonthlyShows(@JsonProperty("live_shows") LiveShow[] live_shows,
                        @JsonProperty("month") String month) {
        super();
        try {
            this.live_shows = new LiveShow[live_shows.length];
            for (int i = 0; i < live_shows.length; i++)
                this.live_shows[i] = live_shows[i];
        } catch (Exception e) {

            System.err.println("Livehows is null");
            e.printStackTrace();
        }

        this.month = month;
    }
}
