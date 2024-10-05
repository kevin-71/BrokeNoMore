import javax.swing.*;
import javax.swing.table.TableCellRenderer;
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

    private double userMoney = 1784.22;

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

        JButton moneyButton = new JButton(Double.toString(this.userMoney)+"$"); // disguising a button into a label so we can click the money to print details

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

        JButton buttonClose = new JButton("Close");
        buttonClose.setFont(new Font(writingPolice, Font.BOLD, 30));
        buttonClose.setBackground(Color.RED);
        buttonClose.setBounds(400, 250, 250, 50);
        buttonClose.setVisible(true);


        frame.add(buttonClose);
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

        buttonClose.addActionListener(e ->{
            frame.dispose();
            System.exit(0); // stop the java program from running
        });
    }

    public void toolWindow() {
        JFrame frame = new JFrame("BrokeNoMore Tools");
        frame.setLocation(this.spawnPointX, this.spawnPointY);
        frame.setSize(this.windowX, this.windowY);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        GridLayout grid = new GridLayout(3, 3, 10, 10);
        frame.setLayout(grid);


        for (int i = 1; i <= 8; i++) {
            JButton button = new JButton("Button " + i);
            button.setFont(new Font(writingPolice, Font.BOLD, 20));
            frame.add(button);

            button.addActionListener(e -> {
                int ____ = 4; // do nothing
            });
        }

        JButton buttonReturn = new JButton("Return to menu");
        buttonReturn.setFont(new Font(writingPolice, Font.BOLD, 20));
        buttonReturn.setBackground(Color.RED);
        buttonReturn.setVisible(true);

        //buttonReturn.setLayout(new GridLayout(3,3, 50, 50));

        frame.setLayout(grid);
        frame.add(buttonReturn);
        frame.setVisible(true);

        buttonReturn.addActionListener(e -> {
            frame.dispose();
            menuLauncher();
        });
    }

    public void moneyWindow(){
        JFrame frame = new JFrame("Asset Viewer");
        frame.setLocation(spawnPointX, spawnPointY);
        frame.setLayout(null);
        frame.setSize(this.windowX, this.windowY);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // do not let the user close this windows, he must use return button for comfort

        JLabel moneyLabel = new JLabel("Total : ");

        JLabel titleLabel = new JLabel("Asset Viewer");
        titleLabel.setFont(new Font(writingPolice, Font.BOLD, 30));
        //titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        titleLabel.setBounds(250,10,300,30);

        String[][] moneyRepartition = {{"1", "In-Bank", "1500$", "1500$"},
                {"2", "Cash", "51.18$", "51.18$"},
                {"3", "Euro", "20€", "22.09$"},
                {"4", "Gold", "0.035 oz", "12.97$"},
                {"5", "Bitcoin", "0.00002 ₿", "11.45$"}
        };

        String[] column = {"Rank", "Asset", "Quantity", "Dollar Value"};

        JTable tableMoney = new JTable(moneyRepartition, column);
        tableMoney.setSize(500,250);
        tableMoney.setPreferredSize(new Dimension(500,500));
        tableMoney.setBounds(50, 50, windowX - 150, windowY - 150);
        JScrollPane scrollPane = new JScrollPane(tableMoney);

        tableMoney.getColumnModel().getColumn(0).setPreferredWidth(20);

        tableMoney.setRowHeight(40);
        tableMoney.setFont(new Font(writingPolice, Font.BOLD, 30));

        JButton buttonReturn = new JButton("Return to Menu");
        buttonReturn.setFont(new Font(writingPolice, Font.BOLD, 30));
        buttonReturn.setBackground(Color.RED);
        buttonReturn.setBounds(400, 250, 250, 50);
        buttonReturn.setVisible(true);

        titleLabel.setVisible(true);
        frame.add(titleLabel);
        frame.add(buttonReturn);
        frame.add(scrollPane);
        frame.add(tableMoney);
        frame.setVisible(true);

        buttonReturn.addActionListener(e -> {
            frame.dispose();
            menuLauncher();
        });
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.menuLauncher();
    }
}
