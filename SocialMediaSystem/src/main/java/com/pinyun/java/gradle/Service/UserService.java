package com.pinyun.java.gradle.Service;


import com.pinyun.java.gradle.Exception.PhoneNumberExistsException;
import com.pinyun.java.gradle.Repository.UserRepository;
import com.pinyun.java.gradle.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user) throws PhoneNumberExistsException {
        // Check if phone number already exists
        if (userRepository.existsByPhoneNumber(user.getPhoneNnum())) {
            throw new PhoneNumberExistsException("Phone number already exists");
        }

        // Save user to the database
        userRepository.save(user);
    }
    public void loginUser(User request) {
        // Implement login logic
    }
}
