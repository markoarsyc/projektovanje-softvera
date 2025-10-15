/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.Serializable;
import operacije.Operacija;

/**
 *
 * @author Marko
 */
public class Request implements Serializable{
    Object params;
    Operacija operacija;

    public Request() {
    }

    public Request(Object params, Operacija operacija) {
        this.params = params;
        this.operacija = operacija;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    public Operacija getOperacija() {
        return operacija;
    }

    public void setOperacija(Operacija operacija) {
        this.operacija = operacija;
    }
    
    
}
