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
import modelo.Empleado;
import modelo.modeloEmpleado;
import vista.InterfazPrincipal;
import vista.InterfazEmpleado;

/**
 *
 * @author jomro
 */
public class controladorEmpleado implements ActionListener,MouseListener {
    
    InterfazEmpleado vistaEmpleados;
    modeloEmpleado me = new modeloEmpleado();
    Empleado em = new Empleado();
    
    public enum AccionMVC
    {
        jButtonAsociar,
        jButtonCrearEmpleado,
        jButtonEliminarEmpleado,
        jButtonListarProyectos,
        jButtonModificarEmpleado,
        jButtonVolver
    }
    
    public controladorEmpleado( InterfazEmpleado vistaEmpleados )
    {
        this.vistaEmpleados = vistaEmpleados;
    }
    
    /** Inicia el skin y las diferentes variables que se utilizan */
    public void iniciar()
    {
        // Skin tipo WINDOWS
         try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vistaEmpleados);
            vistaEmpleados.setVisible(true);
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {}

        //declara una acción y añade un escucha al evento producido por el componente
        this.vistaEmpleados.jButtonAsociar.setActionCommand( "jButtonAsociar" );
        this.vistaEmpleados.jButtonAsociar.addActionListener(this);
        
        //declara una acción y añade un escucha al evento producido por el componente
        this.vistaEmpleados.jButtonCrearEmpleado.setActionCommand( "jButtonCrearEmpleado" );
        this.vistaEmpleados.jButtonCrearEmpleado.addActionListener(this);
        
        //declara una acción y añade un escucha al evento producido por el componente
        this.vistaEmpleados.jButtonEliminarEmpleado.setActionCommand( "jButtonEliminarEmpleado" );
        this.vistaEmpleados.jButtonEliminarEmpleado.addActionListener(this);
       
        //declara una acción y añade un escucha al evento producido por el componente
        this.vistaEmpleados.jButtonModificarEmpleado.setActionCommand( "jButtonModificarEmpleado" );
        this.vistaEmpleados.jButtonModificarEmpleado.addActionListener(this);
        
        //declara una acción y añade un escucha al evento producido por el componente
        this.vistaEmpleados.jButtonListarProyectos.setActionCommand( "jButtonListarProyectos" );
        this.vistaEmpleados.jButtonListarProyectos.addActionListener(this);
        
        //declara una acción y añade un escucha al evento producido por el componente
        this.vistaEmpleados.jButtonVolver.setActionCommand( "jButtonVolver" );
        this.vistaEmpleados.jButtonVolver.addActionListener(this);

        //añade e inicia el jtable 
        this.vistaEmpleados.jTableEmpleados.addMouseListener(this);
        this.vistaEmpleados.jTableEmpleados.setModel(me.getTablaEmpleado());
       
        //añade e inicia el jtable
        this.vistaEmpleados.jTableProyectos.addMouseListener(this);
        this.vistaEmpleados.jTableProyectos.setModel( new DefaultTableModel() );
        
        //añade e inicia el combobox con los nombres de los empleados
        this.vistaEmpleados.jCBEmpleados.addMouseListener(this);
        this.vistaEmpleados.jCBEmpleados.setModel(me.getComboBoxEmpleados());
       
        //añade e inicia el combobox con los nombres de los proyectos
        this.vistaEmpleados.jCBProyectos.addMouseListener(this);
        this.vistaEmpleados.jCBProyectos.setModel(me.getComboBoxProyectos());
    }
    
    //Control de eventos de los controles que tienen definido un "ActionCommand"
    @Override
    public void actionPerformed(ActionEvent e) {
        switch ( controladorEmpleado.AccionMVC.valueOf( e.getActionCommand() ) )
        {
            case jButtonCrearEmpleado:
            {
            //añade un nuevo empleado
            em.setNombre(this.vistaEmpleados.jFormattedTextFieldNombre.getText());
            em.setApellidos(this.vistaEmpleados.jFormattedTextFieldApellidos.getText());
            em.setFecha_nacimiento(LocalDate.parse(this.vistaEmpleados.jFormattedTextFieldFeachaNacimiento.getText()));
            em.setNif(this.vistaEmpleados.jFormattedTextFieldNIF.getText());
            if ( this.me.NuevoEmpleado(em))
            {
                this.vistaEmpleados.jTableEmpleados.setModel( this.me.getTablaEmpleado());
                JOptionPane.showMessageDialog(vistaEmpleados,"Exito: Nuevo Empleado agregado.");
                this.vistaEmpleados.jFormattedTextFieldNombre.setText("");
                this.vistaEmpleados.jFormattedTextFieldApellidos.setText("");
                this.vistaEmpleados.jFormattedTextFieldNIF.setText("");
                this.vistaEmpleados.jFormattedTextFieldFeachaNacimiento.setText("");
            }
            else //ocurrio un error
                JOptionPane.showMessageDialog(vistaEmpleados,"Error: Los datos son incorrectos.");
        }
                break;
            case jButtonModificarEmpleado:
                //modifica un empleado
                em.setNombre(this.vistaEmpleados.jFormattedTextFieldNombre.getText());
                em.setApellidos(this.vistaEmpleados.jFormattedTextFieldApellidos.getText());
                em.setFecha_nacimiento(LocalDate.parse(this.vistaEmpleados.jFormattedTextFieldFeachaNacimiento.getText()));
                em.setNif(this.vistaEmpleados.jFormattedTextFieldNIF.getText());
                if ( this.me.ModificarEmpleado(em))
                {
                    this.vistaEmpleados.jTableEmpleados.setModel( this.me.getTablaEmpleado());
                    JOptionPane.showMessageDialog(vistaEmpleados,"Exito: Nuevo registro agregado.");
                    this.vistaEmpleados.jFormattedTextFieldNombre.setText("");
                    this.vistaEmpleados.jFormattedTextFieldApellidos.setText("") ;
                    this.vistaEmpleados.jFormattedTextFieldNIF.setText("");
                    this.vistaEmpleados.jFormattedTextFieldFeachaNacimiento.setText("") ;
                }
                else //ocurrio un error
                    JOptionPane.showMessageDialog(vistaEmpleados,"Error: Los datos son incorrectos.");
                break;
            case jButtonEliminarEmpleado:
                //elimina un empleado
                if ( this.me.EliminarEmpleado(this.vistaEmpleados.jFormattedTextFieldNIF.getText() ) )
                {
                    this.vistaEmpleados.jTableEmpleados.setModel( this.me.getTablaEmpleado());
                    JOptionPane.showMessageDialog(vistaEmpleados,"Exito: Empleado eliminado.");
                    this.vistaEmpleados.jFormattedTextFieldNombre.setText("");
                    this.vistaEmpleados.jFormattedTextFieldApellidos.setText("") ;
                    this.vistaEmpleados.jFormattedTextFieldNIF.setText("");
                    this.vistaEmpleados.jFormattedTextFieldFeachaNacimiento.setText("") ;
                }
                break;
            case jButtonAsociar:
                //asocia a un empleado un proyecto
                if (this.me.AsociarEmpleado(this.vistaEmpleados.jFormattedTextFieldNIF.getText(), 
                        String.valueOf(this.vistaEmpleados.jCBProyectos.getSelectedItem()))) {
                    JOptionPane.showMessageDialog(vistaEmpleados,"Exito: Empleado asociado al proyecto.");
                    this.vistaEmpleados.jFormattedTextFieldNombre.setText("");
                    this.vistaEmpleados.jFormattedTextFieldApellidos.setText("") ;
                    this.vistaEmpleados.jFormattedTextFieldNIF.setText("");
                    this.vistaEmpleados.jFormattedTextFieldFeachaNacimiento.setText("") ;
                }else{
                    JOptionPane.showMessageDialog(vistaEmpleados,"Error: datos errones.");
                }   
                break;  
            case jButtonListarProyectos:
                //obtiene del modeloProyectos los registros en un DefaultTableModel y lo asigna en la vista
                this.vistaEmpleados.jTableProyectos.setModel(this.me.getTablaProyecto(this.vistaEmpleados.jCBEmpleados.getSelectedItem().toString()));
                break;
            case jButtonVolver:
                new controlador(new InterfazPrincipal()).iniciar();
                this.vistaEmpleados.dispose();
                break;
        }
    }
    
    //Cuando pinchas en la tabla
    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource()==this.vistaEmpleados.jTableEmpleados) {
           if( me.getButton()== 1)//boton izquierdo
        {
             int filaEmpleados = this.vistaEmpleados.jTableEmpleados.rowAtPoint(me.getPoint());
             if (filaEmpleados > -1){                
                this.vistaEmpleados.jFormattedTextFieldNombre.setText( String.valueOf( this.vistaEmpleados.jTableEmpleados.getValueAt(filaEmpleados, 0) ));
                this.vistaEmpleados.jFormattedTextFieldApellidos.setText(String.valueOf( this.vistaEmpleados.jTableEmpleados.getValueAt(filaEmpleados, 1) ));
                this.vistaEmpleados.jFormattedTextFieldFeachaNacimiento.setText( String.valueOf( this.vistaEmpleados.jTableEmpleados.getValueAt(filaEmpleados, 2) ));
                this.vistaEmpleados.jFormattedTextFieldNIF.setText( String.valueOf( this.vistaEmpleados.jTableEmpleados.getValueAt(filaEmpleados, 3) ));
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
