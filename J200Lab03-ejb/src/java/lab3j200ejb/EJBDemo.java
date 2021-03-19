package lab3j200ejb;

import javax.ejb.Local;


@Local
public interface EJBDemo {

    boolean login(String login, String psw);

    String getMessage(String login);
    
}
