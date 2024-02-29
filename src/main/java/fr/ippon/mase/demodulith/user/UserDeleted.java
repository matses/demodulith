package fr.ippon.mase.demodulith.user;

import org.springframework.context.ApplicationEvent;

public class UserDeleted extends ApplicationEvent {

    public UserDeleted(User.UserId id) {
        super(id);
    }
}
