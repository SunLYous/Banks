package Exceptions;

public class BankException extends RuntimeException{
    public BankException(String problem){
        super(problem);
    }
}
