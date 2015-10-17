package is.ru.stringcalculator;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

//import org.junit.*;

public class CalculatorTest {

    public static void main(String args[]) {
	org.junit.runner.JUnitCore.main("is.ru.stringcalculator.CalculatorTest");
    }
                    
    @Test
    public void testEmptyString(){
	assertEquals(0, Calculator.add(""));
    }
    
    @Test
    public void testOneNumber(){
	assertEquals(1, Calculator.add("1"));
    }
    
    @Test
    public void testTwoNumbers(){
	assertEquals(3, Calculator.add("1,2"));
    }	

    @Test
    public void testThreeNumbers(){
	assertEquals(6, Calculator.add("1,2,3"));
    }

    @Test
    public void testMultipleNumbers(){
	assertEquals(55, Calculator.add("1,2,3,4,5,6,7,8,9,10"));
    }
    
    @Test
    public void testNewlineDelimiter(){
	assertEquals(10, Calculator.add("1\n2,3\n4"));
    }
    
    @Test
    public void testNewDelimiter(){
	assertEquals(3, Calculator.add("//;\n1;2"));
    }
         
    @Test
    public void testNegatives() {
	try {
	    Calculator.add("2,-4,3,-5,-6");
	    fail("Exception expected.");
	}
 
	catch (RuntimeException ex) {
	    assertEquals("Negatives not allowed: -4,-5,-6", ex.getMessage());
	}
    
    }
    
    @Test
    public void testLargeNumbers(){
	assertEquals(2, Calculator.add("1001,2"));
    }

    @Test
    public void testDelimitersize(){
	assertEquals(6, Calculator.add("//[;;;]\n1;;;2;;;3"));
    }
    
    @Test
    public void testWithBrackets(){
	assertEquals(6, Calculator.add("//[***]\n1***2***3"));
    }
    
    @Test
    public void testMultipleDelimiters(){
	assertEquals(6, Calculator.add("//[*][%]\n1*2%3"));
    }

    @Test
    public void testMultipleDelimitersAnySize(){
	assertEquals(10, Calculator.add("//[**][%%%][++++]\n1**2%%%3++++4"));
    }

}
