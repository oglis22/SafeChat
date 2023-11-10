package de.code.gui;

import de.code.server.TCPServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    private boolean isServerRun = false;
    private JButton listenBtn;
    private JTextArea out;
    private JScrollPane scrollPane;

    private TCPServer tcpServer;

    public JTextField ipinput;
    public JTextField messageinput;

    public GUI() {

        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(Color.DARK_GRAY);

        UIManager.put("Button.background", Color.GRAY);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Label.foreground", Color.WHITE);
        UIManager.put("TextField.background", Color.GRAY);
        UIManager.put("TextField.foreground", Color.WHITE);
        UIManager.put("TextArea.background", Color.GRAY);
        UIManager.put("TextArea.foreground", Color.WHITE);

        listenBtn = new JButton("Listen..");
        listenBtn.addActionListener(this);
        listenBtn.setBackground(Color.RED);

        JLabel conlabel = new JLabel("Con: ");

        out = new JTextArea();
        out.setEditable(false);
        out.setBackground(Color.DARK_GRAY);
        out.setForeground(Color.WHITE);

        scrollPane = new JScrollPane(out);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JLabel iplabel = new JLabel("IP: ");
        iplabel.setForeground(Color.WHITE);

        ipinput = new JTextField();
        ipinput.setBackground(Color.GRAY);
        ipinput.setForeground(Color.WHITE);

        JLabel messagelabel = new JLabel("Message: ");
        messagelabel.setForeground(Color.WHITE);

        messageinput = new JTextField();
        messageinput.setBackground(Color.GRAY);
        messageinput.setForeground(Color.WHITE);

        JButton sendbtn = new JButton("Send");
        sendbtn.addActionListener(new SendMessageEvent());

        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.setSize(700, 350);

        panel.add(listenBtn);
        panel.add(conlabel);
        panel.add(scrollPane);
        panel.add(iplabel);
        panel.add(ipinput);
        panel.add(messagelabel);
        panel.add(messageinput);
        panel.add(sendbtn);

        frame.setSize(900, 500);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Safe Chat");
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isServerRun) {
            isServerRun = true;
            listenBtn.setBackground(Color.GREEN);
            newSocket();
            tcpServer.startServer();
        } else {
            isServerRun = false;
            listenBtn.setBackground(Color.RED);
            tcpServer.stop();
        }
    }

    public void newSocket() {
        tcpServer = new TCPServer();
    }

    public void addText(String message) {
        out.append("\n" + message);
    }
}
