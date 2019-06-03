package SitDown;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

public class Table implements ActionListener {

	JPanel tablePanel;

	JPanel orderPanel;
	JPanel BtnPanel;
	JTable order;
	JButton menuAdd;
	JButton menuPay;

	JPanel tableTotalPanel;
	JPanel tableListPanel;
	JPanel tableEditPanel;
	JButton tableAdd;
	JButton tableDelete;

	JPanel[] tableNum = new JPanel[50];
	// ���̺� + ��Ż �� ���� �г�
	JPanel[] table = new JPanel[50];

	JToggleButton[] tableBtn = new JToggleButton[50];
	ButtonGroup tableBtnGroup = new ButtonGroup();
	// ���̾�α׿� ����� ���̺� ��ư

	JTextField[] tableTotal = new JTextField[50];
	// ���̺� �Ѿ� ��� �ؽ�Ʈ �ʵ�

	Frame cash;

	static JTable[] T = new JTable[50];
	static JTable MEM;

	DefaultTableModel[] modelT = new DefaultTableModel[50];
	DefaultTableModel modelOrder;
	DefaultTableModel modelMem;

	JScrollPane[] scrollpane = new JScrollPane[50];
	JScrollPane scrollpaneOrder;
	// JTable ���� JScrollPane
	JScrollPane scrollpaneMem;

	JComboBox menuList;
	JTextField menuQuantity;
	// �޴� ���� �ڽ�

	int tables = 6;
	int[][] count = new int[50][100];

	public Table() {
		tablePanel = new JPanel();
		tablePanel.setLayout(new GridLayout(1, 2));

		tableListPanel = new JPanel();
		tableListPanel.setLayout(new GridLayout(5, 2));

		for (int i = 0; i < tableNum.length; i++) {
			tableNum[i] = new JPanel();
			tableNum[i].setLayout(new BorderLayout());
			tableNum[i].setBackground(Color.WHITE);
		}

		String name = new String("���̺� 1");
		for (int i = 0; i < tableBtn.length; i++) {
			tableBtn[i] = new JToggleButton(name);
			tableBtnGroup.add(tableBtn[i]);
			name = name.replace(Integer.toString(i + 1), Integer.toString(i + 2));
		}

		for (int i = 0; i < tableTotal.length; i++)
			tableTotal[i] = new JTextField(10);
		String tableColumn[] = { "�̸�", "����", "����" };
		for (int i = 0; i < T.length; i++) {
			Vector[] userColumn = new Vector[50];
			userColumn[i] = new Vector<String>();
			userColumn[i].addElement("�̸�");
			userColumn[i].addElement("����");
			userColumn[i].addElement("����");
			modelT[i] = new DefaultTableModel(userColumn[i], 0);
			T[i] = new JTable(modelT[i]);
			scrollpane[i] = new JScrollPane(T[i]);
		}

		Vector<String> userColumnMem = new Vector<>();
		userColumnMem.addElement("�̸�");
		userColumnMem.addElement("����ó");
		userColumnMem.addElement("���ϸ���");
		userColumnMem.addElement("���");
		modelMem = new DefaultTableModel(userColumnMem, 0);
		MEM = new JTable(modelMem);
		scrollpaneMem = new JScrollPane(MEM);

		for (int i = 0; i < tableNum.length; i++) {
			tableNum[i].add(tableBtn[i]);
			tableNum[i].add(tableTotal[i], BorderLayout.SOUTH);
		}

		for (int i = 0; i < tableBtn.length; i++)
			tableBtn[i].addActionListener(this);

		for (int i = 0; i < tables; i++)
			tableListPanel.add(tableNum[i]);

		tableTotalPanel = new JPanel(new BorderLayout());
		tableEditPanel = new JPanel(new GridLayout(1, 2));
		tableAdd = new JButton("���̺� �߰�");
		tableAdd.addActionListener(this);
		tableDelete = new JButton("���̺� ����");
		tableDelete.addActionListener(this);

		tableEditPanel.add(tableAdd);
		tableEditPanel.add(tableDelete);
		tableTotalPanel.add(tableListPanel);
		tableTotalPanel.add(tableEditPanel, BorderLayout.SOUTH);

		/////////// �Ʒ����� �ֹ� ������ �����ִ� �г� ������ ////////////////
		orderPanel = new JPanel();
		orderPanel.setLayout(new BorderLayout());
		BtnPanel = new JPanel(new GridLayout(1, 3));

		Vector<String> userColumnOrder = new Vector<>();
		userColumnOrder.addElement("�̸�");
		userColumnOrder.addElement("����");
		userColumnOrder.addElement("����");
		modelOrder = new DefaultTableModel(userColumnOrder, 0);
		order = new JTable(modelOrder);
		scrollpaneOrder = new JScrollPane(order);

		menuList = new JComboBox(getTableData(Menu.menuTable));
		menuList.addActionListener(this);
		menuQuantity=new JTextField(100);
		menuQuantity.setText("1");
		menuAdd = new JButton("�߰�");
		menuAdd.addActionListener(this);
		menuPay = new JButton("����");
		menuPay.addActionListener(this);

		BtnPanel.add(menuList);
		BtnPanel.add(menuQuantity);
		BtnPanel.add(menuAdd);
		BtnPanel.add(menuPay);

		orderPanel.add(scrollpaneOrder);
		orderPanel.add(BtnPanel, BorderLayout.SOUTH);

		tablePanel.add(tableTotalPanel, BorderLayout.CENTER);
		tablePanel.add(orderPanel, BorderLayout.EAST);
	}

	void Payment(int data) {
		cash = new Frame("����â");
		cash.setSize(450, 500);
		cash.setLayout(new BorderLayout());
		cash.addWindowListener(new WindowAdapter() {
			@Override
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
						modelMem.addRow(
								new Object[] { Member.memberTable.getValueAt(i, 1), Member.memberTable.getValueAt(i, 2),
										Member.memberTable.getValueAt(i, 3), Member.memberTable.getValueAt(i, 4) });
					}
				}
			}
		});
		p.add(scrollpaneMem, BorderLayout.CENTER);

		JPanel paypanel = new JPanel();
		JButton cashBtn = new JButton("���ݰ���");
		paypanel.add(cashBtn);
		cashBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (MEM.getSelectedRow() == -1)
					Final.todayMoney += data;
				else {
					Object name = new Object();
					name = modelMem.getValueAt(MEM.getSelectedRow(), 0);
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
								Final.todayMoney += data * 0.98;
							} else if (rating.compareTo("���") == 0) {
								int mileage = 0;
								try {
									mileage = (int) Member.memberTable.getValueAt(i, 3);
								} catch (ClassCastException e1) {
									mileage = Integer.parseInt((String) Member.memberTable.getValueAt(i, 3));
								}
								mileage += data * 0.02;
								Member.memberTable.setValueAt(mileage, i, 3);
								Final.todayMoney += data * 0.95;
							} else if (rating.equals("�÷�Ƽ��")) {
								int mileage = 0;
								try {
									mileage = (int) Member.memberTable.getValueAt(i, 3);
								} catch (ClassCastException e1) {
									mileage = Integer.parseInt((String) Member.memberTable.getValueAt(i, 3));
								}
								mileage += data * 0.02;
								Member.memberTable.setValueAt(mileage, i, 3);
								Final.todayMoney += data * 0.9;
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
				String str = "���� ���� : " + Final.todayMoney + "     ��ü �ܰ� : " + Final.totalMoney;
				Final.priceLabel.setText(str);

				for (int i = 0; i < modelMem.getRowCount(); i++) {
					modelMem.removeRow(0);
				}

				cash.setVisible(false);

				tableTotal[clickedTableBtn].setText("");

				for (int i = 0; i <= modelT[clickedTableBtn].getRowCount(); i++) {
					modelT[clickedTableBtn].removeRow(0);
				}
				for (int i = 0; i < 100; i++) {
					count[clickedTableBtn][i] = 0;
				}
			}
		});

		JButton cardBtn = new JButton("ī�����");
		paypanel.add(cardBtn);
		cardBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (MEM.getSelectedRow() == -1)
					Final.todayMoney += data;
				else {
					Object name = new Object();
					name = modelMem.getValueAt(MEM.getSelectedRow(), 0);
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
								Final.todayMoney += data * 0.98;
							} else if (rating.compareTo("���") == 0) {
								int mileage = 0;
								try {
									mileage = (int) Member.memberTable.getValueAt(i, 3);
								} catch (ClassCastException e1) {
									mileage = Integer.parseInt((String) Member.memberTable.getValueAt(i, 3));
								}
								mileage += data * 0.02;
								Member.memberTable.setValueAt(mileage, i, 3);
								Final.todayMoney += data * 0.95;
							} else if (rating.equals("�÷�Ƽ��")) {
								int mileage = 0;
								try {
									mileage = (int) Member.memberTable.getValueAt(i, 3);
								} catch (ClassCastException e1) {
									mileage = Integer.parseInt((String) Member.memberTable.getValueAt(i, 3));
								}
								mileage += data * 0.02;
								Member.memberTable.setValueAt(mileage, i, 3);
								Final.todayMoney += data * 0.9;
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
				String str = "���� ���� : " + Final.todayMoney + "     ��ü �ܰ� : " + Final.totalMoney;
				Final.priceLabel.setText(str);

				for (int i = 0; i < modelMem.getRowCount(); i++) {
					modelMem.removeRow(0);
				}

				cash.setVisible(false);

				tableTotal[clickedTableBtn].setText("");

				for (int i = 0; i <= modelT[clickedTableBtn].getRowCount(); i++) {
					modelT[clickedTableBtn].removeRow(0);
				}
				for (int i = 0; i < 100; i++) {
					count[clickedTableBtn][i] = 0;
				}
			}
		});

		cash.add(p, BorderLayout.NORTH);
		cash.add(paypanel, BorderLayout.SOUTH);
		cash.setVisible(true);
	}

	int clickedTableBtn = -1;
	int mQuantity=-1;

	public Object[] getTableData(JTable table) {

		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		int nRow = dtm.getRowCount();
		Object[] tableData = new Object[nRow];

		for (int i = 0; i < nRow; i++) {
			tableData[i] = dtm.getValueAt(i, 0);
		}

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
				order.setModel(modelT[i]);
				menuList.removeAllItems();
				menuList.setModel(new DefaultComboBoxModel(getTableData(Menu.menuTable)));
				menuList.repaint();
				clickedTableBtn = i;
				tableNum[i].setBackground(Color.DARK_GRAY);
				break;

			}
		}

		if (actionCommand.compareTo("���̺� �߰�") == 0) {
			tableNum[tables].add(tableBtn[tables]);
			tableNum[tables].add(tableTotal[tables], BorderLayout.SOUTH);

			tableListPanel.add(tableNum[tables]);
			tables++;
		} else if (actionCommand.compareTo("���̺� ����") == 0) {
			if (tables > 1) {
				if (tableTotal[tables - 1].getText().equals("")) {
					tableListPanel.remove(tableNum[tables - 1]);
					tableTotal[tables - 1].setText("");
					
					for (int i = 0; i < 100; i++)
						count[tables - 1][i] = 0;

					tableListPanel.setVisible(true);
					tables--;
					clickedTableBtn = -1;
				}

				else if (!tableTotal[tables - 1].getText().equals("")) {
					String notificationStr = "���̺�" + String.valueOf(tables) + "�� ���� �������ּ���";
					JOptionPane.showMessageDialog(null, notificationStr, "�̰��� ���̺�", JOptionPane.INFORMATION_MESSAGE);
				}
			}

		}

		tableListPanel.revalidate();
		tableListPanel.repaint();

		if (actionCommand.compareTo("�߰�") == 0) {
			int selectedMenu = menuList.getSelectedIndex();
			int cannotOrderFlag = 0;
			String emptyIngredient = "";
			String ingredientArr[] = String.valueOf(Menu.unvisibleTable.getValueAt(selectedMenu, 2)).split("[\n]+");
			String ingredientNameArr[]=new String[ingredientArr.length];
			String ingredientQuantityArr[]=new String[ingredientArr.length];
			int ingredientQauntity;
			
			for(int i=0;i<ingredientArr.length;i++)
			{
				if(ingredientArr[i].split("[ ]+").length<2)
				{
					JOptionPane.showMessageDialog(null, "��� �Է��� (����̸�) (����)���� �ٲ��ּ���.", "��� �Է� ����", JOptionPane.INFORMATION_MESSAGE);
			        return; 
				}
				else if(ingredientArr[i].split("[ ]+").length>2)
				{
					JOptionPane.showMessageDialog(null, "����̸����� ������ �� �� �����ϴ�", "��� �Է� ����", JOptionPane.INFORMATION_MESSAGE);
			        return; 
				}
				ingredientNameArr[i]=ingredientArr[i].split("[ ]+")[0];
				ingredientQuantityArr[i]=ingredientArr[i].split("[ ]+")[1];
				//System.out.println(ingredientQuantityArr[i]);
			}
			
			String storageIngredient[] = new String[Storage.storageTable.getRowCount()];

			if(clickedTableBtn!=-1) {
				
				//���� �Է¹��� �������� Ȯ��
				try {
					Integer.parseInt(menuQuantity.getText());
				} catch(NumberFormatException error) { 
					JOptionPane.showMessageDialog(null, "������ �����θ� �Է��� �� �ֽ��ϴ�!", "�Է� ����", JOptionPane.INFORMATION_MESSAGE);
			        return; 
			    } catch(NullPointerException error) {
			    	JOptionPane.showMessageDialog(null, "������ �Է����ּ���!", "�Է� ����", JOptionPane.INFORMATION_MESSAGE);
			        return;
			    }
				
				//�޴� ���� quantity ������ ����
				mQuantity=Integer.parseInt(menuQuantity.getText());
				if(mQuantity<1) {
					mQuantity=-1;
					JOptionPane.showMessageDialog(null, "1 �̻��� ���� �Է����ּ���!", "�Է� ����", JOptionPane.INFORMATION_MESSAGE);
			        return;
				}
				
				menuQuantity.setText("1");
				
			for (int i = 0; i < Storage.storageTable.getRowCount(); i++) {
				storageIngredient[i] = String.valueOf(Storage.storageTable.getValueAt(i, 0));
			}
			// ��� ��ᰡ �ִ��� Ȯ��
			for (int sIndex = 0; sIndex < ingredientArr.length; sIndex++) {
				//System.out.println(ingredientArr[sIndex].split("[ ]+")[0]);
				boolean ingredientContains = Arrays.stream(storageIngredient)
						.anyMatch(ingredientArr[sIndex].split("[ ]+")[0]::equals);
				if (Boolean.FALSE.equals(ingredientContains)) {
					cannotOrderFlag = 1;
					emptyIngredient = ingredientArr[sIndex].split("[ ]+")[0];
					break;
				}
			}


			// ��� ������ 0�� �̻����� Ȯ��
			if (cannotOrderFlag != 1) {
				for (int sIndex = 0; sIndex < Storage.storageTable.getRowCount(); sIndex++) {
					boolean ingredientContains = Arrays.stream(ingredientNameArr)
							.anyMatch(storageIngredient[sIndex]::equals);

					if (Boolean.TRUE.equals(ingredientContains)) {
						ingredientQauntity=Integer.parseInt((String)ingredientQuantityArr[Arrays.asList(ingredientNameArr).indexOf(storageIngredient[sIndex])]);
						if (Integer.valueOf((String) Storage.storageTable.getValueAt(sIndex, 1)) < mQuantity*ingredientQauntity) {
							cannotOrderFlag = 1;
							emptyIngredient = String.valueOf(Storage.storageTable.getValueAt(sIndex, 0));
							break;
						}
					}
				}
			}
			
			// ���࿡ ��� ��ᰡ �����Ѵٸ�
			if (cannotOrderFlag == 0) {
				count[clickedTableBtn][selectedMenu] += mQuantity;
				
				boolean inTable=false;
				
				for(int j = 0; j < modelT[clickedTableBtn].getRowCount(); j++) 
				{
					if ((modelT[clickedTableBtn].getValueAt(j, 0))
							.equals(Menu.menuTable.getValueAt(selectedMenu, 0))) {
						inTable=true;
						break;
					}
				}

				if (!(inTable)) {

					modelT[clickedTableBtn].addRow(new Object[] { Menu.menuTable.getValueAt(selectedMenu, 0),
							Menu.unvisibleTable.getValueAt(selectedMenu, 0),
							count[clickedTableBtn][selectedMenu] });

					for (int sIndex = 0; sIndex < Storage.storageTable.getRowCount(); sIndex++) {
						boolean ingredientContains = Arrays.stream(ingredientNameArr)
								.anyMatch(storageIngredient[sIndex]::equals);
						if (Boolean.TRUE.equals(ingredientContains)) {
							int quantity = Integer.parseInt((String) Storage.storageTable.getValueAt(sIndex, 1));
							ingredientQauntity=Integer.parseInt((String)ingredientQuantityArr[Arrays.asList(ingredientNameArr).indexOf(storageIngredient[sIndex])]);
							System.out.printf("quan: %d\n",quantity);
							System.out.printf("quan*menu: %d\n",mQuantity*ingredientQauntity);
							quantity-=mQuantity*ingredientQauntity;
							Storage.storageTable.setValueAt(Integer.toString(quantity), sIndex, 1);
						}
					}

				}

				else {
					for (int j = 0; j < modelT[clickedTableBtn].getRowCount(); j++) {
						if ((modelT[clickedTableBtn].getValueAt(j, 0))
								.equals(Menu.menuTable.getValueAt(selectedMenu, 0))) {
							modelT[clickedTableBtn].setValueAt(count[clickedTableBtn][selectedMenu], j, 2);
							break;
						}
					}

					for (int sIndex = 0; sIndex < Storage.storageTable.getRowCount(); sIndex++) {
						boolean ingredientContains = Arrays.stream(ingredientNameArr)
								.anyMatch(storageIngredient[sIndex]::equals);
						if (Boolean.TRUE.equals(ingredientContains)) {
							ingredientQauntity=Integer.parseInt((String)ingredientQuantityArr[Arrays.asList(ingredientNameArr).indexOf(storageIngredient[sIndex])]);
							int quantity = Integer.parseInt((String) Storage.storageTable.getValueAt(sIndex, 1));
							quantity-=mQuantity*ingredientQauntity;
							Storage.storageTable.setValueAt(Integer.toString(quantity), sIndex, 1);
						}
					}

				}

				int data = 0;
				String total;
				for (int i = 0; i < modelT[clickedTableBtn].getRowCount(); i++) {
					data += (Integer.parseInt((String) modelT[clickedTableBtn].getValueAt(i, 1))
							* Integer.parseInt(modelT[clickedTableBtn].getValueAt(i, 2).toString()));
				}
				total = String.valueOf(data);
				tableTotal[clickedTableBtn].setText(total);
				modelT[clickedTableBtn].fireTableDataChanged();
				order.setModel(modelT[clickedTableBtn]);//
				mQuantity=-1;
			}
			// ��� ��ᰡ �������� �ʴ´ٸ�

			else if (cannotOrderFlag == 1) {
				String notificationStr = String.valueOf(Menu.menuTable.getValueAt(selectedMenu, 0)) + "�� '"
						+ emptyIngredient + "'��/�� �����մϴ�!";
				JOptionPane.showMessageDialog(null, notificationStr, "��� ����", JOptionPane.INFORMATION_MESSAGE);
			}

		}
			else {
				JOptionPane.showMessageDialog(null, "�޴��� �ֹ��� ���̺��� �������ּ���!", "���̺� �̼���", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		if (actionCommand.compareTo("����") == 0) {
			if (clickedTableBtn != -1) {
				tableNum[clickedTableBtn].setBackground(Color.WHITE);
				int data = Integer.parseInt(tableTotal[clickedTableBtn].getText());
				Payment(data);
			}
			
			else
				JOptionPane.showMessageDialog(null, "�����Ͻ� ���̺��� �������ּ���!", "���̺� �̼���", JOptionPane.INFORMATION_MESSAGE);
		}

	}
}