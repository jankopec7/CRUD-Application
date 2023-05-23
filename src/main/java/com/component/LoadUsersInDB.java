package com.component;

import com.model.User;
import com.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Transactional
public class LoadUsersInDB implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User("janK", UUID.randomUUID().toString(),"Jan", "Kowalski", 23, "Poland");
        User user2 = new User("rickS", UUID.randomUUID().toString(), "Rick", "Salmon", 35, "USA");
        User user3 = new User("sashaM", UUID.randomUUID().toString(),  "Sasha", "Madej", 39, "Ukraine");
        User user4 = new User("amyS", UUID.randomUUID().toString(), "Amy", "Silva", 21, "Brazil");
        User user5 = new User("olaK", UUID.randomUUID().toString(), "Ola", "Kot", 28, "Poland");
        User user6 = new User("jerzyJ", UUID.randomUUID().toString(),"Jerzy", "Jurek", 44, "Australia");
        User user7 = new User("tsuM", UUID.randomUUID().toString(),"Tsu", "Mado", 63, "China");
        User user8 = new User("maciejK", UUID.randomUUID().toString(), "Maciej", "Kozak", 21, "Japan");
        User user9 = new User("valentinK", UUID.randomUUID().toString(), "Valentin", "Kawka", 27, "Peru");
        User user10 = new User("eeroB", UUID.randomUUID().toString(), "Eero", "Bloom", 20, "Finland");

        List<User> usersList = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10);

        usersList.stream().map(user-> {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    return user;
                }).collect(Collectors.toList());
        userRepository.saveAll(usersList);
    }
}
