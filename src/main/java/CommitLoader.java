import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommitLoader {

    static final ClassLoader loader = CommitLoader.class.getClassLoader();

    static List<Commit> loadCommits(String directory) {
        return findFilesToRead(directory)
                .stream()
                .map(CommitLoader::readFile)
                .map(CommitLoader::parseJsonStringToCommitList)
                .flatMap(commitList -> commitList.stream())
                .collect(Collectors.toList());
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

    private static String readFile(Path filePath) {
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
