/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;

/**
 *
 * @author jomro
 */
public class Proyecto {
    
    protected String titulo;
    protected LocalDate fecha_inicio;
    protected LocalDate fecha_final;
    protected String descripcion;
    
    public Proyecto(){}
    
    public Proyecto(String titulo, LocalDate fecha_inicio, LocalDate fecha_final, String descripcion){
        this.titulo = titulo;
        this.fecha_inicio = fecha_inicio;
        this.fecha_final = fecha_final;
        this.descripcion = descripcion;   
    }
    
    public String getTitulo() {
        return titulo;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public LocalDate getFecha_final() {
        return fecha_final;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public void setFecha_final(LocalDate fecha_final) {
        this.fecha_final = fecha_final;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
