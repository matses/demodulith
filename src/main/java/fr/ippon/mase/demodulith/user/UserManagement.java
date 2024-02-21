package fr.ippon.mase.demodulith.user;

import fr.ippon.mase.demodulith.user.impl.UserRepository;
import org.jmolecules.ddd.annotation.Service;

@Service
public class UserManagement {

    private UserRepository userRepository;

    public User signUp(User user) {
        return this.userRepository.save(user);
    }
}
