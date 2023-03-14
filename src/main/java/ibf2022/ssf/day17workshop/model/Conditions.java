package ibf2022.ssf.day17workshop.model;

import java.io.Serializable;

import jakarta.json.JsonObject;

public class Conditions implements Serializable{
    
    private String description;
    private String icon;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public static Conditions createJSON(JsonObject obj) {
        Conditions cond = new Conditions();
        cond.description = "%s - %s".formatted(obj.getString("main"), obj.getString("description"));
        cond.icon = "https://openweathermap.org/img/wn/%s@4x.png".formatted(obj.getString("icon"));
        
        return cond;
    }

    
}
