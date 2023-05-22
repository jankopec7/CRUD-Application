package com.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.model.User;
import com.repository.UserRepository;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    public static List<User> usersList = new ArrayList<>();
    private static Long COUNTER = 1l;

    @Autowired
    private UserRepository userRepository;

    static{
        User user = new User(COUNTER++,"Jan", "Kowalski", 23, "Poland");
        usersList.add(user);
        user = new User(COUNTER++, "Rick", "Salmon", 35, "USA");
        usersList.add(user);
        user = new User(COUNTER++,"Sasha", "Madej", 39, "Ukraine");
        usersList.add(user);
        user = new User(COUNTER++, "Amy", "Silva", 21, "Brazil");
        usersList.add(user);
    }
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
