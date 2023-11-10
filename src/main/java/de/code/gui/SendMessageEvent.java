package de.code.gui;

import de.code.Main;
import de.code.client.TCPClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendMessageEvent implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String ip = Main.gui.ipinput.getText();
        String message = Main.gui.messageinput.getText();
        TCPClient tcpClient = new TCPClient(ip, 4020);
        tcpClient.send(message);
    }
}
