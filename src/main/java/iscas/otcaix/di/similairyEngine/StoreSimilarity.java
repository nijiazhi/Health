package iscas.otcaix.di.similairyEngine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreSimilarity {

	public static void main(String[] args) {
		File pfv = new File("D:\\项目\\HRS\\源码\\hrs-v3\\resource\\500sfzh_新特征_1038维.csv");
		BufferedReader reader = null;
		List<String> sfzhList = new ArrayList<String>();
		try {

			reader = new BufferedReader(new FileReader(pfv));
			String tmpString = null;

			reader.readLine();
			while ((tmpString = reader.readLine()) != null) {
				// 显示行号
				String sfzh = tmpString.split(",")[0];
				sfzhList.add(sfzh);

			}
			// System.out.println(n);
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

		System.out.println(sfzhList.size());
		for (int i = 0; i < sfzhList.size(); i++) {
			System.out.println(sfzhList.get(i));
		}

		File sfzh2PsnId = new File("D:\\项目\\HRS\\源码\\hrs-v3\\resource\\selected_sfzh.csv");
		Map<String, Long> sfzhMap = new HashMap<String, Long>();
		try {

			reader = new BufferedReader(new FileReader(sfzh2PsnId));
			String tmpString = null;

			// 一次读入一行，直到读入null为文件结束

			reader.readLine();
			while ((tmpString = reader.readLine()) != null) {
				// 显示行号
				String[] tmp = tmpString.split(",");
				String sfzh = tmp[0];
				Long psnId = Long.parseLong(tmp[1]);
				System.out.println(sfzh + " " + psnId);
				sfzhMap.put(sfzh, psnId);
			}
			// System.out.println(n);
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

		List<Long> psnIdList = new ArrayList<Long>();
		for (int i = 0; i < sfzhList.size(); i++) {
			if (sfzhMap.containsKey(sfzhList.get(i)))
				psnIdList.add(sfzhMap.get(sfzhList.get(i)));
		}

		System.out.println("------------" + psnIdList.size());

		double matrix[][] = SimilarityMatirx.matrix;

		/*
		 * System.out.println(psnIdList.size());
		 * System.out.println(matrix.length); for(int
		 * i=0;i<psnIdList.size();i++){ System.out.println(psnIdList.get(i)); }
		 */

		File similarity = new File("D:\\项目\\HRS\\源码\\hrs-v3\\resource\\similarity.csv");// patient
																						// feature
																						// vector
		BufferedWriter writer = null;
		try {

			writer = new BufferedWriter(new FileWriter(similarity));
			for (int i = 0; i < psnIdList.size(); i++) {
				for (int j = 0; j < psnIdList.size(); j++) {
					if (i != j) {

						Long psnId_i = psnIdList.get(i);
						Long psnId_j = psnIdList.get(j);
						double sim = 0f - matrix[i][j];
						writer.write(psnId_i + "," + psnId_j + "," + sim + "\n");

					}
				}
			}

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e1) {
				}
			}
		}

	}

}
