/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;
import java.util.ArrayList;


/**
 *
 * @author jomro
 */
public class Empleado {
    
    protected String nombre;
    protected String apellidos;
    protected String nif;
    protected LocalDate fecha_nacimiento;
    
    public Empleado(){}
    
    public Empleado(String nombre, String apellidos, LocalDate fecha_nacimiento, String nif){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNif() {
        return nif;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    
    @Override
    public String toString() {
        return "Empleado{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", nif=" + nif + ", fecha_nacimiento=" + fecha_nacimiento +'}';
    }
    
           
}
