package pkg15_game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        Button btn = new Button();
        
        btn.setText("Säg 'Hej Världen'");
        btn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                System.out.println("Hej Världen!");
            }
        });
        
        StackPane root = new StackPane();
        
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hej Världen!");
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
