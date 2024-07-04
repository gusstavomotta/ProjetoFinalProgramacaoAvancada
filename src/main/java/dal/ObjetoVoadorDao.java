/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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

}
