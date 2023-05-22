package com.service.impl;

import java.util.List;
import java.util.Optional;

import com.model.User;
import com.repository.UserRepository;
import com.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.User;
import com.repository.UserRepository;
import com.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> update(User user) {

        Optional<User> userOpt = userRepository.findById(user.getId());

        if (userOpt.isPresent()){
            User  existingUser = userOpt.get();

            if(user.getFirstName() != null){
                existingUser.setFirstName(user.getFirstName());
            }
            if(user.getLastName() !=null){
                existingUser.setLastName(user.getLastName());
            }
            if(user.getAge() != null){
                existingUser.setAge(user.getAge());
            }
            if(user.getCountry() != null){
                existingUser.setCountry(user.getCountry());
            }
            userRepository.save(existingUser);

            return Optional.of(existingUser);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> delete(Long id) {
        Optional<User> userOpt = userRepository.findById(id);

        if(userOpt.isPresent()){
            userRepository.delete(userOpt.get());
            return userOpt;
        }
        return Optional.empty();
    }
}
