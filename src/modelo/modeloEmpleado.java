/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jomro
 */
public class modeloEmpleado extends Conexion {
    
    /** Constructor de clase */
    public modeloEmpleado (){}
    
    /** Obtiene registros de la tabla EMPLEADO y los devuelve en un DefaultTableMode
     * @return l*/
    public DefaultTableModel getTablaEmpleado()
    {
      DefaultTableModel tablaEmpleado = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Nombre","Apellidos","Fecha nacimiento","NIF"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConnection().prepareStatement( "SELECT count(*) as total FROM Empleado");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][4];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM Empleado");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "e_nombre" );
                data[i][1] = res.getString( "e_apellidos" );
                data[i][2] = res.getString( "e_fecha_nacimiento" );
                data[i][3] = res.getString( "e_nif" );
            i++;
         }
         res.close();
         //se añade la matriz de datos en el DefaultTableModel
         tablaEmpleado.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablaEmpleado;
    }
    
    public DefaultTableModel getTablaProyecto(String nombre)
    {
      DefaultTableModel tablaProyecto = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Titulo","Fecha inicio","Fecha final","Descripcion"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConnection().prepareStatement( "SELECT count(*) as total FROM Proyecto");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][4];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM Proyecto");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "p_titulo" );
                data[i][1] = res.getString( "p_fecha_inicio" );
                data[i][2] = res.getString( "p_fecha_final" );
                data[i][3] = res.getString( "p_descripcion" );
            i++;
         }
         res.close();
         //se añade la matriz de datos en el DefaultTableModel
         tablaProyecto.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablaProyecto;
    }
    
    public DefaultComboBoxModel getComboBoxEmpleados(){
      String nombre;
      DefaultComboBoxModel cbm = new DefaultComboBoxModel();
	ArrayList<String> elementos=new ArrayList<String>();
	try {
	getConnection();
	PreparedStatement pstm = getConnection().prepareStatement("SELECT e_nombre FROM Empleado");
	ResultSet res = pstm.executeQuery();
	while (res.next()) {
	nombre = res.getString("e_nombre");
	elementos.add(nombre);
	}
	pstm.close();
	} catch (Exception e) {
	e.printStackTrace();
	}
	return new DefaultComboBoxModel(elementos.toArray());    
    }
    
     public DefaultComboBoxModel getComboBoxProyectos(){
      String nombre;
      DefaultComboBoxModel cbm = new DefaultComboBoxModel();
	ArrayList<String> elementos=new ArrayList<String>();
	try {
	getConnection();
	PreparedStatement pstm = getConnection().prepareStatement("SELECT p_titulo FROM Proyecto");
	ResultSet res = pstm.executeQuery();
	while (res.next()) {
	nombre = res.getString("p_titulo");
	elementos.add(nombre);
	}
	pstm.close();
	} catch (Exception e) {
	e.printStackTrace();
	}
	return new DefaultComboBoxModel(elementos.toArray());    
    }
    
    /** Registra un nuevo Empleado */
    public boolean NuevoEmpleado(Empleado empleado) {
            try {
                CallableStatement st = this.getConnection().prepareCall("{call crear_empleado(?,?,?,?)}");
                st.setString(1, empleado.getNombre());
                st.setString(2, empleado.getApellidos());
                st.setDate(3, Date.valueOf(empleado.getFecha_nacimiento()));
                st.setString(4, empleado.getNif());
                st.execute();
                st.close();
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
            }
            return false;
    }
    
    /** Modifica un empleado ya creado anteriormente */
     public boolean ModificarEmpleado(Empleado empleado) {
        try {
            CallableStatement st = this.getConnection().prepareCall("{call modificar_empleado(?,?,?,?)}");
            st.setString(1, empleado.getNombre());
            st.setString(2, empleado.getApellidos());
            st.setDate(3, Date.valueOf(empleado.getFecha_nacimiento()));
            st.setString(4, empleado.getNif());
            st.execute();
            st.close();
            return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
            }
        return false;
   }
     
    public boolean AsociarEmpleado (String nif, String titulo) {
            try {
                CallableStatement st = this.getConnection().prepareCall("{call asociar(?,?)}");
                st.setString(1, nif);
                st.setString(2, titulo);
                st.execute();
                st.close();
                return true;
                }catch(SQLException e){
                System.err.println( e.getMessage() );
                }
            return false;
    }
    
    /** Elimina un registro dado su nif -> Llave primaria */
    public boolean EliminarEmpleado(String nif) {
        boolean res=false;
         try {
            CallableStatement st = this.getConnection().prepareCall("{call eliminar_empleado(?)}");
            st.setString(1, nif);
            st.execute();
            st.close();
            res=true;
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return res;
    }
    
    /** Valida si el nif esta bien formado**/
    public boolean validarNif(String nif) {
        boolean correcto = false;
        Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
        Matcher matcher = pattern.matcher(nif);
        if (matcher.matches()) {
            String letra = matcher.group(2);
            String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
            int index = Integer.parseInt(matcher.group(1));
            index = index % 23;
            String reference = letras.substring(index, index + 1);
            if (reference.equalsIgnoreCase(letra)) {
                correcto = true;
            } else {
                correcto = false;
            }
        } else {
            correcto = false;
        }
        return correcto;
    }
}
