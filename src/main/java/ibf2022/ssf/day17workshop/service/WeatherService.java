package ibf2022.ssf.day17workshop.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.ssf.day17workshop.model.Weather;

@Service
public class WeatherService {
    
    @Value("${day17workshop.open.weather.url}")
    private String openWeatherURL;

    @Value("${day17workshop.open.weather.api.key}")
    private String openWeatherAPIKey;


    public Optional<Weather> getWeather(String city, String unitMeasurement) throws IOException {
        String weatherURL = UriComponentsBuilder.fromUriString(openWeatherURL).queryParam("q", city.replaceAll(" ", "+"))
        .queryParam("units", unitMeasurement)
        .queryParam("appId", openWeatherAPIKey).toUriString();

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.getForEntity(weatherURL, String.class);
        Weather weather = Weather.create(response.getBody());

        if (weather != null) {
            return Optional.of(weather);
        }
        return Optional.empty();
    }
}
