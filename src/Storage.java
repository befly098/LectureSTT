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
import java.awt.event.WindowAdapter;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JOptionPane;

public class Storage implements ActionListener{

	Connection con = null;
	
	String className = "org.gjt.mm.mysql.Driver";
	String url = "jdbc:mysql://localhost:3306/SitDown?useSSL=false&useUnicode=true&characterEncoding=euckr";
	AES128 aes = new AES128();
	String key = "0123456789abcdef";
	String user = "root";
	String encode_user = aes.encrypt(user, key);
	String passwd = "123456";
	String encode_passwd = aes.encrypt(passwd, key);
	String sql = "INSERT INTO Storage(Iname, Iprice, Iseller, Icontact, Iquant, Iorder) VALUES";
	Statement stmt = null;
	PreparedStatement pstmt = null;
	
	JPanel storagePanel;
	
	static JTable storageTable;
	static JTable unvisibleTable;
	
	JPanel storageBtnPanel;
	JPanel storageInfoPanel;
	JPanel storageTablePanel;
	JPanel dialogPanel;
	
	JTextField storageName;
	JTextField storagePrice;
	JTextField storageSelling;
	JTextField storagePhone;
	JTextField storageQuantity;
	JTextField storageOrderquantity;
	
	JTextField storageTablename;
	JTextField storageTableprice;
	JTextField storageTableselling;
	JTextField storageTablephone;
	JTextField storageTablequantity;
	JTextField storageTableorderquantity;
	
	JButton storageAdd;
	JButton storageDelete;
	JButton storageOrder;
	JButton storageCancel;
	JButton storageInfo;
	JButton storageFrameAdd;
	
	JLabel nameStorage;
	JLabel namePrice;
	JLabel nameSelling;
	JLabel namePhone;
	JLabel nameQuantity;
	JLabel nameOrderquantity;
	
	JLabel frameStorage;
	JLabel framePrice;
	JLabel frameSelling;
	JLabel framePhone;
	JLabel frameQuantity;
	JLabel frameOrderquantity;
	
	int orderquantity; // �ֹ��� ���� ���� 
	Frame orderDialog; // �ֹ��� ���� ���̾�α�  
	
	static DefaultTableModel storageTableModel;
	static DefaultTableModel unvisibleTableModel;
	
	Frame addDialog;
	
	public static final String name = "�̸�";
	public static final String inventory = "���";
	public static final String order = "�ֹ�";
	public static final String price = "����";
	public static final String saleAgent = "�Ǹ�ó";
	public static final String phoneNumber = "����ó";
	
	public Storage() throws IOException {
		
		storagePanel = new JPanel();
		storagePanel.setLayout(new BorderLayout());
		storageTablePanel = new JPanel();
		storageTablePanel.setLayout(new BorderLayout());
		storageBtnPanel = new JPanel();
		storageBtnPanel.setLayout(new GridLayout(1,3));
		storageInfoPanel = new JPanel();
		storageInfoPanel.setLayout(new GridLayout(7,2));
		
		
		// ������ �� �Է¹޾Ƽ� ��� ���̺� �����ְ� ���� ���� �κ��� �� ���̴� ���̺� ���� ���� 
		ObjectInputStream inputStream = null;
		ObjectInputStream inputStream2 = null;
		
		try {
			inputStream = new ObjectInputStream(new FileInputStream("StorageTable"));
			inputStream2 = new ObjectInputStream(new FileInputStream("unvisibleTable"));
			
			Vector rowData = (Vector)inputStream.readObject();
			Vector columnNames = (Vector)inputStream.readObject();
			
			if(rowData.isEmpty()) {
				Vector<String> userColumn = new Vector<> ();
				userColumn.addElement(name);
				userColumn.addElement(inventory);
				userColumn.addElement(order);
				userColumn.addElement(price);
				storageTableModel = new DefaultTableModel(userColumn, 0){
					@Override
					public boolean isCellEditable(int i, int c) {
						return false;
					}
				};
			}
			else {
				storageTableModel = new DefaultTableModel(){
					@Override
					public boolean isCellEditable(int i, int c) {
						return false;
					}
				};
				storageTableModel.setDataVector(rowData, columnNames);
			}
			
			Vector rowData2 = (Vector)inputStream2.readObject();
			Vector columnNames2 = (Vector)inputStream2.readObject();
			
			if(rowData2.isEmpty()) {
				Vector<String> unvisibleUserColumn = new Vector<> ();
				unvisibleUserColumn.addElement(saleAgent);
				unvisibleUserColumn.addElement(phoneNumber);
				unvisibleTableModel = new DefaultTableModel(unvisibleUserColumn, 0);
			}
			else {
				unvisibleTableModel = new DefaultTableModel();
				unvisibleTableModel.setDataVector(rowData2, columnNames2);
			}
			
			inputStream.close();
			inputStream2.close();
			
		} catch(ClassNotFoundException | IOException e) {
			Vector<String> userColumn = new 
					Vector<> ();
			userColumn.addElement(name);
			userColumn.addElement(inventory);
			userColumn.addElement(order);
			userColumn.addElement(price);
			storageTableModel = new DefaultTableModel(userColumn, 0);
			
			Vector<String> unvisibleUserColumn = new Vector<> ();
			unvisibleUserColumn.addElement(saleAgent);
			unvisibleUserColumn.addElement(phoneNumber);
			unvisibleTableModel = new DefaultTableModel(unvisibleUserColumn, 0);
			
			e.printStackTrace();
		} finally {
			inputStream.close();
			inputStream2.close();
		}
		
		storageTable = new JTable(storageTableModel);
		JScrollPane scrollpane = new JScrollPane(storageTable);
		storageTablePanel.add(scrollpane, BorderLayout.CENTER);
		unvisibleTable = new JTable(unvisibleTableModel);
		
		storageTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		storageTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = storageTable.getSelectedRow();
					String data = (String) storageTable.getValueAt(row, 0);
					storageName.setText(data);
					data = String.valueOf(storageTable.getValueAt(row, 1));
					storageQuantity.setText(data);
					data = String.valueOf(storageTable.getValueAt(row, 2));
					storageOrderquantity.setText(data);
					data = (String) storageTable.getValueAt(row, 3);
					storagePrice.setText(data);
					data = (String) unvisibleTable.getValueAt(row, 0);
					storageSelling.setText(data);
					data = (String) unvisibleTable.getValueAt(row, 1);
					storagePhone.setText(data);
				}
			}
		});
		
		storageName = new JTextField(10);
		storagePrice = new JTextField(10);
		storageSelling = new JTextField(10);
		storagePhone = new JTextField(10);
		storageQuantity = new JTextField(10);
		storageOrderquantity = new JTextField(10);
		
		nameStorage = new JLabel(name);
		nameStorage.setHorizontalAlignment(JLabel.CENTER);
		namePrice = new JLabel(price);
		namePrice.setHorizontalAlignment(JLabel.CENTER);
		nameSelling = new JLabel(saleAgent);
		nameSelling.setHorizontalAlignment(JLabel.CENTER);
		namePhone = new JLabel(phoneNumber);
		namePhone.setHorizontalAlignment(JLabel.CENTER);
		nameQuantity = new JLabel("����");
		nameQuantity.setHorizontalAlignment(JLabel.CENTER);
		nameOrderquantity = new JLabel("�ֹ���");
		nameOrderquantity.setHorizontalAlignment(JLabel.CENTER);
		
		storageAdd = new JButton("�߰�");
		storageAdd.addActionListener(this);
		storageDelete = new JButton("����");
		storageDelete.addActionListener(this);
		storageOrder = new JButton(order);
		storageOrder.addActionListener(this);
		storageCancel = new JButton("�ֹ� ���");
		storageCancel.addActionListener(this);

		storageBtnPanel.add(storageAdd);
		storageBtnPanel.add(storageDelete);
		
		storageTablePanel.add(storageBtnPanel, BorderLayout.SOUTH);
		
		
		storageInfoPanel.add(nameStorage);
		storageInfoPanel.add(storageName);
		
		storageInfoPanel.add(namePrice);
		storageInfoPanel.add(storagePrice);
		
		storageInfoPanel.add(nameSelling);
		storageInfoPanel.add(storageSelling);
		
		storageInfoPanel.add(namePhone);
		storageInfoPanel.add(storagePhone);
		
		storageInfoPanel.add(nameQuantity);
		storageInfoPanel.add(storageQuantity);
		
		storageInfoPanel.add(nameOrderquantity);
		storageInfoPanel.add(storageOrderquantity);
		
		storageInfoPanel.add(storageOrder);
		storageInfoPanel.add(storageCancel);
	
		storagePanel.add(storageInfoPanel, BorderLayout.EAST);
		storagePanel.add(storageTablePanel);
	}
	
	void frameOrderBox() {
		orderDialog = new Frame("�ֹ� ���� �Է�");
		orderDialog.setSize(300, 300);
		orderDialog.setLayout(new GridLayout(3,1));
		orderDialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				orderDialog.setVisible(false);
			}
		});
		
		JTextField orderInput = new JTextField(15); // �ֹ��� ���� �ؽ�Ʈ �ʵ� 
		JLabel order = new JLabel("�ֹ��� ������ �Է��ϼ���."); // ���� ���� ��
		order.setHorizontalAlignment(JLabel.CENTER);
		
		JButton orderFrameAdd = new JButton("�ֹ� �Ϸ�");
		orderFrameAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = storageTable.getSelectedRow();
				String s1 = (String)storageTable.getValueAt(x, 0);
				int orderQ = Integer.parseInt(String.valueOf(storageTable.getValueAt(x, 2)));
				orderQ += Integer.valueOf(orderInput.getText());
				storageTable.setValueAt(orderQ, x, 2);
				storageOrderquantity.setText(orderInput.getText());
				orderDialog.setVisible(false);
				orderInput.setText("");
				
				sql = "UPDATE Storage SET Iorder =" + orderQ + " WHERE Iname=" + s1 +";";
						try {
							Class.forName(className);
							con = DriverManager.getConnection(url, aes.decrypt(encode_user, key), aes.decrypt(encode_passwd, key));
							stmt = (Statement) con.createStatement();
							stmt.executeUpdate(sql);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
			}
		});
		
		orderDialog.add(order);
		orderDialog.add(orderInput);
		orderDialog.add(orderFrameAdd);
		orderDialog.setVisible(true);
	}
	void frameOpen() { // �߰� ��ư ������ �۵��� �Է��ϴ� ���̾�α� ������ ���� 
		
		addDialog = new Frame("��� ���� �Է�");
		addDialog.setSize(300, 300);
		addDialog.setLayout(new BorderLayout());
		addDialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				addDialog.setVisible(false);
			}
		});
		
		storageFrameAdd = new JButton("���� �߰�");
		storageFrameAdd.addActionListener(this);
		
		storageTablename = new JTextField(10);
		storageTableprice = new JTextField(10);
		storageTableorderquantity = new JTextField(10);
		storageTableselling = new JTextField(10);
		storageTablephone = new JTextField(10);
		
		frameStorage = new JLabel(name);
		frameStorage.setHorizontalAlignment(JLabel.CENTER);
		frameOrderquantity = new JLabel(inventory);
		frameOrderquantity.setHorizontalAlignment(JLabel.CENTER);
		framePrice = new JLabel(price);
		framePrice.setHorizontalAlignment(JLabel.CENTER);
		frameSelling = new JLabel(saleAgent);
		frameSelling.setHorizontalAlignment(JLabel.CENTER);
		framePhone = new JLabel(phoneNumber);
		framePhone.setHorizontalAlignment(JLabel.CENTER);
		
		dialogPanel = new JPanel();
		dialogPanel.setLayout(new GridLayout(5,2));
		
		dialogPanel.add(frameStorage);
		dialogPanel.add(storageTablename);
		dialogPanel.add(frameOrderquantity );
		dialogPanel.add(storageTableorderquantity);
		dialogPanel.add(framePrice);
		dialogPanel.add(storageTableprice);
		dialogPanel.add(frameSelling);
		dialogPanel.add(storageTableselling);
		dialogPanel.add(framePhone);
		dialogPanel.add(storageTablephone);
		
		addDialog.add(storageFrameAdd, BorderLayout.SOUTH);
		addDialog.add(dialogPanel);
		addDialog.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("�߰�")) {
			frameOpen();
		}
		
		else if(actionCommand.equals("���� �߰�")) {// ���̾�α׿� ���� �Է� �ϰ� ���̺� ����
			boolean check = false;
			int count = storageTable.getRowCount();
			for (int i = 0; i < count; i++) {
				String s1 = String.valueOf(storageTable.getValueAt(i,0));
				String s2 = String.valueOf(storageTable.getValueAt(i,3));
				String s3 = String.valueOf(unvisibleTable.getValueAt(i,0));
				String s4 = String.valueOf(unvisibleTable.getValueAt(i,1));
				
				if (s1.contentEquals(storageTablename.getText()) &&
						s2.contentEquals(storageTableprice.getText()) &&
						s3.contentEquals(storageTableselling.getText()) &&
						s4.contentEquals(storageTablephone.getText())) {
					
					int stockCount = Integer.parseInt(storageTableorderquantity.getText());
					int preStock = Integer.parseInt(String.valueOf(storageTable.getValueAt(i, 1))); 
					preStock += stockCount;
					storageTable.setValueAt(preStock, i, 1);
					JOptionPane.showMessageDialog(null, "�̹� â�� �ִ� ����Դϴ�\n �Է��� ��� ��ŭ ��ᰡ �߰��Ǿ����ϴ�.");
					check = true;
					addDialog.setVisible(false);
					
					sql = "UPDATE Storage SET Iquant =" + preStock + " WHERE Iname=" +
					s1 + " AND Iprice=" + s2 + " AND Iseller=" + s3 + " AND Icontact=" + s4 + ";";
					try {
						Class.forName(className);
						con = DriverManager.getConnection(url, aes.decrypt(encode_user, key), aes.decrypt(encode_passwd, key));
						stmt = (Statement) con.createStatement();
						stmt.executeUpdate(sql);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					break;
				}
				
				else if (s1.contentEquals(storageTablename.getText())) {
					JOptionPane.showMessageDialog(null, "������ �̸��� ��ᰡ �����մϴ�.\n ��� �̸��� �������ֽʽÿ�.");
					check = true;
					break;
				}
			}
			if (!(check)) {
				storageTableModel.addRow(new Object[] {storageTablename.getText(), storageTableorderquantity.getText(), 0, storageTableprice.getText()});
				unvisibleTableModel.addRow(new Object[] {storageTableselling.getText(), storageTablephone.getText()});
				addDialog.setVisible(false);
				
				sql = "INSERT INTO Storage(Iname, Iprice, Iseller, Icontact, Iquant, Iorder) VALUES";
				sql += "(\"" + storageTablename.getText() + "\"," + storageTableprice.getText() + ",\"" + storageTableselling.getText() + "\"," + 
				storageTablephone.getText() + "," +storageTableorderquantity.getText() + "," + "0);";
				
				try {
					Class.forName(className);
					con = DriverManager.getConnection(url, aes.decrypt(encode_user, key), aes.decrypt(encode_passwd, key));
					stmt = (Statement) con.createStatement();
					stmt.executeUpdate(sql);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		
		else if(actionCommand.equals("����")) {
			int row = storageTable.getSelectedRow();
			String iName = String.valueOf(storageTable.getValueAt(row, 0));
			sql = "DELETE FROM Storage WHERE Iname=\"" + iName + "\";";
			try {
				Class.forName(className);
				con = DriverManager.getConnection(url, aes.decrypt(encode_user, key), aes.decrypt(encode_passwd, key));
				stmt = (Statement) con.createStatement();
				stmt.executeUpdate(sql);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			storageTableModel.removeRow(row);
			unvisibleTableModel.removeRow(row);
			storageName.setText("");
			storageQuantity.setText("");
			storageOrderquantity.setText("");
			storagePrice.setText("");
			storageSelling.setText("");
			storagePhone.setText("");
		}
		
		else if(actionCommand.equals(order)) {
			frameOrderBox();
		}
		
		else if(actionCommand.equals("�ֹ� ���")) {
			int x = storageTable.getSelectedRow();
			storageTable.setValueAt(0, x, 2);
			storageOrderquantity.setText("0");
		}
	}
}