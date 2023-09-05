

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    private Connection conn;
    private conectaDAO conecta;
    //List<ProdutosDTO> listagem = new ArrayList<>();

    public ProdutosDAO() {
        this.conecta = new conectaDAO();
        this.conn = this.conecta.connectDB();
    }
    
    
    public void cadastrarProduto (ProdutosDTO produto){
        //conn = new conectaDAO().connectDB();
        String sql = ("INSERT INTO produtos (nome, valor, status) VALUES (?,?,?)");
         
        try {
            PreparedStatement prest = this.conn.prepareStatement(sql);
            prest.setString(1,produto.getNome());
            prest.setInt(2,produto.getValor());
            prest.setString(3,"A Venda");
            prest.execute();

        } catch (Exception e) {
            System.out.println("Desculpe. Ocorreu um erro ao inserir produto: " + e.getMessage());
        }     
        
    }
    
    
    /** Método para retornar todos os filmes cadastrados no BD */
    public List<ProdutosDTO> getProdutos(){
        String sql = "SELECT * FROM produtos";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            /** Como temos um parâmetro, devemos defini-lo */
            
            /** Os resultados obtivos pela consulta serão armazenados na variavel ResultSet */
            ResultSet rs = stmt.executeQuery();            
            
            List<ProdutosDTO> listaProd = new ArrayList<>();
            
            while (rs.next()) { /** .next retorna verdadeiro caso exista uma próxima posição dentro do array */
            ProdutosDTO produto = new ProdutosDTO();
            /** Salvando dentro do objeto produto as informações */            
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getInt("valor"));
            produto.setStatus(rs.getString("status"));
            /* Adicionando os elementos na lista criada */
            listaProd.add(produto);
            }
            /** Após finalizar o while, o retorno será a listaEmpresas, onde cada posição é um registro do banco de dados */
            return listaProd;
            
            /** Se o método entrar no "Catch" quer dizer que não encontrou nenhuma info, então damos um "return null" */
        } catch (Exception e) {
            return null;
        }
    }
    
    public void venderProduto (ProdutosDTO produto){
        
        String sql = ("UPDATE produtos SET status = ? WHERE id = ?");
         
        try {
            PreparedStatement prest = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE);
            //PreparedStatement prest = this.conn.prepareStatement(sql);
            prest.setString(1,"Vendido");
            prest.setInt(2,produto.getId());
            prest.execute();

        } catch (Exception e) {
            System.out.println("Desculpe. Ocorreu um erro ao atualizar o produto: " + e.getMessage());
        }     
        
    }

       /* public ProdutosDTO getProduto (int id){
            String sql = "SELECT * FROM produtos WHERE id LIKE ?";
                try {
                    PreparedStatement prest = this.conn.prepareStatement(sql);
                    prest.setInt(1, id);
                    ResultSet rs = prest.executeQuery();

                    ProdutosDTO produto = new ProdutosDTO();

                    rs.next();
                    produto.setNome(rs.getString("nome"));
                    produto.setValor(rs.getInt("valor"));   
                    produto.setStatus(rs.getString("status"));

                    return produto;

                } catch (Exception e) {
                    System.out.println("Ocorreu um erro: " + e.getMessage());  
                }
                return null;
        }*/    
    
        
}

