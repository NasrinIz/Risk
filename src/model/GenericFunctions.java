package src.model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

/**
 * This class contains generic functions and common constants that are used
 * across the whlole application.
 * @author Team20
 *
 */
public class GenericFunctions 
{
	
	public final int GAMEPHASENONE = 0;
	public final int GAMEPHASESTARTUP = 1;
	public final int GAMEPHASEREINFORCEMENT = 2;
	public final int GAMEPHASEFORTIFICATION = 3;
	
	public final int EDITORMODENONE = 0;
	public final int EDITORMODEEDIT = 1;
	public final int EDITORMODECREATE = 2;
	
	public Integer genStrToInt(String inVal) 
	{
		return Integer.valueOf(inVal);
	}
	
	/**
	 * Separates the string on basis of comma as delimiter.
	 * @param inVal Input String
	 * @return tmpVec Vector containing resultant strings.
	 */
	public Vector<String> genCommaSepStrToVector(String inVal) 
	{
		String[] tmp = inVal.split(",");
		Vector<String> tmpVec = new Vector<String>();
		tmpVec.addAll(Arrays.asList(tmp));
		return tmpVec;
	}
	
	/**
	 * This function separates strings according to comma
	 * and return them in an array list.
	 * @param inVal The string to be separated.
	 * @return tmpVec the arraylist containing separated strings.
	 */
	public ArrayList<String> genCommaSepStrToArrayList(String inVal) 
	{
		String[] tmp = inVal.split(",");
		ArrayList<String> tmpVec = new ArrayList<String>();
		tmpVec.addAll(Arrays.asList(tmp));
		return tmpVec;
	}
	
	/**
	 * This function separates the string and puts the result in an array
	 * @param inVal String to be separated.
	 * @return tmp The resultant array containing separated strings.
	 */
	public String[] genCommaSepStrToArray(String inVal) 
	{
		String[] tmp = inVal.split(",");
		return tmp;
	}
	
	/**
	 * This function returns the value after '=' operator.
	 * @param inVal The input string containing an operand and an assignment operator.
	 * @return tmp[1] The output string after assignment operator.
	 */
	public String genStringGetValueAfterEquals(String inVal) 
	{
		String[] tmp = inVal.split("=");
		if(tmp.length < 2)
		{
			return null;
		}
		return tmp[1];
	}
	
	/**
	 * This function splits the array using '=' as delimiter,
	 * and returns the resultant strings.
	 * @param inVal The input string.
	 * @return tmp The resultant separated strings.
	 */
	public String[] genStringGetSplitArrayEquals(String inVal) 
	{
		String[] tmp = inVal.split("=");
		return tmp;
	}
	
	/**
	 * This functions returns the specific occurance of a substring in a string
	 * @param str The input string
	 * @param substr The input substring
	 * @param n The number of occurence
	 * @return pos The returned position.
	 */
	public int genOrdinalIndexOf(String str, String substr, int n) 
	{
	    int pos = str.indexOf(substr);
	    while (--n > 0 && pos != -1)
	        pos = str.indexOf(substr, pos + 1);
	    pos += 1;
	    return pos;
	}
	
	/**
	 * This function opens the file to a buffered reader.
	 * @param inPath Path at which, source file is present.
	 * @return rtTmp The returned buffered reader.
	 */
	public BufferedReader genOpenFileToBufferedReader(String inPath) 
	{
		File tmp = new File(inPath);
		BufferedReader rtTmp = null;
		try 
		{
			rtTmp = new BufferedReader(new FileReader(tmp));
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		return rtTmp;
	}
	
	/**
	 * This function generates random number between given limits.
	 * @param min The minimum number limit
	 * @param max The maximum number limit
	 * @return The returned random number.
	 */
	public Integer genRandomNumber(Integer min, Integer max)
	{
		Random rand = new Random();
		return (rand.nextInt((max - min) + 1) + min);
	}
}
