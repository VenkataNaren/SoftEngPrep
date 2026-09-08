package com.nz.prep.service;

import com.nz.prep.entity.User;
import com.nz.prep.event.UserCreatedEvent;
import com.nz.prep.event.UserUpdatedEvent;
import com.nz.prep.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public User saveUser(User user) {
        boolean isNew = user.getId() == null;
        User savedUser = userRepository.save(user);
        
        if (isNew) {
            eventPublisher.publishEvent(new UserCreatedEvent(this, savedUser));
        } else {
            eventPublisher.publishEvent(new UserUpdatedEvent(this, savedUser));
        }
        
        return savedUser;
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }
}