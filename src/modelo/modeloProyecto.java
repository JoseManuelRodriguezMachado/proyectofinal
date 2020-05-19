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
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import static modelo.Conexion.getConnection;

/**
 *
 * @author jomro
 */
public class modeloProyecto extends Conexion {
    
    /** Constructor de clase */
    public modeloProyecto (){}
    
    /** Obtiene registros de la tabla EMPLEADO y los devuelve en un DefaultTableMode
     * @return l*/
    public DefaultTableModel getTablaEmpleado(String titulo)
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
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM Empleado WHERE e_nif = ANY (SELECT e_nif FROM Riot WHERE p_titulo LIKE (?))");
         pstm.setString(1, titulo);
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
    
    public DefaultTableModel getTablaProyecto()
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
    
    /** Registra un nuevo Proyecto */
    public boolean NuevoProyecto(Proyecto proyecto) {
            try {
                CallableStatement st = this.getConnection().prepareCall("{call crear_proyecto(?,?,?,?)}");
                st.setString(1, proyecto.getTitulo());
                st.setDate(2, Date.valueOf(proyecto.getFecha_inicio()));
                st.setDate(3, Date.valueOf(proyecto.getFecha_final()));
                st.setString(4, proyecto.getDescripcion());
                st.execute();
                st.close();
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
            }
            return false;
    }
    
    /** Modifica un Proyecto */
    public boolean Modificarproyecto(Proyecto proyecto) {
            try {
                CallableStatement st = this.getConnection().prepareCall("{call modificar_proyecto(?,?,?,?)}");
                st.setString(1, proyecto.getTitulo());
                st.setDate(2, Date.valueOf(proyecto.getFecha_inicio()));
                st.setDate(3, Date.valueOf(proyecto.getFecha_final()));
                st.setString(4, proyecto.getDescripcion());
                st.execute();
                st.close();
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
            }
            return false;
    }

    /** Elimina un registro dado su titulo -> Llave primaria */
    public boolean EliminarProyecto( String titulo ) {
        boolean res=false;
        try {
            CallableStatement st = this.getConnection().prepareCall("{call eliminar_proyecto(?)}");
            st.setString(1, titulo);
            st.execute();
            st.close();
            res=true;
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return res;
    }
    
}
