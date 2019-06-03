package SitDown;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
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

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Member implements ActionListener {
	
	Connection con = null;
	
	String className = "org.gjt.mm.mysql.Driver";
	String url = "jdbc:mysql://localhost:3306/SitDown?useSSL=false&useUnicode=true&characterEncoding=euckr";
	String user = "root";
	String passwd = "123456";
	String sql = "INSERT INTO Storage(Iname, Iprice, Iseller, Icontact, Iquant, Iorder) VALUES";
	Statement stmt = null;
	PreparedStatement pstmt = null;
	
	
	JPanel memberPanel; // ��� �г�
	JPanel memberBtnPanel; // �ؽ�Ʈ�ʵ�� ��ư ���� �г� 
	JPanel textfieldPanel; // �ؽ�Ʈ�ʵ� �г� 
	JPanel btnPanel; // ��ư �г� 
	static JTable memberTable; // ��� ���̺�
	static DefaultTableModel memberTableModel;
	
	JTextField memberNameTextField;
	JTextField memberNumberTextField;
	JTextField memberMileageTextField;
	JTextField memberPhoneTextField;
	// ���̺� �Է��� �ؽ�Ʈ ��� �ʵ�
	
	JLabel memberName;
	JLabel memberNumber;
	JLabel memberMileage;
	JLabel memberPhone;
	
	JButton addMember;
	JButton deleteMember;
	JButton editMember;
	// ���̺� ���� ��ư 
	
	private static final String NUMBER = "��ȣ";  
	private static final String NAME = "�̸�";  
	private static final String PHONENUMBER = "����ó";
	private static final String MILEAGE = "���ϸ���";
	private static final String LEVEL = "ȸ�����";
	private static final String sqlMent = "INSERT INTO Member(Mnum, Mname, Mcontact, Mmileage, Mlevel) VALUES";
	
	public Member() {
		memberPanel = new JPanel();
		memberPanel.setLayout(new BorderLayout());
		
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("MemberTable"));
			
			Vector rowData = (Vector)inputStream.readObject();
			Vector columnNames = (Vector)inputStream.readObject();
			
			if(rowData.isEmpty()) {
				Vector<String> userColumn = new Vector<> ();
				userColumn.addElement(NUMBER);
				userColumn.addElement(NAME);
				userColumn.addElement(PHONENUMBER);
				userColumn.addElement(MILEAGE);
				userColumn.addElement(LEVEL);
				memberTableModel = new DefaultTableModel(userColumn, 0);
			}
			else {
				memberTableModel = new DefaultTableModel();
				memberTableModel.setDataVector(rowData, columnNames);
			}
			
			inputStream.close();
			
		} catch(FileNotFoundException e) {
			Vector<String> userColumn = new Vector<> ();
			userColumn.addElement(NUMBER);
			userColumn.addElement(NAME);
			userColumn.addElement(PHONENUMBER);
			userColumn.addElement(MILEAGE);
			userColumn.addElement(LEVEL);
			memberTableModel = new DefaultTableModel(userColumn, 0);
		} catch (ClassNotFoundException | IOException e) {
			Vector<String> userColumn = new Vector<> ();
			userColumn.addElement(NUMBER);
			userColumn.addElement(NAME);
			userColumn.addElement(PHONENUMBER);
			userColumn.addElement(MILEAGE);
			userColumn.addElement(LEVEL);
			memberTableModel = new DefaultTableModel(userColumn, 0);
			e.printStackTrace();
		}
		
		memberTable = new JTable(memberTableModel);
		JScrollPane scrollpane = new JScrollPane(memberTable);
		memberPanel.add(scrollpane, BorderLayout.CENTER);
		
		memberName = new JLabel(NAME);
		memberName.setHorizontalAlignment(JLabel.CENTER);

		memberNumber = new JLabel(NUMBER);
		memberNumber.setHorizontalAlignment(JLabel.CENTER);
		
		memberMileage = new JLabel(MILEAGE);
		memberMileage.setHorizontalAlignment(JLabel.CENTER);
		
		memberPhone = new JLabel(PHONENUMBER);
		memberPhone.setHorizontalAlignment(JLabel.CENTER);
		
		memberNameTextField = new JTextField(10);
		memberNumberTextField = new JTextField(10);
		memberMileageTextField = new JTextField(10);
		memberPhoneTextField = new JTextField(10);
		
		addMember = new JButton("�߰�");
		addMember.addActionListener(this);
		
		deleteMember = new JButton("����");
		deleteMember.addActionListener(this);
		
		editMember = new JButton("����");
		editMember.addActionListener(this);
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
		textfieldPanel.add(memberNumberTextField);
		textfieldPanel.add(memberNameTextField);
		textfieldPanel.add(memberPhoneTextField);
		textfieldPanel.add(memberMileageTextField);
		
		btnPanel.add(addMember);
		btnPanel.add(deleteMember);
		btnPanel.add(editMember);
		
		memberBtnPanel.add(textfieldPanel);
		memberBtnPanel.add(btnPanel);
		// �гο� �ؽ�Ʈ�ʵ�� ��ư �߰� 
		
		memberPanel.add(memberBtnPanel, BorderLayout.SOUTH);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("�߰�")) {
			if(Integer.parseInt(memberMileageTextField.getText()) <= 500) {
				memberTableModel.addRow(new Object[] {memberNumberTextField.getText(), memberNameTextField.getText(), memberPhoneTextField.getText(), memberMileageTextField.getText(), "�Ϲ�"});
			
				sql = sqlMent;
				sql += "(" + memberNumberTextField.getText() + ",\"" + memberNameTextField.getText() + "\", \"" + memberPhoneTextField.getText()
				+ "\"," + memberMileageTextField.getText() + ", \"�Ϲ�\");";
				
				try {
					Class.forName(className);
					con = DriverManager.getConnection(url, user, passwd); 
					stmt = (Statement) con.createStatement();
					stmt.executeUpdate(sql);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			else if(Integer.parseInt(memberMileageTextField.getText()) > 500 && Integer.parseInt(memberMileageTextField.getText()) <= 1000) {
				memberTableModel.addRow(new Object[] {memberNumberTextField.getText(), memberNameTextField.getText(), memberPhoneTextField.getText(), memberMileageTextField.getText(), "���"});
			
				sql =sqlMent;
				sql += "(" + memberNumberTextField.getText() + ",\"" + memberNameTextField.getText() + "\",\"" + memberPhoneTextField.getText()
				+ "\"," + memberMileageTextField.getText() + ", \"���\");";
				
				try {
					Class.forName(className);
					con = DriverManager.getConnection(url, user, passwd); 
					stmt = (Statement) con.createStatement();
					stmt.executeUpdate(sql);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			else {
				memberTableModel.addRow(new Object[] {memberNumberTextField.getText(), memberNameTextField.getText(), memberPhoneTextField.getText(), memberMileageTextField.getText(), "�÷�Ƽ��"});
			
				sql = sqlMent;
				sql += "(" + memberNumberTextField.getText() + ",\"" + memberNameTextField.getText() + "\",\"" + memberPhoneTextField.getText()
				+ "\"," + memberMileageTextField.getText() + ", \"�÷�Ƽ��\");";
				
				try {
					Class.forName(className);
					con = DriverManager.getConnection(url, user, passwd); 
					stmt = (Statement) con.createStatement();
					stmt.executeUpdate(sql);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			memberNumberTextField.setText("");
			memberNameTextField.setText("");
			memberPhoneTextField.setText("");
			memberMileageTextField.setText("");
		}
		
		else if(actionCommand.equals("����")) {
			int i = memberTable.getSelectedRow();
			
			sql = "DELETE FROM Member WHERE Mcontact =\"" + memberTableModel.getValueAt(i, 2) + "\"";
			memberTableModel.removeRow(i);
			
			try {
				Class.forName(className);
				con = DriverManager.getConnection(url, user, passwd); 
				stmt = (Statement) con.createStatement();
				stmt.executeUpdate(sql);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		
		else if(actionCommand.equals("����")) {
			int row = memberTable.getSelectedRow();
			memberTable.setValueAt(memberNumberTextField.getText(), row, 0);
			memberTable.setValueAt(memberNameTextField.getText(), row, 1);
			memberTable.setValueAt(memberPhoneTextField.getText(), row, 2);
			memberTable.setValueAt(memberMileageTextField.getText(), row, 3);
			
			if(Integer.parseInt(memberMileageTextField.getText()) <= 500) {
				memberTable.setValueAt("�Ϲ�", row, 4);
			}
			
			else if(Integer.parseInt(memberMileageTextField.getText()) > 500 && Integer.parseInt(memberMileageTextField.getText()) <= 1000) {
				memberTable.setValueAt("���", row, 4);
			}
			
			else {
				memberTable.setValueAt("�÷�Ƽ��", row, 4);
			}
			
			memberNumberTextField.setText("");
			memberNameTextField.setText("");
			memberPhoneTextField.setText("");
			memberMileageTextField.setText("");
		}
		
	}

}