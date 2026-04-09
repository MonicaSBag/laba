package com.solvd.project;

import com.solvd.project.service.ReportService;

import java.io.File;

public class ReportMain {
    public static void main(String[] args) throws Exception {
        ReportService service = new ReportService();
        service.analyzeReport(
                new File("src/main/resources/input.txt"),
                new File("src/main/resources/results.txt")
        );
    }
}
