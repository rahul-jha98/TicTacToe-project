import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class SimpleChatClient {
    //suffix with 1 -> chat messages and suffix with 2 -> game messages
    JTextArea incoming;
    JTextField outgoing;
    BufferedReader reader1 , reader2;
    PrintWriter writer1 , writer2;
    Socket sock1, sock2;
    public void go() {
        JFrame frame = new JFrame("Ludicrously Simple Chat Client");
        JPanel mainPanel = new JPanel();
        incoming = new JTextArea(15,50);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);
        JScrollPane qScroller = new JScrollPane(incoming);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());
        mainPanel.add(qScroller);
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        setUpNetworking();
        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(800,500);
        frame.setVisible(true);
    } // close go
    private void setUpNetworking() {
        try {
            sock1 = new Socket("127.0.0.1", 5050);
            sock2 = new Socket("127.0.0.1", 6050);
            InputStreamReader streamReader = new InputStreamReader(sock1.getInputStream());
            reader1 = new BufferedReader(streamReader);
            writer1 = new PrintWriter(sock1.getOutputStream());

            streamReader = new InputStreamReader(sock2.getInputStream());
            reader2 = new BufferedReader(streamReader);
            writer2 = new PrintWriter(sock2.getOutputStream());

            System.out.println("networking established");
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    } // close setUpNetworking
    public class SendButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            try {
                writer1.println(outgoing.getText());
                writer1.flush();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            outgoing.setText("");
            outgoing.requestFocus();
        }
    } // close inner class
    public class IncomingReader implements Runnable {
        public void run() {
            String message;
            try {
                while ((message = reader1.readLine()) != null) {
                    System.out.println("read " + message);
                    incoming.append(message + "\n");
                } // close while
            } catch(Exception ex) {ex.printStackTrace();}
        } // close run
    } // close inner class
} // close outer class