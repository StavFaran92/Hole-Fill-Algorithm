public class Test_CommandLine {

  public static void main(String ...args)
  {
    int exitCode = new picocli.CommandLine(new CommandLine()).execute("./hello.txt"," ./hello.txt",
        "3", "0.00001");
  }
}
