package is.ru.stringcalculator;
import java.util.*;
import java.util.regex.Matcher;
public class Calculator {

    private static final String defaultdelim = ",|\n";
    //   private static String[] illchars = "";
    private static ArrayList<String> illchars = new ArrayList<String>();

    public static int add(String text){
	illchars.add("*");
	illchars.add("-");
	illchars.add("+");
	System.out.println("Text is " + text);

	if(text.equals("")){
	    return 0;
	}

	else if( text.indexOf("//") == 0 ){
	    String delim = ",";
	    String[] substring = text.split("\n");
	    for (String s : substring){
		System.out.println("Substring is " + s);
	    }
	    String[] temp = substring[0].split("//");
	    for (String s : temp){
		System.out.println("temp is " + s);
	    }
	    int bracketCount = temp[1].length() - ( temp[1].replace("[","").length());
	    //temp[1] = escapeChars(temp[1]);
	    System.out.println("brackets are " + bracketCount);
	    delim = temp[1];
	    System.out.println("delim is: " + delim);	    
	    //System.out.println("delim is" + delim);
	    
	    String tmpSubstring = substring[1];
	    for (int i=0; i<bracketCount; i++){
		int endPos = delim.indexOf("]");
		String currentDelim = delim.substring(1,endPos);
		System.out.println("Current Delim: " + currentDelim);
		tmpSubstring = tmpSubstring.substring(endPos+1,tmpSubstring.length());
		substring[1] = substring[1].replace(currentDelim, ",");
	    }

	    substring[1] = escapeChars(substring[1]);
	    System.out.println("Substring is now" + substring[1]);
	    
	    if (bracketCount > 0){
		return sum(splitNumbers(substring[1],","));
	    }
	    else{
		return sum(splitNumbers(substring[1],delim));
	    }
	}	
    

	else if(text.contains(",")){
	    return sum(splitNumbers(text,defaultdelim));
	}

	else{
	    return toInt(text);
	}
    }

    private static int toInt(String number){
	return Integer.parseInt(number);
    }

    private static String[] splitNumbers(String numbers, String delim){
	String[] temp = numbers.split(delim);
	for (String s : temp){
	    System.out.println("number in splitNumbers " + s);
	}
	return temp;
    }

    private static String escapeChars(String s){
	for (String ill: illchars){
	    if (s.contains(ill)){
		s = s.replace(ill, ",");	
	  }
	   
	}

	return s;
    }
      
    private static int sum(String[] numbers){
	int total = 0;

	ArrayList<String> negnumbers = new ArrayList<String>();
	for(String number : numbers){
	    int temp = toInt(number);
	    if ( temp < 0){
		negnumbers.add(number);
	    }
	    else if (temp > 1000){
		//ignore numbers of this size
	    }
	    else{
		total += temp;
	    }
	}
	if (!(negnumbers.isEmpty())){
	    String errorMessage = "Negatives not allowed: ";
	    boolean first = true;
	    for (String s : negnumbers){
		if (first){
		    errorMessage += s;
		    first = false;
		}
		else{
		    errorMessage += "," + s;
		}
	    }
	    throw new RuntimeException(errorMessage);
	}

	return total;
    }

}
