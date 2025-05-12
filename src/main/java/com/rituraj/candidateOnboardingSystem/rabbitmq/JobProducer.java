package com.rituraj.candidateOnboardingSystem.rabbitmq;

import com.rituraj.candidateOnboardingSystem.config.RabbitConfig;
import com.rituraj.candidateOnboardingSystem.model.Job;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class JobProducer {

    private RabbitTemplate rabbitTemplate;

    public JobProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJob(Job job) {
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE, job);
    }
}

