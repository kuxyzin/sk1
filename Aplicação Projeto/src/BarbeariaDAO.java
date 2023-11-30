import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BarbeariaDAO {

    private Connection conexao;

    public BarbeariaDAO() {
        this.conexao = ConexaoBD.conectar();
    }

    public void adicionarCliente(String nome, String servico, String horario, String email, String telefone) {
        String sql = "INSERT INTO clientes (nome, servico, horario, email, telefone) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, servico);
            stmt.setString(3, horario);
            stmt.setString(4, email);
            stmt.setString(5, telefone);
            stmt.executeUpdate();
            System.out.println("Cliente adicionado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar cliente: " + e.getMessage());
        }
    }

    public boolean verificarCadastroCliente(String nome, String email) {
        String sql = "SELECT * FROM clientes WHERE nome = ? AND email = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Retorna true se o cliente existe no banco de dados
        } catch (SQLException e) {
            System.err.println("Erro ao verificar cadastro do cliente: " + e.getMessage());
            return false;
        }
    }

    public void agendarServico(String nome, String servico, String horario) {
        // Verifica se o cliente está cadastrado antes de permitir o agendamento
        if (verificarCadastroCliente(nome, null)) {
            String sql = "INSERT INTO agendamentos (nome_cliente, servico, horario) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, nome);
                stmt.setString(2, servico);
                stmt.setString(3, horario);
                stmt.executeUpdate();
                System.out.println("Agendamento realizado com sucesso!");
            } catch (SQLException e) {
                System.err.println("Erro ao agendar serviço: " + e.getMessage());
            }
        } else {
            System.out.println("Cliente não cadastrado. Faça o cadastro primeiro.");
        }
    }
}
