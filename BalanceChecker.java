package stacks;
import java.util.Stack;

/**
 * A class that checks whether the parentheses, brackets, and braces 
 * in a string occur in left/right pairs.
 */
public class BalanceChecker
{
  /** Task: Decides whether the parentheses, brackets, and braces 
   *        in a string occur in left/right pairs.
   *  @param expression  a string to be checked
   *  @return true if the delimiters are paired correctly */
  public static boolean checkBalance(String expression){
    Stack<Character> openDelimiterStack = new Stack<Character>();

    int characterCount = expression.length();
    boolean isBalanced = true;
    int index = 0;
    char nextCharacter = ' ';

    
    for (; isBalanced && (index < characterCount); index++) {
      nextCharacter = expression.charAt(index);
   
      switch (nextCharacter) {
        case '(': 
        case '[': 
        case '{':
          openDelimiterStack.push(nextCharacter);
          break;
          
        case ')': 
        case ']': 
        case '}':
          if (openDelimiterStack.isEmpty())
            isBalanced = false;
          else {
            char openDelimiter = openDelimiterStack.pop();
            isBalanced = isPaired(openDelimiter, nextCharacter);
          } 
          break;
          
        default: break;
      } 
    } 
    
    if (!openDelimiterStack.isEmpty())
      isBalanced = false;
      
    return isBalanced;
  } 
  
  /** 
   *  Method:  isPaired
   *  Task: Detects whether two delimiters such as
   *  are a pair of parentheses ( ),
   *  brackets [ ], or braces { }.
   *  @param  open   a character
   *  @param  close  a character
   *  @return true   if open/close form a pair of parentheses, brackets, 
   *               or braces */
  private static boolean isPaired(char open, char close) {
    return (open == '(' && close == ')') ||
           (open == '[' && close == ']') ||
           (open == '{' && close == '}');
  } 
  
  public static void main(String[] args){
    System.out.println("Test string { [ (  ) ] } ) is balanced?" + checkBalance("{ [ (  ) ] } )"));
    System.out.println("Line a) is balanced? " + checkBalance("{a[b+(c+2)/d]+e)+f}"));
    System.out.println("Line b) is balanced? " + checkBalance("[a{b/(c-d)+e/f+g)}-h]"));
    System.out.println("Line c) is balanced? " + checkBalance("[a{b+[c(d+e)-f]+g}"));
  }
} 
