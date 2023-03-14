package ibf2022.ssf.day17workshop.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2022.ssf.day17workshop.model.Weather;
import ibf2022.ssf.day17workshop.service.WeatherService;

@Controller
@RequestMapping(path = "/weather")
public class WeatherController {

    @Autowired
    private WeatherService wService;

    @GetMapping
    public String getWeather(@RequestParam(required=true) String city, @RequestParam(defaultValue = "metric", required=false) String units, Model model) throws IOException {
        Optional<Weather> weather = wService.getWeather(city, units);
        model.addAttribute("weather", weather.get());
        if (units.equals("metric")) {
            model.addAttribute("units", MeasurementValues.metric);
        } else if (units.equals("standard")) {
            model.addAttribute("units", MeasurementValues.standard);
        } else if (units.equals("imperial")) {
            model.addAttribute("units", MeasurementValues.imperial);
        }
        return "weather";
    }
}

class MeasurementValues {
    public static final String metric = "Celsius";
    public static final String standard = "Kelvin";
    public static final String imperial = "Fahrenheit";
}
