import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu {
    public int windowX = 700;
    public int windowY = 350;

    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();  // get user screen size to make the manager pop up on the center of his screen
    double userWindowWidth = gd.getDisplayMode().getWidth();
    double userWindowHeight = gd.getDisplayMode().getHeight();

    double spawnX = (userWindowWidth - windowX) / 3;
    double spawnY = (userWindowHeight - windowY) / 3;

    int spawnPointX = (int) spawnX;
    int spawnPointY = (int) spawnY;

    public String writingPolice = "Times New Roman";

    public void menuLauncher(){
        JFrame frame = new JFrame("BrokeNoMore Manager");
        frame.setLocation(spawnPointX, spawnPointY);
        frame.setLayout(null);
        frame.setSize(this.windowX, this.windowY);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("Balance");
        titleLabel.setFont(new Font(writingPolice, Font.BOLD, 30));
        titleLabel.setBounds(300,10,300,30);

        JButton moneyButton = new JButton("1784.22$"); // disguising a button into a label so we can click the money to print details
        moneyButton.setFont(new Font(writingPolice, Font.PLAIN, 50));
        moneyButton.setHorizontalAlignment(SwingConstants.CENTER);
        moneyButton.setVerticalAlignment(SwingConstants.CENTER);
        moneyButton.setBorderPainted(false);
        moneyButton.setFocusPainted(false);
        moneyButton.setBackground(Color.GREEN);
        moneyButton.setForeground(Color.BLACK); // make the money string black


        JPanel panelBalance = new JPanel();
        panelBalance.add(moneyButton);
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
        frame.add(panelBalance);
        frame.setVisible(true);

        buttonTool.addActionListener(e -> {
            frame.dispose();
            toolWindow();
        });

        moneyButton.addActionListener(e -> {
            frame.dispose();
            moneyWindow();
        });
    }

    public void toolWindow(){
        JFrame frame = new JFrame("BrokeNoMore Tools");
        frame.setLocation(spawnPointX, spawnPointY);
        frame.setLayout(null);
        frame.setSize(this.windowX, this.windowY);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // do not let the user close this windows, he must use return button for comfort

        JButton buttonReturn = new JButton("Return to Menu");
        buttonReturn.setFont(new Font(writingPolice, Font.BOLD, 30));
        buttonReturn.setBackground(Color.RED);
        buttonReturn.setBounds(400, 250, 250, 50);
        buttonReturn.setVisible(true);

        frame.add(buttonReturn);
        frame.setVisible(true);



        buttonReturn.addActionListener(e -> {
            frame.dispose();
            menuLauncher();
        });
    }

    public void moneyWindow(){
        JFrame frame = new JFrame("Money Viewer");
        frame.setLocation(spawnPointX, spawnPointY);
        frame.setLayout(null);
        frame.setSize(this.windowX, this.windowY);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // do not let the user close this windows, he must use return button for comfort

        String moneyRepartition[][] = {{"1", "In-Bank Money", "1500$", "1500$"},
                {"2", "Cash", "51.18$", "51.18$"},
                {"3", "Euro", "20€", "22.09$"},
                {"4", "Gold", "0.035 oz", "12.97$"},
                {"5", "Bitcoin", "0.00002 ₿", "11.45$"}
        };

        String[] column = {"Rank", "Asset", "Quantity", "Dollar Value"};

        JTable tableMoney = new JTable(moneyRepartition, column);
        tableMoney.setSize(500,250);
        tableMoney.setPreferredSize(new Dimension(500,500));
        tableMoney.setBounds(50, 50, windowX - 100, windowY - 100);
        JScrollPane scrollPane = new JScrollPane(tableMoney);
        //tableMoney.setFont(new Font(writingPolice, Font.BOLD, 30));

        frame.add(scrollPane);
        frame.add(tableMoney);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.menuLauncher();
    }
}
