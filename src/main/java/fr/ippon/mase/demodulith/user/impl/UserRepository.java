package fr.ippon.mase.demodulith.user.impl;

import fr.ippon.mase.demodulith.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserRepository {

    public User save(User user) {
        return user;
    }
}
