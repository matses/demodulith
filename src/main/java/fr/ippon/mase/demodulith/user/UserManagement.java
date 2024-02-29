package fr.ippon.mase.demodulith.user;

import fr.ippon.mase.demodulith.user.impl.Users;
import org.jmolecules.ddd.annotation.Service;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserManagement {

    private final Users userRepository;
    private final ApplicationEventPublisher events;

    public UserManagement(Users userRepository, ApplicationEventPublisher events) {
        this.userRepository = userRepository;
        this.events = events;
    }

    public User signUp(User user) {
        return this.userRepository.save(user);
    }

    @Transactional
    public void unsubscribe(User.UserId id) {
        userRepository.deleteById(id);
        events.publishEvent(new UserDeleted(id));
    }

}
