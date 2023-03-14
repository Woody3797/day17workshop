package ibf2022.ssf.day17workshop.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;


public class Weather implements Serializable{
    
    private String city;
    private String temperature;
    private List<Conditions> conditions = new LinkedList<>();
    private Long weatherTimeStamp;
    private Long sunsetTimeStamp;
    private Long sunriseTimeStamp;
    
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getTemperature() {
        return temperature;
    }
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
    public List<Conditions> getConditions() {
        return conditions;
    }
    public void setConditions(List<Conditions> conditions) {
        this.conditions = conditions;
    }
    public Long getWeatherTimeStamp() {
        return weatherTimeStamp;
    }
    public void setWeatherTimeStamp(Long weatherTimeStamp) {
        this.weatherTimeStamp = weatherTimeStamp;
    }
    public Long getSunsetTimeStamp() {
        return sunsetTimeStamp;
    }
    public void setSunsetTimeStamp(Long sunsetTimeStamp) {
        this.sunsetTimeStamp = sunsetTimeStamp;
    }
    public Long getSunriseTimeStamp() {
        return sunriseTimeStamp;
    }
    public void setSunriseTimeStamp(Long sunriseTimeStamp) {
        this.sunriseTimeStamp = sunriseTimeStamp;
    }

    public static Weather create(String json) throws IOException {
        Weather weather = new Weather();
        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader jr = Json.createReader(is);
            JsonObject jo = jr.readObject();
            weather.setCity(jo.getString("name"));
            JsonNumber wDt = jo.getJsonNumber("dt");
            weather.setWeatherTimeStamp(wDt.longValue());
            JsonObject mainObj = jo.getJsonObject("main");
            weather.setTemperature(mainObj.getJsonNumber("temp").toString());
            JsonObject sysObj = jo.getJsonObject("sys");
            weather.setSunriseTimeStamp(sysObj.getJsonNumber("sunrise").longValue());
            weather.setSunsetTimeStamp(sysObj.getJsonNumber("sunset").longValue());
            weather.conditions = jo.getJsonArray("weather").stream().map(v -> (JsonObject) v)
            .map(v -> Conditions.createJSON(v)).toList();
        }
        return weather;
    }
    


}
