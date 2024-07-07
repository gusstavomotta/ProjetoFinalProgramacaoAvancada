package br.com.unisc.trabpa.dal;

import java.sql.*;

public class Conexao {

    private static Conexao instance = null;
    private Connection conn = null;

    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost/" + "trabalhopa";
    private final String usuario = "root";
    private final String senha = "root";

    private Conexao() {
    }

    private void init() throws SQLException {
        conn = DriverManager.getConnection(url, usuario, senha);
        System.out.println("Banco conectado");
    }

    public Connection getConnection() {
        return conn;
    }

    public static Connection getInstance() throws SQLException {
        if (instance != null && !instance.getConnection().isClosed()) {
            return instance.conn;
        } else {
            instance = new Conexao();
            instance.init();
            return instance.conn;
        }
    }
}
