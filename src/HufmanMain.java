package Hufman;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class HufmanMain {

	public static ArrayList<HufmanEntety> dp(ArrayList<HufmanEntety> list) {

		// System.out.println(list.size());
		if (list.size() > 2) {
			int last = list.size() - 1;

			ArrayList<HufmanEntety> tempList = new ArrayList<HufmanEntety>(
					list.subList(0, last - 1));

			tempList.add(new HufmanEntety(list.get(last).count
					+ list.get(last - 1).count, list.get(last - 1).data
					+ list.get(last).data));
			Collections.sort(tempList, new HufmanEntety());

			tempList = dp(tempList);

			boolean flag = true;

			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < tempList.size(); j++) {

					if (list.get(i).data.equals(tempList.get(j).data)) {
						list.get(i).code = tempList.get(j).code;
						break;
					} else if (tempList.get(j).data.contains(list.get(i).data)) {
						list.get(i).code = tempList.get(j).code
								+ (flag ? "1" : "0");
						flag = !flag;
					}

				}
			}
		} else if (list.size() == 2) {
			list.get(0).code += "1";
			list.get(1).code += "0";
		} else {
			list.get(0).code += "1";
		}
		return list;

	}

	static ArrayList<HufmanEntety> probabiltiy(String data) {

		ArrayList<HufmanEntety> list = new ArrayList<HufmanEntety>();
		HashMap<String, Integer> probMap = new HashMap<String, Integer>();

		for (int i = 0; i < data.length(); i++)
			probMap.put(
					data.charAt(i) + "",
					(probMap.containsKey(data.charAt(i) + "") ? probMap
							.get(data.charAt(i) + "") + 1 : 1));

		for (String key : probMap.keySet()) {
			HufmanEntety temp = new HufmanEntety();
			temp.count = probMap.get(key);
			temp.data = key;
			list.add(temp);
		}

		Collections.sort(list, new HufmanEntety());

		dp(list);

		 System.out.println(list);

		return list;
	}

	public static String Compress(ArrayList<HufmanEntety> list, String data) {
		String compresssed = "";
		HashMap<Character, String> Dic = new HashMap<Character, String>();

		for (int i = 0; i < list.size(); i++)
			Dic.put(list.get(i).data.charAt(0), list.get(i).code);

		for (int i = 0; i < data.length(); i++) {
			compresssed += Dic.get(data.charAt(i));
		}
		return compresssed;
	}

	public static String deCompress(ArrayList<HufmanEntety> list, String data,
			int len) {
		String decopress = "", temp = "";
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i++) {
			map.put(list.get(i).code, list.get(i).data);
		}

		for (int i = 0; i < len; i++) {
			temp += data.charAt(i);
			if (map.containsKey(temp)) {
				decopress += map.get(temp);
				temp = "";
			}
		}

		return decopress;
	}

	public static double Entrpy(ArrayList<HufmanEntety> list) {
		double sum = 0, result = 0;

		for (int i = 0; i < list.size(); i++)
			sum += list.get(i).count;

		//System.out.println(sum);

		double prob;

		for (int i = 0; i < list.size(); i++) {
			prob = list.get(i).count / sum;
			// System.out.println(list.get(i).count + " " + prob + " " + (prob *
			// (Math.log10( 1 / prob) / Math.log10(2))));
			result += (prob * (Math.log10(1. / prob) / Math.log10(2)));
		}
		//System.out.println(sum + " +" + result);
		return result * sum;
	}
	
	public static double actual (String compresed){
		return compresed.length();
	}
	
	public static int Header (ArrayList<HufmanEntety> list){
		return (list.size() * 6 + 8) * 8 ;
	}

	public static void main(String[] args) throws IOException {
		 new HufmanAPP();

		// ** // Sample data
	//	String data = "mohamed fouad morsy attia ahmed salem mansour";
		
		 //String data = "aaacccccccccccccccccccddddddddddddddddddddaadadadddddd";
//		 File f = new File("1.java");
//		 Scanner x = new Scanner(f);
//		 while (x.hasNext()) {
//		 data += x.nextLine() + "\n";
		
		 //}
		// System.out.print(data);

		// ** // Compression

	//	ArrayList<HufmanEntety> list = probabiltiy(data);
		// String copressed = Compress(list, data);
		//System.out.println(copressed);
//		 BinaryFile bf = new BinaryFile(list, copressed, "crazy.me");
//		 bf.genrate();
//		 bf.save();

		// ** // DeCompression

		// BinaryFile bf1 = new BinaryFile("output.txt");
		// bf1.read();
		// bf1.deGenrate();
		// String decopress = deCompress(bf1.list, bf1.data, bf1.len);
		// System.out.print(decopress);

		// ** // Entrupy
//		System.out.println(Entrpy(list));
//		System.out.println(actual(copressed));
//		System.out.print(Header(list));

	}

}
