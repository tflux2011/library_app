package edu.miu;
import java.io.Serializable;

public class LoginException
        extends Exception
        implements Serializable {

    public LoginException() {
    }

    public LoginException(String msg) {
        super(msg);
    }

    public LoginException(Throwable t) {
        super(t);
    }
}
