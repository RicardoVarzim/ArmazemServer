/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comands;

import java.io.Serializable;

/**
 *
 * @author Ricardo
 */
public class Command implements Serializable {
    
    public String type, sender;
    public Arguments args;
    public Object result;
    
    public Command(String type, String sender, Object[] arguments){
        this.type = type;
        this.sender = sender;
        this.args = new Arguments(arguments);
    }      
    
    @Override
    public String toString(){
        return "{type='"+type+"', sender='"+sender+"', arguments='"+args.toString()+"', result='"+result+"'}";    
    }
}
