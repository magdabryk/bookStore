package pl.camp.it.book.store.services.impl;

import jakarta.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.camp.it.book.store.database.IUserDAO;
import pl.camp.it.book.store.model.User;
import pl.camp.it.book.store.services.IAutenthicationService;
import pl.camp.it.book.store.session.SessionObject;

import java.util.Optional;

@Component
public class AuthenticationServicesImpl implements IAutenthicationService {

    @Autowired
    IUserDAO userDAO;
    @Resource
    SessionObject sessionObject;
    @Override
    public boolean authenticate(String login, String password) {
        Optional<User> userBox = this.userDAO.getUserByLogin(login);
        if (userBox.isEmpty() || !userBox.get().getPassword().equals(DigestUtils.md5Hex(password))) {
            return false;
        }
        userBox.get().setPassword(null);
        this.sessionObject.setUser(userBox.get());
        return true;
    }

    @Override
    public void logout() {
        this.sessionObject.setUser(null);
    }

    @Override
    public void registerUser(User user) {
        user.setRole(User.Role.USER);
        this.userDAO.persistUser(user);
    }
}
