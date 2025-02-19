import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Conversation implements Chatbot {

  // 
  /**
   * Constructor 
   */
  Conversation() {
  }

  /**
   * Prints out a canned response each time it's called
   */
  public String RandomResponse() {
    Random rand = new Random();
    List<String> responses = Arrays.asList("Gotcha gotcha.", "I see what you mean.", "Hmmmmmmm.", 
    "That makes sense!");
    String randomElement = responses.get(rand.nextInt(responses.size()));
    return randomElement;
  }


  /**
   * Starts and runs the conversation with the user
   * Prints two welcome lines
   */
  public void chat() {
    System.out.println("Welcome to this chatbot!");
    System.out.println("How many rounds of conversation would you like?");
  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript(List transcript) {
    for (Integer num = 0; num < transcript.size(); num++) {
      System.out.println(transcript.get(num));
    }
  }


  public static void main(String[] args) {
    List<String> transcript = new ArrayList<>();

    Conversation newconvo = new Conversation();
    newconvo.chat();
    Scanner input = new Scanner(System.in);

    //list of key words and what they will be replaced by
    List<String> keywords = Arrays.asList("I", "me", "am", "you", "my", "your", "I'm");
    List<String> newwords = Arrays.asList("You", "you", "are", "I", "your", "my", "You're");
    
    //getting the number of rounds wanted and starting the conversation
    int rounds = input.nextInt();
    System.out.println("\nGreat! How are you feeling?");
    transcript.add("Great! How are you feeling");


    //completing the rounds of conversation
    for (Integer i = 0; i < rounds; i++) {
      //i don't feel like i should need a second scanner, but it wouldn't pause for the user input with just the first scanner present
      Scanner input2 = new Scanner(System.in);
      String answer = input2.nextLine();
      transcript.add(answer);

      //false if there are no keywords in the user's input
      Boolean hasKeyword = false;

      //if there are keywords in the user's input, it replaces them with the mirrored version
      String[] answerarray = answer.split("\\s+");
      List<String> answerlist = Arrays.asList(answerarray);
      for (Integer j = 0; j < answerlist.size(); j++) {
        if (keywords.contains(answerlist.get(j))) {
          answer = answer.replace(answerlist.get(j), newwords.get(keywords.indexOf(answerlist.get(j))));
          hasKeyword = true;
        }
      }

      //if the user input doesn't contain any keywords, give a canned response
      if (hasKeyword){
        System.out.println(answer + "?");
        transcript.add(answer + "?");
      } else {
        String randomresponse = newconvo.RandomResponse();
        System.out.println(randomresponse);
        transcript.add(randomresponse);

      }
    }

    //final comments to user + transcript
    System.out.println("Goodbye!");
    transcript.add("Goodbye!");
    System.out.println("\nHere's a transcript of our conversation:");

    newconvo.printTranscript(transcript);

  }
}

//for loop syntax used https://runestone.academy/ns/books/published/java4python/Java4Python/loopsanditeration.html
//split syntax used https://stackoverflow.com/questions/4674850/converting-a-sentence-string-to-a-string-array-of-words-in-java
