/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2pbo2021;

import java.sql.*;
import java.lang.Object;
/**
 *
 * @author haqiz
 */
public class Connector {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mysql";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    static Connection connect;
    static Statement stmt;
    static ResultSet rs;
    
    public static void Connector(){}
    
    public static void startConnection(){
        try{
            //Koneksi database
            Class.forName(DRIVER);
            connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            stmt = connect.createStatement();
            
            String sql = "CREATE DATABASE IF NOT EXISTS kendaraan;";
            stmt.executeUpdate(sql);

        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void startConnection(String database){
        try{
            // Koneksi database mysql
            Class.forName(DRIVER);
            connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            stmt = connect.createStatement();
            
            // Buat dulu database kendaraan jika belum ada
            String sql = "CREATE DATABASE IF NOT EXISTS kendaraan;";
            stmt.executeUpdate(sql);
            
            //koneksi ke database pilihan
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, USERNAME, PASSWORD);
            stmt = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            //Buat table jika belum ada
            sql = "CREATE TABLE IF NOT EXISTS mobil(id int(10) PRIMARY KEY AUTO_INCREMENT, merk varchar(20), plat varchar(20), warna varchar(20), jenis varchar(20))";
            stmt.executeUpdate(sql);    
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void sqlUpdate(String sql){
        // Diberi nama sqlUpdate karena methodnya executeUpdate, untuk menyesuaikan
        try{
            stmt.executeUpdate(sql);
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static ResultSet sqlExec(String sql){
        try{
            // get data dari database
            rs = stmt.executeQuery(sql);
            
            return rs;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return null;
    }
    
    public void closeConnection(){
        //menutup koneksi
        try{
            stmt.close();
            connect.close();
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
