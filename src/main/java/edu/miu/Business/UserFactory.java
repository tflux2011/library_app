package edu.miu.Business;

import edu.miu.Model.Auth;
import edu.miu.Model.Data;
import edu.miu.Model.User;

public class UserFactory {
    public static Auth authenticateUser(String username, String password) {
        for (User user : Data.logins) {
            if (user.username.equals(username) && user.password.equals(password)) {
                return user.authorization;
            }
        }
        throw new IllegalArgumentException("Invalid credentials. Please try again.");
    }
}
