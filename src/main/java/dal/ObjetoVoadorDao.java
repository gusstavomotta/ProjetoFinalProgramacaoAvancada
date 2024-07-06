/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.ObjetoVoador;

/**
 *
 * @author Gustavo Motta
 */
public class ObjetoVoadorDao {

    public void inserirNoBanco(ObjetoVoador obj, Conexao conn) throws SQLException {

        PreparedStatement st;
        String query = "INSERT INTO objeto_voador (id,data,nome,diametroMinKm,diametroMaxKm,risco,dataDeAproximacao,velocidadeAproxKm) VALUES (?,?,?,?,?,?,?,?);";

        st = conn.prepareStatement(query);

        st.setString(1, obj.getId());
        st.setString(2, obj.getData());
        st.setString(3, obj.getNome());
        st.setString(4, obj.getDiametroMinKm());
        st.setString(5, obj.getDiametroMaxKm());
        st.setBoolean(6, obj.getRisco());
        st.setString(7, obj.getDataDeAproximacao());
        st.setDouble(8, obj.getVelocidadeAproxKm());

        st.executeUpdate();
        st.close();
    }

    public void removerDoBanco() {

    }

    public ArrayList<ObjetoVoador> listarComFiltro(Conexao conn, String atributo, String valor) throws SQLException {
        ArrayList<ObjetoVoador> listObj = new ArrayList<>();
        // Concatenar o atributo diretamente na string SQL
        String sql = "SELECT * FROM objeto_voador WHERE " + atributo + " LIKE " + valor;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            System.out.println(sql);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ObjetoVoador obj = new ObjetoVoador(
                            rs.getString("id"),
                            rs.getString("data"),
                            rs.getString("nome"),
                            rs.getString("diametroMinKm"),
                            rs.getString("diametroMaxKm"),
                            rs.getBoolean("risco"),
                            rs.getString("dataDeAproximacao"),
                            rs.getDouble("velocidadeAproxKm")
                    );
                    listObj.add(obj);
                    System.out.println(obj.toString());
                }
                // Fechando os recursos (opcional, mas recomendado)
            }
        }

        return listObj;
    }
}
