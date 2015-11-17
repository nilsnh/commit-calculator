import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CalculateDaysSpent {

    static final ClassLoader loader = CalculateDaysSpent.class.getClassLoader();
    static String whooseCommitsToLookFor = null;

    public static void main (String [] args) throws Exception {
        ArrayList<String> arguments = parseArgs(args);
        if (arguments.isEmpty()) {
            System.out.println("No commit author email was passed in from command line");
            System.out.println("Please specify like so:");
            System.out.println("./gradlew -q run -Pargs=\"author@someemail.org\"");
            System.exit(0);
        }
        else {
            whooseCommitsToLookFor = arguments.get(0);
        }
        System.out.println("The commit author email to filter commits by: " + whooseCommitsToLookFor);

        List<Path> filesToRead = findFilesToRead("exported-commit-logs");

        List<Commit> allParsedCommits = filesToRead
                .stream()
                .map(CalculateDaysSpent::readFile)
                .map(CalculateDaysSpent::parseJsonStringToCommitList)
                .flatMap(commitList -> commitList.stream())
                .collect(Collectors.toList());

        System.out.println("Total number of commits found: " + allParsedCommits.size());

        List<Commit> commitsOwnedByAuthor = allParsedCommits
                .stream()
                .filter(CalculateDaysSpent::isCommitByMe)
                .collect(Collectors.toList());

        System.out.println("Number of commits owned by commit author \"" + whooseCommitsToLookFor + "\": " +
                commitsOwnedByAuthor.size());

        Set<LocalDate> daysSpentCommittingCode = commitsOwnedByAuthor.stream()
                .map(commit -> commit.getDate())
                .collect(Collectors.toSet());

        System.out.println("Days spent committing code to the project " + daysSpentCommittingCode.size());

    }

    private static ArrayList<String> parseArgs(String[] args) {
        ArrayList<String> result = new ArrayList<>();
        for (String stringArg : args) {
            result.add(stringArg);
        }
        return result;
    }

    private static boolean isCommitByMe(Commit commit) {
        if (commit.getAuthor().contains(whooseCommitsToLookFor)) {
            return true;
        } else {
            return false;
        }
    }

    private static List<Path> findFilesToRead(String fileDir) {
        List<Path> listOfFilePaths = null;
        try {
            listOfFilePaths = Files
                    .list(Paths.get(loader.getResource(fileDir).getPath()))
                    .filter(path -> path.toString().endsWith(".json"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return listOfFilePaths;
    }

    private static String readFile (Path filePath) {
        String fileContent = null;
        try {
            fileContent = Files.readAllLines(filePath)
                    .stream()
                    .collect(Collectors.joining(""));;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    private static ArrayList<Commit> parseJsonStringToCommitList(String jsonArrayString) {
        JSONArray jsonArray = new JSONArray(jsonArrayString);
        ArrayList<Commit> result = new ArrayList<>();
        jsonArray.forEach((objectInList -> result.add(new Commit((JSONObject) objectInList))));
        return result;
    }

}
