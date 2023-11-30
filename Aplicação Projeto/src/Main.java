import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private BarbeariaDAO barbeariaDAO = new BarbeariaDAO();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sistema de Barbearia");

        // Layout principal
        StackPane layout = new StackPane();
        layout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        // Componentes da interface
        TextField nomeInput = new TextField();
        nomeInput.setPromptText("Nome do Cliente");

        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Selecione a Data");

        ComboBox<String> servicoComboBox = new ComboBox<>();
        servicoComboBox.getItems().addAll("Corte de Cabelo", "Barba", "Outro");
        servicoComboBox.setPromptText("Selecione o Serviço");

        Button cadastrarButton = new Button("Cadastrar Cliente e Agendar Serviço");

        // Evento do botão de cadastro
        cadastrarButton.setOnAction(e -> {
            String nome = nomeInput.getText();
            String data = datePicker.getValue() != null ? datePicker.getValue().toString() : "";
            String servico = servicoComboBox.getValue();

            // Substitua com suas credenciais reais do banco de dados
            String email = "exemplo@email.com";
            String telefone = "123456789";

            // Adiciona cliente e agenda serviço
            barbeariaDAO.adicionarCliente(nome, servico, data, email, telefone);
            barbeariaDAO.agendarServico(nome, servico, data);

            // Limpa os campos após o cadastro
            nomeInput.clear();
            datePicker.getEditor().clear();
            servicoComboBox.getSelectionModel().clearSelection();
        });

        // Adiciona componentes ao layout
        layout.getChildren().addAll(nomeInput, datePicker, servicoComboBox, cadastrarButton);

        // Cria a cena e define no palco principal
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);

        // Exibe o palco
        primaryStage.show();
    }

    @Override
    public void stop() {
        // Fecha a conexão com o banco de dados ao fechar a aplicação
        ConexaoBD.fecharConexao();
    }
}
