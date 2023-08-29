
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



public class conectaDAO {
    
    public Connection connectDB(){
        //Connection conn = null;
        
        try {
            Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost/uc11?" + "autoReconnect=true&useSSL=false",    // linha de conexao
            "root",       // usuario do mysql
            "Tak3yourtime."        // senha do mysql
            );
            System.out.println("Conexão realizada com sucesso");
            return con;
            
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
            return null;
        }
        
    }
    
}
