import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        System.out.println("Введите команду или help для списка команд \n" +
                "путь указывается в кавычках \"path\"");
        Scanner scanner = new Scanner(System.in);
        outerLoop:
        while (true) {
            String command = scanner.nextLine();
            String cl;
            String path = null;
            String permissions = null;
            String regex = "(?<!\"[^\\s]*)\\s(?![^\"]*\"$)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(command);
            int count = 0;
            while (matcher.find()) {
                count++;
            }

            if (count <= 1 && !command.contains("\"")) {
                cl = command.strip();
                switch (cl) {
                    case "help":
                        MyFile.help();
                        break;
                    case "exit":
                        break outerLoop;
                    default:

                }
            } if (count == 1 && command.contains("\"")) {
                cl = command.substring(0, command.indexOf(" "));
                path = command.substring(command.indexOf("\"") + 1, command.lastIndexOf("\""));
                switch (cl) {
                    case "ls":
                        MyFile.listDirectory(path);
                        break;
                    case "ls_py":
                        MyFile.listPythonFiles(path);
                        break;
                    case "is_dir":
                        MyFile.isDirectory(path);
                        break;
                    case "define":
                        MyFile.define(path);
                        break;
                    case "readmod":
                        MyFile.printPermissions(path);
                        break;
                    case "cat":
                        MyFile.printContent(path);
                        break;
                    case "append":
                        MyFile.appendFooter(path);
                        break;
                    case "bc":
                        MyFile.createBackup(path);
                        break;
                    case "greplong":
                        MyFile.printLongestWord(path);
                        break;
                }
            } else if (count >= 2 && command.contains("\"")){
                cl = command.substring(0, command.indexOf(" "));
                path = command.substring(command.indexOf("\""), command.lastIndexOf("\""));
                permissions = command.substring(command.lastIndexOf("\"")).strip();

                if (cl.equals("setmod"))
                    MyFile.setPermissions(path, permissions);
            } else
                System.out.println("No matching case found");
        }
    }
}