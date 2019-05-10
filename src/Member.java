import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Member implements ActionListener {
	
	JPanel memberPanel; // ��� �г�
	JPanel memberBtnPanel; // �ؽ�Ʈ�ʵ�� ��ư ���� �г� 
	JPanel textfieldPanel; // �ؽ�Ʈ�ʵ� �г� 
	JPanel btnPanel; // ��ư �г� 
	static JTable memberTable; // ��� ���̺�
	static DefaultTableModel memberTableModel;
	
	JTextField member_name;
	JTextField member_number;
	JTextField member_mileage;
	JTextField member_phone;
	// ���̺� �Է��� �ؽ�Ʈ ��� �ʵ�
	
	JLabel memberName;
	JLabel memberNumber;
	JLabel memberMileage;
	JLabel memberPhone;
	
	JButton member_add;
	JButton member_delete;
	JButton member_edit;
	// ���̺� ���� ��ư 
	
	public Member() {
		memberPanel = new JPanel();
		memberPanel.setLayout(new BorderLayout());
		
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("MemberTable"));
			
			Vector rowData = (Vector)inputStream.readObject();
			Vector columnNames = (Vector)inputStream.readObject();
			
			if(rowData.isEmpty()) {
				Vector<String> userColumn = new Vector<String> ();
				userColumn.addElement("��ȣ");
				userColumn.addElement("�̸�");
				userColumn.addElement("����ó");
				userColumn.addElement("���ϸ���");
				userColumn.addElement("ȸ�����");
				memberTableModel = new DefaultTableModel(userColumn, 0);
			}
			else {
				memberTableModel = new DefaultTableModel();
				memberTableModel.setDataVector(rowData, columnNames);
			}
			
			inputStream.close();
			
		} catch(FileNotFoundException e) {
			Vector<String> userColumn = new Vector<String> ();
			userColumn.addElement("��ȣ");
			userColumn.addElement("�̸�");
			userColumn.addElement("����ó");
			userColumn.addElement("���ϸ���");
			userColumn.addElement("ȸ�����");
			memberTableModel = new DefaultTableModel(userColumn, 0);
		} catch (ClassNotFoundException e) {
			Vector<String> userColumn = new Vector<String> ();
			userColumn.addElement("��ȣ");
			userColumn.addElement("�̸�");
			userColumn.addElement("����ó");
			userColumn.addElement("���ϸ���");
			userColumn.addElement("ȸ�����");
			memberTableModel = new DefaultTableModel(userColumn, 0);
			e.printStackTrace();
		} catch (IOException e) {
			Vector<String> userColumn = new Vector<String> ();
			userColumn.addElement("��ȣ");
			userColumn.addElement("�̸�");
			userColumn.addElement("����ó");
			userColumn.addElement("���ϸ���");
			userColumn.addElement("ȸ�����");
			memberTableModel = new DefaultTableModel(userColumn, 0);
			e.printStackTrace();
		}
		
		memberTable = new JTable(memberTableModel);
		JScrollPane scrollpane = new JScrollPane(memberTable);
		memberPanel.add(scrollpane, BorderLayout.CENTER);
		
		memberName = new JLabel("�̸�");
		memberName.setHorizontalAlignment(JLabel.CENTER);

		memberNumber = new JLabel("��ȣ");
		memberNumber.setHorizontalAlignment(JLabel.CENTER);
		
		memberMileage = new JLabel("���ϸ���");
		memberMileage.setHorizontalAlignment(JLabel.CENTER);
		
		memberPhone = new JLabel("����ó");
		memberPhone.setHorizontalAlignment(JLabel.CENTER);
		
		member_name = new JTextField(10);
		member_number = new JTextField(10);
		member_mileage = new JTextField(10);
		member_phone = new JTextField(10);
		
		member_add = new JButton("�߰�");
		member_add.addActionListener(this);
		
		member_delete = new JButton("����");
		member_delete.addActionListener(this);
		
		member_edit = new JButton("����");
		member_edit.addActionListener(this);
		// �ؽ�Ʈ�ʵ�� ��ư ���� 
		
		memberBtnPanel = new JPanel();
		memberBtnPanel.setLayout(new GridLayout(2,1));
		btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(1,3));
		textfieldPanel = new JPanel();
		textfieldPanel.setLayout(new GridLayout(2,4));
		
		textfieldPanel.add(memberNumber);
		textfieldPanel.add(memberName);
		textfieldPanel.add(memberPhone);
		textfieldPanel.add(memberMileage);
		textfieldPanel.add(member_number);
		textfieldPanel.add(member_name);
		textfieldPanel.add(member_phone);
		textfieldPanel.add(member_mileage);
		
		btnPanel.add(member_add);
		btnPanel.add(member_delete);
		btnPanel.add(member_edit);
		
		memberBtnPanel.add(textfieldPanel);
		memberBtnPanel.add(btnPanel);
		// �гο� �ؽ�Ʈ�ʵ�� ��ư �߰� 
		
		memberPanel.add(memberBtnPanel, BorderLayout.SOUTH);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("�߰�")) {
			if(Integer.parseInt(member_mileage.getText()) <= 500) {
				memberTableModel.addRow(new Object[] {member_number.getText(), member_name.getText(), member_phone.getText(), member_mileage.getText(), "�Ϲ�"});
			}
			
			else if(Integer.parseInt(member_mileage.getText()) > 500 && Integer.parseInt(member_mileage.getText()) <= 1000) {
				memberTableModel.addRow(new Object[] {member_number.getText(), member_name.getText(), member_phone.getText(), member_mileage.getText(), "���"});
			}
			
			else {
				memberTableModel.addRow(new Object[] {member_number.getText(), member_name.getText(), member_phone.getText(), member_mileage.getText(), "�÷�Ƽ��"});
			}
			
			member_number.setText("");
			member_name.setText("");
			member_phone.setText("");
			member_mileage.setText("");
		}
		
		else if(actionCommand.equals("����")) {
			memberTableModel.removeRow(memberTable.getSelectedRow());
		}
		
		else if(actionCommand.equals("����")) {
			int row = memberTable.getSelectedRow();
			memberTable.setValueAt(member_number.getText(), row, 0);
			memberTable.setValueAt(member_name.getText(), row, 1);
			memberTable.setValueAt(member_phone.getText(), row, 2);
			memberTable.setValueAt(member_mileage.getText(), row, 3);
			
			if(Integer.parseInt(member_mileage.getText()) <= 500) {
				memberTable.setValueAt("�Ϲ�", row, 4);
			}
			
			else if(Integer.parseInt(member_mileage.getText()) > 500 && Integer.parseInt(member_mileage.getText()) <= 1000) {
				memberTable.setValueAt("���", row, 4);
			}
			
			else {
				memberTable.setValueAt("�÷�Ƽ��", row, 4);
			}
			
			member_number.setText("");
			member_name.setText("");
			member_phone.setText("");
			member_mileage.setText("");
		}
		
	}

}
