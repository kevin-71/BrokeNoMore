import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;

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


    public JFrame createFrame(String frameName, LayoutManager layout){  // auto frame creator so all the frame has the same size properties
        JFrame frame = new JFrame(frameName);
        frame.setLocation(spawnPointX, spawnPointY);
        frame.setLayout(layout);
        frame.setSize(this.windowX, this.windowY);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);
        return frame;
    }

    public void menuLauncher(){
        JFrame frame = createFrame("BrokeNoMore Manager", null);
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
        //frame.setVisible(true);

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
        JFrame frame = createFrame("BrokeNoMore Tools", null);

        GridLayout grid = new GridLayout(3, 3, 10, 10);
        frame.setLayout(grid);

        String[] buttonNames = {"Converter", "button 2", "button 3", "button 4", "e", "d", "d", "r", "Return to menu"};
        Color[] colors = {Color.GREEN, Color.YELLOW, Color.WHITE, Color.BLUE, Color.PINK, Color.CYAN, Color.GRAY, Color.ORANGE, Color.RED};
        ActionListener[] eventListeners = {
                e -> {
                    frame.dispose();
                    converterWindow();
                },

                e -> System.out.println("Button 2 clicked"),
                e -> System.out.println("Button 3 clicked"),
                e -> System.out.println("Button 4 clicked"),
                e -> System.out.println("Button e clicked"),
                e -> System.out.println("Button d clicked"),
                e -> System.out.println("Button d clicked"),
                e -> System.out.println("Button r clicked"),

                e -> {frame.dispose();
                    menuLauncher();}
        };

        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(buttonNames[i-1]);
            button.setFont(new Font(writingPolice, Font.BOLD, 20));
            button.setBackground(colors[i-1]);
            frame.add(button);

            button.addActionListener(eventListeners[i-1]);
        }
        frame.setVisible(true);
    }

    public void moneyWindow(){
        JFrame frame = createFrame("Asset Viewer", null);

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

    public void converterWindow(){
        JFrame frame = createFrame("Convertor", null);

        JButton buttonReturn = new JButton("Return to Tools");
        buttonReturn.setFont(new Font(writingPolice, Font.BOLD, 30));
        buttonReturn.setBackground(Color.RED);
        buttonReturn.setBounds(400, 250, 250, 50);
        buttonReturn.setVisible(true);
        frame.add(buttonReturn);

        JTextArea textArea = new JTextArea();
        JTextArea textArea2 = new JTextArea();

        textArea.setBounds(100, 110, 200, 50);
        textArea.setFont(new Font(writingPolice, Font.BOLD, 30));
        textArea2.setBounds(400, 110, 200, 50);
        textArea2.setFont(new Font(writingPolice, Font.BOLD, 30));
        textArea.setBorder(BorderFactory.createLineBorder(Color.black, 5, true));
        textArea2.setBorder(BorderFactory.createLineBorder(Color.black, 5, true));

        String[] listCurrencies = {"EUR", "USD", "GBP", "WON"};
        JComboBox<String> currenciesBox = new JComboBox<>(listCurrencies);
        JComboBox<String> currenciesBox2 = new JComboBox<>(listCurrencies);
        currenciesBox2.setSelectedIndex(1);

        currenciesBox.setFont(new Font(writingPolice, Font.BOLD, 30));
        currenciesBox.setBounds(100, 40, 200, 50);

        currenciesBox2.setFont(new Font(writingPolice, Font.BOLD, 30));
        currenciesBox2.setBounds(400, 40, 200, 50);

        frame.add(currenciesBox);
        frame.add(currenciesBox2);

        String userInput = textArea.getText();

        JButton buttonConvert = new JButton("Convert");
        buttonConvert.setFont(new Font(writingPolice, Font.BOLD, 30));
        buttonConvert.setBackground(Color.GREEN);

        buttonConvert.setBounds(50, 250,250, 50);
        buttonConvert.setVisible(true);

        frame.add(buttonConvert);
        frame.add(textArea);
        frame.add(textArea2);

        buttonConvert.addActionListener(e -> {
            int _____ = 5; // do nothing !
        });

        buttonReturn.addActionListener(e -> {
            frame.dispose();
            toolWindow();
        });
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.menuLauncher();
    }
}
