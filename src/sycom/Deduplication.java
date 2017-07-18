package sycom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Deduplication {
	String fileName;
	String[] idArray;
	ArrayList<String> idList;
	boolean check;

	public Deduplication() {
		// �ߺ� ���� Ŭ����
		idList = new ArrayList<String>();
		check = false;

		fileName = "result.txt";
		String sir = System.getProperty("user.dir");
		System.out.println("���� ���丮�� " + sir + " �Դϴ�");
		try {
			////////////////////////////////////////////////////////////////
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String s;

			while ((s = in.readLine()) != null) {
				idArray = s.split(",");
				for (int i = 0; i < idArray.length; i++) {
					for (int j = 0; j < idList.size(); j++) {
						if (idArray[i].equals(idList.get(j))) {
							check = true;
						}
					}

					if (check == false) {
						idList.add(idArray[i]);
					} else {
						check = false;
					}
				}
			}
			
			String outputFile = sdf.format(dt).toString() + ".txt";
			
			FileWriter fw = new FileWriter(outputFile);
			BufferedWriter bw = new BufferedWriter(fw);

			for (int i = 0; i < idList.size(); i++) {
				if (i % 10 == 0 && i != 0) {
					bw.newLine();
				}
				bw.write(idList.get(i));
				bw.write(",");
			}

			bw.close();
			in.close();
			////////////////////////////////////////////////////////////////
		} catch (FileNotFoundException fe) {
			System.out.println("������ ã�� �� �����ϴ�.");
		} catch (IOException e) {
			System.out.println("IOExeption");
			System.err.println(e); // ������ �ִٸ� �޽��� ���
			System.exit(1);
		}
	}
}
