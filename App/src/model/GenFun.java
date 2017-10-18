package model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

public class GenFun {
	
	public final int GAMEPHASENONE = 0;
	public final int GAMEPHASESTARTUP = 1;
	public final int GAMEPHASEREINFORCEMENT = 2;
	public final int GAMEPHASEFORTIFICATION = 3;
	
	
	public Integer genStrToInt(String inVal) {
		return Integer.valueOf(inVal);
	}
	
	public Vector<String> genCommaSepStrToVector(String inVal) {
		String[] tmp = inVal.split(",");
		Vector<String> tmpVec = new Vector<String>();
		tmpVec.addAll(Arrays.asList(tmp));
		return tmpVec;
	}
	
	public ArrayList<String> genCommaSepStrToArrayList(String inVal) {
		String[] tmp = inVal.split(",");
		ArrayList<String> tmpVec = new ArrayList<String>();
		tmpVec.addAll(Arrays.asList(tmp));
		return tmpVec;
	}
	
	public String[] genCommaSepStrToArray(String inVal) {
		String[] tmp = inVal.split(",");
		return tmp;
	}
	
	public String genStringGetValueAfterEquals(String inVal) {
		String[] tmp = inVal.split("=");
		if(tmp.length < 2)
		{
			return null;
		}
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
	    pos += 1;
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
	
	public Integer genRandomNumber(Integer min, Integer max)
	{
		Random rand = new Random();
		return (rand.nextInt((max - min) + 1) + min);
	}
}
