import model.Customer;
import panel.AddressDisplayPanel;
import panel.ButtonPanel;
import panel.CustomerListPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.html.Option;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

public class CustomerScreen extends JFrame {


    public CustomerScreen() {
        setTitle("Q6");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        AddressDisplayPanel addressDisplayPanel = new AddressDisplayPanel();
        CustomerListPanel customerListPanel = new CustomerListPanel(addressDisplayPanel);
        ButtonPanel buttonPanel = new ButtonPanel();
        add(customerListPanel, BorderLayout.WEST);
        add(addressDisplayPanel, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.PAGE_END);
        buttonPanel.getAdd().addActionListener(new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (count < 3) {
                    addressDisplayPanel.setAddressVisible(true, count);
                    count++;

                }
                else
                {
                    count = 0;
                }
            }
        });
        buttonPanel.getDelete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    addressDisplayPanel.setAddressVisible(false, -1);
            }
        });

        buttonPanel.getModify().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addressDisplayPanel.updateCustomers();
            }
        });
//        TextField address1 = new TextField();
//        TextField address2 = new TextField();
//        TextField address3 = new TextField();

    }


    public static void main(String[] args) {
        new CustomerScreen();
    }
}
