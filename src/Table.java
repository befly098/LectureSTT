import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Table implements ActionListener {

	JPanel tablePanel;

	JPanel table_num1;
	JPanel table_num2;
	JPanel table_num3;
	JPanel table_num4;
	JPanel table_num5;
	JPanel table_num6;
	// ���̺� + ��Ż �� ���� �г� 

	JButton table1_btn;
	JButton table2_btn;
	JButton table3_btn;
	JButton table4_btn;
	JButton table5_btn;
	JButton table6_btn;
	// ���̾�α׿� ����� ���̺� ��ư

	JTextField table1_total;
	JTextField table2_total;
	JTextField table3_total;
	JTextField table4_total;
	JTextField table5_total;
	JTextField table6_total;
	// ���̺� �Ѿ� ��� �ؽ�Ʈ �ʵ� 

	Frame table1;
	Frame table2;
	Frame table3;
	Frame table4;
	Frame table5;
	Frame table6;
	// �� ���̺�� ����� ���̾�α� ������ 

	static JTable T1;
	static JTable T2;
	static JTable T3;
	static JTable T4;
	static JTable T5;
	static JTable T6;
	// ���̾�α׿� ��� JTable

	DefaultTableModel model_T1;
	DefaultTableModel model_T2;
	DefaultTableModel model_T3;
	DefaultTableModel model_T4;
	DefaultTableModel model_T5;
	DefaultTableModel model_T6;
	// JTable�� ������ DefaultTableModel

	JScrollPane scrollpane;
	JScrollPane scrollpane2;
	JScrollPane scrollpane3;
	JScrollPane scrollpane4;
	JScrollPane scrollpane5;
	JScrollPane scrollpane6;
	// JTable ���� JScrollPane

	JComboBox add_order1;
	JComboBox add_order2;
	JComboBox add_order3;
	JComboBox add_order4;
	JComboBox add_order5;
	JComboBox add_order6;
	// �޴� ���� �ڽ�

	JComboBox member_info1;
	JComboBox member_info2;
	JComboBox member_info3;
	JComboBox member_info4;
	JComboBox member_info5;
	JComboBox member_info6;
	// ��� ���� �ڽ�

	public Table() {
		tablePanel = new JPanel();
		tablePanel.setLayout(new GridLayout(2,3));

		table_num1 = new JPanel();
		table_num1.setLayout(new BorderLayout());	
		table_num1.setBackground(Color.WHITE);
		table_num2 = new JPanel();
		table_num2.setLayout(new BorderLayout());	
		table_num2.setBackground(Color.WHITE);
		table_num3 = new JPanel();
		table_num3.setLayout(new BorderLayout());
		table_num3.setBackground(Color.WHITE);
		table_num4 = new JPanel();
		table_num4.setLayout(new BorderLayout());	
		table_num4.setBackground(Color.WHITE);
		table_num5 = new JPanel();
		table_num5.setLayout(new BorderLayout());	
		table_num5.setBackground(Color.WHITE);
		table_num6 = new JPanel();
		table_num6.setLayout(new BorderLayout());
		table_num6.setBackground(Color.WHITE);

		table1_btn = new JButton("���̺� 1");
		table2_btn = new JButton("���̺� 2");
		table3_btn = new JButton("���̺� 3");
		table4_btn = new JButton("���̺� 4");
		table5_btn = new JButton("���̺� 5");
		table6_btn = new JButton("���̺� 6");

		table1_total = new JTextField(10);
		table2_total = new JTextField(10);
		table3_total = new JTextField(10);
		table4_total = new JTextField(10);
		table5_total = new JTextField(10);
		table6_total = new JTextField(10);

		Vector<String> userColumn1 = new Vector<String> ();
		userColumn1.addElement("�̸�");
		userColumn1.addElement("����");
		model_T1 = new DefaultTableModel(userColumn1, 0);
		T1 = new JTable(model_T1);
		scrollpane = new JScrollPane(T1);

		Vector<String> userColumn2 = new Vector<String> ();
		userColumn2.addElement("�̸�");
		userColumn2.addElement("����");
		model_T2 = new DefaultTableModel(userColumn2, 0);
		T2 = new JTable(model_T2);
		scrollpane2 = new JScrollPane(T2);

		Vector<String> userColumn3 = new Vector<String> ();
		userColumn3.addElement("�̸�");
		userColumn3.addElement("����");
		model_T3 = new DefaultTableModel(userColumn3, 0);
		T3 = new JTable(model_T3);
		scrollpane3 = new JScrollPane(T3);

		Vector<String> userColumn4 = new Vector<String> ();
		userColumn4.addElement("�̸�");
		userColumn4.addElement("����");
		model_T4 = new DefaultTableModel(userColumn4, 0);
		T4 = new JTable(model_T4);
		scrollpane4 = new JScrollPane(T4);

		Vector<String> userColumn5 = new Vector<String> ();
		userColumn5.addElement("�̸�");
		userColumn5.addElement("����");
		model_T5 = new DefaultTableModel(userColumn5, 0);
		T5 = new JTable(model_T5);
		scrollpane5 = new JScrollPane(T5);

		Vector<String> userColumn6 = new Vector<String> ();
		userColumn6.addElement("�̸�");
		userColumn6.addElement("����");
		model_T6 = new DefaultTableModel(userColumn6, 0);
		T6 = new JTable(model_T6);
		scrollpane6 = new JScrollPane(T6);

		table_num1.add(table1_btn);
		table_num1.add(table1_total, BorderLayout.SOUTH);
		table_num2.add(table2_btn);
		table_num2.add(table2_total, BorderLayout.SOUTH);
		table_num3.add(table3_btn);
		table_num3.add(table3_total, BorderLayout.SOUTH);
		table_num4.add(table4_btn);
		table_num4.add(table4_total, BorderLayout.SOUTH);
		table_num5.add(table5_btn);
		table_num5.add(table5_total, BorderLayout.SOUTH);
		table_num6.add(table6_btn);
		table_num6.add(table6_total, BorderLayout.SOUTH);

		table1_btn.addActionListener(this);
		table2_btn.addActionListener(this);
		table3_btn.addActionListener(this);
		table4_btn.addActionListener(this);
		table5_btn.addActionListener(this);
		table6_btn.addActionListener(this);

		tablePanel.add(table_num1);
		tablePanel.add(table_num2);
		tablePanel.add(table_num3);
		tablePanel.add(table_num4);
		tablePanel.add(table_num5);
		tablePanel.add(table_num6);

	}

	void Frame_Open1() {
		JPanel BtnPanel = new JPanel(new GridLayout(1,3));
		table1 = new Frame("���̺� 1");
		table1.setSize(400, 400);
		table1.setLayout(new BorderLayout());
		table1.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				table_num1.setBackground(Color.LIGHT_GRAY);
				table1.setVisible(false);
			}
		});

		add_order1 = new JComboBox(getTableData(Menu.menuTable));

		int row = Member.memberTable.getRowCount();
		Object[] val = new Object[row];
		for (int i = 0; i < val.length; i++)
			val[i] = Member.memberTable.getValueAt(i,1);
		member_info1 = new JComboBox(val);
		member_info1.addItem("��ȸ��");


		JButton table1_add = new JButton("���̺� 1 �߰�");
		table1_add.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = add_order1.getSelectedIndex();
				model_T1.addRow(new Object[] {Menu.menuTable.getValueAt(x, 0), Menu.unvisibleTable.getValueAt(x, 0)});

				int data = 0;
				String total;
				for(int i=0; i<model_T1.getRowCount();i++) {
					data += Integer.parseInt((String)model_T1.getValueAt(i, 1));
				}
				total = String.valueOf(data);
				table1_total.setText(total);
			}
		});
		
		JButton table1_pay = new JButton("���̺� 1 ����");
		table1_pay.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				table_num1.setBackground(Color.WHITE);
				table1.setVisible(false);
				int data = Integer.parseInt(table1_total.getText());
				int x = member_info1.getSelectedIndex();
				if (x < Member.memberTable.getRowCount()) {
					String rating = (String)Member.memberTable.getValueAt(x, 4);

					if (rating.equals("�Ϲ�")) {
						int mileage = 0;
						try {
							mileage = (int)Member.memberTable.getValueAt(x, 3);
						}
						catch(ClassCastException e1) {
							mileage = Integer.parseInt((String)Member.memberTable.getValueAt(x, 3));
						}
						mileage += data*0.02;
						Member.memberTable.setValueAt(mileage, x, 3);
						Final.today_money += data * 0.98;
					}
					else if(rating.compareTo("���") == 0) {
						int mileage = 0;
						try {
							mileage = (int)Member.memberTable.getValueAt(x, 3);
						}
						catch(ClassCastException e1) {
							mileage = Integer.parseInt((String)Member.memberTable.getValueAt(x, 3));
						}
						mileage += data*0.02;
						Member.memberTable.setValueAt(mileage, x, 3);
						Final.today_money += data * 0.95;
					}
					else if(rating.equals("�÷�Ƽ��")){
						int mileage = 0;
						try {
							mileage = (int)Member.memberTable.getValueAt(x, 3);
						}
						catch(ClassCastException e1) {
							mileage = Integer.parseInt((String)Member.memberTable.getValueAt(x, 3));
						}
						mileage += data*0.02;
						Member.memberTable.setValueAt(mileage, x, 3);
						Final.today_money += data * 0.9;
					}}
				else {
					Final.today_money += data;
				}

				table1_total.setText("");
				DefaultTableModel model = (DefaultTableModel) T1.getModel();
				model.setNumRows(0);
				String str = "���� ���� : " + Final.today_money + "     ��ü �ܰ� : " + Final.total_money;
				Final.priceLabel.setText(str);

				if(Member.memberTable.getRowCount() != 0) {
					int mileage = (int) Member.memberTable.getValueAt(x, 3);
					if (mileage >= 500 && mileage < 1000)
						Member.memberTable.setValueAt("���", x, 4);
					else if(mileage > 1000) {
						Member.memberTable.setValueAt("�÷�Ƽ��",x, 4);
					}
					else {
						Member.memberTable.setValueAt("�Ϲ�", x, 4);
					}
				}
			}

		});

		BtnPanel.add(add_order1);
		BtnPanel.add(member_info1);
		BtnPanel.add(table1_add);
		BtnPanel.add(table1_pay);
		table1.add(BtnPanel, BorderLayout.SOUTH);
		table1.add(scrollpane);
		table1.setVisible(true);
	}

	void Frame_Open2() {
		JPanel BtnPanel = new JPanel(new GridLayout(1,2));
		table2 = new Frame("���̺� 2");
		table2.setSize(400, 400);
		table2.setLayout(new BorderLayout());
		table2.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				table_num2.setBackground(Color.LIGHT_GRAY);
				table2.setVisible(false);
			}
		});

		add_order2 = new JComboBox(getTableData(Menu.menuTable));

		int row = Member.memberTable.getRowCount();
		Object[] val = new Object[row];
		for (int i = 0; i < val.length; i++)
			val[i] = Member.memberTable.getValueAt(i,1);
		member_info2 = new JComboBox(val);
		member_info2.addItem("��ȸ��");

		JButton table2_add = new JButton("���̺� 2 �߰�");
		table2_add.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = add_order2.getSelectedIndex();
				model_T2.addRow(new Object[] {Menu.menuTable.getValueAt(x, 0), Menu.unvisibleTable.getValueAt(x, 0)});

				int data = 0;
				String total;
				for(int i=0; i<model_T2.getRowCount();i++) {
					data += Integer.parseInt((String)model_T2.getValueAt(i, 1));
				}
				total = String.valueOf(data);
				table2_total.setText(total);
			}

		});
		JButton table2_pay = new JButton("���̺� 2 ����");
		table2_pay.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				table_num2.setBackground(Color.WHITE);
				table2.setVisible(false);
				int data = Integer.parseInt(table2_total.getText());
				int x = member_info2.getSelectedIndex();
				if (x < Member.memberTable.getRowCount()) {
					String rating = (String)Member.memberTable.getValueAt(x, 4);

					if (rating.equals("�Ϲ�")) {
						int mileage = 0;
						try {
							mileage = (int)Member.memberTable.getValueAt(x, 3);
						}
						catch(ClassCastException e1) {
							mileage = Integer.parseInt((String)Member.memberTable.getValueAt(x, 3));
						}
						mileage += data*0.02;
						Member.memberTable.setValueAt(mileage, x, 3);
						Final.today_money += data * 0.98;
					}
					else if(rating.compareTo("���") == 0) {
						int mileage = 0;
						try {
							mileage = (int)Member.memberTable.getValueAt(x, 3);
						}
						catch(ClassCastException e1) {
							mileage = Integer.parseInt((String)Member.memberTable.getValueAt(x, 3));
						}
						mileage += data*0.02;
						Member.memberTable.setValueAt(mileage, x, 3);
						Final.today_money += data * 0.95;
					}
					else if(rating.equals("�÷�Ƽ��")){
						int mileage = 0;
						try {
							mileage = (int)Member.memberTable.getValueAt(x, 3);
						}
						catch(ClassCastException e1) {
							mileage = Integer.parseInt((String)Member.memberTable.getValueAt(x, 3));
						}
						mileage += data*0.02;
						Member.memberTable.setValueAt(mileage, x, 3);
						Final.today_money += data * 0.9;
					}
				}
				else {
					Final.today_money += data;	
				}

				table2_total.setText("");
				DefaultTableModel model = (DefaultTableModel) T2.getModel();
				model.setNumRows(0);
				String str = "���� ���� : " + Final.today_money + "     ��ü �ܰ� : " + Final.total_money;
				Final.priceLabel.setText(str);

				int mileage = (int) Member.memberTable.getValueAt(x, 3);
				if (mileage >= 500 && mileage < 1000)
					Member.memberTable.setValueAt("���", x, 4);
				else if(mileage > 1000) {
					Member.memberTable.setValueAt("�÷�Ƽ��",x, 4);
				}
				else {
					Member.memberTable.setValueAt("�Ϲ�", x, 4);
				}
			}

		});

		BtnPanel.add(add_order2);
		BtnPanel.add(member_info2);
		BtnPanel.add(table2_add);
		BtnPanel.add(table2_pay);
		table2.add(BtnPanel, BorderLayout.SOUTH);
		table2.add(scrollpane2);
		table2.setVisible(true);
	}

	void Frame_Open3() {
		JPanel BtnPanel = new JPanel(new GridLayout(1,2));
		table3 = new Frame("���̺� 3");
		table3.setSize(400, 400);
		table3.setLayout(new BorderLayout());
		table3.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				table_num3.setBackground(Color.LIGHT_GRAY);
				table3.setVisible(false);
			}
		});

		add_order3 = new JComboBox(getTableData(Menu.menuTable));

		int row = Member.memberTable.getRowCount();
		Object[] val = new Object[row];
		for (int i = 0; i < val.length; i++)
			val[i] = Member.memberTable.getValueAt(i,1);
		member_info3 = new JComboBox(val);
		member_info3.addItem("��ȸ��");

		JButton table3_add = new JButton("���̺� 3 �߰�");
		table3_add.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = add_order3.getSelectedIndex();
				model_T3.addRow(new Object[] {Menu.menuTable.getValueAt(x, 0), Menu.unvisibleTable.getValueAt(x, 0)});

				int data = 0;
				String total;
				for(int i=0; i<model_T3.getRowCount();i++) {
					data += Integer.parseInt((String)model_T3.getValueAt(i, 1));
				}
				total = String.valueOf(data);
				table3_total.setText(total);
			}

		});
		JButton table3_pay = new JButton("���̺� 3 ����");
		table3_pay.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				table_num3.setBackground(Color.WHITE);
				table3.setVisible(false);
				int data = Integer.parseInt(table3_total.getText());
				int x = member_info3.getSelectedIndex();
				if (x < Member.memberTable.getRowCount()) {
					String rating = (String) Member.memberTable.getValueAt(x, 4);

					if (rating.equals("�Ϲ�")) {
						int mileage = 0;
						try {
							mileage = (int) Member.memberTable.getValueAt(x, 3);
						}
						catch(ClassCastException e1) {
							mileage = Integer.parseInt((String) Member.memberTable.getValueAt(x, 3));
						}
						mileage += data*0.02;
						Member.memberTable.setValueAt(mileage, x, 3);
						Final.today_money += data * 0.98;
					}
					else if(rating.compareTo("���") == 0) {
						int mileage = 0;
						try {
							mileage = (int)Member.memberTable.getValueAt(x, 3);
						}
						catch(ClassCastException e1) {
							mileage = Integer.parseInt((String)Member.memberTable.getValueAt(x, 3));
						}
						mileage += data*0.02;
						Member.memberTable.setValueAt(mileage, x, 3);
						Final.today_money += data * 0.95;
					}
					else if(rating.equals("�÷�Ƽ��")){
						int mileage = 0;
						try {
							mileage = (int)Member.memberTable.getValueAt(x, 3);
						}
						catch(ClassCastException e1) {
							mileage = Integer.parseInt((String)Member.memberTable.getValueAt(x, 3));
						}
						mileage += data*0.02;
						Member.memberTable.setValueAt(mileage, x, 3);
						Final.today_money += data * 0.9;
					}
				}
				else {
					Final.today_money += data;	
				}

				table3_total.setText("");
				DefaultTableModel model = (DefaultTableModel) T3.getModel();
				model.setNumRows(0);
				String str = "���� ���� : " + Final.today_money + "     ��ü �ܰ� : " + Final.total_money;
				Final.priceLabel.setText(str);

				int mileage = (int) Member.memberTable.getValueAt(x, 3);
				if (mileage >= 500 && mileage < 1000)
					Member.memberTable.setValueAt("���", x, 4);
				else if(mileage > 1000) {
					Member.memberTable.setValueAt("�÷�Ƽ��",x, 4);
				}
				else {
					Member.memberTable.setValueAt("�Ϲ�", x, 4);
				}
			}

		});

		BtnPanel.add(add_order3);
		BtnPanel.add(member_info3);
		BtnPanel.add(table3_add);
		BtnPanel.add(table3_pay);
		table3.add(BtnPanel, BorderLayout.SOUTH);
		table3.add(scrollpane3);
		table3.setVisible(true);
	}

	void Frame_Open4() {
		JPanel BtnPanel = new JPanel(new GridLayout(1,2));
		table4 = new Frame("���̺� 4");
		table4.setSize(400, 400);
		table4.setLayout(new BorderLayout());
		table4.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				table_num4.setBackground(Color.LIGHT_GRAY);
				table4.setVisible(false);
			}
		});

		add_order4 = new JComboBox(getTableData(Menu.menuTable));

		int row = Member.memberTable.getRowCount();
		Object[] val = new Object[row];
		for (int i = 0; i < val.length; i++)
			val[i] = Member.memberTable.getValueAt(i,1);
		member_info4 = new JComboBox(val);
		member_info4.addItem("��ȸ��");

		JButton table4_add = new JButton("���̺� 4 �߰�");
		table4_add.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = add_order4.getSelectedIndex();
				model_T4.addRow(new Object[] {Menu.menuTable.getValueAt(x, 0), Menu.unvisibleTable.getValueAt(x, 0)});
				int data = 0;
				String total;
				for(int i=0; i<model_T4.getRowCount();i++) {
					data += Integer.parseInt((String)model_T4.getValueAt(i, 1));
				}
				total = String.valueOf(data);
				table4_total.setText(total);
			}

		});
		JButton table4_pay = new JButton("���̺� 4 ����");
		table4_pay.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				table_num4.setBackground(Color.WHITE);
				table4.setVisible(false);
				int data = Integer.parseInt(table4_total.getText());
				int x = member_info4.getSelectedIndex();
				if (x < Member.memberTable.getRowCount()) {
					String rating = (String)Member.memberTable.getValueAt(x, 4);

					if (rating.equals("�Ϲ�")) {
						int mileage = 0;
						try {
							mileage = (int)Member.memberTable.getValueAt(x, 3);
						}
						catch(ClassCastException e1) {
							mileage = Integer.parseInt((String)Member.memberTable.getValueAt(x, 3));
						}
						mileage += data*0.02;
						Member.memberTable.setValueAt(mileage, x, 3);
						Final.today_money += data * 0.98;
					}
					else if(rating.compareTo("���") == 0) {
						int mileage = 0;
						try {
							mileage = (int)Member.memberTable.getValueAt(x, 3);
						}
						catch(ClassCastException e1) {
							mileage = Integer.parseInt((String)Member.memberTable.getValueAt(x, 3));
						}
						mileage += data*0.02;
						Member.memberTable.setValueAt(mileage, x, 3);
						Final.today_money += data * 0.95;
					}
					else if(rating.equals("�÷�Ƽ��")){
						int mileage = 0;
						try {
							mileage = (int)Member.memberTable.getValueAt(x, 3);
						}
						catch(ClassCastException e1) {
							mileage = Integer.parseInt((String)Member.memberTable.getValueAt(x, 3));
						}
						mileage += data*0.02;
						Member.memberTable.setValueAt(mileage, x, 3);
						Final.today_money += data * 0.9;
					}
				}
				else {
					Final.today_money += data;	
				}

				table4_total.setText("");
				DefaultTableModel model = (DefaultTableModel) T4.getModel();
				model.setNumRows(0);
				String str = "���� ���� : " + Final.today_money + "     ��ü �ܰ� : " + Final.total_money;
				Final.priceLabel.setText(str);

				int mileage = (int) Member.memberTable.getValueAt(x, 3);
				if (mileage >= 500 && mileage < 1000)
					Member.memberTable.setValueAt("���", x, 4);
				else if(mileage > 1000) {
					Member.memberTable.setValueAt("�÷�Ƽ��",x, 4);
				}
				else {
					Member.memberTable.setValueAt("�Ϲ�", x, 4);
				}
			}

		});

		BtnPanel.add(add_order4);
		BtnPanel.add(member_info4);
		BtnPanel.add(table4_add);
		BtnPanel.add(table4_pay);
		table4.add(BtnPanel, BorderLayout.SOUTH);
		table4.add(scrollpane4);
		table4.setVisible(true);
	}

	void Frame_Open5() {
		JPanel BtnPanel = new JPanel(new GridLayout(1,2));
		table5 = new Frame("���̺� 5");
		table5.setSize(400, 400);
		table5.setLayout(new BorderLayout());
		table5.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				table_num5.setBackground(Color.LIGHT_GRAY);
				table5.setVisible(false);
			}
		});

		add_order5 = new JComboBox(getTableData(Menu.menuTable));

		int row = Member.memberTable.getRowCount();
		Object[] val = new Object[row];
		for (int i = 0; i < val.length; i++)
			val[i] = Member.memberTable.getValueAt(i,1);
		member_info5 = new JComboBox(val);
		member_info5.addItem("��ȸ��");

		JButton table5_add = new JButton("���̺� 5 �߰�");
		table5_add.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = add_order5.getSelectedIndex();
				model_T5.addRow(new Object[] {Menu.menuTable.getValueAt(x, 0), Menu.unvisibleTable.getValueAt(x, 0)});

				int data = 0;
				String total;
				for(int i=0; i<model_T5.getRowCount();i++) {
					data += Integer.parseInt((String)model_T5.getValueAt(i, 1));
				}
				total = String.valueOf(data);
				table5_total.setText(total);
			}

		});
		JButton table5_pay = new JButton("���̺� 5 ����");
		table5_pay.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				table_num5.setBackground(Color.WHITE);
				table5.setVisible(false);
				int data = Integer.parseInt(table5_total.getText());
				int x = member_info5.getSelectedIndex();
				if (x < Member.memberTable.getRowCount()) {
					String rating = (String)Member.memberTable.getValueAt(x, 4);

					if (rating.equals("�Ϲ�")) {
						int mileage = 0;
						try {
							mileage = (int)Member.memberTable.getValueAt(x, 3);
						}
						catch(ClassCastException e1) {
							mileage = Integer.parseInt((String)Member.memberTable.getValueAt(x, 3));
						}
						mileage += data*0.02;
						Member.memberTable.setValueAt(mileage, x, 3);
						Final.today_money += data * 0.98;
					}
					else if(rating.compareTo("���") == 0) {
						int mileage = 0;
						try {
							mileage = (int)Member.memberTable.getValueAt(x, 3);
						}
						catch(ClassCastException e1) {
							mileage = Integer.parseInt((String)Member.memberTable.getValueAt(x, 3));
						}
						mileage += data*0.02;
						Member.memberTable.setValueAt(mileage, x, 3);
						Final.today_money += data * 0.95;
					}
					else if(rating.equals("�÷�Ƽ��")){
						int mileage = 0;
						try {
							mileage = (int)Member.memberTable.getValueAt(x, 3);
						}
						catch(ClassCastException e1) {
							mileage = Integer.parseInt((String)Member.memberTable.getValueAt(x, 3));
						}
						mileage += data*0.02;
						Member.memberTable.setValueAt(mileage, x, 3);
						Final.today_money += data * 0.9;
					}
				}
				else {
					Final.today_money += data;	
				}

				table5_total.setText("");
				DefaultTableModel model = (DefaultTableModel) T5.getModel();
				model.setNumRows(0);
				String str = "���� ���� : " + Final.today_money + "     ��ü �ܰ� : " + Final.total_money;
				Final.priceLabel.setText(str);

				int mileage = (int) Member.memberTable.getValueAt(x, 3);
				if (mileage >= 500 && mileage < 1000)
					Member.memberTable.setValueAt("���", x, 4);
				else if(mileage > 1000) {
					Member.memberTable.setValueAt("�÷�Ƽ��",x, 4);
				}
				else {
					Member.memberTable.setValueAt("�Ϲ�", x, 4);
				}
			}

		});

		BtnPanel.add(add_order5);
		BtnPanel.add(member_info5);
		BtnPanel.add(table5_add);
		BtnPanel.add(table5_pay);
		table5.add(BtnPanel, BorderLayout.SOUTH);
		table5.add(scrollpane5);
		table5.setVisible(true);
	}

	void Frame_Open6() {
		JPanel BtnPanel = new JPanel(new GridLayout(1,2));
		table6 = new Frame("���̺� 6");
		table6.setSize(400, 400);
		table6.setLayout(new BorderLayout());
		table6.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				table_num6.setBackground(Color.LIGHT_GRAY);
				table6.setVisible(false);
			}
		});

		add_order6 = new JComboBox(getTableData(Menu.menuTable));

		int row = Member.memberTable.getRowCount();
		Object[] val = new Object[row];
		for (int i = 0; i < val.length; i++)
			val[i] = Member.memberTable.getValueAt(i,1);
		member_info6 = new JComboBox(val);
		member_info6.addItem("��ȸ��");

		JButton table6_add = new JButton("���̺� 6 �߰�");
		table6_add.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = add_order6.getSelectedIndex();
				model_T6.addRow(new Object[] {Menu.menuTable.getValueAt(x, 0), Menu.unvisibleTable.getValueAt(x, 0)});

				int data = 0;
				String total;
				for(int i=0; i<model_T6.getRowCount();i++) {
					data += Integer.parseInt((String)model_T6.getValueAt(i, 1));
				}
				total = String.valueOf(data);
				table6_total.setText(total);
			}

		});

		JButton table6_pay = new JButton("���̺� 6 ����");
		table6_pay.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				table_num6.setBackground(Color.WHITE);
				table6.setVisible(false);
				int data = Integer.parseInt(table6_total.getText());
				int x = member_info6.getSelectedIndex();
				if (x < Member.memberTable.getRowCount()) {
					String rating = (String)Member.memberTable.getValueAt(x, 4);

					if (rating.equals("�Ϲ�")) {
						int mileage = 0;
						try {
							mileage = (int)Member.memberTable.getValueAt(x, 3);
						}
						catch(ClassCastException e1) {
							mileage = Integer.parseInt((String)Member.memberTable.getValueAt(x, 3));
						}
						mileage += data*0.02;
						Member.memberTable.setValueAt(mileage, x, 3);
						Final.today_money += data * 0.98;
					}
					else if(rating.compareTo("���") == 0) {
						int mileage = 0;
						try {
							mileage = (int)Member.memberTable.getValueAt(x, 3);
						}
						catch(ClassCastException e1) {
							mileage = Integer.parseInt((String)Member.memberTable.getValueAt(x, 3));
						}
						mileage += data*0.02;
						Member.memberTable.setValueAt(mileage, x, 3);
						Final.today_money += data * 0.95;
					}
					else if(rating.equals("�÷�Ƽ��")){
						int mileage = 0;
						try {
							mileage = (int)Member.memberTable.getValueAt(x, 3);
						}
						catch(ClassCastException e1) {
							mileage = Integer.parseInt((String)Member.memberTable.getValueAt(x, 3));
						}
						mileage += data*0.02;
						Member.memberTable.setValueAt(mileage, x, 3);
						Final.today_money += data * 0.9;
					}
				}
				else {
					Final.today_money += data;	
				}

				table6_total.setText("");
				DefaultTableModel model = (DefaultTableModel) T6.getModel();
				model.setNumRows(0);
				String str = "���� ���� : " + Final.today_money + "     ��ü �ܰ� : " + Final.total_money;
				Final.priceLabel.setText(str);

				int mileage = (int) Member.memberTable.getValueAt(x, 3);
				if (mileage >= 500 && mileage < 1000)
					Member.memberTable.setValueAt("���", x, 4);
				else if(mileage > 1000) {
					Member.memberTable.setValueAt("�÷�Ƽ��",x, 4);
				}
				else {
					Member.memberTable.setValueAt("�Ϲ�", x, 4);
				}
			}

		});

		BtnPanel.add(add_order6);
		BtnPanel.add(member_info6);
		BtnPanel.add(table6_add);
		BtnPanel.add(table6_pay);
		table6.add(BtnPanel, BorderLayout.SOUTH);
		table6.add(scrollpane6);
		table6.setVisible(true);
	}

	public Object[] getTableData(JTable table) { 

		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		int nRow = dtm.getRowCount();
		Object[] tableData = new Object[nRow];

		for (int i = 0; i < nRow; i++)
			tableData[i] = dtm.getValueAt(i,0);

		return tableData;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();

		if(actionCommand.equals("���̺� 1")) {
			Frame_Open1();
			table_num1.setBackground(Color.DARK_GRAY);
		}

		else if(actionCommand.equals("���̺� 2")) {
			Frame_Open2();
			table_num2.setBackground(Color.DARK_GRAY);
		}

		else if(actionCommand.equals("���̺� 3")) {
			Frame_Open3();
			table_num3.setBackground(Color.DARK_GRAY);
		}

		else if(actionCommand.equals("���̺� 4")) {
			Frame_Open4();
			table_num4.setBackground(Color.DARK_GRAY);
		}

		else if(actionCommand.equals("���̺� 5")) {
			Frame_Open5();
			table_num5.setBackground(Color.DARK_GRAY);
		}

		else if(actionCommand.equals("���̺� 6")) {
			Frame_Open6();
			table_num6.setBackground(Color.DARK_GRAY);
		}

	}
}
