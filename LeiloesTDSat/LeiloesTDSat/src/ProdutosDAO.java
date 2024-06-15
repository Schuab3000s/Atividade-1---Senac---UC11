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

public class ProdutosDAO {

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

        } catch (SQLException e) {

        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        return listagem;
    }

    /**
     * @param id
     */
    public void venderProduto(Integer id) {

    }

}
