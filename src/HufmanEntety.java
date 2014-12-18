package Hufman;

import java.util.Comparator;

class HufmanEntety implements Comparator<HufmanEntety> {
	public int count;
	public String data, code;

	HufmanEntety() {
		count = 0;
		data = code = "";
	}

	HufmanEntety(int co, String da) {
		count = co;
		data = da;
		code = "";
	}

	HufmanEntety(String d, int co) {
		count = 0;
		data = d;
		code = "";
		while (co > 1) {
			if (co % 11 == 0){
				code = "1" + code;
				co /= 11;
			}else{
				code = "0" + code;
				co -= 2;
			}

		}
		//System.out.println(code);
	}

	public String toString() {
		return "\n" + data + " " + count + " " + code + " ";
	}

	@Override
	public int compare(HufmanEntety arg0, HufmanEntety arg1) {
		return -(arg0.count - arg1.count);
	}
}