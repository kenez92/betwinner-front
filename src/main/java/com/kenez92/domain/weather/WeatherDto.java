package com.kenez92.domain.weather;

import lombok.Data;

import java.util.Date;

@Data
public class WeatherDto {
    private String country;
    private Date date;
    private Double tempFelt;
    private Double tempMin;
    private Double tempMax;
    private Integer pressure;
}
