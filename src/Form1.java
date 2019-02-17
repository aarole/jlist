import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.*;

public class Form1 {
    private JPanel panel1;
    private JTextField txtFirst;
    private JTextField txtLast;
    private JButton enterButton;
    private JButton displayButton;
    private JButton clearButton;
    private JButton exitButton;
    private JList lstOut;

    private DefaultListModel model = (DefaultListModel) lstOut.getModel();
    private String[] mFirst = new String[100];
    private String[] mLast = new String[100];
    private int mIndex = 0;

    public Form1() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(null,"Do you want to quit?") == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClearInput();
                model.removeAllElements();
            }
        });
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!ValidateInput()){
                    return;
                }

                String first = camelCase(txtFirst.getText());
                String last = camelCase(txtLast.getText());
                mFirst[mIndex] = first;
                mLast[mIndex] = last;
                mIndex++;

                ClearInput();
            }
        });
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.removeAllElements();

                if (mIndex==0)
                    return;
                for(int i=0;i<mIndex;i++){
                    model.addElement(mFirst[i] + " " + mLast[i]);
                }
            }
        });
        lstOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = lstOut.getSelectedIndex();

                txtFirst.setText(mFirst[index]);
                txtLast.setText(mLast[index]);
            }
        });
    }

    public void ClearInput(){
        txtFirst.setText("");
        txtLast.setText("");
        txtFirst.requestFocus();
    }

    public boolean ValidateInput(){
        boolean flag = true;
        if(txtFirst.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please enter your first name.");
            flag = false;
        }
        if(txtLast.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please enter your last name.");
            flag = false;
        }
        return flag;
    }

    public String camelCase(String input){
        String part1 = input.substring(0,1).toUpperCase();
        String part2 = input.substring(1).toLowerCase();
        String correctedCase = part1 + part2;

        return correctedCase;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("JList");
        frame.setContentPane(new Form1().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
