package is.ru.stringcalculator;

public class Calculator {

    private static String delim = ",|\n";

    public static int add(String text){
	if(text.equals("")){
	    return 0;
	}

	else if(text.contains(",")){
	    return sum(splitNumbers(text));
	}

	else{
	    return toInt(text);
	}
    }

    private static int toInt(String number){
		return Integer.parseInt(number);
	}

    private static String[] splitNumbers(String numbers){
	    return numbers.split(delim);
	}
      
    private static int sum(String[] numbers){
	int total = 0;
        for(String number : numbers){
	    total += toInt(number);
	}
	return total;
    }

}
