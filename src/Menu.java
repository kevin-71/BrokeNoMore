import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;

public class Menu {

    public String writingPolice = "Times New Roman";
    int windowX = 600;
    int windowY =350;

    private double userMoney = 1784.22;

    //window
    JFrame frame;
    CardLayout cardLayout;
    JPanel panel;


    public Menu(){
        //set up frame
        frame = new JFrame("BrokeNoMore");
        frame.setSize(this.windowX, this.windowY);
        frame.setLocationRelativeTo(null); // put the frame on the middle of the frame
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        JPanel menuLauncher = menuLauncher();
        JPanel toolWindow = toolWindow();
        JPanel moneyWindow = moneyWindow();
        JPanel converterWindow = converterWindow();

        panel.add(menuLauncher, "MenuLauncher");
        panel.add(toolWindow, "ToolWindow");
        panel.add(moneyWindow, "MoneyWindow");
        panel.add(converterWindow, "ConverterWindow");
        frame.add(panel);

        frame.setVisible(true);


    }


    public JPanel menuLauncher(){
        // set menu panel
        JPanel menuLauncher = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Balance");
        titleLabel.setFont(new Font(writingPolice, Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the title

        JButton moneyButton = new JButton(Double.toString(this.userMoney)+"$");
        moneyButton.setFont(new Font(writingPolice, Font.PLAIN, 50));
        moneyButton.setBackground(Color.GREEN); // Change button color to differentiate

        // Panel for the Balance, using BoxLayout to align along Y
        JPanel panelBalance = new JPanel();
        panelBalance.setLayout(new BoxLayout(panelBalance, BoxLayout.Y_AXIS));
        panelBalance.add(titleLabel);

        // Add some spacing between the label and button
        panelBalance.add(Box.createVerticalStrut(10));
        panelBalance.add(moneyButton);

        // Align components to the center
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        moneyButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton buttonTool = new JButton("Tools");
        buttonTool.setFont(new Font(writingPolice, Font.BOLD, 30));
        buttonTool.setBackground(Color.ORANGE);

        JButton buttonClose = new JButton("Close");
        buttonClose.setFont(new Font(writingPolice, Font.BOLD, 30));
        buttonClose.setBackground(Color.RED);

        // Panel for the buttons close and tools
        JPanel panelButtons = new JPanel(new GridLayout(1, 2));
        panelButtons.add(buttonTool);
        panelButtons.add(buttonClose);

        // Add the panels to the main menu
        menuLauncher.add(Box.createVerticalStrut(windowY/6), BorderLayout.NORTH); // Add space at the top
        menuLauncher.add(panelBalance, BorderLayout.CENTER); // Add balance to the center
        menuLauncher.add(Box.createVerticalStrut(windowY/6), BorderLayout.NORTH); // Add space at the top
        menuLauncher.add(panelButtons, BorderLayout.SOUTH);

        // Action Listeners
        buttonTool.addActionListener(e -> {
            cardLayout.show(panel, "ToolWindow");
        });

        moneyButton.addActionListener(e -> {
            cardLayout.show(panel, "MoneyWindow");
        });

        buttonClose.addActionListener(e -> {
            frame.dispose();
            System.exit(0); // stop the Java program from running
        });

        return menuLauncher;
    }


    public JPanel toolWindow() {
        JPanel toolWindow = new JPanel();

        GridLayout grid = new GridLayout(3, 3, 10, 10);
        toolWindow.setLayout(grid);

        String[] buttonNames = {"Converter", "button 2", "button 3", "button 4", "e", "d", "d", "r", "Return to menu"};
        Color[] colors = {Color.GREEN, Color.YELLOW, Color.WHITE, Color.BLUE, Color.PINK, Color.CYAN, Color.GRAY, Color.ORANGE, Color.RED};
        ActionListener[] eventListeners = {
                e -> {
                    cardLayout.show(panel, "ConverterWindow");
                },

                e -> System.out.println("Button 2 clicked"),
                e -> System.out.println("Button 3 clicked"),
                e -> System.out.println("Button 4 clicked"),
                e -> System.out.println("Button e clicked"),
                e -> System.out.println("Button d clicked"),
                e -> System.out.println("Button d clicked"),
                e -> System.out.println("Button r clicked"),

                e -> {cardLayout.show(panel, "MenuLauncher");}
        };

        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(buttonNames[i-1]);
            button.setFont(new Font(writingPolice, Font.BOLD, 20));
            button.setBackground(colors[i-1]);
            toolWindow.add(button);

            button.addActionListener(eventListeners[i-1]);
        }
        //frame.setVisible(true);
        return toolWindow;
    }

    public JPanel moneyWindow(){
        JPanel moneyWindow = new JPanel(new BorderLayout());


        JLabel titleLabel = new JLabel("Asset Viewer");
        titleLabel.setFont(new Font(writingPolice, Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // New JPanel to center
        titlePanel.add(titleLabel);

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
        JScrollPane scrollPane = new JScrollPane(tableMoney);

        tableMoney.getColumnModel().getColumn(0).setPreferredWidth(20);

        tableMoney.setRowHeight(40);
        tableMoney.setFont(new Font(writingPolice, Font.BOLD, 30));

        JButton buttonReturn = new JButton("Return to Menu");
        buttonReturn.setFont(new Font(writingPolice, Font.BOLD, 30));
        buttonReturn.setBackground(Color.RED);

        titlePanel.setVisible(true); // Assurez-vous que le titlePanel est visible
        moneyWindow.add(titlePanel, BorderLayout.NORTH); // Ajout du titlePanel
        moneyWindow.add(buttonReturn, BorderLayout.SOUTH);
        moneyWindow.add(scrollPane, BorderLayout.CENTER); // Mettre scrollPane au centre

        buttonReturn.addActionListener(e -> {
            cardLayout.show(panel, "MenuLauncher");
        });
        return moneyWindow;
    }

    public JPanel converterWindow(){
        JPanel converterWindow = new JPanel();

        JButton buttonReturn = new JButton("Return to Tools");
        buttonReturn.setFont(new Font(writingPolice, Font.BOLD, 30));
        buttonReturn.setBackground(Color.RED);
        buttonReturn.setBounds(400, 250, 250, 50);
        buttonReturn.setVisible(true);
        converterWindow.add(buttonReturn);

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

        converterWindow.add(currenciesBox);
        converterWindow.add(currenciesBox2);

        String userInput = textArea.getText();

        JButton buttonConvert = new JButton("Convert");
        buttonConvert.setFont(new Font(writingPolice, Font.BOLD, 30));
        buttonConvert.setBackground(Color.GREEN);

        buttonConvert.setBounds(50, 250,250, 50);
        buttonConvert.setVisible(true);

        converterWindow.add(buttonConvert);
        converterWindow.add(textArea);
        converterWindow.add(textArea2);

        buttonConvert.addActionListener(e -> {
            int _____ = 5; // do nothing !
        });

        buttonReturn.addActionListener(e -> {
            cardLayout.show(panel, "ToolWindow");
        });
        return converterWindow;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Menu(); // Crée une instance de Brouillon
            }
        });
    }

}
