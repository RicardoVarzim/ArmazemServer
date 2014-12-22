/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author Ricardo
 */
public class Arguments implements Serializable {
    
    public ArrayList<Object> listArgs;
    public int size;
    
    public Arguments(Object[] args){
        this.size = args.length;
        listArgs = new ArrayList<>();
        listArgs.addAll(Arrays.asList(args));  
    }
    
    @Override
    public String toString(){
        StringBuilder temp = new StringBuilder();
        for (Object listArg : listArgs) {
            temp.append(listArg + ";");
        }
        return temp.toString();
    }
}
