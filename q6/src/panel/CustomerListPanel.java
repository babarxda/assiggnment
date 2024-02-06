package panel;

import model.Customer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CustomerListPanel extends Panel {
    private List<Customer> customers;
    private JList<Customer>customerJList;

    public CustomerListPanel(AddressDisplayPanel context)
    {
//        this.setSize(1000,600);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        customers = this.getCustomers();
        customerJList = new JList<>(new Vector<>(customers));
//        customerJList.setMinimumSize(new Dimension(1000,600));
        customerJList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof Customer) {
                    ((JLabel) renderer).setText(((Customer) value).getFullName());
                }
                return renderer;
            }
        });
        customerJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting())
                {
                    context.setCustomerId(customerJList.getSelectedValue().getCustomerId());
                }
            }
        });
        JLabel label = new JLabel("Customers");

        this.add(label);
        this.add(customerJList);
    }

    private List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        try {
            Connection connection = (Connection) DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/maybank", "root", "");

            PreparedStatement st = connection.prepareCall("SELECT `customer_id`, `short_name`, `full_name`, `address1`, `address2`, `address3`, `city`, `postal_code` FROM customer");
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                System.out.println(rs.getRow());
                Customer customer = new Customer(rs.getInt("customer_id"),
                        rs.getString("short_name"),
                        rs.getString("full_name"),
                        rs.getString("address1"),
                        rs.getString("address2"),
                        rs.getString("address3"),
                        rs.getString("city"),
                        rs.getString("postal_code")
                );

                customers.add(customer);

            }


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return customers;
    }
}
