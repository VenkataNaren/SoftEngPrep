package com.nz.prep.event;

import com.nz.prep.entity.User;
import org.springframework.context.ApplicationEvent;

public abstract class UserEvent extends ApplicationEvent {

    private final User user;

    public UserEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}