package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.ObjetoVoador;

public class ObjetoVoadorDao {

    public void inserirObjetoVoadorNoBanco(ObjetoVoador obj, Conexao conn) throws SQLException {

        try {
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

        } catch (Exception e) {
            System.out.println("Chave duplicada");
        }
    }

    public ArrayList<ObjetoVoador> listarObjetosPorAtributo(Conexao conn, String atributo, String valor) throws SQLException {

        ArrayList<ObjetoVoador> listObj = new ArrayList<>();
        String sql = "SELECT * FROM objeto_voador WHERE " + atributo + " = ?;";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, valor);

        try (ResultSet rs = st.executeQuery()) {
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

            }
        }

        return listObj;
    }

    public ArrayList<ObjetoVoador> ordernarObjetosPorAtributo(Conexao conn, String atributo) throws SQLException {
        ArrayList<ObjetoVoador> listObj = new ArrayList<>();
        String sql = "SELECT * FROM objeto_voador ORDER BY " + atributo + ";";

        PreparedStatement st = conn.prepareStatement(sql);
        try (ResultSet rs = st.executeQuery()) {
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

            }
        }

        return listObj;
    }

    public ArrayList<ObjetoVoador> listarObjetosProximos(Conexao conn, String data) throws SQLException {

        ArrayList<ObjetoVoador> listObj = new ArrayList<>();
        String query = "SELECT * FROM objeto_voador WHERE data >= ?";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, data);

        try (ResultSet rs = st.executeQuery()) {
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

            }
        }

        return listObj;
    }
}
