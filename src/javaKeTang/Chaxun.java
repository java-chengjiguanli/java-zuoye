package javaKeTang;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Chaxun extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable table;
	private JTable table_1;
	private DefaultTableModel tableMode1;
	private DefaultTableModel tableMode2;
	String[] tableHeadName1 = { "ѧ��", "����" };
	String[] tableHeadName2 = { "�ɼ�" };
	ZhuJieMian zhujiemian;
	/**
	 * Create the dialog.
	 */
	public Chaxun(){}
	public Chaxun(ZhuJieMian zhujiemian) {
		this.zhujiemian=zhujiemian;
		setTitle("��ѯ����");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel label = new JLabel("������ѧ��:");
			contentPanel.add(label);
		}
		{
			textField = new JTextField();
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JButton button = new JButton("ȷ��");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String xuehao=textField.getText()+"\t";
					chaxun(xuehao);
				}
			});
			{
				JLabel lblNewLabel = new JLabel("             ");
				contentPanel.add(lblNewLabel);
			}
			contentPanel.add(button);
		}
		{
			tableMode1 = new DefaultTableModel() {
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
			tableMode1.setDataVector(new Object[1][2], tableHeadName1);
			table = new JTable(tableMode1);
			contentPanel.add(table);
		}
		{
			tableMode2 = new DefaultTableModel();
			tableMode2.setDataVector(new Object[1][1], tableHeadName2);
			table_1 = new JTable(tableMode2);
			contentPanel.add(table_1);
		}
		{
			JButton button = new JButton("ȷ���޸�");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					xiugai();
				}
			});
			contentPanel.add(button);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
	
	void chaxun(String xuehao){
		for(Student s:Ku.getKu().list){
			if(xuehao.equals(s.id)){
				Object[][] o1 = new Object[1][2];
				o1[0][0] = s.id;
				o1[0][1] = s.name;
				Object[][] o2 = new Object[1][1];
				o2[0][0] = s.score;
				tableMode1.setDataVector(o1, tableHeadName1);
				tableMode2.setDataVector(o2, tableHeadName2);
			}
		}
	}
	void xiugai(){
		String xuehao=table.getValueAt(0, 0).toString();
		table_1.getCellEditor().stopCellEditing();
		double chengji=Double.parseDouble(table_1.getValueAt(0, 0).toString());
		for(int i=0;i<Ku.getKu().list.size();i++){
			String x=Ku.getKu().list.get(i).id;
			if(xuehao.equals(x)){
				Ku.getKu().list.get(i).score=chengji;
			}
		}
		zhujiemian.gengxinbiao();
		try{
			daochu();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	void daochu() throws IOException{
		BufferedWriter w1 = new BufferedWriter(new FileWriter(Ku.file));
		try{
			for(Student s:Ku.getKu().list){
				w1.write(s.id);
				w1.newLine();
				w1.write(s.name);
				w1.newLine();
				w1.write(String.valueOf(s.score));
				w1.newLine();
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			w1.close();
		}
	}
}
