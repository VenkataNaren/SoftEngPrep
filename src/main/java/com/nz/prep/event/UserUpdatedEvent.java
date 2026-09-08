package com.nz.prep.event;

import com.nz.prep.entity.User;
import org.springframework.context.ApplicationEvent;

public class UserUpdatedEvent extends UserEvent {

    public UserUpdatedEvent(Object source, User user) {
        super(source, user);
    }
}