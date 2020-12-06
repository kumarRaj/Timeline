package com.project.timeline.service;

import com.project.timeline.model.User;
import com.project.timeline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

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
        if(user.getEmailId().equals("") || VALID_EMAIL_ADDRESS_REGEX.matcher(user.getEmailId()).find() )
            return false;
        if(user.getPassword().equals("") || user.getPassword().length() < 6)
            return false;
        return true;
    }

}
