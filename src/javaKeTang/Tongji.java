package javaKeTang;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tongji extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	Object[][] o1 = new Object[8][2];

	private final static class setTongji {
		private static final Tongji tongji = new Tongji();
	}

	/**
	 * Create the dialog.
	 */
	private Tongji() {
		shuju();
		setTitle("统计");
		setBounds(100, 100, 356, 241);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("close");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Tongji.getTongji().setVisible(false);
					}
				});
				cancelButton.setActionCommand("close");
				buttonPane.add(cancelButton);
			}
		}
		DefaultTableModel tablemodel=new DefaultTableModel();
		String []s=new String[]{"",""};
		tablemodel.setDataVector(o1,s);
		JTable table = new JTable(tablemodel);
		contentPanel.add(table);
	}

	public static Tongji getTongji() {
		return setTongji.tongji;
	}

	void shuju() {
		o1[0][0] = "0~59分";
		o1[1][0] = "60~69分";
		o1[2][0] = "70~79分";
		o1[3][0] = "80~89分";
		o1[4][0] = "90~100分";
		o1[5][0] = "最高分";
		o1[6][0] = "最低分";
		o1[7][0] = "平均分";
		int f0 = 0;
		int f1 = 0;
		int f2 = 0;
		int f3 = 0;
		int f4 = 0;
		double sum = 0;
		double max = 0;
		double min = 100;
		for (Student s : Ku.getKu().list) {
			if (s.score <= 59) {
				f0++;
			} else if (s.score <= 69) {
				f1++;
			} else if (s.score <= 79) {
				f2++;
			} else if (s.score <= 89) {
				f3++;
			} else if (s.score <= 100) {
				f4++;
			}
			sum += s.score;
			if (max < s.score) {
				max = s.score;
			}
			if (min > s.score) {
				min = s.score;
			}
		}
		o1[0][1] = f0;
		o1[1][1] = f1;
		o1[2][1] = f2;
		o1[3][1] = f3;
		o1[4][1] = f4;
		o1[5][1] = max;
		o1[6][1] = min;
		o1[7][1] = sum/Ku.getKu().list.size();
	}

}
