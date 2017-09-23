package model;
import java.util.Arrays;
import java.util.Vector;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.*;

public class GenFun {
	public Integer genStrToInt(String inVal) {
		return Integer.valueOf(inVal);
	}
	
	public Vector<String> genCommaSepStrToVector(String inVal) {
		String[] tmp = inVal.split(",");
		Vector<String> tmpVec = new Vector<String>();
		tmpVec.addAll(Arrays.asList(tmp));
		return tmpVec;
	}
	
	public String[] genCommaSepStrToArray(String inVal) {
		String[] tmp = inVal.split(",");
		return tmp;
	}
	
	public String genStringGetValueAfterEquals(String inVal) {
		String[] tmp = inVal.split("=");
		return tmp[1];
	}
	
	public String[] genStringGetSplitArrayEquals(String inVal) {
		String[] tmp = inVal.split("=");
		return tmp;
	}
	
	public int genOrdinalIndexOf(String str, String substr, int n) {
	    int pos = str.indexOf(substr);
	    while (--n > 0 && pos != -1)
	        pos = str.indexOf(substr, pos + 1);
	    return pos;
	}
	
	public BufferedReader genOpenFileToBufferedReader(String inPath) {
		File tmp = new File(inPath);
		BufferedReader rtTmp = null;
		try {
			rtTmp = new BufferedReader(new FileReader(tmp));
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return rtTmp;
	}
}
