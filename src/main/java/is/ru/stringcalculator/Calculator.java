package is.ru.stringcalculator;
import java.util.*;

public class Calculator {

    private static final String defaultdelim = ",|\n";

    public static int add(String text){

	if(text.equals("")){
	    return 0;
	}

	else if( text.indexOf("//") == 0 ){
	    String delim = "";
	    String[] substring = text.split("\n");
	    String[] temp = substring[0].split("//");
	    delim = temp[1];
	    return sum(splitNumbers(substring[1],delim));
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
	return numbers.split(delim);
    }
      
    private static int sum(String[] numbers){
	int total = 0;

	ArrayList<String> negnumbers = new ArrayList<String>();
	for(String number : numbers){
	    int temp = toInt(number);
	    if ( temp < 0){
		negnumbers.add(number);
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
