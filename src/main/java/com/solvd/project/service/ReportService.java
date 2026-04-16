package com.solvd.project.service;

import com.solvd.project.enums.ContructionType;
import com.solvd.project.enums.MaterialType;
import com.solvd.project.enums.ProjectStatus;
import com.solvd.project.models.HouseProject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportService {

    public void analyzeReport(File input, File output) throws Exception{
        List<String> content = FileUtils.readLines( input, StandardCharsets.UTF_8);

        Map<ContructionType, Integer> typeCount = new HashMap<>();
        Map<ProjectStatus, Integer> statusCount = new HashMap<>();
        Map<MaterialType, Integer> materialCount = new HashMap<>();

        List<String> nonBlankLines = content.stream()
                .filter(line -> !StringUtils.isBlank(line))
                .collect(Collectors.toList());

        int totalProject = (int) nonBlankLines.stream()
                .filter(line -> line.contains("----------------"))
                .count();

        nonBlankLines.forEach(line -> {
            Arrays.stream(ContructionType.values())
                    .filter(type -> StringUtils.containsIgnoreCase(line, type.name()))
                    .forEach(type -> typeCount.merge(type, 1, Integer::sum));

            Arrays.stream(ProjectStatus.values())
                    .filter(status -> StringUtils.containsIgnoreCase(line, status.name()))
                    .forEach(status -> statusCount.merge(status, 1, Integer::sum));

            Arrays.stream(MaterialType.values())
                    .filter(type -> StringUtils.containsIgnoreCase(line, type.name()))
                    .forEach(type -> materialCount.merge(type, 1, Integer::sum));
        });

        StringBuilder report = new StringBuilder();
        report.append("Total project number: ").append(totalProject).append("\n");
        report.append("By contruction type:\n");
        Arrays.stream(ContructionType.values())
                .forEach(type -> report.append("- ")
                        .append(type.name()).append(": ")
                        .append(typeCount.getOrDefault(type, 0)).append("\n"));
        report.append("By project status:\n");
        Arrays.stream(ProjectStatus.values())
                .forEach(status -> report.append("- ")
                        .append(status.name()).append(": ")
                        .append(statusCount.getOrDefault(status, 0)).append("\n"));
        report.append("By material quality:\n");
        Arrays.stream(MaterialType.values())
                .forEach(type -> report.append("- ")
                        .append(type.name()).append(": ")
                        .append(materialCount.getOrDefault(type, 0)).append("\n"));
        FileUtils.writeStringToFile(output, report.toString(), StandardCharsets.UTF_8, false);
    }

    public void saveNewProject(HouseProject project, File input) throws Exception{
        String new_line = project.generateReport();
        FileUtils.writeStringToFile(input, new_line + System.lineSeparator() + "----------------\n", StandardCharsets.UTF_8, true);

    }
}
