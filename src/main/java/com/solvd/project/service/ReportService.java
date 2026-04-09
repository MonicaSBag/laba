package com.solvd.project.service;

import com.solvd.project.enums.ContructionType;
import com.solvd.project.enums.MaterialType;
import com.solvd.project.enums.PorjectStatus;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {
    public void analyzeReport(File input, File output) throws Exception{
        List<String> content = FileUtils.readLines( input, StandardCharsets.UTF_8);

        int totalProject = 0;

        Map<ContructionType, Integer> typeCount = new HashMap<>();
        Map<PorjectStatus, Integer> statusCount = new HashMap<>();
        Map<MaterialType, Integer> materialCount = new HashMap<>();

        for (ContructionType type : ContructionType.values()) {typeCount.put(type, 0);}
        for (PorjectStatus status : PorjectStatus.values()) {statusCount.put(status, 0);}
        for (MaterialType type : MaterialType.values()) {materialCount.put(type, 0);}

        for (String line : content) {
            if (StringUtils.isBlank(line)) continue;
            totalProject++;

            for(ContructionType type : ContructionType.values()){
                if (StringUtils.containsIgnoreCase(line, type.name())){
                    typeCount.put(type,typeCount.get(type)+1);
                }
            }
            for(PorjectStatus status : PorjectStatus.values()){
                if (StringUtils.containsIgnoreCase(line, status.name())){
                    statusCount.put(status,statusCount.get(status)+1);
                }
            }
            for(MaterialType type : MaterialType.values()){
                if (StringUtils.containsIgnoreCase(line, type.name())){
                    materialCount.put(type,materialCount.get(type)+1);
                }
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("Total project number: ").append(totalProject).append("\n");
        report.append("By contruction type:\n");
        for  (ContructionType type : ContructionType.values()) {
            report.append("- ").append(type.name()).append(": ").append(typeCount.get(type)).append("\n");
        }
        report.append("By project status:\n");
        for  (PorjectStatus status : PorjectStatus.values()) {
            report.append("- ").append(status.name()).append(": ").append(statusCount.get(status)).append("\n");
        }
        report.append("By material quality:\n");
        for  (MaterialType type : MaterialType.values()) {
            report.append("- ").append(type.name()).append(": ").append(materialCount.get(type)).append("\n");
        }
        FileUtils.writeStringToFile(output, report.toString(), StandardCharsets.UTF_8, true);
    }
}
