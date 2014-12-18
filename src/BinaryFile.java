package Hufman;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class BinaryFile {

	public ArrayList<HufmanEntety> list;
	ArrayList<Integer> integer, code;
	public String data;
	File file;
	public int len;

	BinaryFile(String path) {
		file = new File(path);
		list = new ArrayList<HufmanEntety>();
		integer = new ArrayList<Integer>();
		code = new ArrayList<Integer>();
		data = "";
	}

	BinaryFile(ArrayList<HufmanEntety> l, String d, String path) {
		list = l;
		data = d;
		file = new File(path);
		integer = new ArrayList<Integer>();
		code = new ArrayList<Integer>();
	}

	public void genrate() {
		int num;
		for (int i = 0; i < list.size(); i++) {
			
			if (list.get(i).code.charAt(0) == '1')
				num = 11;
			else
				num = 2;
			
			for (int j = 1; j < list.get(i).code.length(); j++) {
				if (list.get(i).code.charAt(j) == '1')
					num *= 11;
				else
					num += 2;
			}
//			System.out.println(list.get(i).code + " " + num + " "
//					+ (num >= Integer.MAX_VALUE ? true : false));
			code.add(num);
		}
		num = 0;
		for (int i = 0, j = 1; i < data.length(); i++, j++) {
			if (j % 32 == 0) {
				j = 1;
				integer.add(num);
				num = (int) (Math.pow(2, j - 1) * (data.charAt(i) == '1' ? 1
						: 0));
			} else
				num += Math.pow(2, j - 1) * (data.charAt(i) == '1' ? 1 : 0);
		}
		if (num != 0) {
			integer.add(num);
		}
	}

	public void deGenrate() {
		int num;
		for (int i = 0; i < integer.size(); i++) {
			num = integer.get(i);
			for (int j = 0; j < 31; j++) {
				data += (num % 2 == 1 ? "1" : "0");
				num /= 2;
			}
		}
	}

	public void save() throws IOException {
		FileOutputStream output = new FileOutputStream(file);
		DataOutputStream out = new DataOutputStream(output);
		out.writeShort(list.size());
		for (int i = 0; i < list.size(); i++) {
			out.writeChar(list.get(i).data.charAt(0));
			out.writeInt(code.get(i));
		}
		out.writeInt(data.length());
		out.writeShort(integer.size());
		for (int i = 0; i < integer.size(); i++) {
			out.writeInt(integer.get(i));
		}
		out.close();
		output.close();
	}

	public void read() throws IOException {
		FileInputStream input = new FileInputStream(file);
		DataInputStream in = new DataInputStream(input);
		int temp = in.readShort();

		for (int i = 0; i < temp; i++) {
			list.add(new HufmanEntety(in.readChar() + "", in.readInt()));
		}
		
		len = in.readInt();
		temp = in.readShort();
		for (int i = 0; i < temp; i++) {
			integer.add(in.readInt());
		}
		input.close();
		in.close();

	}
}
