package br.com.unisc.trabpa.dal;

import java.sql.*;

public class Conexao {
    
    private static Conexao instance = null;
    private Connection conn = null;
    
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost/" + "trabalhopa";
    private final String usuario = "root";
    private final String senha = "admin";
    
    private Conexao(){}
    
    private void init() throws SQLException{
        conn = DriverManager.getConnection(url, usuario, senha);
        System.out.println("Banco conectado");
    }
    
    public Connection getConnection(){
        return conn;
    }
    
    public static Connection getInstance() throws SQLException{
        if(instance != null && !instance.getConnection().isClosed()){
            return instance.conn;
        }else{
            instance = new Conexao();
            instance.init();
            return instance.conn;
        }
    }

    

//    private Conexao() throws ClassNotFoundException, SQLException {
//        Class.forName(driver);
//        conexao_instance = DriverManager.getConnection(url, usuario, senha);
//        System.out.println("Banco conectado");
//    }
//
//    public static synchronized Connection getInstance() throws ClassNotFoundException, SQLException {
//        if(conexao_instance == null)
//            new Conexao();
//        return conexao_instance;
//    }
//
//    public PreparedStatement prepareStatement(String sql) throws SQLException {
//        return conexao_instance.prepareStatement(sql);
//    }
//
//    public void fechar() throws SQLException {
//        conexao_instance.close();
//    }

}
