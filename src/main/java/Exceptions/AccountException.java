package Exceptions;

public class AccountException extends RuntimeException{
    public AccountException(String problem){
        super(problem);
    }
}
