package com.github.alexpfx.udacity.beercollection.domain.model.beer;

/**
 * Created by alexandre on 24/10/17.
 */

public class Beer {
    private String id;
    private String name;
    private String description;
    private String abv;
    private String ibu;
    private String srm;
    private String labelLarge;
    private String labelIcon;
    private String labelMedium;
    private String style;
    private String styleCategory;
    private String servingTemperature;
    private String glass;
    private String shortStyle;
    private String brewery;


    public Beer(String name) {
        this.name = name;
    }

    public Beer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public String getIbu() {
        return ibu;
    }

    public void setIbu(String ibu) {
        this.ibu = ibu;
    }

    public String getSrm() {
        return srm;
    }

    public void setSrm(String srm) {
        this.srm = srm;
    }

    public String getLabelLarge() {
        return labelLarge;
    }

    public void setLabelLarge(String labelLarge) {
        this.labelLarge = labelLarge;
    }

    public String getLabelIcon() {
        return labelIcon;
    }

    public void setLabelIcon(String labelIcon) {
        this.labelIcon = labelIcon;
    }

    public String getLabelMedium() {
        return labelMedium;
    }

    public void setLabelMedium(String labelMedium) {
        this.labelMedium = labelMedium;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyleCategory() {
        return styleCategory;
    }

    public void setStyleCategory(String styleCategory) {
        this.styleCategory = styleCategory;
    }

    public String getServingTemperature() {
        return servingTemperature;
    }

    public void setServingTemperature(String servingTemperature) {
        this.servingTemperature = servingTemperature;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public String getShortStyle() {
        return shortStyle;
    }

    public void setShortStyle(String shortStyle) {
        this.shortStyle = shortStyle;
    }

    public String getBrewery() {
        return brewery;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }


    @Override
    public String toString() {
        return "Beer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", abv='" + abv + '\'' +
                ", ibu='" + ibu + '\'' +
                ", srm='" + srm + '\'' +
                ", labelLarge='" + labelLarge + '\'' +
                ", labelIcon='" + labelIcon + '\'' +
                ", labelMedium='" + labelMedium + '\'' +
                ", style='" + style + '\'' +
                ", styleCategory='" + styleCategory + '\'' +
                ", servingTemperature='" + servingTemperature + '\'' +
                ", glass='" + glass + '\'' +
                ", shortStyle='" + shortStyle + '\'' +
                ", brewery='" + brewery + '\'' +
                '}';
    }
}
