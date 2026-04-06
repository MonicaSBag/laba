package com.solvd.project.models;

import java.util.HashMap;
import java.util.Map;

public class MaterialQuality  extends QualityBase {
    private final Map<String, Integer> qualityPrice = new HashMap<>();

    public MaterialQuality(String qualityName) {
        super(qualityName);

        qualityPrice.put("low", 100000);
        qualityPrice.put("medium", 150000);
        qualityPrice.put("high", 200000);
    }

    public int getPrice() {
        return qualityPrice.get(qualityName);
    }

    public void setPrice(String quality, int price) {
        qualityPrice.put(qualityName, price);
    }

    public Map<String, Integer> getQualityPrice() {
        return qualityPrice;
    }

}
