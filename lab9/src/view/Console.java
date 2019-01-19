/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 09, Console.java
 */
package view;

import controller.ConsoleWriter;
import javafx.scene.control.Label;

public class Console implements ConsoleWriter {

    private Label label;

    @Override
    public void write(String text){
        javafx.application.Platform.runLater(()->label.setText(text));
    }

    public Console(Label label){
        this.label = label;
    }
}
