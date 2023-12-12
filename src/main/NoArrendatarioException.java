package main;

public class NoArrendatarioException extends Exception{
    public NoArrendatarioException(String msg)
    {
        super(msg);
    }
}