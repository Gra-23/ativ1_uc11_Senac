

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        conn = new conectaDAO().connectDB();
        String sql = ("INSERT INTO produtos (nome, valor, status) VALUES (?,?,?)");
         
        try {
            PreparedStatement prest = this.conn.prepareStatement(sql);
            prest.setString(1,produto.getNome());
            prest.setInt(2,produto.getValor());
            prest.setString(3,"A Venda");
            prest.execute();

        } catch (Exception e) {
            System.out.println("Desculpe. Ocorreu um erro ao inserir o produto: " + e.getMessage());
        }
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
        
}

