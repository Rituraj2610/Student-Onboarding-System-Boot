package com.rituraj.candidateOnboardingSystem.dto;

import com.rituraj.candidateOnboardingSystem.enums.TechName;
import lombok.Data;
import java.util.List;

@Data
public class JobNotificationDTO {
    private JobRabbitDTO jobRabbitDTO; // Job details for email
    private List<TechName> techNames; // Tech stack for matching
}
