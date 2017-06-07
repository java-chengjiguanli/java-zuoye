package javaKeTang;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JTable;

public class ZhuJieMian extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel tableModel;
	String[] tableHeadName = { "ѧ��", "����", "�ɼ�" };
	static ZhuJieMian frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ZhuJieMian();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ZhuJieMian() {
		setTitle("ѧ���ɼ�����ϵͳ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 556);
		inItJmenu();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		tableModel = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		tableModel.setDataVector(new Object[100][3], tableHeadName);
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
	}

	void inItJmenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenuItem menuItem = new JMenuItem("����");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser filechooser = new JFileChooser();
				filechooser.showOpenDialog(ZhuJieMian.this);
				Ku.file = filechooser.getSelectedFile();
				if(Ku.file != null){
					try {
						Ku.getKu().duqu();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					gengxinbiao();
				}
			}
		});
		menuBar.add(menuItem);

		JMenu mnNewMenu = new JMenu("����");
		menuBar.add(mnNewMenu);
		
		JMenuItem menuItem_1 = new JMenuItem("��ѧ��");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sortByid();
				gengxinbiao();
			}
		});
		mnNewMenu.add(menuItem_1);

		JMenuItem menuItem_2 = new JMenuItem("������");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortByname();
				gengxinbiao();
			}
		});
		mnNewMenu.add(menuItem_2);

		JMenu menu = new JMenu("���ɼ�");
		mnNewMenu.add(menu);

		JMenuItem menuItem_3 = new JMenuItem("�ɵ͵���");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sortByscore();
				gengxinbiao();
			}
		});
		menu.add(menuItem_3);

		JMenuItem menuItem_6 = new JMenuItem("�ɸߵ���");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortByscore1();
				gengxinbiao();
			}
		});
		menu.add(menuItem_6);

		JMenuItem menuItem_4 = new JMenuItem("ͳ��");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Tongji dialog = Tongji.getTongji();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		menuBar.add(menuItem_4);

		JMenuItem menuItem_5 = new JMenuItem("��ѯ");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Chaxun dialog = new Chaxun(frame);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		menuBar.add(menuItem_5);
	}

	void gengxinbiao() {
		Object[][] o1 = new Object[100][3];
		for (int i = 0; i < Ku.getKu().list.size(); i++) {
			o1[i][0] = Ku.getKu().list.get(i).id;
			o1[i][1] = Ku.getKu().list.get(i).name;
			o1[i][2] = Ku.getKu().list.get(i).score;
		}
		tableModel.setDataVector(o1, tableHeadName);
	}

	void sortByid() {
		Ku.getKu().list.sort((e1, e2) -> {
			return e1.id.compareTo(e2.id);
		});
	}

	void sortByname() {
		Ku.getKu().list.sort((e1, e2) -> {
			return e1.name.compareTo(e2.name);
		});
	}

	void sortByscore() {
		Ku.getKu().list.sort((e1, e2) -> {
			return (e1.score > e2.score) ? 1 : -1;
		});
	}

	void sortByscore1() {
		Ku.getKu().list.sort((e1, e2) -> {
			return (e1.score < e2.score) ? 1 : -1;
		});
	}
}
