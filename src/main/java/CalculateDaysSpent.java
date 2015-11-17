import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CalculateDaysSpent {

    static String whooseCommitsToLookFor = null;

    public static void main (String [] args) throws Exception {

        whooseCommitsToLookFor = Util.getCommentAuthorEmail(args);
        System.out.println("The commit author email to filter commits by: " + whooseCommitsToLookFor);

        List<Commit> allCommits = CommitLoader.loadCommits("exported-commit-logs");
        System.out.println("Total number of commits found: " + allCommits.size());

        List<Commit> commitsOwnedByAuthor = allCommits
                .stream()
                .filter(CalculateDaysSpent::isCommitByMe)
                .collect(Collectors.toList());

        System.out.println("Number of commits owned by commit author \"" + whooseCommitsToLookFor + "\": " +
                commitsOwnedByAuthor.size());

        Set<LocalDate> daysSpentCommittingCode = commitsOwnedByAuthor
                .stream()
                .map(commit -> commit.getDate())
                .collect(Collectors.toSet());

        System.out.println("Days spent committing code to the project " + daysSpentCommittingCode.size());

    }

    private static boolean isCommitByMe(Commit commit) {
        if (commit.getAuthor().contains(whooseCommitsToLookFor)) {
            return true;
        } else {
            return false;
        }
    }

}
