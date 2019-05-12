package SitDown;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class Final extends JFrame implements ActionListener {
	
	Date today = new Date();
	SimpleDateFormat date = new SimpleDateFormat("yyyy�� MM�� dd��");
	SimpleDateFormat day = new SimpleDateFormat("dd");
	
	JPanel datePanel;
	static JLabel dateLabel;
	JButton finishBtn; // ��ܹٿ� �� ���� ��ư 
	// ��¥ �����ϴ� �κ�, ��¥ ���� ����, datePanel �� �󺧰� ��ư ���̷��� ����
	
	JPanel moneyPanel;
	static JLabel priceLabel;
	JButton quitBtn; // �ϴܹٿ� �� ���� ��ư
	// ���� �����ϴ� �κ�, moneyPanel �� �󺧰� ��ư ���̷��� ���� 
	
	static JTabbedPane tabPanel; // ī�װ� �� tab
	
	public static int today_money;
	public static int total_money;
	//  today : ���� ����, total : ��ü ���� 
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	// ������ ũ�⿡ �̿��� ���� : ���̿� �ʺ�
	
	private Member member;
	private Menu menu;
	private Storage storage;
	private Table table;
	
	public static void main(String[] args) { // �����Լ� 
		Final gui = new Final();
		gui.setVisible(true);
	}

	public Final() { // constructor
		super("SitDown");
		setSize(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		
		member = new Member();
		menu = new Menu();
		storage = new Storage();
		table = new Table();
		
		upper_bar();
		under_bar();
		
		tabPanel = new JTabbedPane();

		tabPanel.addTab("���̺�", table.tablePanel);
		tabPanel.addTab("�޴�", menu.menuPanel);
		tabPanel.addTab("â��", storage.storagePanel);
		tabPanel.addTab("ȸ��", member.memberPanel);
		
		add(tabPanel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	void upper_bar() {	// ������� ��ܹ� �߰� ���� (��¥, ������ư) 
		
		datePanel = new JPanel();
		datePanel.setBackground(Color.WHITE);
		datePanel.setLayout(new BorderLayout());
		
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("date_money"));
			Date day = (Date) inputStream.readObject();	
			today = day;
		} catch (IOException | ClassNotFoundException e) {
			today = new Date();
		}
		
		dateLabel = new JLabel(date.format(today));
		dateLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		datePanel.add(dateLabel, BorderLayout.WEST);
		
		finishBtn = new JButton("����");
		finishBtn.addActionListener(this);
		datePanel.add(finishBtn, BorderLayout.EAST);
		add(datePanel, BorderLayout.NORTH);
		
	}
	
	void under_bar() {  // ������� �ϴܹ� �߰� ���� (����, �����ư)
		
		moneyPanel = new JPanel();
		moneyPanel.setBackground(Color.WHITE);
		moneyPanel.setLayout(new BorderLayout());
		
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("date_money"));
			Date day = (Date) inputStream.readObject();
			int money = inputStream.readInt();
			total_money = money;
		} catch (IOException | ClassNotFoundException e) {
			total_money = 0;
		}

		priceLabel = new JLabel("���� ���� : " + today_money + "     ��ü �ܰ� : " + total_money);
		priceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		moneyPanel.add(priceLabel, BorderLayout.WEST);
		
		quitBtn = new JButton("����");
		quitBtn.addActionListener(this);
		moneyPanel.add(quitBtn, BorderLayout.EAST);	
		add(moneyPanel, BorderLayout.SOUTH);

	}
	
	private Vector<String> getColumnNames(DefaultTableModel myJTable) {
        Vector<String> columnNames = new Vector<String>();
		for (int i = 0; i < myJTable.getColumnCount(); i++)
            columnNames.add(myJTable.getColumnName(i) );
            return columnNames;
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand(); // ��ư �̸� �޾ƿ��� String
		
		if(actionCommand.compareTo("����") == 0) { // ���� ��ư 
			int check = 0;
			for (int i = 0; i < Table.T.length; i++) {
				if (Table.T[i].getRowCount() <= 0)
					check++;
			}
			
			if(check != Table.T.length) {
				Frame errorBox = new Frame("Error");
				errorBox.setSize(300, 300);
				errorBox.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent windowEvent) {
						errorBox.setVisible(false);
					}
				});
				JLabel errorMessage = new JLabel("����� �ȵ� ���̺��� �ֽ��ϴ�!");
				errorMessage.setHorizontalAlignment(JLabel.CENTER);
				errorBox.add(errorMessage);
				errorBox.setVisible(true);
				return;
			}
			
			if(today_money > 0) {
				total_money += today_money;
			}
			
			try {
				ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("MemberTable"));
				ObjectOutputStream outputStream2 = new ObjectOutputStream(new FileOutputStream("MenuTable"));
				ObjectOutputStream outputStream3 = new ObjectOutputStream(new FileOutputStream("unvisibleMenuTable"));
				ObjectOutputStream outputStream4 = new ObjectOutputStream(new FileOutputStream("StorageTable"));
				ObjectOutputStream outputStream5 = new ObjectOutputStream(new FileOutputStream("unvisibleTable"));
				ObjectOutputStream outputStream6 = new ObjectOutputStream(new FileOutputStream("WorkerTable"));
				ObjectOutputStream outputStream_DM = new ObjectOutputStream(new FileOutputStream("date_money"));
				
				outputStream.writeObject(Member.memberTableModel.getDataVector());
				outputStream.writeObject(getColumnNames(Member.memberTableModel));
				outputStream2.writeObject(Menu.menuTableModel.getDataVector());
				outputStream2.writeObject(getColumnNames(Menu.menuTableModel));
				outputStream3.writeObject(Menu.unvisibleTableModel.getDataVector());
				outputStream3.writeObject(getColumnNames(Menu.unvisibleTableModel));
				outputStream4.writeObject(Storage.storageTableModel.getDataVector());
				outputStream4.writeObject(getColumnNames(Storage.storageTableModel));
				outputStream5.writeObject(Storage.unvisibleTableModel.getDataVector());
				outputStream5.writeObject(getColumnNames(Storage.unvisibleTableModel));
				
				outputStream_DM.writeObject(today);
				outputStream_DM.writeInt(total_money);
				
				outputStream.close();
				outputStream2.close();
				outputStream3.close();
				outputStream4.close();
				outputStream5.close();
				outputStream6.close();
				outputStream_DM.close();
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			System.exit(0);
		}
		else if(actionCommand.equals("����")) { // ���� ��ư -> ��¥ �ٲ��
			int check = 0;
			for (int i = 0; i < Table.T.length; i++) {
				if (Table.T[i].getRowCount() <= 0)
					check++;
			}
			
			if(check != Table.T.length) {
				Frame errorBox = new Frame("Error");
				errorBox.setSize(300, 300);
				errorBox.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent windowEvent) {
						errorBox.setVisible(false);
					}
				});
				JLabel errorMessage = new JLabel("����� �ȵ� ���̺��� �ֽ��ϴ�!");
				errorMessage.setHorizontalAlignment(JLabel.CENTER);
				errorBox.add(errorMessage);
				errorBox.setVisible(true);
				return;
			}
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			cal.add(Calendar.DATE, 1);
			
			String strDay = day.format(cal.getTime());
			int get_day = Integer.parseInt(strDay);
			
			today = new Date(cal.getTimeInMillis());
			String strDate = date.format(cal.getTime());
			dateLabel.setText(strDate);
			
			total_money += today_money;
			today_money = 0;
			
			for(int i = 0; i<Storage.storageTable.getRowCount();i++){
				int data = 0;
				
				try {
					data = Integer.parseInt((String)Storage.storageTable.getValueAt(i, 2));
				}
				catch(ClassCastException e1) {
					data = (int)Storage.storageTable.getValueAt(i, 2);
				}
				
				if(data != 0) {
					int k = Integer.parseInt((String) Storage.storageTable.getValueAt(i, 1));
					int price = Integer.parseInt((String)Storage.storageTable.getValueAt(i, 3));
					price *= data;
					k += data;
					Storage.storageTable.setValueAt(0, i, 2);
					Storage.storageTable.setValueAt(Integer.toString(k), i, 1);
					total_money -= price;
				}
			
			}
			
			strDate = "���� ���� : " + today_money + "     ��ü �ܰ� : " + total_money;
			priceLabel.setText(strDate);
			
			
		}
		
	}
}
