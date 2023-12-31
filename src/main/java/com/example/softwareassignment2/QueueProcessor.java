package com.example.softwareassignment2;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class QueueProcessor {

    @Scheduled(fixedRate = 5000) // 10 seconds
    public void processQueue() {
        // Your code to pop element from the queue goes here
        // e.g., queue.pop();
        System.out.println("Processing queue at: " + System.currentTimeMillis());
    }
}
