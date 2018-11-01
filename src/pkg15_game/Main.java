package pkg15_game;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application
{    
    @Override
    public void start(Stage primaryStage)
    {
        //Basen som håller grid
        BorderPane root = new BorderPane();
        
        //Gridet som håller knapparna
        GridPane grid = new GridPane();
        
        //Variabler för att ge knapparna text med nr 1-16.
        int intNum = 1;
        String stringNum = "";
        
        //Lista som håller alla 16 knapparna, så att de kan kallas var för sig
        //med index nr.
        List<Button> btns = new ArrayList<Button>();
        
        //Skapar upp 16 knappar med nr 1-16, dessa sätts in i ett 4x4 grid och
        //en knapp-lista, storleken på knapparna sätts så att de tillsamans
        //fyller toppen av BorderPanet.
        for (int r = 0; r <= 3 ; r++)
        {
            for (int c = 0; c <= 3; c++)
            {
                stringNum = Integer.toString(intNum);
                Button orderedButton = new Button(stringNum);
                orderedButton.setPrefSize(100, 100);
                grid.add(orderedButton, c, r);
                btns.add(orderedButton);
                intNum++;
            }
        }
        
        //Gör knapp 16 blank
        Button blankButton = new Button();
        blankButton = btns.get(15);
        blankButton.setText("");
        
        //Skapar en knapp för nytt spel (blandar om numren i knapparna när den
        //klickas)
        Button newGameButton = new Button("New Game");
        newGameButton.setPrefSize(400, 50);
        
        //Placerar gridet av knappar i toppen av BorderPanet och nytt spel-
        //knappen i botten.
        root.setTop(grid);
        root.setBottom(newGameButton);
        
        //Sätter storleken på scenen och placerar där BorderPanet som innehåller
        //knapp-gridet och nytt spel-knappen
        Scene scene = new Scene(root, 400, 450);
        
        //Sätter ram-titel, lägger till scenen i stagen och visar dem.
        primaryStage.setTitle("A Game of 15");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){launch(args);}
}
