package pl.camp.it.book.store.validator;


import pl.camp.it.book.store.exception.UserValidationException;
import pl.camp.it.book.store.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    public static void validateLogin(String login) {
        String regex = "^[a-zA-Z0-9]{5,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(login);
        if (!matcher.matches()) {
            throw new UserValidationException("Nieprawiłowy login");
        }
    }
   public static void validatePassword(String password) {
        String regex = "^[a-zA-Z0-9]{5,}$";
        if (!password.matches(regex)) {
            throw new UserValidationException("Nieprawidłowe hasło");

        }
    }

    public static void validateName(String name) {
        String regex = "^[A-Z]{1}[a-z]+$";
        if (!name.matches(regex)) {
            throw new UserValidationException("Nieprawidłowe imię");
        }
    }

    public static void validateSurname(String surname){
        String regex = "^[A-Z]{1}[a-z]+(-[A-Z]{1}[a-z]+)?$";
        if(!surname.matches(regex)){
            throw new UserValidationException("Nieprawdłowe nazwisko");
        }
    }

    public static void validatePasswordsEquality(String password, String password2){
        if(!password.equals(password2)){
            throw new UserValidationException("Hasła nie są jednakowe");
        }
    }
    public static void validateRegisterUser(User user, String repeatedPassword){
        validateName(user.getName());
        validateSurname(user.getSurname());
        validateLogin(user.getLogin());
        validatePassword(user.getPassword());
        validatePasswordsEquality(user.getPassword(), repeatedPassword);
    }

}
