package comp127.weather.api;

import net.aksingh.owmjapis.AbstractWeather;
import net.aksingh.owmjapis.HourlyForecast;

import java.util.Date;

/**
 * A class to provide simple access to hourly forecasts.
 * The array of these objects should have 5 days worth of data with one object for every 3 hours.
 */
public class ForecastConditions extends Conditions {
    public static final ForecastConditions BLANK = new ForecastConditions();

    private Date predictionTime;
    private Double minTemperature;
    private Double maxTemperature;

    private ForecastConditions() {
    }

    /**
     * For fetching from API
     */
    ForecastConditions(HourlyForecast.Forecast rawForecast) {
        predictionTime = rawForecast.getDateTime();
        if (rawForecast.hasCloudsInstance()) {
            cloudCoverage = nullIfNaN(rawForecast.getCloudsInstance().getPercentageOfClouds());
        }
        if (rawForecast.hasMainInstance()) {
            temperature = nullIfNaN(rawForecast.getMainInstance().getTemperature());
            minTemperature = nullIfNaN(rawForecast.getMainInstance().getMinTemperature());
            maxTemperature = nullIfNaN(rawForecast.getMainInstance().getMaxTemperature());
            pressure = nullIfNaN(rawForecast.getMainInstance().getPressure());
            humidity = nullIfNaN(rawForecast.getMainInstance().getHumidity());
        }
        if (rawForecast.hasWindInstance() && rawForecast.getWindInstance().hasWindSpeed()) {
            windSpeed = nullIfNaN(rawForecast.getWindInstance().getWindSpeed());
            windDirectionInDegrees = nullIfNaN(rawForecast.getWindInstance().getWindDegree());
        }
        if (rawForecast.hasWeatherInstance() && rawForecast.getWeatherCount() > 0 && rawForecast.getWeatherInstance(0) != null) {
            AbstractWeather.Weather weather = rawForecast.getWeatherInstance(0);
            if (weather.hasWeatherDescription()) {
                weatherDescription = weather.getWeatherDescription();
                weatherIconFile = weather.getWeatherIconName();
            }
        }
    }

    /**
     * For generating test data
     */
    ForecastConditions(Date predictionTime, double temperature, double minTemperature, double maxTemperature,
                       double humidity, double pressure, double cloudCoverage,
                       double windSpeed, double windDirectionInDegrees,
                       String weatherDescription, String weatherIconFile) {
        this.predictionTime = predictionTime;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.cloudCoverage = cloudCoverage;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDirectionInDegrees = windDirectionInDegrees;
        this.weatherDescription = weatherDescription;
        this.weatherIconFile = weatherIconFile;
    }

    /**
     * Returns the moment in time that this prediction is for.
     */
    public Date getPredictionTime() {
        return predictionTime;
    }

    /**
     * Gets the predicted minimum temperature in whatever unit the openWeatherConnection is set to (Default fahrenheit)
     *  @return (returns -100 in case of error)
     */
    public Double getMinTemperature() {
        return minTemperature;
    }

    /**
     * Gets the predicted maximum temperature in whatever unit the openWeatherConnection is set to (Default fahrenheit)
     * @return (returns -100 in case of error)
     */
    public Double getMaxTemperature() {
        return maxTemperature;
    }

    /**
     * Gets an image representing the current weather
     */
    public String getWeatherIcon() {
        return "condition-icons/" + weatherIconFile + ".png";
    }

    @Override
    public String toString() {
        return "ForecastConditions{"
            + "predictionTime=" + predictionTime
            + ", cloudCoverage=" + cloudCoverage
            + ", temperature=" + temperature
            + ", minTemperature=" + minTemperature
            + ", maxTemperature=" + maxTemperature
            + ", pressure=" + pressure
            + ", humidity=" + humidity
            + ", windSpeed=" + windSpeed
            + ", windDirectionInDegrees=" + windDirectionInDegrees
            + ", weatherDescription='" + weatherDescription + '\''
            + ", weatherIconFile='" + weatherIconFile + '\''
            + '}';
    }
}
