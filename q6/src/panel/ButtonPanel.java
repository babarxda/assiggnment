package panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends Panel {
    private JButton add;
    private JButton modify;
    private JButton delete;
    public ButtonPanel()
    {
        add = new JButton("Add");
        modify = new JButton("Modify");
        delete = new JButton("Delete");
        add.setMaximumSize(new Dimension(80,20));
        modify.setMaximumSize(new Dimension(80,20));
        delete.setMaximumSize(new Dimension(80,20));
        this.add(add);
        this.add(modify);
        this.add(delete);

    }

    public JButton getAdd() {
        return add;
    }

    public JButton getModify() {
        return modify;
    }

    public JButton getDelete() {
        return delete;
    }
}
