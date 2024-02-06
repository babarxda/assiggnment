package panel;

import model.Customer;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDisplayPanel extends Panel {
    private List<JLabel> addressLabels;
    private List<JTextField> addresses;

    private int customerId;

    public AddressDisplayPanel() {
        addressLabels = new ArrayList<>();
        addresses = new ArrayList<>();
        this.setSize(200, 300);
        this.setBounds(100, 0, 200, 300);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.addAddresses();
    }

    private void addAddresses() {

        for (int i = 0; i < 3; i++) {

            JLabel addressLabel = new JLabel("Address " + (addressLabels.size() + 1));
            JTextField address = new JTextField(20);
            address.setMaximumSize(new Dimension(200, 50));
            addressLabel.setVisible(false);
            address.setVisible(false);
            this.add(addressLabel);
            this.add(address);
            addressLabels.add(addressLabel);
            addresses.add(address);

        }
    }

    public void setAddressVisible(Boolean isVisible, int index) {
        if (index == -1) {
            for (int i = 2; i >= 0; i--) {
                if (addressLabels.get(i).isVisible()) {
                    addressLabels.get(i).setVisible(false);
                    addresses.get(i).setVisible(false);
                    addresses.get(i).setText("");
                    return;
                }
            }
        } else {

            addressLabels.get(index).setVisible(isVisible);
            addresses.get(index).setVisible(isVisible);
        }
    }

    public void updateCustomers() {
        try {
            Connection connection = (Connection) DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/maybank", "root", "");

            PreparedStatement st = connection.prepareCall("UPDATE `customer` SET `address1`='"+addresses.get(0).getText()+"',`address2`='"+addresses.get(1).getText()+"',`address3`='"+addresses.get(2).getText()+"' WHERE `customer_id` = " + customerId);
            st.execute();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
