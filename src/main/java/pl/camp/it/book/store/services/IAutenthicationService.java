package pl.camp.it.book.store.services;

import pl.camp.it.book.store.model.User;

public interface IAutenthicationService {
    void authenticate(String login, String password);
    void logout();
    void registerUser(User user);

}
