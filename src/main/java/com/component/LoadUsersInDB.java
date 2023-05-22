package com.component;

import com.model.User;
import com.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Transactional
public class LoadUsersInDB implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User("Jan", "Kowalski", 23, "Poland");
        User user2 = new User("Rick", "Salmon", 35, "USA");
        User user3 = new User("Sasha", "Madej", 39, "Ukraine");
        User user4 = new User("Amy", "Silva", 21, "Brazil");
        User user5 = new User("Ola", "Kot", 28, "Poland");
        User user6 = new User("Jerzy", "Jurek", 44, "Australia");
        User user7 = new User("Tsu", "Mado", 63, "China");
        User user8 = new User("Maciej", "Kozak", 21, "Japan");
        User user9 = new User("Valentin", "Kawka", 27, "Peru");
        User user10 = new User("Eero", "Bloom", 20, "Finland");

        List<User> usersList = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10);
        userRepository.saveAll(usersList);
    }
}
