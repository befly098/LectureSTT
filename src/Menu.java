package SitDown;

import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Vector;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

import java.awt.event.WindowAdapter;

public class Menu implements ActionListener {
	
	Connection con = null;
	
	String className = "org.gjt.mm.mysql.Driver";
	String url = "jdbc:mysql://localhost:3306/SitDown?useSSL=false&useUnicode=true&characterEncoding=euckr";
	String user = "root";
	String passwd = "123456";
	String sql = "INSERT INTO Storage(Iname, Iprice, Iseller, Icontact, Iquant, Iorder) VALUES";
	Statement stmt = null;
	PreparedStatement pstmt = null;
	
	JPanel menuPanel;
	static JTable menuTable;
	
	JPanel menuTablePanel; // ���̺�+��ư ���� �г� 
	JPanel menuInfoPanel; // �޴� ���� ���� �г� (�׸��� 3,4)
	JPanel menuTableBtnPanel;  // �ؽ�Ʈ �ʵ�� ��ư ���� �г� 
	
	JLabel menuNameInfo2;
	JLabel menuPriceInfo2;
	JLabel menuOriginalPriceInfo2;
	JLabel menuIngredientInfo2;
	// �޴� ���� ���̺� �� �� 
	
	static DefaultTableModel menuTableModel;
	
	// ���⼭���� 
	static DefaultTableModel unvisibleTableModel;
	static JTable unvisibleTable;
	Frame addDialog;
	JTextField menuFrameName;
	JTextField menuFramePrice;
	JTextField menuFrameOriginalPrice;
	JTextArea menuFrameIngredients;
	JPanel dialogPanel;
	
	JLabel menuNameInfo;
	JLabel menuPriceInfo;
	JLabel menuOriginalPriceInfo;
	JLabel menuIngredientInfo;
	// ������� �޴� �߰� ���̾�α׿� ����� ���� 
	
	// ���⼭���� 
	Frame editDialog;
	JTextField menuFrameName3;
	JTextField menuFramePrice3;
	JTextField menuFrameOriginalPrice3;
	JTextArea menuFrameIngredients3;
	JPanel editPanel;
	JLabel menuNameInfo3;
	JLabel menuPriceInfo3;
	JLabel menuOriginalPriceInfo3;
	JLabel menuIngredientInfo3;
	// ������� �޴� ���� ���̾�α׿� ����� ���� 
	
	JTextField menuName;
	// �޴� ���̺� �� �ؽ�Ʈ�ʵ� 
	
	JTextField menuInfoName;
	JTextField menuInfoPrice;
	JTextField menuInfoOriginalPrice;
	JTextArea menuInfoIngredients;
	// �޴� ���� ���̺� �� �ؽ�Ʈ �ʵ� 
	
	JButton menuAdd;
	JButton menuEdit;
	JButton menuDelete;
	JButton menuInfo;
	JButton menuFrameAdd;
	JButton menuFrameEdit;
	// �޴� �߰� ���� ��ư 
	
	public Menu() {
		
		menuPanel = new JPanel();
		menuPanel.setLayout(new BorderLayout());
		menuTablePanel = new JPanel();
		menuTablePanel.setLayout(new BorderLayout());
		menuTableBtnPanel = new JPanel();
		menuTableBtnPanel.setLayout(new GridLayout(1,2));
		menuInfoPanel = new JPanel();
		menuInfoPanel.setLayout(new GridLayout(5,2));
		
		//
		unvisibleTable = new JTable();
		// �ؽ�Ʈ�ʵ忡 ���� �Ⱥ��̴� ���̺� 
		
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("MenuTable"));
			ObjectInputStream inputStream2 = new ObjectInputStream(new FileInputStream("unvisibleMenuTable"));
			
			Vector rowData = (Vector)inputStream.readObject();
			Vector columnNames = (Vector)inputStream.readObject();
			if(rowData.isEmpty()) {
				Vector<String> userColumn = new Vector<> ();
				userColumn.addElement("�̸�");
				menuTableModel = new DefaultTableModel(userColumn, 0) {
					@Override
					public boolean isCellEditable(int i, int c) {
						return false;
					}
				};
			}
			
			else {
				menuTableModel = new DefaultTableModel() {
					@Override
					public boolean isCellEditable(int i, int c) {
						return false;
					}
				};
				menuTableModel.setDataVector(rowData, columnNames);
			}
			
			Vector rowData2 = (Vector)inputStream2.readObject();
			Vector columnNames2 = (Vector)inputStream2.readObject();
			if(rowData2.isEmpty()) {
				Vector<String> userColumnUnvisible = new Vector<> ();
				userColumnUnvisible.addElement("����");
				userColumnUnvisible.addElement("����ܰ�");
				userColumnUnvisible.addElement("���");
				unvisibleTableModel = new DefaultTableModel(userColumnUnvisible, 0);
			}
			
			else {
				unvisibleTableModel = new DefaultTableModel();
				unvisibleTableModel.setDataVector(rowData2, columnNames2);
			}
			
			inputStream.close();
			inputStream2.close();
			
		} catch(ClassNotFoundException | IOException e) {
			
			Vector<String> userColumn = new Vector<> ();
			userColumn.addElement("�̸�");
			menuTableModel = new DefaultTableModel(userColumn, 0);
			
			Vector<String> userColumnUnvisible = new Vector<> ();
			userColumnUnvisible.addElement("����");
			userColumnUnvisible.addElement("����ܰ�");
			userColumnUnvisible.addElement("���");
			unvisibleTableModel = new DefaultTableModel(userColumnUnvisible, 0);

			e.printStackTrace();
		}

		menuTable = new JTable(menuTableModel);
		JScrollPane scrollpane = new JScrollPane(menuTable);
		menuTablePanel.add(scrollpane, BorderLayout.CENTER);
		unvisibleTable = new JTable(unvisibleTableModel);
		
		menuTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		menuTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = menuTable.getSelectedRow();
					String data = (String)menuTable.getValueAt(row, 0);
					menuInfoName.setText(data);
					data = String.valueOf(unvisibleTable.getValueAt(row,  0));
					menuInfoPrice.setText(data);
					data = String.valueOf(unvisibleTable.getValueAt(row,  1));
					menuInfoOriginalPrice.setText(data);
					data = String.valueOf(unvisibleTable.getValueAt(row, 2));
					menuInfoIngredients.setText(data);
					
				}
			}
		});
		
		menuNameInfo = new JLabel("�̸�");
		menuNameInfo.setHorizontalAlignment(JLabel.CENTER);
		menuPriceInfo = new JLabel("����");
		menuPriceInfo.setHorizontalAlignment(JLabel.CENTER);
		menuOriginalPriceInfo = new JLabel("����ܰ�");
		menuOriginalPriceInfo.setHorizontalAlignment(JLabel.CENTER);
		menuIngredientInfo = new JLabel("���� ���");
		menuIngredientInfo.setHorizontalAlignment(JLabel.CENTER);
		
		menuNameInfo2 = new JLabel("�̸�");
		menuNameInfo2.setHorizontalAlignment(JLabel.CENTER);
		menuPriceInfo2 = new JLabel("����");
		menuPriceInfo2.setHorizontalAlignment(JLabel.CENTER);
		menuOriginalPriceInfo2 = new JLabel("����ܰ�");
		menuOriginalPriceInfo2.setHorizontalAlignment(JLabel.CENTER);
		menuIngredientInfo2 = new JLabel("���� ���");
		menuIngredientInfo2.setHorizontalAlignment(JLabel.CENTER);
		
		menuNameInfo3 = new JLabel("�̸�");
		menuNameInfo3.setHorizontalAlignment(JLabel.CENTER);
		menuPriceInfo3 = new JLabel("����");
		menuPriceInfo3.setHorizontalAlignment(JLabel.CENTER);
		menuOriginalPriceInfo3 = new JLabel("����ܰ�");
		menuOriginalPriceInfo3.setHorizontalAlignment(JLabel.CENTER);
		menuIngredientInfo3 = new JLabel("���� ���");
		menuIngredientInfo3.setHorizontalAlignment(JLabel.CENTER);
		
		menuAdd = new JButton("�߰�");
		menuAdd.addActionListener(this);
		menuEdit = new JButton("����");
		menuEdit.addActionListener(this);
		menuDelete = new JButton("����");
		menuDelete.addActionListener(this);
		
		menuTableBtnPanel.add(menuAdd);
		menuTablePanel.add(menuTableBtnPanel, BorderLayout.SOUTH);
		
		menuInfoName = new JTextField(10);
		menuInfoPrice = new JTextField(10);
		menuInfoOriginalPrice = new JTextField(10);
		menuInfoIngredients = new JTextArea(20,10);
		JScrollPane menuinfoScrollPane=new JScrollPane(menuInfoIngredients);
		
		
		menuInfoPanel.add(menuNameInfo2);
		menuInfoPanel.add(menuInfoName);
		menuInfoPanel.add(menuPriceInfo2);
		menuInfoPanel.add(menuInfoPrice);
		menuInfoPanel.add(menuOriginalPriceInfo2);
		menuInfoPanel.add(menuInfoOriginalPrice);
		menuInfoPanel.add(menuIngredientInfo2);
		menuInfoPanel.add(menuinfoScrollPane);
		menuInfoPanel.add(menuEdit);
		menuInfoPanel.add(menuDelete);
		
		menuPanel.add(menuInfoPanel, BorderLayout.EAST);
		menuPanel.add(menuTablePanel);
	}

	void FrameOpen() {
		
		addDialog = new Frame("�޴� ���� �Է�");
		addDialog.setSize(300, 300);
		addDialog.setLayout(new BorderLayout());
		addDialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				addDialog.setVisible(false);
			}
		});
		
		menuFrameAdd = new JButton("���� �߰�");
		menuFrameAdd.addActionListener(this);
		
		menuFrameName = new JTextField(10);
		menuFramePrice = new JTextField(10);
		menuFrameOriginalPrice = new JTextField(10);
		menuFrameIngredients = new JTextArea(20,10); //added
		JScrollPane scrollPane=new JScrollPane(menuFrameIngredients);
		
		dialogPanel = new JPanel();
		dialogPanel.setLayout(new GridLayout(4,2));
		
		dialogPanel.add(menuNameInfo);
		dialogPanel.add(menuFrameName);
		dialogPanel.add(menuPriceInfo);
		dialogPanel.add(menuFramePrice);
		dialogPanel.add(menuOriginalPriceInfo);
		dialogPanel.add(menuFrameOriginalPrice);
		dialogPanel.add(menuIngredientInfo);
		dialogPanel.add(scrollPane);
		
		addDialog.add(menuFrameAdd, BorderLayout.SOUTH);
		addDialog.add(dialogPanel);
		addDialog.setVisible(true);
	}
	
	void FrameEdit() {
		
		editDialog = new Frame("�޴� ���� �Է�");
		editDialog.setSize(300, 300);
		editDialog.setLayout(new BorderLayout());
		editDialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				editDialog.setVisible(false);
			}
		});
		
		menuFrameEdit = new JButton("���� �Ϸ�");
		menuFrameEdit.addActionListener(this);
		
		menuFrameName3 = new JTextField(10);
		menuFramePrice3 = new JTextField(10);
		menuFrameOriginalPrice3 = new JTextField(10);
		menuFrameIngredients3 = new JTextArea(20,10); //added
		JScrollPane scrollPane3=new JScrollPane(menuFrameIngredients3);
		
		editPanel = new JPanel();
		editPanel.setLayout(new GridLayout(4,2));
		
		editPanel.add(menuNameInfo3);
		editPanel.add(menuFrameName3);
		editPanel.add(menuPriceInfo3);
		editPanel.add(menuFramePrice3);
		editPanel.add(menuOriginalPriceInfo3);
		editPanel.add(menuFrameOriginalPrice3);
		editPanel.add(menuIngredientInfo3);
		editPanel.add(scrollPane3);
		
		int row = menuTable.getSelectedRow();
		String data = (String) menuTable.getValueAt(row, 0);
		menuFrameName3.setText(data);
		data = (String) unvisibleTable.getValueAt(row, 0);
		menuFramePrice3.setText(data);
		data = (String) unvisibleTable.getValueAt(row, 1);
		menuFrameOriginalPrice3.setText(data);
		data = (String) unvisibleTable.getValueAt(row, 2);
		menuFrameIngredients3.setText(data);
		
		editDialog.add(menuFrameEdit, BorderLayout.SOUTH);
		editDialog.add(editPanel);
		editDialog.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("�߰�")) {
			FrameOpen();
		}
		
		else if(actionCommand.equals("���� �߰�")) {
			menuTableModel.addRow(new Object[] {menuFrameName.getText()});
			unvisibleTableModel.addRow(new Object[] {menuFramePrice.getText(), menuFrameOriginalPrice.getText(), menuFrameIngredients.getText()});
			
			sql = "INSERT INTO Menu(Fname, Fprice, Fbase) VALUES";
			sql += "(\"" + menuFrameName.getText() + "\"," + menuFramePrice.getText() + "," + menuFrameOriginalPrice.getText()
			+ ");";
			
			try {
				Class.forName(className);
				con = DriverManager.getConnection(url, user, passwd); 
				stmt = (Statement) con.createStatement();
				stmt.executeUpdate(sql);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			String ingredients = menuFrameIngredients.getText();
			String[] changeIngredients = ingredients.split("\\n");
			
			for(int i=0; i<changeIngredients.length; i++) {
				String[] changeIngredients2 = changeIngredients[i].split(" ");
				
				sql = "INSERT INTO Recipe(Food, Ing, Needs) VALUES";
				sql += "(\"" + menuFrameName.getText() + "\"," + "\"" + changeIngredients2[0] + "\"" + "," + changeIngredients2[1] + ");";
			
				try {
					Class.forName(className);
					con = DriverManager.getConnection(url, user, passwd); 
					stmt = (Statement) con.createStatement();
					stmt.executeUpdate(sql);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			addDialog.setVisible(false);
		}
		
		else if(actionCommand.equals("����")) {
			FrameEdit();
		}
		
		else if(actionCommand.equals("����")) {
			int row = menuTable.getSelectedRow();
			
			String DBName = String.valueOf(menuTable.getValueAt(row, 0));
			sql = "DELETE FROM Recipe WHERE Food=\"" + DBName + "\";";
			try {
				Class.forName(className);
				con = DriverManager.getConnection(url, user, passwd);
				stmt = (Statement) con.createStatement();
				stmt.executeUpdate(sql);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			sql = "DELETE FROM Menu WHERE Fname=\"" + DBName + "\";";
			try {
				Class.forName(className);
				con = DriverManager.getConnection(url, user, passwd);
				stmt = (Statement) con.createStatement();
				stmt.executeUpdate(sql);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			
			menuTableModel.removeRow(row);
			unvisibleTableModel.removeRow(row);
			menuInfoName.setText("");
			menuInfoPrice.setText("");
			menuInfoOriginalPrice.setText("");
			menuInfoIngredients.setText("");
		}
		
		else if(actionCommand.equals("���� �Ϸ�")) {
			int row = menuTable.getSelectedRow();
			menuTable.setValueAt(menuFrameName3.getText(), row, 0);
			unvisibleTable.setValueAt(menuFramePrice3.getText(), row, 0);
			unvisibleTable.setValueAt(menuFrameOriginalPrice3.getText(), row, 1);
			unvisibleTable.setValueAt(menuFrameIngredients3.getText(), row, 2);
			
			String data = (String) menuTable.getValueAt(row, 0);
			menuInfoName.setText(data);
			data = (String) unvisibleTable.getValueAt(row, 0);
			menuInfoPrice.setText(data);
			data = (String) unvisibleTable.getValueAt(row, 1);
			menuInfoOriginalPrice.setText(data);
			data = (String) unvisibleTable.getValueAt(row, 2);
			System.out.println(data);
			menuInfoIngredients.setText(data);
			
			editDialog.setVisible(false);
		}
		
	}

}