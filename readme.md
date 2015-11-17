Here's a project for calculating days spent committing to a project.

# Usage

1. Run the provided `git-log2json.sh` script inside the repositories from which you want
to export the git history. You'll need to `pipe` the script output to .json a file.
2. Place the exported .json files inside `resources/exported-commit-logs/`
3. Run `gradlew -q run -Pargs="commitAuthorToLookFor@email.org"` in the project folder to run the application.
This should output the number of days you have spent committing code to a project.

# How it's calculated

1. Gather all the commits
2. Parse the dates, stripping away hours and minutes.
3. Filter out commits owned by author
4. Get all the dates and remove duplicates.
5. Count the dates (number of unique days committing code).