Here's a project for calculating days spent committing to a git project.It will
parse the commits.

# Usage

1. Run the provided `git-log2json.sh` script inside the repositories from which you want
to export the git history ([Original source](https://gist.github.com/textarcana/1306223)). You'll need to `pipe` the script output to .json a file.
2. Place the exported .json files inside `src/resources/exported-commit-logs/`
3. Run `gradlew -q run -Pargs="commitAuthorToLookFor@email.org"` in the project folder to run the application.
This should output the number of days you have spent committing code to a project.

# How it's calculated

1. Gather all the commits
2. Parse the dates, stripping away hours and minutes.
3. Filter out commits owned by author
4. Get all the dates and remove duplicates.
5. Count the dates (number of unique days committing code).

# License: MIT

Copyright (c) 2015 Nils Norman Haukås

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
