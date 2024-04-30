package com.thbs.attendance.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LearningPlanDTO {
    private long batchId;
    private List<PathDTO> path;
}
