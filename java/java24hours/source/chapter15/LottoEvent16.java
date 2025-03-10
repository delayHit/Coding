import javax.swing.*;
import java.awt.event.*;

public class LottoEvent16 implements ItemListener, ActionListener,
    Runnable {
    
    LottoMadness16 gui;
    Thread playing;

    public LottoEvent16(LottoMadness16 in) {
        gui = in;
    }

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        if (command == "Play") {
            startPlaying();
        }
        if (command == "Stop") {
            stopPlaying();
        }
        if (command == "Reset") {
            clearAllFields();
        }
    }

    void startPlaying() {
        playing = new Thread(this);
        playing.start();
        gui.play.setEnabled(false);
        gui.stop.setEnabled(true);
        gui.reset.setEnabled(false);
        gui.quickpick.setEnabled(false);
        gui.personal.setEnabled(false);
    }
 
    void stopPlaying() {
        gui.stop.setEnabled(false);
        gui.play.setEnabled(true);
        gui.reset.setEnabled(true);
        gui.quickpick.setEnabled(true);
        gui.personal.setEnabled(true);
        playing = null;
    }

    void clearAllFields() {
        for (int i = 0; i < 5; i++) {
            gui.numbers[i].setText(null);
            gui.winners[i].setText(null);
        }
        gui.got3.setText("0");
        gui.got4.setText("0");
        gui.got5.setText("0");
        gui.drawings.setText("0");
        gui.years.setText(null);
    }

    public void itemStateChanged(ItemEvent event) {
        Object item = event.getItem();
        if (item == gui.quickpick) {
            for (int i = 0; i < 5; i++) {
                int pick;
                do {
                    pick = (int) Math.floor(Math.random() * 90 + 1);
                } while (numberGone(pick, gui.numbers, i));
                gui.numbers[i].setText("" + pick);
            }
        } else {
            for (int i = 0; i < 5; i++) {
                gui.numbers[i].setText(null);
            }
        }
    }

    void addOneToField(JTextField field) {
        int num = Integer.parseInt("0" + field.getText());
        num++;
        field.setText("" + num);
    }

    boolean numberGone(int num, JTextField[] pastNums, int count) {
        for (int i = 0; i < count; i++) {
            if (Integer.parseInt(pastNums[i].getText()) == num) {
                return true;
            }
        }
        return false;
    }

    boolean matchedOne(JTextField win, JTextField[] allPicks) {
        for (int i = 0; i < 5; i++) {
            String winText = win.getText();
            if ( winText.equals( allPicks[i].getText() ) ) {
                return true;
            }
        }
        return false;
    }

    public void run() {
        Thread thisThread = Thread.currentThread();
        while (playing == thisThread) {
            addOneToField(gui.drawings);
            int draw = Integer.parseInt(gui.drawings.getText());
            float numYears = (float)draw / 104;
            gui.years.setText("" + numYears);

            int matches = 0;
            for (int i = 0; i < 5; i++) {
                int ball;
                do {
                    ball = (int)Math.floor(Math.random() * 90 + 1);
                } while (numberGone(ball, gui.winners, i));
                gui.winners[i].setText("" + ball);
                if (matchedOne(gui.winners[i], gui.numbers)) {
                    matches++;
                }
            }
            switch (matches) {
                case 3:
                    addOneToField(gui.got3);
                    break;
                case 4:
                    addOneToField(gui.got4);
                    break;
                case 5:
                    addOneToField(gui.got5);
                    gui.stop.setEnabled(false);
                    gui.play.setEnabled(true);
                    playing = null;
            }
            try {
                int pauseRate = Integer.parseInt(gui.pause.getText());
                Thread.sleep(pauseRate);
            } catch (InterruptedException e) {
                // do nothing
            } catch (NumberFormatException nfe) {
                gui.pause.setText("100");
            }
        }
    }
}
