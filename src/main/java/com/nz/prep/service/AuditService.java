package com.nz.prep.service;

import com.nz.prep.entity.User;
import com.nz.prep.event.UserCreatedEvent;
import com.nz.prep.event.UserUpdatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class AuditService {

    @EventListener
    public void handleUserCreated(UserCreatedEvent event) {
        User user = event.getUser();
        System.out.println("[AUDIT] User created: id=" + user.getId() + ", email=" + user.getEmail() + ", name=" + user.getName());
    }

    @EventListener
    public void handleUserUpdated(UserUpdatedEvent event) {
        User user = event.getUser();
        System.out.println("[AUDIT] User updated: id=" + user.getId() + ", email=" + user.getEmail() + ", name=" + user.getName());
    }
}