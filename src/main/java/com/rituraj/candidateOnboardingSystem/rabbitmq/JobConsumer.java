package com.rituraj.candidateOnboardingSystem.rabbitmq;

import com.rituraj.candidateOnboardingSystem.config.RabbitConfig;
import com.rituraj.candidateOnboardingSystem.dto.CandidateRabbitDTO;
import com.rituraj.candidateOnboardingSystem.dto.JobNotificationDTO;
import com.rituraj.candidateOnboardingSystem.dto.JobRabbitDTO;
import com.rituraj.candidateOnboardingSystem.repo.CandidateRepo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class JobConsumer {
    private final CandidateRepo candidateRepository;
    private final JavaMailSender mailSender;

    public JobConsumer(CandidateRepo candidateRepository, JavaMailSender mailSender) {
        this.candidateRepository = candidateRepository;
        this.mailSender = mailSender;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void receiveJob(JobNotificationDTO notificationDTO) {
        // Fetch CandidateDTOs (name, email, techNames)
        List<CandidateRabbitDTO> candidates = candidateRepository.findAllCandidateDTOs();

        for (CandidateRabbitDTO candidate : candidates) {
            if (jobMatchesCandidate(notificationDTO, candidate)) {
                sendEmail(candidate.getEmail(), notificationDTO.getJobRabbitDTO());
            }
        }
    }

    private boolean jobMatchesCandidate(JobNotificationDTO notificationDTO, CandidateRabbitDTO candidate) {
        return !Collections.disjoint(notificationDTO.getTechNames(), candidate.getTechNames());
    }

    private void sendEmail(String to, JobRabbitDTO jobDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("New Job Opportunity");
        message.setText("A new job matching your tech stack was posted: " +
                "ID: " + jobDTO.getId() +
                ", Role: " + jobDTO.getJobRole() +
                ", CTC: " + jobDTO.getCtc() +
                ", Degree: " + jobDTO.getDegreeType() +
                ", Min Marks: " + jobDTO.getMinMarks() +
                ", Status: " + jobDTO.getActiveStatus());
        mailSender.send(message);
    }
}