package main;

import java.io.Serializable;

public class Messaggio implements Serializable{
    
    protected static final long serialVersionUID = 1112122200L;
    
    private String messaggio, nick;
    private int type;
    static final int WHOISIN = 0, MESSAGE = 1, LOGOUT = 2;
    
    Messaggio(String _nick, String _messaggio, int _type){
        this.nick=_nick;
        this.messaggio=_messaggio;
        this.type=_type;
    }
    
    String getNick(){
        return nick;
    }
    
    String getMsg(){
        return messaggio;
    }
    
    int getType(){
        return type;
    }
    
}

