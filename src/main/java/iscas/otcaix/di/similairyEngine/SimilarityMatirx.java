package iscas.otcaix.di.similairyEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取相似度矩阵文件，得到病人相似度
 * 
 * @author andyni
 */
public class SimilarityMatirx {

	public static double[][] matrix;
	public static String SimilarityMatirxDumpPath;

	static {
		File file = new File(SimilarityMatirxDumpPath);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int n = 0;
			// 一次读入一行，直到读入null为文件结束
			List<double[]> list = new ArrayList<double[]>();
			reader.readLine();
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				String[] line = tempString.split(",");

				double[] d = new double[line.length - 1];
				for (int i = 1; i < line.length; i++) {
					d[i - 1] = Double.parseDouble(line[i]);
				}
				list.add(d);
				n++;
			}
			// System.out.println(n);
			reader.close();
			matrix = new double[n][n];
			for (int i = 0; i < n; i++) {
				matrix[i] = list.get(i);
			}
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

	}

	public static void main(String[] args) {
		System.out.println(SimilarityMatirx.matrix[0][5]);
	}

}
