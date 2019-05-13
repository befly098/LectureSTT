package SitDown;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Table implements ActionListener {

	JPanel tablePanel;

	JPanel orderPanel;
	JPanel BtnPanel;
	JTable order;
	JButton menu_add, menu_pay;

	JPanel tableTotalPanel;
	JPanel tableListPanel;
	JPanel tableEditPanel;
	JButton table_add, table_delete;

	JPanel[] table_num = new JPanel[50];
	// ���̺� + ��Ż �� ���� �г�
	JPanel[] table = new JPanel[50];

	JButton[] table_btn = new JButton[50];
	// ���̾�α׿� ����� ���̺� ��ư

	JTextField[] table_total = new JTextField[50];
	// ���̺� �Ѿ� ��� �ؽ�Ʈ �ʵ�

	Frame cash;

	static JTable[] T = new JTable[50];
	static JTable MEM;

	DefaultTableModel[] model_T = new DefaultTableModel[50];
	DefaultTableModel model_order;
	DefaultTableModel model_mem;

	JScrollPane[] scrollpane = new JScrollPane[50];
	JScrollPane scrollpane_order;
	// JTable ���� JScrollPane
	JScrollPane scrollpane_mem;

	JComboBox menu_list;
	// �޴� ���� �ڽ�

	int tables = 6;
	int[][] count = new int[50][100];

	public Table() {
		tablePanel = new JPanel();
		tablePanel.setLayout(new GridLayout(1, 2));

		tableListPanel = new JPanel();
		tableListPanel.setLayout(new GridLayout(5, 2));

		for (int i = 0; i < table_num.length; i++) {
			table_num[i] = new JPanel();
			table_num[i].setLayout(new BorderLayout());
			table_num[i].setBackground(Color.WHITE);
		}

		String name = new String("���̺� 1");
		for (int i = 0; i < table_btn.length; i++) {
			table_btn[i] = new JButton(name);
			name = name.replace(Integer.toString(i + 1), Integer.toString(i + 2));
		}

		for (int i = 0; i < table_total.length; i++)
			table_total[i] = new JTextField(10);

		for (int i = 0; i < T.length; i++) {
			Vector[] userColumn = new Vector[50];
			userColumn[i] = new Vector<String>();
			userColumn[i].addElement("�̸�");
			userColumn[i].addElement("����");
			userColumn[i].addElement("����");
			model_T[i] = new DefaultTableModel(userColumn, 0);
			T[i] = new JTable(model_T[i]);
			scrollpane[i] = new JScrollPane(T[i]);
		}

		Vector<String> userColumn_mem = new Vector<String>();
		userColumn_mem.addElement("�̸�");
		userColumn_mem.addElement("����ó");
		userColumn_mem.addElement("���ϸ���");
		userColumn_mem.addElement("���");
		model_mem = new DefaultTableModel(userColumn_mem, 0);
		MEM = new JTable(model_mem);
		scrollpane_mem = new JScrollPane(MEM);

		for (int i = 0; i < table_num.length; i++) {
			table_num[i].add(table_btn[i]);
			table_num[i].add(table_total[i], BorderLayout.SOUTH);
		}

		for (int i = 0; i < table_btn.length; i++)
			table_btn[i].addActionListener(this);

		for (int i = 0; i < tables; i++)
			tableListPanel.add(table_num[i]);
			
		tableTotalPanel = new JPanel(new BorderLayout());
		tableEditPanel = new JPanel(new GridLayout(1, 2));
		table_add = new JButton("���̺� �߰�");
		table_add.addActionListener(this);
		table_delete = new JButton("���̺� ����");
		table_delete.addActionListener(this);

		tableEditPanel.add(table_add);
		tableEditPanel.add(table_delete);
		tableTotalPanel.add(tableListPanel);
		tableTotalPanel.add(tableEditPanel, BorderLayout.SOUTH);

		orderPanel = new JPanel();
		orderPanel.setLayout(new BorderLayout());
		BtnPanel = new JPanel(new GridLayout(1, 3));

		Vector<String> userColumn_order = new Vector<String>();
		userColumn_order.addElement("�̸�");
		userColumn_order.addElement("����");
		userColumn_order.addElement("����");
		model_order = new DefaultTableModel(userColumn_order, 0);
		order = new JTable(model_order);
		scrollpane_order = new JScrollPane(order);

		menu_list = new JComboBox(getTableData(Menu.menuTable));
		menu_list.addActionListener(this);
		menu_add = new JButton("�߰�");
		menu_add.addActionListener(this);
		menu_pay = new JButton("����");
		menu_pay.addActionListener(this);

		BtnPanel.add(menu_list);
		BtnPanel.add(menu_add);
		BtnPanel.add(menu_pay);
		
		/*for (int i = 0; i < table.length; i++) {
			table[i] = new JPanel(new BorderLayout());
			table[i].add(scrollpane[i]);
			table[i].add(BtnPanel, BorderLayout.SOUTH);
		}*/
		
		orderPanel.add(scrollpane_order);
		orderPanel.add(BtnPanel, BorderLayout.SOUTH);

		tablePanel.add(tableTotalPanel, BorderLayout.CENTER);
		tablePanel.add(orderPanel, BorderLayout.EAST);
	}

	void Payment(int data) {
		cash = new Frame("����â");
		cash.setSize(450, 500);
		cash.setLayout(new BorderLayout());
		cash.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				cash.setBackground(Color.LIGHT_GRAY);
				cash.setVisible(false);
			}
		});

		JPanel p = new JPanel();
		p.add(new JLabel("��ȣ ���ڸ�"));
		JTextField textfield = new JTextField("4�ڸ�", 10);
		p.add(textfield);
		JButton search = new JButton("�˻�");
		p.add(search);
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String num = new String();
				num = textfield.getText();
				int row = Member.memberTable.getRowCount();
				String[] val = new String[row];
				for (int i = 0; i < val.length; i++) {
					val[i] = (String) Member.memberTable.getValueAt(i, 2);
					val[i] = val[i].substring(val[i].length() - 4, val[i].length());
					if (num.equals(val[i])) {
						model_mem.addRow(
								new Object[] { Member.memberTable.getValueAt(i, 1), Member.memberTable.getValueAt(i, 2),
										Member.memberTable.getValueAt(i, 3), Member.memberTable.getValueAt(i, 4) });
					}
				}
			}
		});
		p.add(scrollpane_mem, BorderLayout.CENTER);

		JPanel paypanel = new JPanel();
		JButton cash_btn = new JButton("���ݰ���");
		paypanel.add(cash_btn);
		cash_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (MEM.getSelectedRow() == -1)
					Final.today_money += data;
				else {
					Object name = new Object();
					name = model_mem.getValueAt(MEM.getSelectedRow(), 0);
					int row = Member.memberTable.getRowCount();
					for (int i = 0; i < row; i++) {
						if (name.equals(Member.memberTable.getValueAt(i, 1))) {
							String rating = (String) Member.memberTable.getValueAt(i, 4);

							if (rating.equals("�Ϲ�")) {
								int mileage = 0;
								try {
									mileage = (int) Member.memberTable.getValueAt(i, 3);
								} catch (ClassCastException e1) {
									mileage = Integer.parseInt((String) Member.memberTable.getValueAt(i, 3));
								}
								mileage += data * 0.02;
								Member.memberTable.setValueAt(mileage, i, 3);
								Final.today_money += data * 0.98;
							} else if (rating.compareTo("���") == 0) {
								int mileage = 0;
								try {
									mileage = (int) Member.memberTable.getValueAt(i, 3);
								} catch (ClassCastException e1) {
									mileage = Integer.parseInt((String) Member.memberTable.getValueAt(i, 3));
								}
								mileage += data * 0.02;
								Member.memberTable.setValueAt(mileage, i, 3);
								Final.today_money += data * 0.95;
							} else if (rating.equals("�÷�Ƽ��")) {
								int mileage = 0;
								try {
									mileage = (int) Member.memberTable.getValueAt(i, 3);
								} catch (ClassCastException e1) {
									mileage = Integer.parseInt((String) Member.memberTable.getValueAt(i, 3));
								}
								mileage += data * 0.02;
								Member.memberTable.setValueAt(mileage, i, 3);
								Final.today_money += data * 0.9;
							}

							int mileage = (int) Member.memberTable.getValueAt(i, 3);
							if (mileage >= 500 && mileage < 1000)
								Member.memberTable.setValueAt("���", i, 4);
							else if (mileage > 1000) {
								Member.memberTable.setValueAt("�÷�Ƽ��", i, 4);
							} else {
								Member.memberTable.setValueAt("�Ϲ�", i, 4);
							}
							break;
						}
					}
				}
				String str = "���� ���� : " + Final.today_money + "     ��ü �ܰ� : " + Final.total_money;
				Final.priceLabel.setText(str);

				for (int i = 0; i < model_mem.getRowCount(); i++) {
					model_mem.removeRow(0);
				}

				cash.setVisible(false);
			}
		});

		JButton card_btn = new JButton("ī�����");
		paypanel.add(card_btn);
		card_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (MEM.getSelectedRow() == -1)
					Final.today_money += data;
				else {
					Object name = new Object();
					name = model_mem.getValueAt(MEM.getSelectedRow(), 0);
					int row = Member.memberTable.getRowCount();
					for (int i = 0; i < row; i++) {
						if (name.equals(Member.memberTable.getValueAt(i, 1))) {
							String rating = (String) Member.memberTable.getValueAt(i, 4);

							if (rating.equals("�Ϲ�")) {
								int mileage = 0;
								try {
									mileage = (int) Member.memberTable.getValueAt(i, 3);
								} catch (ClassCastException e1) {
									mileage = Integer.parseInt((String) Member.memberTable.getValueAt(i, 3));
								}
								mileage += data * 0.02;
								Member.memberTable.setValueAt(mileage, i, 3);
								Final.today_money += data * 0.98;
							} else if (rating.compareTo("���") == 0) {
								int mileage = 0;
								try {
									mileage = (int) Member.memberTable.getValueAt(i, 3);
								} catch (ClassCastException e1) {
									mileage = Integer.parseInt((String) Member.memberTable.getValueAt(i, 3));
								}
								mileage += data * 0.02;
								Member.memberTable.setValueAt(mileage, i, 3);
								Final.today_money += data * 0.95;
							} else if (rating.equals("�÷�Ƽ��")) {
								int mileage = 0;
								try {
									mileage = (int) Member.memberTable.getValueAt(i, 3);
								} catch (ClassCastException e1) {
									mileage = Integer.parseInt((String) Member.memberTable.getValueAt(i, 3));
								}
								mileage += data * 0.02;
								Member.memberTable.setValueAt(mileage, i, 3);
								Final.today_money += data * 0.9;
							}

							int mileage = (int) Member.memberTable.getValueAt(i, 3);
							if (mileage >= 500 && mileage < 1000)
								Member.memberTable.setValueAt("���", i, 4);
							else if (mileage > 1000) {
								Member.memberTable.setValueAt("�÷�Ƽ��", i, 4);
							} else {
								Member.memberTable.setValueAt("�Ϲ�", i, 4);
							}
							break;
						}
					}
				}
				String str = "���� ���� : " + Final.today_money + "     ��ü �ܰ� : " + Final.total_money;
				Final.priceLabel.setText(str);

				for (int i = 0; i < model_mem.getRowCount(); i++) {
					model_mem.removeRow(0);
				}

				cash.setVisible(false);
			}
		});

		cash.add(p, BorderLayout.NORTH);
		cash.add(paypanel, BorderLayout.SOUTH);
		cash.setVisible(true);
	}

	void Table_Open(int index) {
		/*
		 * table[index] = new Frame("���̺� 1"); table[index].setSize(400, 400);
		 * table[index].setLayout(new BorderLayout());
		 * table[index].addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent windowEvent) {
		 * table_num[index].setBackground(Color.LIGHT_GRAY);
		 * table[index].setVisible(false); } });
		 */
		
		menu_add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = menu_list.getSelectedIndex();
				count[index][x] += 1;
				if (count[index][x] == 1) {
					int check=0;
					for (int s_index = 0; s_index < Storage.storageTable.getRowCount(); s_index++) {
						check = 0;
						if (Menu.unvisibleTable.getValueAt(x, 2).equals(Storage.storageTable.getValueAt(s_index, 0))) {
							if (Integer.valueOf((String) Storage.storageTable.getValueAt(s_index, 1)) > 0) {
								check = 1;
								model_T[index].addRow(new Object[] { Menu.menuTable.getValueAt(x, 0),
										Menu.unvisibleTable.getValueAt(x, 0), count[index][x] });
								// have to reduce quantity of storage!
								int quantity = Integer.valueOf((String) Storage.storageTable.getValueAt(x, 1));
								quantity--;
								Storage.storageTable.setValueAt(Integer.toString(quantity), x, 1);
								break;
							} else
								break;
						}
					}
					if (check == 0) {
						System.out.println("Cannot add it!");
					}
				}

				else {
					int check=0;
					for(int j=0;j<model_T[index].getRowCount();j++)
					{
						if((model_T[index].getValueAt(j,0)).equals(Menu.menuTable.getValueAt(x, 0))) {
							
								for(int s_index=0;s_index<Storage.storageTable.getRowCount();s_index++) {
									check=0;

									if(Menu.unvisibleTable.getValueAt(x, 2).equals(Storage.storageTable.getValueAt(s_index, 0))) {
										if(Integer.valueOf((String)Storage.storageTable.getValueAt(s_index,1))>0) {
											model_T[index].setValueAt(count[index][x],j,2);
											int quantity=Integer.valueOf((String)Storage.storageTable.getValueAt(x,1));
											quantity--;
											Storage.storageTable.setValueAt(Integer.toString(quantity), x, 1);
											check=1;
											break;
										}
										else break;
									}
								}
								if(check==0) {
									System.out.println("Cannot add it!");
								}
						}
					}
				}
				int data = 0;
				String total;
				for (int i = 0; i < model_T[index].getRowCount(); i++) {
					data += (Integer.parseInt((String) model_T[index].getValueAt(i, 1))
							* Integer.parseInt(model_T[index].getValueAt(i, 2).toString()));
				}
				total = String.valueOf(data);
				table_total[index].setText(total);
			}
		});

		menu_pay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				table_num[index].setBackground(Color.WHITE);
				// table[index].setVisible(false);
				int data = Integer.parseInt(table_total[index].getText());
				Payment(data);
				table_total[index].setText("");

				for (int i = 0; i <= model_T[index].getRowCount(); i++) {
					model_T[index].removeRow(0);
				}
				for (int i = 0; i < 100; i++) {
					count[index][i] = 0;
				}
			}
		});

		/*tablePanel.remove(orderPanel);
		tablePanel.add(table[index]);
		BtnPanel.add(menu_add);
		BtnPanel.add(menu_pay);*/
	}

	public Object[] getTableData(JTable table) {

		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		int nRow = dtm.getRowCount();
		Object[] tableData = new Object[nRow];

		for (int i = 0; i < nRow; i++)
			tableData[i] = dtm.getValueAt(i, 0);

		return tableData;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		String[] name = new String[50];

		for (int i = 0; i < 50; i++) {
			name[i] = new String("���̺� ");
			name[i] = name[i].concat(Integer.toString(i + 1));
		}

		for (int i = 0; i < tables; i++) {
			if (actionCommand.equals(name[i])) {
				Table_Open(i);
				table_num[i].setBackground(Color.DARK_GRAY);
				//tablePanel.remove(table[i]);
				//tablePanel.add(orderPanel);
				break;
			}
		}

		if (actionCommand.compareTo("���̺� �߰�") == 0) {
			table_num[tables].add(table_btn[tables]);
			table_num[tables].add(table_total[tables], BorderLayout.SOUTH);

			tableListPanel.add(table_num[tables]);

			tables++;
		}
		else if (actionCommand.compareTo("���̺� ����") == 0) {
			if (tables > 0) {
				tableListPanel.remove(table_num[tables-1]);
				
				table_total[tables-1].setText("");

				/*for (int i = 0; i <= model_T[tables-1].getRowCount(); i++)
					model_T[tables-1].removeRow(0);*/
				
				for (int i = 0; i < 100; i++)
					count[tables-1][i] = 0;
				
				tableListPanel.setVisible(true);
				
				tables--;
			}
		}
	}
}
