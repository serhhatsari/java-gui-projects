package src;
import java.util.ArrayList;

/**
 * Constants of the program
 * 
 * @since  27-07-2022
 * 
 */

/**
 * The class Constants contains the constant variables and static methods.
 */
public  class Constants{

   public static final String  PROGRAM_NAME = "Calculator GUI";

   public static final Character EQUAL_SIGN = '=';

   public static final String PLUS_SIGN = "+";

   public static final String MINUS_SIGN = "-";

   public static final String DIVIDE_SIGN = "÷";

   public static final String MULTIPLICATION_SIGN = "*";

   public static final Character DOT_CHARACTER = '.';

   public static final Character CLEAR_SIGN = 'C';


 /**
  * It creates an ArrayList of calculator characters
  * @return An ArrayList of Strings
  */
   public static ArrayList<String> getButtonStrings(){
    ArrayList<String> buttonStrings = new ArrayList<>();
    buttonStrings.add("7");
    buttonStrings.add("8");
    buttonStrings.add("9");
    buttonStrings.add("+");
    buttonStrings.add("4");
    buttonStrings.add("5");
    buttonStrings.add("6");
    buttonStrings.add("-");
    buttonStrings.add("1");
    buttonStrings.add("2");
    buttonStrings.add("3");
    buttonStrings.add("X");
    buttonStrings.add("0");
    buttonStrings.add(".");
    buttonStrings.add("=");
    buttonStrings.add("÷");

    return buttonStrings;
   }

}