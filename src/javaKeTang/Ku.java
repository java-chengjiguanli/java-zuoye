package javaKeTang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Ku {
	// �����õ�ѧ����Ϣ
	ArrayList<Student> list = new ArrayList<>();
	static File file = new File("");

	// ʵ�ֵ���ģʽ
	private Ku() {
	}

	private static class SETKu {
		static private final Ku ku = new Ku();
	}

	public static final Ku getKu() {
		return SETKu.ku;
	}

	// ��ȡ���ݣ�����list��
	void duqu() throws IOException {
		list.clear();
		BufferedReader b1 = new BufferedReader(new FileReader(file));
		try {
			while (true) {
				Student s = new Student();
				s.id = b1.readLine();
				s.name = b1.readLine();
				s.score = Double.valueOf(b1.readLine());
				list.add(s);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			b1.close();
		}
	}
}
