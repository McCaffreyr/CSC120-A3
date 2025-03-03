import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Conversation implements Chatbot {
private List<String> responses;
private List<String> transcript;
private List<String> keywords;
private List<String> newwords;


  // 
  /**
   * Constructor 
   */
  Conversation() {
    this.responses = Arrays.asList("Gotcha gotcha.", "I see what you mean.", "Hmmmmmmm.", 
    "That makes sense!");
    this.transcript = new ArrayList<>();
    this.keywords = Arrays.asList("I", "me", "am", "are", "you", "my", "your", "I'm");
    this.newwords = Arrays.asList("You", "you", "are", "am", "I", "your", "my", "You're");
  }    

  /**
   * Prints out a canned response each time it's called
   */
  public String randomResponse() {
    Random rand = new Random();
    String randomElement = this.responses.get(rand.nextInt(responses.size()));
    return randomElement;
  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    for (Integer num = 0; num < transcript.size(); num++) {
      System.out.println(transcript.get(num));
    }
  }

  /**
   * Starts and runs the conversation with the user
   * Prints two welcome lines
   */
  public void chat() {
    System.out.println("Welcome to this chatbot!");
    System.out.println("How many rounds of conversation would you like?");
    try (Scanner input = new Scanner(System.in)) {
        //getting the number of rounds wanted and starting the conversation
        int rounds = input.nextInt();
        //discarding the new line
        input.nextLine();
        System.out.println("\nGreat! How are you feeling?");
        transcript.add("Great! How are you feeling");

        //completing the rounds of conversation
        for (int i = 0; i < rounds; i++) {
          String useranswer = input.nextLine();
          transcript.add(useranswer);

          //false if there are no keywords in the user's input
          Boolean hasKeyword = false;

          //if there are keywords in the user's input, it replaces them with the mirrored version
          String[] answerarray = useranswer.split("\\s+");
          List<String> answerlist = Arrays.asList(answerarray);
            for (Integer j = 0; j < answerlist.size(); j++) {
              if (keywords.contains(answerlist.get(j))) {
                useranswer = useranswer.replace(answerlist.get(j), newwords.get(keywords.indexOf(answerlist.get(j))));
                hasKeyword = true;
              }
            }

            //if the user input doesn't contain any keywords, give a canned response
            if (hasKeyword){
              System.out.println(useranswer + "?");
              transcript.add(useranswer + "?");
            } else {
              String randomresponse = randomResponse();
              System.out.println(randomresponse);
              transcript.add(randomresponse);
            }

          }
      input.close();

      
    }

    //final comments to user + transcript
    System.out.println("Goodbye!");
    transcript.add("Goodbye!");
    System.out.println("\nHere's a transcript of our conversation:");
  }


  public static void main(String[] args) {
    Conversation newconvo = new Conversation();
    newconvo.chat();
    newconvo.printTranscript();

  }
}

//for loop syntax used https://runestone.academy/ns/books/published/java4python/Java4Python/loopsanditeration.html
//split syntax used https://stackoverflow.com/questions/4674850/converting-a-sentence-string-to-a-string-array-of-words-in-java
