package com.project.timeline.service;

import com.project.timeline.model.User;
import com.project.timeline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {


    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^(.+)@(.+)$");

    public User save(User user) {
        if(validate(user))
            return userRepository.save(user);
        return null;
    }

    private boolean validate(User user){

        if(user.getFirstName().equals("") || user.getFirstName().length() < 3)
            return false;
        if(user.getLastName().equals("") || user.getLastName().length() < 3)
            return false;
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(user.getEmailId());
        if(user.getEmailId().equals("") || !matcher.matches() )
            return false;
        if(user.getPassword().equals("") || user.getPassword().length() < 6)
            return false;
        return true;
    }

}
