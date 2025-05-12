package com.rituraj.candidateOnboardingSystem.rabbitmq;

import com.rituraj.candidateOnboardingSystem.config.RabbitConfig;
import com.rituraj.candidateOnboardingSystem.model.Candidate;
import com.rituraj.candidateOnboardingSystem.model.Job;
import com.rituraj.candidateOnboardingSystem.repo.CandidateRepo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class JobConsumer {

    private CandidateRepo candidateRepository;
    private JavaMailSender mailSender;

    public JobConsumer(CandidateRepo candidateRepository, JavaMailSender mailSender) {
        this.candidateRepository = candidateRepository;
        this.mailSender = mailSender;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void receiveJob(Job job) {
        List<Candidate> allCandidates = candidateRepository.findAll();

        for (Candidate candidate : allCandidates) {
            if (jobMatchesCandidate(job, candidate)) {
                sendEmail(candidate.getEmail(), job);
            }
        }
    }

    private boolean jobMatchesCandidate(Job job, Candidate candidate) {
        return !Collections.disjoint(job.getTechStackList(), candidate.getTechStackList());
    }

    private void sendEmail(String to, Job job) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("New Job Opportunity");
        message.setText("A new job matching your tech stack was posted: " + job.toString());
        mailSender.send(message);
    }

//    private void sendEmail(String to, Job job) {
//        try {
//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true); // true = multipart
//
//            helper.setTo(to);
//            helper.setSubject("New Job Matching Your Skills");
//
//            // You can customize this HTML content as needed
//            String htmlContent = "<html><body>"
//                    + "<h3>Hi,</h3>"
//                    + "<p>A new job has been posted that matches your tech stack!</p>"
//                    + "<p><strong>Job Title:</strong> " + job.getJobRole() + "</p>"
//                    + "<p><strong>Required Tech Stack:</strong> " + String.join((CharSequence) ", ", (CharSequence) job.getTechStackList()) + "</p>"
//                    + "<p>Visit our platform to apply!</p>"
//                    + "</body></html>";
//
//            helper.setText(htmlContent, true); // true = HTML
//
//            mailSender.send(message);
//        } catch (MessagingException e) {
//            e.printStackTrace(); // You might want to log this instead
//        }
//    }

}
