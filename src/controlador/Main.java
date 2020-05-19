/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import vista.InterfazPrincipal;

/**
 *
 * @author jomro
 */
public class Main {
    
    public static void main(String[] args){
        //ejecuta el controlador y este la vista
        new controlador(new InterfazPrincipal()).iniciar();
    }
     
}
