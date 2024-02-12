package fr.ippon.mase.demodulith.user;

import jakarta.persistence.Table;
import org.jmolecules.ddd.types.AggregateRoot;
import org.jmolecules.ddd.types.Identifier;

import java.util.UUID;

@Table(name = "USER_DEMO")
public class User implements AggregateRoot<User, User.UserId> {

    private final UserId id;
    private final String firstName;
    private final String lastName;

    private final String email;


    public record UserId(UUID id) implements Identifier {
    }

    public User(String firstName, String lastName, String email) {
        this.id = new UserId(UUID.randomUUID());
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public UserId getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
