package pkg15_game;

import javafx.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.application.Application;
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
        //Två lager, BorderPane håller GridPane och knappen för nytt spel,
        //GridPane håller 16 knappar som innhåller olika nummer.
        BorderPane root = new BorderPane();
        GridPane grid = new GridPane();
        
        //Kontroll-Lista för hur vinst ser ut. 1-15 + 16 blank
        List<String> ctrlList = new ArrayList();
        for (int i = 1; i <= 16; i++)
        {
            String s = Integer.toString(i);
            ctrlList.add(s);
        }
        ctrlList.set(15, ""); //Gör nr "16" blank.
        
        //Button-lista som håller alla knappar.
        List<Button> btns = new ArrayList<Button>();
        
        //Variabler för att ge knapparna deras nr.
        int intNum = 1;
        String stringNum = "";
        
        //Skapar 16 knappar, ger dem varsitt nummer 1-16 i form av String,
        //sätter storlek, placerar ut dem i GridPane och lägger dem i Button-listan.
        for (int r = 0; r <= 3 ; r++)
        {
            for (int c = 0; c <= 3; c++)
            {
                stringNum = Integer.toString(intNum);
                Button btn = new Button(stringNum);
                intNum++;
                btn.setPrefSize(100, 100);
                grid.add(btn, c, r);
                btns.add(btn);
            }
        }
        btns.get(15).setText(""); //Gör knapp 16 blank.
        
        //Lägger till händelse för knappar i GridPane.
        for (Button clickedBtn : btns)
        {
            clickedBtn.setOnAction((ActionEvent e) ->
            {
                //Tar fram koordinater för den klickade knappen.
                int c = GridPane.getColumnIndex(clickedBtn);
                int r = GridPane.getRowIndex(clickedBtn);
                
                //Går igenom alla knappar i knapp-listan och undersöker om de
                //angränsar till den klickade knappen.
                for (Button adjacentBtn : btns)
                {
                    if(GridPane.getColumnIndex(adjacentBtn) == c-1 &&
                       GridPane.getRowIndex(adjacentBtn) == r ||

                       GridPane.getColumnIndex(adjacentBtn) == c+1 &&
                       GridPane.getRowIndex(adjacentBtn) == r ||

                       GridPane.getColumnIndex(adjacentBtn) == c &&
                       GridPane.getRowIndex(adjacentBtn) == r-1 ||

                       GridPane.getColumnIndex(adjacentBtn) == c &&
                       GridPane.getRowIndex(adjacentBtn) == r+1)
                    {
                        //Om någon angränsande knapp till den klickade knappen
                        //saknar text, då sätts texten från den klickade knappen
                        //in i den tomma knappen och texten i den klickade knappen 
                        //tas bort.
                        if(adjacentBtn.getText() == "")
                        {
                            adjacentBtn.setText(clickedBtn.getText());
                            clickedBtn.setText("");
                        }   
                    }
                }
                
                //String-lista som populeras med alla knapparnas text-innehåll,
                //sätts in efter den ordning de befinner sig i i GridPane.
                List<String> btnsStringList = new ArrayList();
                for (int i = 0; i <= 15; i++)
                {
                    String s = btns.get(i).getText();
                    btnsStringList.add(s);
                }
                
                //Om kontroll-listan stämmer mot knapptext-listan så visas
                //en meddelanderuta.
                if (ctrlList.equals(btnsStringList))
                {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    
                    alert.setHeaderText("You Won!");
                    alert.showAndWait();
                }
            });
        }
        
        //Knapp för att blanda om numren i GridPane-knapparna (nytt spel).
        Button newGameButton = new Button("New Game");
        newGameButton.setPrefSize(400, 50);
        
        //Lägger till händelse vid klick på nytt spel-knappen
        newGameButton.setOnAction((ActionEvent e) ->
        {
            //String-lista med nummer 1-16
            List<String> shuffleList = new ArrayList();
            for (int i = 1; i <= 16; i++)
            {
                String s = Integer.toString(i);
                shuffleList.add(s);
            }
            shuffleList.set(15, ""); //Gör nr "16" blank.
            
            //Blandar alla numren i listan
            Collections.shuffle(shuffleList);
            
            //Sätter numren från den blandade listan som text i knapparna i
            //knapp-listan.
            for (int i = 0; i <= 15; i++)
            {
                btns.get(i).setText(shuffleList.get(i));
            }
        });
        
        //Placerar GridPane (med knapparna) och nytt spel-knappen i BorderPane.
        root.setTop(grid);
        root.setBottom(newGameButton);
        
        //Sätter in BorderPane i, och bestämmer storleken på scen-rutan.
        Scene scene = new Scene(root, 400, 450);
        
        //Sätter ram-titel, lägger till scenen i stagen och visar det.
        primaryStage.setTitle("A Game of 15");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){launch(args);}
}
