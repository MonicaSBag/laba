package com.solvd.project.models;

@FunctionalInterface
public interface ProjectSummarizer {
    String summarize(HouseProject project);
}