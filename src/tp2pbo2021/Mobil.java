/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2pbo2021;

import java.sql.*;
/**
 *
 * @author haqiz
 */
public class Mobil {
    private static Connector connect = new Connector();
    private static ResultSet rs;
    
    
    public void Mobil(){};
    
    public static void createMobil(String merk, String plat, String warna, String jenis){
        try{
            // Buka koneksi
            connect.startConnection("kendaraan");
            
            // Insert data ke table
            String sql = "INSERT INTO mobil(merk, plat, warna, jenis) VALUES('" + merk + "','" + plat + "','" + warna + "','" + jenis +"');";            
            connect.sqlUpdate(sql);
            
            //Tutup koneksi
            connect.closeConnection();
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public Object[][] getMobils(){
        try{
            //Buka koneksi
            connect.startConnection("kendaraan");
            
            //get data dari database
            String sql = "SELECT merk, plat, warna, jenis FROM mobil;";
            rs = connect.sqlExec(sql);
            
            //Cari banyak/panjang data
            rs.last();
            int dataLength = rs.getRow();
            rs.beforeFirst();
            
            //init Object untuk di return ke model
            Object[][] data = new Object[dataLength][4];
            
            // Assign tiap row ke Object data
            int i = 0;
            while(rs.next()){
                data[i][0] = rs.getString("merk");
                data[i][1] = rs.getString("plat");
                data[i][2] = rs.getString("warna");
                data[i][3] = rs.getString("jenis");
                i++;
            }
            
            // Tutup koneksi
            connect.closeConnection();
            
            // return data
            return data;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
