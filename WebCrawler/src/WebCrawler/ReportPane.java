
package csc302;

import javafx.scene.layout.GridPane;
import java.util.List;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

public class ReportPane extends GridPane
{
    GridPane grid_status;
    GridPane grid_details;
    GridPane external_grid;
    
     public ReportPane(List<String> search_details, GridPane pane){
        external_grid = pane;
        String css1 = "-fx-background-color: grey;\n"
                      +"-fx-border-insets: 1 1 1 1;\n"
                       +"-fx-border-radius:6,3,2;\n"
                       +"-fx-background-radius:6,3,2;\n"
                       +"-fx-border-color:  transparent;\n"
                       +"-fx-padding: 2;\n"
                       +"-fx-border-width: 2;\n";
        
        String css2 =  "-fx-background-color: grey;\n"
                       +"-fx-border-insets: 1 1 1 1;\n"
                       +"-fx-border-radius:6,3,2;\n"
                       +"-fx-background-radius:6,3,2;\n"
                       +"-fx-border-color:  transparent;\n"
                       +"-fx-padding: 2;\n"
                       +"-fx-border-width: 2;\n";
        
        grid_status = new GridPane();
        grid_status.setStyle(css1);
        grid_status.prefHeight(30);
        grid_status.prefWidthProperty().bind(external_grid.widthProperty());
        
        grid_details = new GridPane();
        grid_details.setStyle(css2);
        grid_details.prefWidthProperty().bind(external_grid.widthProperty());
        
        
        Text text_1 = new Text();
        text_1.setText(search_details.get(0));
        text_1.setFont(Font.font ("", 15));   
        text_1.setFill(Color.BLACK);
        
        Text text_2 = new Text();
        text_2.setText(search_details.get(1));
       text_2.setFont(Font.font ("", 15));
        text_2.setFill(Color.BLACK);
        
        Text text_3 = new Text();
        text_3.setText(search_details.get(2));
        text_3.setFont(Font.font ("", 15));   
        text_3.setFill(Color.BLACK);
        
        Text text_4 = new Text();
        text_4.setText(search_details.get(3));
        text_4.setFont(Font.font ("", 15));
        text_4.setFill(Color.BLACK);
        
        Text text_5 = new Text();
        text_5.setText(search_details.get(4));
        text_5.setFont(Font.font ("", 16));
        text_5.setFill(Color.BLACK);
        
        
        grid_status.add(text_1, 0, 0);
        grid_details.add(text_2 , 0, 1);
        grid_details.add(text_3 , 0, 2);
        grid_details.add(text_4 , 0, 3);
        grid_details.add(text_5 , 0, 4);
        
        add(grid_status,0,0);
        add(grid_details,0,1);
        setStyle(css2);
       
    }
    
}
