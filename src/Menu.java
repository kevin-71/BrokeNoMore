import javax.swing.*;
import java.awt.*;

public class Menu {
    public int windowX = 700;
    public int windowY = 350;

    public String writingPolice = "Times New Roman";

    public void menuLauncher(){
        JFrame frame = new JFrame("BrokeNoMore Manager");
        frame.setSize(this.windowX, this.windowY);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("Balance");
        titleLabel.setFont(new Font(writingPolice, Font.BOLD, 30));
        titleLabel.setBounds(300,10,300,30);

        JLabel moneyLabel = new JLabel("1784.22$");
        moneyLabel.setFont(new Font(writingPolice, Font.PLAIN, 50));
        moneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        moneyLabel.setVerticalAlignment(SwingConstants.CENTER);

        JPanel panelBalance = new JPanel();
        panelBalance.add(moneyLabel);
        panelBalance.setBorder(BorderFactory.createLineBorder(Color.black, 5, true));
        panelBalance.setBackground(Color.green);
        panelBalance.setBounds(200, 50, 300, 100);

        JButton buttonTool = new JButton("Tools");
        buttonTool.setFont(new Font(writingPolice, Font.BOLD, 30));
        buttonTool.setBackground(Color.ORANGE);


        buttonTool.setBounds(50, 250,250, 50);
        buttonTool.setVisible(true);

        frame.add(buttonTool);
        frame.add(titleLabel);
        frame.setLayout(null);
        frame.add(panelBalance);
        frame.setVisible(true);

        buttonTool.addActionListener(e -> {
            frame.dispose();
            toolWindow();
            menuLauncher();
        });
    }

    public void toolWindow(){
        JFrame frame = new JFrame("BrokeNoMore Tools");
        frame.setSize(this.windowX, this.windowY);
        frame.setResizable(false);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.menuLauncher();
    }
}
