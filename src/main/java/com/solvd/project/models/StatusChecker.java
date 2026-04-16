package com.solvd.project.models;

@FunctionalInterface
public interface StatusChecker {
    String check(HouseProject project);
}

