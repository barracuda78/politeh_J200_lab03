
package lab3j200ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;

@Stateful
public class Registrator implements EJBDemo, StandardValues {
    private boolean registered;
    private String loginName;
    public int counter;
    
   @PostConstruct
   void init(){
       registered = false;
       System.out.println("---> registrator created");
   }        

    @Override
    public boolean login(String login, String psw) {
        registered = LOGIN.equals(login) && PSW.equals(psw);
        System.out.println("---> Проверка " + login + " , " + psw + " ===>" + registered);
        
        if(registered) 
            loginName = LOGIN;
        
        return registered;
    }

    @Override
    public String getMessage(String login) {
        
        if(loginName == null){
            return "Вы не можете получить сообщение! Залогиньтесь сначала!"; //\n<p><a href=\"index.jsp\">Перейти на главную</a></p>
        }

        if(counter <3){
            counter++;
            return "Стандартное сообщение для " + loginName + ". Количество раз: " + counter + "!";
        }else{
            registered = false;
            loginName = null;
            counter = 0;
            return "Вы уже получили сообщение 3 раза. Мы вас разлогинили. " ;
        }
    }
}
