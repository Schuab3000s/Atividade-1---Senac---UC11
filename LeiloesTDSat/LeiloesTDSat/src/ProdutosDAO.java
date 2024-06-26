/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutosDAO {
//opa
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {

        conn = new conectaDAO().connectDB();
        try {
            // Preparando a query SQL para inserção de um novo filme
            prep = conn.prepareStatement("INSERT INTO produtos "
                    + "(nome, valor, status) "
                    + "VALUES "
                    + "(?,?,?)", Statement.RETURN_GENERATED_KEYS);

            // Setando os parâmetros da query com os dados do filme
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            // Executando a query
            prep.executeUpdate();

            conn.close();
            JOptionPane.showMessageDialog(null, "Produto salvo com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prep != null) {
                    prep.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        listagem.clear();
        conn = new conectaDAO().connectDB();
        try {

            prep = conn.prepareStatement("SELECT * FROM produtos");

            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                listagem.add(produto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechando recursos
            try {
                if (resultset != null) {
                    resultset.close();
                }
                if (prep != null) {
                    prep.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listagem;
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {

        listagem.clear();
        conn = new conectaDAO().connectDB();
        try {

            prep = conn.prepareStatement("SELECT * FROM produtos "
                    + "WHERE status = 'Vendido'");

            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                listagem.add(produto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechando recursos
            try {
                if (resultset != null) {
                    resultset.close();
                }
                if (prep != null) {
                    prep.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listagem;
    }

    public void venderProduto(int id) {
        conn = new conectaDAO().connectDB();
        try {
            String sql = "UPDATE produtos SET status = ? WHERE id = ?";
            prep = conn.prepareStatement(sql);
            prep.setString(1, "Vendido");
            prep.setInt(2, id);

            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prep != null) {
                    prep.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
