package com.nz.prep.event;

import com.nz.prep.entity.User;
import org.springframework.context.ApplicationEvent;

public class UserCreatedEvent extends UserEvent {

    public UserCreatedEvent(Object source, User user) {
        super(source, user);
    }
}