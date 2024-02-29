package fr.ippon.mase.demodulith.user.impl;

import fr.ippon.mase.demodulith.user.User;
import org.jmolecules.ddd.types.Repository;

public interface Users extends Repository<User, User.UserId> {

    User save(User user);
    
    User findById(User.UserId id);

    void deleteById(User.UserId id);
}
