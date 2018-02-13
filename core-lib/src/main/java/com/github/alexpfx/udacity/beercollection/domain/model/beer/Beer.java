package com.github.alexpfx.udacity.beercollection.domain.model.beer;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Beer {

    private String id;

    private String labelLarge;

    private String labelIcon;

    private String labelMedium;

    private String name;

    private String description;

    private String style;

    private String shortStyle;

    private String styleCategory;

    private String abv;

    private String ibu;

    private String organic;

    private String srm;

    private String srmHexColor;

    private String servingTemperature;

    private String glass;

    private String breweryName;

    private String breweryWebsite;


    public Beer(String name) {
        this.name = name;
    }


    public Beer() {
    }
}