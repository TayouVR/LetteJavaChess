import javax.swing.*;

public class UserInterfaceThread extends Thread {

    JFrame window = new JFrame();

    @Override
    public void run() {
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        window.setVisible(true);
    }
}
