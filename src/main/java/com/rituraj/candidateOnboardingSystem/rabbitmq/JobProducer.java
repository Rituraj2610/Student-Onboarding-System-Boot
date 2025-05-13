package com.rituraj.candidateOnboardingSystem.rabbitmq;

import com.rituraj.candidateOnboardingSystem.config.RabbitConfig;
import com.rituraj.candidateOnboardingSystem.dto.JobNotificationDTO;
import com.rituraj.candidateOnboardingSystem.dto.JobRabbitDTO;
import com.rituraj.candidateOnboardingSystem.model.Job;
import com.rituraj.candidateOnboardingSystem.enums.TechName;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class JobProducer {
    private final RabbitTemplate rabbitTemplate;

    public JobProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJob(Job job) {
        // Map Job to DTOs
        JobRabbitDTO jobDTO = new JobRabbitDTO();
        jobDTO.setId(job.getId());
        jobDTO.setCtc(job.getCtc());
        jobDTO.setJobRole(job.getJobRole());
        jobDTO.setDegreeType(job.getDegreeType());
        jobDTO.setMinMarks(job.getMinMarks());
        jobDTO.setActiveStatus(job.getActiveStatus());

        JobNotificationDTO notificationDTO = new JobNotificationDTO();
        notificationDTO.setJobRabbitDTO(jobDTO);
        notificationDTO.setTechNames(
                job.getTechStackList().stream()
                        .map(techStack -> techStack.getName())
                        .collect(Collectors.toList())
        );

        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE, notificationDTO);
    }
}