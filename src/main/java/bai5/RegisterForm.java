package bai5;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.*;
import java.util.Date;

public class RegisterForm extends JFrame {

    JTextField txtId, txtName, txtEmail, txtPhone;
    JTextArea txtAddress;
    JPasswordField txtPass, txtConfirm;
    JSpinner spBirth;
    JRadioButton rMale, rFemale, rOther;
    JCheckBox chkAccept;

    public RegisterForm() {
        setTitle("Đăng ký tài khoản");
        setSize(450, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5,5,5,5);
        g.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        txtId = addText(p, g, "Mã KH", row++);
        txtName = addText(p, g, "Họ và tên", row++);
        txtEmail = addText(p, g, "Email", row++);
        txtPhone = addText(p, g, "SĐT", row++);

        addLabel(p, g, "Địa chỉ", row);
        txtAddress = new JTextArea(3, 20);
        g.gridx = 1; g.gridy = row++;
        p.add(new JScrollPane(txtAddress), g);

        txtPass = addPassword(p, g, "Mật khẩu", row++);
        txtConfirm = addPassword(p, g, "Xác nhận MK", row++);

//        // Ngày sinh
//        addLabel(p, g, "Ngày sinh", row);
//        spBirth = new JSpinner(new SpinnerDateModel());
//        spBirth.setEditor(new JSpinner.DateEditor(spBirth, "dd/MM/yyyy"));
//        g.gridx = 1; g.gridy = row++;
//        p.add(spBirth, g);
// Ngày sinh
        addLabel(p, g, "Ngày sinh", row);

        spBirth = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor =
                new JSpinner.DateEditor(spBirth, "dd/MM/yyyy");
        spBirth.setEditor(editor);

        g.gridx = 1;
        g.gridy = row++;
        p.add(spBirth, g);

        // Giới tính
        addLabel(p, g, "Giới tính", row);
        rMale = new JRadioButton("Nam");
        rFemale = new JRadioButton("Nữ");
        rOther = new JRadioButton("Khác");
        ButtonGroup bg = new ButtonGroup();
        bg.add(rMale); bg.add(rFemale); bg.add(rOther);
        JPanel gp = new JPanel();
        gp.add(rMale); gp.add(rFemale); gp.add(rOther);
        g.gridx = 1; g.gridy = row++;
        p.add(gp, g);

        chkAccept = new JCheckBox("Tôi đồng ý điều khoản");
        g.gridx = 1; g.gridy = row++;
        p.add(chkAccept, g);

        JButton btnReg = new JButton("Đăng ký");
        btnReg.addActionListener(e -> register());
        g.gridx = 1; g.gridy = row;
        p.add(btnReg, g);

        add(p);
    }

    private JTextField addText(JPanel p, GridBagConstraints g, String label, int row) {
        addLabel(p, g, label, row);
        JTextField t = new JTextField();
        g.gridx = 1; g.gridy = row;
        p.add(t, g);
        return t;
    }

    private JPasswordField addPassword(JPanel p, GridBagConstraints g, String label, int row) {
        addLabel(p, g, label, row);
        JPasswordField t = new JPasswordField();
        g.gridx = 1; g.gridy = row;
        p.add(t, g);
        return t;
    }

    private void addLabel(JPanel p, GridBagConstraints g, String label, int row) {
        g.gridx = 0; g.gridy = row;
        p.add(new JLabel(label), g);
    }

    private void register() {
        try {
            if (txtId.getText().length() < 6 || txtId.getText().length() > 10) {
                show("Mã KH 6–10 ký tự");
                return;
            }
            if (!txtEmail.getText().matches(".+@.+\\..+")) {
                show("Email không hợp lệ");
                return;
            }
            if (!txtPhone.getText().matches("0\\d{9,11}")) {
                show("SĐT sai");
                return;
            }
            if (txtPass.getPassword().length < 8) {
                show("Mật khẩu ≥ 8 ký tự");
                return;
            }
            if (!String.valueOf(txtPass.getPassword())
                    .equals(String.valueOf(txtConfirm.getPassword()))) {
                show("Mật khẩu không khớp");
                return;
            }
            if (!chkAccept.isSelected()) {
                show("Phải đồng ý điều khoản");
                return;
            }

            Date d = (Date) spBirth.getValue();
            LocalDate birth = d.toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDate();
            if (Period.between(birth, LocalDate.now()).getYears() < 18) {
                show("Chưa đủ 18 tuổi");
                return;
            }

            String gender = rMale.isSelected() ? "Male" :
                    rFemale.isSelected() ? "Female" : "Other";

            Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO dbo.Customers(customer_id, full_name, email, phone, address, password, gender, birth_date) VALUES (?,?,?,?,?,?,?,?)"
            );
            ps.setString(1, txtId.getText());
            ps.setString(2, txtName.getText());
            ps.setString(3, txtEmail.getText());
            ps.setString(4, txtPhone.getText());
            ps.setString(5, txtAddress.getText());
            ps.setString(6, String.valueOf(txtPass.getPassword()));
            ps.setString(7, gender);
            ps.setDate(8, java.sql.Date.valueOf(birth));
            ps.executeUpdate();

            show("Đăng ký thành công");
        } catch (Exception e) {
            show(e.getMessage());
        }
    }

    private void show(String m) {
        JOptionPane.showMessageDialog(this, m);
    }

    public static void main(String[] args) {
        new RegisterForm().setVisible(true);
    }
}
