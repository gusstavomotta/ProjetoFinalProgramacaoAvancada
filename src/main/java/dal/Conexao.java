package dal;

import java.sql.*;


public class Conexao {

    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost/" + "trabalhopa";
    private final String usuario = "root";
    private final String senha = "root";
    private final Connection conexao;

    public Conexao() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        conexao = DriverManager.getConnection(url, usuario, senha);
        System.out.println("Banco conectado");                                                         
    }

    public Connection getConexao() {
        return conexao;
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return conexao.prepareStatement(sql);
    }

    public void fechar() throws SQLException {
        conexao.close();
    }

}