
package csc302;

import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Project_Title extends GridPane
{
     public Project_Title(Stage primaryStage){  
        String css1 = "-fx-background-color: #f9fbf9;\n"
                       +"-fx-border-color: transparent;\n"
                       +"-fx-padding: 10;\n"
                       +"-fx-border-spacing: 0;\n;";
        
         String css2 = "-fx-background-color: transparent;\n"
                       +"-fx-border-color: transparent;\n"
                       +"-fx-font-size: 14px;\n"
                       +"-fx-font-weight: bold;\n" 
                       +"-fx-padding: 2;\n"
                       +"-fx-size: 20;\n"
                       +"-fx-border-spacing: 0;\n;"; 
        prefWidthProperty().bind(primaryStage.widthProperty());
        setHeight(15);       
        
        //Text containing the title for the header
        Text HeaderText = new Text("CRAWLER FOR CSC 302 PROJECT");      
        HeaderText.setFill(Color.BLACK);
        HeaderText.setStyle(css2);      
        setStyle(css1);      
        add(HeaderText,0,0);       
    }  
}
    

