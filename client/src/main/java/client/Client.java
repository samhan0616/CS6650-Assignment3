package client;


import constant.message.ArgsMessage;
import exception.ArgsException;

import java.util.Arrays;

public class Client {

  public static void main(String[] args) {
      Executor executor = new Executor(args);
      executor.run();
  }
}
