package virtusa.project.exceptions;

public class AccountDeletedException extends RuntimeException {

    public AccountDeletedException() {
        super("This account has been deleted.");
    }
}