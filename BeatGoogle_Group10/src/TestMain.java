import java.io.IOException;
import java.util.Scanner;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;

public class TestMain {
	

	public static void main(String[] args) throws IOException  {

			WordCounter wc = new WordCounter("https://zh-tw.facebook.com/RayDuEnglish/");
			System.out.println(wc.facebookLike());
		
	
		
	}

}
