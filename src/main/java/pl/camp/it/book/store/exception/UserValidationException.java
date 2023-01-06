package pl.camp.it.book.store.exception;

public class UserValidationException extends RuntimeException{

    private String info;

    public UserValidationException(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
