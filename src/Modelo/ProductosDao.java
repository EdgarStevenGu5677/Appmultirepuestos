package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class ProductosDao {
  
   Connection con;
   Conexion cn=new Conexion();
   PreparedStatement ps;
   ResultSet rs;
   
   
    public boolean RegistrarProductos(Productos pro){
        String sql = "INSERT INTO productos (codigo, nombre, proveedor, cantidad, precio) VALUES (?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getProveedor());
            ps.setInt(4, pro.getCantidad());
            ps.setDouble(5, pro.getPrecio());
            ps.execute();
         return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
   
   public void ConsultarProveedor(JComboBox proveedor){
       String sql = "SELECT nombre FROM proveedor";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while  (rs.next()) {
               proveedor.addItem(rs.getString("nombre"));
           }
       } catch (SQLException e){
           System.out.println(e.toString());
       }
       
   }
   
   public List ListarProductos(){
       List<Productos> ListaPro = new ArrayList();
       String sql = "SELECT * FROM productos";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {               
               Productos pro = new Productos();
               pro.setId(rs.getInt("id"));
               pro.setCodigo(rs.getString("codigo"));
               pro.setNombre(rs.getString("nombre"));
               pro.setProveedor(rs.getString("proveedor"));
               pro.setCantidad(rs.getInt("cantidad"));
               pro.setPrecio(rs.getDouble("precio"));
               ListaPro.add(pro);
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return ListaPro;
   }
   
   public boolean EliminarProductos(int id){
       String sql = "DELETE FROM productos WHERE id = ?";
       try {
           ps = con.prepareStatement(sql);
           ps.setInt(1, id);
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }finally{
           try {
               con.close();
           } catch (SQLException ex) {
               System.out.println(ex.toString());
           }
       }
   }
   
        public boolean ActualizarProductos(Productos pro1){
       String sql = "UPDATE productos SET codigo=?, nombre=?, proveedor=?, cantidad=?, precio=? WHERE id=?";
       try {
           ps = con.prepareStatement(sql);
           ps.setString(1, pro1.getCodigo());
           ps.setString(2, pro1.getNombre());
           ps.setString(3, pro1.getProveedor());
           ps.setInt(4, pro1.getCantidad());
           ps.setDouble(5, pro1.getPrecio());
           ps.setInt(6, pro1.getId());
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }finally{
           try {
               con.close();
           } catch (SQLException e) {
               System.out.println(e.toString());
           }
       }
   }
        
        public Productos BuscarPro(String cod){
            Productos productos=new Productos();
            String sql = "SELECT * FROM productos WHERE codigo = ?";
            try{
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setString(1, cod);
                rs = ps.executeQuery();
                if (rs.next()) {
                    productos.setNombre(rs.getString("nombre"));
                    productos.setPrecio(rs.getDouble("precio"));
                    productos.setCantidad(rs.getInt("cantidad"));
                }
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        
        return productos;
}
        
        public Config BuscarDatos(){
            Config conf=new Config();
            String sql = "SELECT * FROM config";
            try{
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    conf.setId(rs.getInt("id"));
                    conf.setRut(rs.getInt("rut"));
                    conf.setNombre(rs.getString("nombre"));
                    conf.setTelefono(rs.getInt("telefono"));
                    conf.setDireccion(rs.getString("direccion"));
                    conf.setMensaje(rs.getString("mensaje"));
                }
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        
        return conf;
}
        
        public Cliente Buscarcliente(int dni){
            Cliente cl = new Cliente();
            String sql = "SELECT * FROM clientes WHERE dni = ?";
            try{
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setInt(1, dni);
                rs = ps.executeQuery();
                if (rs.next()) {
                    cl.setNombre(rs.getString("nombre"));
                    cl.setTelefono(rs.getString("telefono"));
                    cl.setDireccion(rs.getString("direccion"));
                    
                }
            }catch (SQLException e){
                System.out.println(e.toString());
            }
            return cl;
        }
        
        
        public boolean ActualizarDatos(Config conf){
       String sql = "UPDATE config SET rut=?, nombre=?, telefono=?, direccion=?, mensaje=? WHERE id=?";
       try {
           ps = con.prepareStatement(sql);
           ps.setInt(1, conf.getRut());
           ps.setString(2, conf.getNombre());
           ps.setInt(3, conf.getTelefono());
           ps.setString(4, conf.getDireccion());
           ps.setString(5, conf.getMensaje());
           ps.setInt(6, conf.getId());
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }finally{
           try {
               con.close();
           } catch (SQLException e) {
               System.out.println(e.toString());
           }
       }
   }
}