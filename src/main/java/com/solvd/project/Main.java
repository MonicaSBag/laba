package com.solvd.project;


import com.solvd.project.models.CostEstimator;
import com.solvd.project.models.StatusChecker;
import com.solvd.project.models.*;
import com.solvd.project.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

import static com.solvd.project.enums.ContructionType.*;
import static com.solvd.project.enums.MaterialType.*;
import static com.solvd.project.enums.ProjectStatus.*;


class Main {
    public static void main(String[] args){
    final Logger logger = LogManager.getLogger(Main.class);

    Client client = new Client("Monica Cliente", "698-25825-548");

    Supplier supplier = new Supplier("Material for Construction S.R.L",
            "96.888.486-9", "6566560", "construction@gmail.com");

    People arquitect = new People("Luisa Lopez", "Architect");
    People siteManager = new People("Victor Brandam", "Site Manager");
    People[] handyman = {
            new People("Pedro Vazques", "Handyman"),
            new People("Luis Perez", "Handyman"),
            new People("Carlos Gonzales", "Handyman"),
    };

    Crew crew = new Crew(arquitect, siteManager, handyman);

    HouseProject project = new HouseProject(
            2000,
            MEDIUM,
            client,
            supplier,
            crew,
            HOUSE,
            COMPLETED
    );

    MaterialQuality materialQuality = new MaterialQuality(project.getMaterialQuality());
    CostEstimator quickCalc = (p,q) -> p.getSquareMeters() * q.getPrice();
    double estimate =  quickCalc.estimate(project, materialQuality);
    logger.info("Total estimate: " + String.format("%,d", (int) estimate));

    StatusChecker checker = p -> p.estimateTime() > 10
            ? "Long project: " + p.estimateTime() + " weeks"
            : "Short project: " + p.estimateTime() + " weeks";
    logger.info(checker.check(project));

    ProjectSummarizer shortSummary = p ->
            "Project: " + p.getSquareMeters() + "m2 | " +
            "Quality: " + p.getMaterialQuality() + " | " +
            "Time: " + p.estimateTime() + " weeks";
    logger.info(shortSummary.summarize(project));

    if (!project.isValid()) {
        logger.error("Invalid Project");
        return;
    }
        ReportService reportService = new ReportService();
        try {
            reportService.saveNewProject(
                    project,
                    new File("src/main/resources/input.txt")
            );
        } catch (Exception e) {
            logger.error("Error while saving project", e);
        }

        logger.info("Project saved!");

    }
}

