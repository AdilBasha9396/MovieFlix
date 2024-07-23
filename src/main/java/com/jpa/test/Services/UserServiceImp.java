package com.jpa.test.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jpa.test.Entities.User;
import com.jpa.test.Repositories.UserRepo;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepo urepo;

    @Override
    public String addUsers(User usr) {
        urepo.save(usr);
        return "User created successfully";
    }

    @Override
    public boolean emailExits(String email) {
        return urepo.findByEmail(email) != null;
    }

    @Override
    public boolean checkUser(String email, String password) {
        User usr = urepo.findByEmail(email);
        return usr != null && usr.getPassword().equals(password);
    }

    @Override
    public List<User> viewUser() {
        return urepo.findAll();
    }

    @Override
    public boolean deleteUser(int userId) {
        if (urepo.existsById(userId)) {
            urepo.deleteById(userId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User getUser(String email) {
        return urepo.findByEmail(email);
    }

    @Override
    public void updateUser(User updatedUser, String email) {
        User user = urepo.findByEmail(email);
        if (user != null) {
            user.setName(updatedUser.getName());
            user.setPassword(updatedUser.getPassword());
            user.setGender(updatedUser.getGender());
            user.setPhone(updatedUser.getPhone());
            user.setAddress(updatedUser.getAddress());
            user.setPremium(updatedUser.isPremium());
            urepo.save(user);
        }
    }
}
