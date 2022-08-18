package com.hackerrank.weather.resource;

import com.hackerrank.weather.model.Weather;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-17T14:52:06+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class WeatherMapperImpl implements WeatherMapper {

    @Override
    public Weather of(WeatherRequest weatherRequest) {
        if ( weatherRequest == null ) {
            return null;
        }

        Weather weather = new Weather();

        weather.setDate( weatherRequest.getDate() );
        weather.setLat( weatherRequest.getLat() );
        weather.setLon( weatherRequest.getLon() );
        weather.setCity( weatherRequest.getCity() );
        weather.setState( weatherRequest.getState() );
        List<Double> list = weatherRequest.getTemperatures();
        if ( list != null ) {
            weather.setTemperatures( new ArrayList<Double>( list ) );
        }

        return weather;
    }

    @Override
    public WeatherCityAndTempResponse of(Weather weather) {
        if ( weather == null ) {
            return null;
        }

        WeatherCityAndTempResponse weatherCityAndTempResponse = new WeatherCityAndTempResponse();

        weatherCityAndTempResponse.setCity( weather.getCity() );
        List<Double> list = weather.getTemperatures();
        if ( list != null ) {
            weatherCityAndTempResponse.setTemperatures( new ArrayList<Double>( list ) );
        }

        return weatherCityAndTempResponse;
    }

    @Override
    public List<WeatherCityAndTempResponse> of(List<Weather> weathers) {
        if ( weathers == null ) {
            return null;
        }

        List<WeatherCityAndTempResponse> list = new ArrayList<WeatherCityAndTempResponse>( weathers.size() );
        for ( Weather weather : weathers ) {
            list.add( of( weather ) );
        }

        return list;
    }
}
