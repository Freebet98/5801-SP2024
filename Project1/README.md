# repo-Team2
We are using out 1 time extension for this portion of the project (Unit Testing and Implementation)
(Derrick Dischinger, Bethany Freeman, and Rock Zgutowicz)

---Instructions to Run Program Through Terminal---
1. Open the repo-Team2 directory
2. Navigate to the java folder, this is located within repo-Team2/Project1/src/main
3. Compile the main class with the command javac Main.java
4. Use the command cd ../../../.. to get back to the repo-Team2 directory base folder
5. run the command java -classpath Project1/src/main/java Main fileName.csv

*Note: Please only use the name of the input file when using the command on instruction 5 or 
while giving input to the program while it is running. Always include the extension

---Information about where input files should go---
-* This should be done before running the command in step 5 above
   Files get placed in the BallotFile, this is located in repo-Team2/Project1/src
   If files are placed anywhere else you can assume that your file will not be found

Test Files
------------------------
Name                | Information
CPLInput01.txt      | Democratic - 24936, Republican - 25093, Green - 25067, Independant - 24904
CPLInput02.txt      | Incorrect Header
CPLInput03.txt      | No Votes, Incorrect Header
CPLPartyInfo01.txt  | 6 Properly formatted parties only
CPLPartyInfo02.txt  | 6 Properly formatted parties only (Same as CPLPartyInfo01.txt)
CPLPartyInfo03.txt  | Candidates missing from Independent Party
CPLBallotTest01.txt | 3 parties, properly formatted Ballots, Grass - 26646, Pluto - 24742, Republican - 26612
CPLBallotTest02.txt | 3 parties, not properly formatted Ballots
CPLBallotTest03.txt | 3 parties, not properly formatted Ballots
CPLBallotTest04.txt | 4 parties, properly formatted Ballots, Green - 22648, Independant - 22613, Grass - 22231, Pluto - 22508

