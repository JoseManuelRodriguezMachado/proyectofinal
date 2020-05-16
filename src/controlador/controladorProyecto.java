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
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import modelo.Proyecto;
import modelo.modeloProyecto;
import vista.Interfaz;
import vista.InterfazProyecto;

/**
 *
 * @author jomro
 */
public class controladorProyecto implements ActionListener,MouseListener {
    
    InterfazProyecto vistaProyecto;
    modeloProyecto mp = new modeloProyecto();
    Proyecto pr = new Proyecto();
    
    public enum AccionMVC
    {
        jButtonCrearProyecto,
        jButtonEliminarProyecto,
        jButtonListarEmpleados,
        jButtonModificarProyecto,
        jButtonVolver
    }
    
    public controladorProyecto( InterfazProyecto vistaProyecto )
    {
        this.vistaProyecto = vistaProyecto;
    }
    
    /** Inicia el skin y las diferentes variables que se utilizan */
    public void iniciar()
    {
        // Skin tipo WINDOWS
         try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vistaProyecto);
            vistaProyecto.setVisible(true);
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {}

        //declara una acción y añade un escucha al evento producido por el componente
        this.vistaProyecto.jButtonCrearProyecto.setActionCommand( "jButtonCrearProyecto" );
        this.vistaProyecto.jButtonCrearProyecto.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vistaProyecto.jButtonEliminarProyecto.setActionCommand( "jButtonEliminarProyecto" );
        this.vistaProyecto.jButtonEliminarProyecto.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vistaProyecto.jButtonModificarProyecto.setActionCommand( "jButtonModificarProyecto" );
        this.vistaProyecto.jButtonModificarProyecto.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vistaProyecto.jButtonListarEmpleados.setActionCommand( "jButtonListarempleados" );
        this.vistaProyecto.jButtonEliminarProyecto.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vistaProyecto.jButtonVolver.setActionCommand( "jButtonVolver" );
        this.vistaProyecto.jButtonVolver.addActionListener(this);

        //añade e inicia el jtable con un DefaultTableModel vacio
        this.vistaProyecto.jTableEmpleados.addMouseListener(this);
        this.vistaProyecto.jTableEmpleados.setModel( mp.getTablaEmpleado(String.valueOf(this.vistaProyecto.jCBProyecto.getSelectedItem())));
        //añade e inicia el jtable con un DefaultTableModel vacio
        this.vistaProyecto.jTableProyectos.addMouseListener(this);
        this.vistaProyecto.jTableProyectos.setModel( mp.getTablaProyecto() );
        
        //añade e inicia el combobox con los nombres de los proyectos
        this.vistaProyecto.jCBProyecto.addMouseListener(this);
        this.vistaProyecto.jCBProyecto.setModel(mp.getComboBoxProyectos());
    }
    
    //Control de eventos de los controles que tienen definido un "ActionCommand"
    @Override
    public void actionPerformed(ActionEvent e) {
        switch ( controladorProyecto.AccionMVC.valueOf( e.getActionCommand() ) )
        {
            case jButtonCrearProyecto:
                //crea un nuevo proyecto
                pr.setTitulo(this.vistaProyecto.jFormattedTextFieldTitulo.getText());
                pr.setFecha_inicio(LocalDate.parse(this.vistaProyecto.jFormattedTextFieldInicio.getText()));
                pr.setFecha_final(LocalDate.parse(this.vistaProyecto.jFormattedTextFieldFinal.getText()));
                pr.setDescripcion(this.vistaProyecto.jFormattedTextFieldDescripcion.getText());
                if ( this.mp.NuevoProyecto(pr))
                {
                    this.vistaProyecto.jTableProyectos.setModel( this.mp.getTablaProyecto());
                    JOptionPane.showMessageDialog(vistaProyecto,"Exito: Nuevo registro agregado.");
                    this.vistaProyecto.jFormattedTextFieldTitulo.setText("");
                    this.vistaProyecto.jFormattedTextFieldInicio.setText("") ;
                    this.vistaProyecto.jFormattedTextFieldFinal.setText("");
                    this.vistaProyecto.jFormattedTextFieldDescripcion.setText("");
                }
                else //ocurrio un error
                    JOptionPane.showMessageDialog(vistaProyecto,"Error: Los datos son incorrectos.");
                break;
            case jButtonEliminarProyecto:
                //elimina un proyecto
                 if ( this.mp.EliminarProyecto(this.vistaProyecto.jFormattedTextFieldTitulo.getText() ) )
                {
                    this.vistaProyecto.jTableProyectos.setModel( this.mp.getTablaProyecto());
                    JOptionPane.showMessageDialog(vistaProyecto,"Exito: Empleado eliminado.");
                    this.vistaProyecto.jFormattedTextFieldTitulo.setText("");
                    this.vistaProyecto.jFormattedTextFieldInicio.setText("") ;
                    this.vistaProyecto.jFormattedTextFieldFinal.setText("");
                    this.vistaProyecto.jFormattedTextFieldDescripcion.setText("");
                }
                break;
            case jButtonModificarProyecto:
                //modifica un proyecto
                pr.setTitulo(this.vistaProyecto.jFormattedTextFieldTitulo.getText());
                pr.setFecha_inicio(LocalDate.parse(this.vistaProyecto.jFormattedTextFieldInicio.getText()));
                pr.setFecha_final(LocalDate.parse(this.vistaProyecto.jFormattedTextFieldFinal.getText()));
                pr.setDescripcion(this.vistaProyecto.jFormattedTextFieldDescripcion.getText());
                if ( this.mp.NuevoProyecto(pr))
                {
                    this.vistaProyecto.jTableProyectos.setModel( this.mp.getTablaProyecto());
                    JOptionPane.showMessageDialog(vistaProyecto,"Exito: Nuevo registro agregado.");
                    this.vistaProyecto.jFormattedTextFieldTitulo.setText("");
                    this.vistaProyecto.jFormattedTextFieldInicio.setText("") ;
                    this.vistaProyecto.jFormattedTextFieldFinal.setText("");
                    this.vistaProyecto.jFormattedTextFieldDescripcion.setText("");
                }
                else //ocurrio un error
                    JOptionPane.showMessageDialog(vistaProyecto,"Error: Los datos son incorrectos.");
                break; 
            case jButtonListarEmpleados:
                //obtiene del modeloempleado los registros en un DefaultTableModel y lo asigna en la vista
                this.vistaProyecto.jTableEmpleados.setModel(this.mp.getTablaEmpleado(String.valueOf(this.vistaProyecto.jCBProyecto.getSelectedItem())));
                break;
            case jButtonVolver:
                new controlador(new Interfaz()).iniciar();
                this.vistaProyecto.dispose();
                break;
        }
    }
    
    //Cuando pinchas en la tabla
    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource()==this.vistaProyecto.jTableProyectos) {
            if( me.getButton()== 1)//boton izquierdo
                {
                int filaProyectos = this.vistaProyecto.jTableEmpleados.rowAtPoint(me.getPoint());
                if (filaProyectos > -1){          
                    this.vistaProyecto.jFormattedTextFieldTitulo.setText(String.valueOf( this.vistaProyecto.jTableProyectos.getValueAt(filaProyectos, 0) ));
                    this.vistaProyecto.jFormattedTextFieldInicio.setText( String.valueOf( this.vistaProyecto.jTableProyectos.getValueAt(filaProyectos, 1) ));
                    this.vistaProyecto.jFormattedTextFieldFinal.setText( String.valueOf( this.vistaProyecto.jTableProyectos.getValueAt(filaProyectos, 2) ));
                    this.vistaProyecto.jFormattedTextFieldDescripcion.setText(String.valueOf( this.vistaProyecto.jTableProyectos.getValueAt(filaProyectos, 3)));
                }
            }
        } 
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
