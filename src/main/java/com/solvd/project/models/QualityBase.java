package com.solvd.project.models;

public abstract class QualityBase {
    protected String qualityName;

    public QualityBase(String qualityName) {
        this.qualityName = qualityName;
    }

    public String getQualityName() {
        return qualityName;
    }
}