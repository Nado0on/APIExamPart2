package service;

import domain.User;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
       User user = userRepository.findById(id).orElse(null);
       if (user == null) {
           throw new ResourceNotFoundException("User " + id + " not found");
       }
       return user;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            userList.add(user);
        }
        return userList;
        }

        public User updateUser(Long id, User userDetails) {
            User user = getUserById(id);
            user.setUsername(userDetails.getUsername());
            user.setGender(userDetails.getGender());
            return userRepository.save(user);
        }

        public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
        }

}


