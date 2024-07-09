package br.com.unisc.trabpa.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.unisc.trabpa.model.ObjetoVoador;
import javax.management.BadAttributeValueExpException;

public class ObjetoVoadorDao {

    public void inserirObjetoVoadorNoBanco(ObjetoVoador obj) throws SQLException {

        try {

            PreparedStatement st;
            String query = "INSERT INTO objeto_voador (id,data,nome,diametroMinKm,diametroMaxKm,risco,dataDeAproximacao,velocidadeAproxKm,distancia) VALUES (?,?,?,?,?,?,?,?,?);";

            st = Conexao.getInstance().prepareStatement(query);

            st.setString(1, obj.getId());
            st.setString(2, obj.getData());
            st.setString(3, obj.getNome());
            st.setDouble(4, obj.getDiametroMinKm());
            st.setDouble(5, obj.getDiametroMaxKm());
            st.setBoolean(6, obj.getRisco());
            st.setString(7, obj.getDataDeAproximacao());
            st.setDouble(8, obj.getVelocidadeAproxKm());
            st.setDouble(9, obj.getDistancia());

            st.executeUpdate();
            st.close();

        } catch (Exception e) {
            System.out.println("Chave duplicada");
        }
    }

    public ArrayList<ObjetoVoador> listarObjetosPorAtributo(String atributo, String valor, String ordena) throws SQLException, BadAttributeValueExpException {

        if (!atributo.matches("^(\\D{1,})") && !ordena.matches("^(\\D{1,})")) {
            throw new BadAttributeValueExpException("Not Today");
        }

        ArrayList<ObjetoVoador> listObj = new ArrayList<>();
        String sql = "SELECT * FROM objeto_voador WHERE " + atributo + " = ? ORDER BY " + ordena;
        PreparedStatement st = Conexao.getInstance().prepareStatement(sql);
        st.setString(1, valor);

        try (ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                ObjetoVoador obj = new ObjetoVoador(
                        rs.getString("id"),
                        rs.getString("data"),
                        rs.getString("nome"),
                        rs.getDouble("diametroMinKm"),
                        rs.getDouble("diametroMaxKm"),
                        rs.getBoolean("risco"),
                        rs.getString("dataDeAproximacao"),
                        rs.getDouble("velocidadeAproxKm"),
                        rs.getDouble("distancia")
                );
                listObj.add(obj);

            }
        }

        return listObj;
    }

    public ArrayList<ObjetoVoador> ordernarObjetosPorAtributo(String atributo) throws SQLException, BadAttributeValueExpException {
        ArrayList<ObjetoVoador> listObj = new ArrayList<>();

        if (!atributo.matches("^(\\D{1,})")) {
            throw new BadAttributeValueExpException("Not Today");
        }
        String sql = "SELECT * FROM objeto_voador ORDER BY " + atributo + ";";

        PreparedStatement st = Conexao.getInstance().prepareStatement(sql);
        try (ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                ObjetoVoador obj = new ObjetoVoador(
                        rs.getString("id"),
                        rs.getString("data"),
                        rs.getString("nome"),
                        rs.getDouble("diametroMinKm"),
                        rs.getDouble("diametroMaxKm"),
                        rs.getBoolean("risco"),
                        rs.getString("dataDeAproximacao"),
                        rs.getDouble("velocidadeAproxKm"),
                        rs.getDouble("distancia")
                );
                listObj.add(obj);

            }
        }

        return listObj;
    }

    public ArrayList<ObjetoVoador> listarObjetosProximos(String data) throws SQLException {

        ArrayList<ObjetoVoador> listObj = new ArrayList<>();
        String query = "SELECT * FROM objeto_voador WHERE data >= ? order by data,distancia";
        PreparedStatement st = Conexao.getInstance().prepareStatement(query);
        st.setString(1, data);

        try (ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                ObjetoVoador obj = new ObjetoVoador(
                        rs.getString("id"),
                        rs.getString("data"),
                        rs.getString("nome"),
                        rs.getDouble("diametroMinKm"),
                        rs.getDouble("diametroMaxKm"),
                        rs.getBoolean("risco"),
                        rs.getString("dataDeAproximacao"),
                        rs.getDouble("velocidadeAproxKm"),
                        rs.getDouble("distancia")
                );
                listObj.add(obj);

            }
        }

        return listObj;
    }

    public ArrayList<ObjetoVoador> listarProxAproximacoes(String data) throws SQLException {

        ArrayList<ObjetoVoador> listObj = new ArrayList<>();
        String query = "SELECT * FROM objeto_voador WHERE data > ?";
        PreparedStatement st = Conexao.getInstance().prepareStatement(query);
        st.setString(1, data);

        try (ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                ObjetoVoador obj = new ObjetoVoador(
                        rs.getString("id"),
                        rs.getString("data"),
                        rs.getString("nome"),
                        rs.getDouble("diametroMinKm"),
                        rs.getDouble("diametroMaxKm"),
                        rs.getBoolean("risco"),
                        rs.getString("dataDeAproximacao"),
                        rs.getDouble("velocidadeAproxKm"),
                        rs.getDouble("distancia")
                );
                listObj.add(obj);
            }
        }

        return listObj;
    }

    public ArrayList<ObjetoVoador> filtrarObjetos(String atributo, String valor, String ordena) throws SQLException, BadAttributeValueExpException {
        System.out.println(atributo + valor);
        
        ArrayList<ObjetoVoador> listObj = new ArrayList<>();
        if (!atributo.matches("^(\\D{1,})") && !ordena.matches("^(\\D{1,})")) {
            throw new BadAttributeValueExpException("Not Today");
        }

        if (valor.equals("true")) {
            listObj = listarObjetosPorAtributo(atributo, "1", ordena);
        } else {

            String query = "SELECT * FROM objeto_voador WHERE " + atributo + " <= ? ORDER BY " + ordena + ";";
            PreparedStatement st = Conexao.getInstance().prepareStatement(query);
            st.setString(1, valor);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ObjetoVoador obj = new ObjetoVoador(
                            rs.getString("id"),
                            rs.getString("data"),
                            rs.getString("nome"),
                            rs.getDouble("diametroMinKm"),
                            rs.getDouble("diametroMaxKm"),
                            rs.getBoolean("risco"),
                            rs.getString("dataDeAproximacao"),
                            rs.getDouble("velocidadeAproxKm"),
                            rs.getDouble("distancia")
                    );
                    listObj.add(obj);
                }
            }
        }
        return listObj;
    }

    public ArrayList<ObjetoVoador> obterTodosObjetosVoadores() throws SQLException {
        ArrayList<ObjetoVoador> listObj = new ArrayList<>();
        String query = "SELECT * FROM objeto_voador";
        PreparedStatement st = Conexao.getInstance().prepareStatement(query);
        try (ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                ObjetoVoador obj = new ObjetoVoador(
                        rs.getString("id"),
                        rs.getString("data"),
                        rs.getString("nome"),
                        rs.getDouble("diametroMinKm"),
                        rs.getDouble("diametroMaxKm"),
                        rs.getBoolean("risco"),
                        rs.getString("dataDeAproximacao"),
                        rs.getDouble("velocidadeAproxKm"),
                        rs.getDouble("distancia")
                );
                listObj.add(obj);
            }
        }
        return listObj;
    }

}
