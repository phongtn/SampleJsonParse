package com.wind.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;

/**
 * @author PhongTn
 * @since 1.0 at 21/07/2017 18:32
 */
public class JsonConverter {

    private ObjectMapper objectMapper;

    public JsonConverter() {
        this.objectMapper = new ObjectMapper();
    }

    public void generateJsonObject() {
        LiveShow liveShow = new LiveShow("s101", "", 1, "ABC sponser");
        MonthlyShows monthlyShows = new MonthlyShows(new LiveShow[]{liveShow}, "July");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.valueToTree(monthlyShows);
        System.out.printf(jsonNode.toString());
    }

    public void readJsonAsObject(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            MonthlyShows showsInApril = objectMapper.readValue(json, TypeFactory.defaultInstance().constructType(MonthlyShows.class));
            System.out.println("month:" + showsInApril.month);
            for (LiveShow s : showsInApril.live_shows)
                System.out.println(s.showid+ "\t" + s.time + "\t" + s.provider);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String json = "{\"live_shows\":[{\"showid\":\"show1\",\"time\":\"02216629\",\"provider\":0,\"sponser\":\"governmental\"},{\"showid\":\"show2\",\"time\":\"00050340\",\"provider\":2,\"sponser\":\"business\"}],\"month\":\"April\"}";
        JsonConverter jsonConverter = new JsonConverter();
//        jsonConverter.generateJsonObject();
        jsonConverter.readJsonAsObject(json);
    }
}
