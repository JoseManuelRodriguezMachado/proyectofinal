/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import vista.Interfaz;
import vista.InterfazEmpleado;
import vista.InterfazProyecto;

/**
 *
 * @author jomro
 */
public class controlador implements ActionListener,MouseListener{
    
    Interfaz vista;

    public enum AccionMVC
    {
        jButtonEmpleados,
        jButtonProyectos,
        jButtonSalir
    }
    
    public controlador( Interfaz vista )
    {
        this.vista = vista;
    }
    
    public void iniciar()
    {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vista);
            vista.setVisible(true);
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {}

        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.jButtonEmpleados.setActionCommand( "jButtonEmpleados" );
        this.vista.jButtonEmpleados.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.jButtonProyectos.setActionCommand( "jButtonProyectos" );
        this.vista.jButtonProyectos.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.jButtonSalir.setActionCommand( "jButtonSalir" );
        this.vista.jButtonSalir.addActionListener(this);
    }
    
    //Control de eventos de los controles que tienen definido un "ActionCommand"
    @Override
    public void actionPerformed(ActionEvent e) {

    switch ( AccionMVC.valueOf( e.getActionCommand() ) )
        {
            case jButtonEmpleados:
                //abre la ventana de los empleados
                new controladorEmpleado(new InterfazEmpleado()).iniciar();
                this.vista.dispose();
                break;
            case jButtonProyectos:
                //abre la ventana de los proyectos
                 new controladorProyecto(new InterfazProyecto()).iniciar();
                 this.vista.dispose();
                break;
            case jButtonSalir:
                //finaliza el programa y se sale
                this.vista.EXIT_ON_CLOSE();
                break;       
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }
}
