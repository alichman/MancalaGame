package ui;

import javax.swing.JButton;

/**
 * Represents a GUI button component that knows its position in a grid.
 */
public class PositionAwareButton extends JButton {
    private int pitNum;

    /**
     * Constructs a new PositionAwareButton.
     */
    public PositionAwareButton() {
        super();
    }

    /**
     * Constructs a new PositionAwareButton with the specified text.
     *
     * @param val The text to display on the button.
     */
    public PositionAwareButton(int pitnum, int value) {
        super(""+value);
        if(pitnum > 7){
            pitnum -=1;
        }

        pitNum = pitnum;
    }

    /**
     * Gets the Pit Number of the button in a grid.
     *
     * @return The pit number of the button.
     */
    public int getPitNum() {
        return pitNum;
    }
}
