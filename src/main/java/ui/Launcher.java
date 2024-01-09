package ui;

import javax.swing.SwingUtilities;

public class Launcher {
    
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                Interface main = new Interface();
                main.showWindow();
            }

        });
    }
}
