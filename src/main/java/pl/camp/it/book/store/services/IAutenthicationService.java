package pl.camp.it.book.store.services;

import pl.camp.it.book.store.model.User;

public interface IAutenthicationService {
    boolean authenticate(String login, String password);
    void logout();

    void registerUser(User user);

}
