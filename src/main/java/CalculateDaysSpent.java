import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CalculateDaysSpent {

    public static void main (String [] args) throws Exception {

        final String whooseCommitsToLookFor = Util.getCommentAuthorEmail(args);
        System.out.println("The commit author email to filter commits by: " + whooseCommitsToLookFor);

        List<Commit> allCommits = CommitLoader.loadCommits("exported-commit-logs");
        System.out.println("Total number of commits found: " + allCommits.size());

        List<Commit> commitsOwnedByAuthor = allCommits
                .stream()
                .filter(commit -> commit.getAuthor().contains(whooseCommitsToLookFor))
                .collect(Collectors.toList());

        System.out.println("Number of commits owned by commit author \"" + whooseCommitsToLookFor + "\": " +
                commitsOwnedByAuthor.size());

        Set<LocalDate> daysSpentCommittingCode = commitsOwnedByAuthor
                .stream()
                .map(commit -> commit.getDate())
                .collect(Collectors.toSet());

        System.out.println("Days spent committing code to the project " + daysSpentCommittingCode.size());

    }

}
