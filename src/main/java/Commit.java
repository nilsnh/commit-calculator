import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Commit {

    String commit;
    String author;
    LocalDate date;
    String message;

    public Commit(JSONObject jsonObject) {
        commit = jsonObject.getString("commit");
        author = jsonObject.getString("author");
        message = jsonObject.getString("message");
        date = LocalDate.parse(jsonObject.getString("date"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z"));
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Commit{" +
                "commit='" + commit + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Commit commit1 = (Commit) o;

        if (commit != null ? !commit.equals(commit1.commit) : commit1.commit != null) return false;
        if (author != null ? !author.equals(commit1.author) : commit1.author != null) return false;
        if (date != null ? !date.equals(commit1.date) : commit1.date != null) return false;
        return !(message != null ? !message.equals(commit1.message) : commit1.message != null);

    }

    @Override
    public int hashCode() {
        int result = commit != null ? commit.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
