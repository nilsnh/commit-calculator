import java.util.ArrayList;

public class Util {
    private static ArrayList<String> parseArgs(String[] args) {
        ArrayList<String> result = new ArrayList<>();
        for (String stringArg : args) {
            result.add(stringArg);
        }
        return result;
    }

    static String getCommentAuthorEmail(String[] args) {
        ArrayList<String> arguments = parseArgs(args);
        if (arguments.isEmpty()) {
            System.out.println("No commit author email was passed in from command line");
            System.out.println("Please specify like so:");
            System.out.println("./gradlew -q run -Pargs=\"author@someemail.org\"");
            System.exit(0);
            return null;
        }
        else {
            return arguments.get(0);
        }
    }
}
