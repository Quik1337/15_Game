package pkg15_game;

import javafx.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
                Button btn = new Button(stringNum);
                btn.setPrefSize(100, 100);
                grid.add(btn, c, r);
                btns.add(btn);
                intNum++;
            }
        }
        
        //Gör knapp 16 blank
        btns.get(15).setText("");
        
        //Lägger till eventhanterare till alla knappar i knapp-listan som
        //hanterar om in knapp i gridet klickas på.
        for (Button btn : btns)
        {
            btn.setOnAction((ActionEvent e) ->
            {
                //Tar fram koordinater för den klickade knappen
                int c = GridPane.getColumnIndex(btn);
                int r = GridPane.getRowIndex(btn);
                
                for (Node node : btns)
                {
                    //Kollar de fyra noder som ligger ovanför, under, höger
                    //och vänster om den klickade nodens koordinater.
                    if(GridPane.getColumnIndex(node) == c-1 &&
                       GridPane.getRowIndex(node) == r ||

                       GridPane.getColumnIndex(node) == c+1 &&
                       GridPane.getRowIndex(node) == r ||

                       GridPane.getColumnIndex(node) == c &&
                       GridPane.getRowIndex(node) == r-1 ||

                       GridPane.getColumnIndex(node) == c &&
                       GridPane.getRowIndex(node) == r+1)
                    {
                        //Gör om node till en knapp
                        Button nodeBtn = (Button) node;
                        
                        //Om någon av de fyra knapparna runt den klickade
                        //knappen har tom text så sätt in texten från den
                        //klickade knappen in i den tomma knappen och ta
                        //sedan bort texten i den klickade knappen.
                        if(nodeBtn.getText() == "")
                        {
                            nodeBtn.setText(btn.getText());
                            btn.setText("");
                        }   
                    }
                }
                
                //Kontroll-Lista, Knapp-Text-Lista, testar matchning mellan dessa
                
                List<String> ctrlList = new ArrayList();
                
                for (int i = 1; i <= 16; i++)
                {
                    String s = Integer.toString(i);
                    ctrlList.add(s);
                }
                
                ctrlList.set(15, "");
                
                List<String> btnsTextList = new ArrayList();
                
                for (int i = 0; i <= 15; i++)
                {
                    String s = btns.get(i).getText();
                    btnsTextList.add(s);
                }
                
                if (ctrlList.equals(btnsTextList))
                {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    
                    alert.setHeaderText("You Won!");
                    alert.showAndWait();
                }
            });
        }
        
        //Skapar en knapp för nytt spel (blandar om numren i knapparna när den
        //klickas)
        Button newGameButton = new Button("New Game");
        newGameButton.setPrefSize(400, 50);
        
        newGameButton.setOnAction((ActionEvent e) ->
        {
            //Lista som kommer hålla nummer 1-16
            List<String> nums = new ArrayList();
            //Lägger in numren 1-16 i listan som stringar
            for (int i = 1; i <= 16; i++) {
                String stringNum1 = Integer.toString(i);
                nums.add(stringNum1);
            }
            //Byter ut numret "16" mot "" (blank)
            nums.set(15, "");
            //Blandar listan med numren 1-16 och den blanka
            Collections.shuffle(nums);
            //Sätter in numren i listan som text i knapparna som finns i
            //knapp-listan.
            for (int i = 0; i <= 15; i++)
            {
                btns.get(i).setText(nums.get(i));
            }
        });
        
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
