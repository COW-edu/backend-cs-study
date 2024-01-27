package org.example.view;

import java.util.Scanner;
import org.example.view.message.GuideMessage;

public class ApplicationView {

  public static final String LINE_BREAK = "\n";

  public int selectProcess() {
    print(GuideMessage.PROCESS_OPTION + LINE_BREAK);
    print(GuideMessage.SELECT_NUMBER);
    return Integer.parseInt(getInput());
  }

  public int selectAccount() {
    print(GuideMessage.ACCOUNT_OPTION + LINE_BREAK);
    print(GuideMessage.SELECT_NUMBER);
    return Integer.parseInt(getInput());
  }

  private void print(String message) {
    System.out.print(message);
  }

  private String getInput()  {
    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();
    sc.close();
    return input;
  }
}
