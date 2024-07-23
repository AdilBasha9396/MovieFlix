package com.jpa.test.Services;

import java.util.List;
import com.jpa.test.Entities.User;

public interface UserService {
    public String addUsers(User user);
    public boolean emailExits(String email);
    public boolean checkUser(String email, String password);
    public List<User> viewUser();
    public boolean deleteUser(int id);
    public User getUser(String email);
    public void updateUser(User user, String email); // Update method signature
}
