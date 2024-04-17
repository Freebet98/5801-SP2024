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


---Test Files---

CPL
------------------------
Name                   | Information
CPLInput01.txt         | Democratic - 24936, Republican - 25093, Green - 25067, Independant - 24904
CPLInput02.txt         | Incorrect Header
CPLInput03.txt         | No Votes, Incorrect Header
CPLPartyInfo01.txt     | 6 Properly formatted parties only
CPLPartyInfo02.txt     | 6 Properly formatted parties only (Same as CPLPartyInfo01.txt)
CPLPartyInfo03.txt     | Candidates missing from Independent Party
CPLBallotTest01.txt    | 3 parties, properly formatted Ballots, Grass - 26646, Pluto - 24742, Republican - 26612
CPLBallotTest02.txt    | 3 parties, not properly formatted Ballots
CPLBallotTest03.txt    | 3 parties, not properly formatted Ballots
CPLBallotTest04.txt    | 4 parties, properly formatted Ballots, Green - 22648, Independant - 22613, Grass - 22231, Pluto - 22508
CPLInputMultiple01.txt | 5 Parties, properly formatted, Dem - 24039, Rep - 23770, Green - 24301, New - 23935, Ind - 23955
CPLInputMultiple02.txt | 5 Parties, properly formatted, Dem - 23883, Rep - 23831, Green - 23864, New - 24110, Ind - 24312
CPLInputMultiple03.txt | 5 Parties, properly formatted, Dem - 24019, Rep - 24130, Green - 23862, New - 24193, Ind - 23796

OPL
------------------------
Name                   | Information
OPLInput01.txt         | 2 Parties, 5 Candidates, Pluto - Becky: 23971, Mariah: 24136, Green - Jonah: 24014, Radius: 24006, Louis: 23873
OPLPartyInfo01.txt     | 5 properly formatted candidates only, 2 parties
OPLPartyInfo02.txt     | Candidates missing from Pluto party, only Green has candidates
OPLBallotTest01.txt    | 5 candidates, properly formatted Ballots, Pluto - Becky: 20105, Mariah: 19943, Green - Jonah: 19943, Radius: 20020, Louis: 19989
OPLBallotTest02.txt    | 5 candidates, not properly formatted Ballots
OPLBallotTest03.txt    | 5 candidates, not properly formatted Ballots
OPLBallotTest04.txt    | 5 candidates, properly formatted Ballots, Pluto - Becky: 20132, Mariah: 19920, Green - Jonah: 20054, Radius: 19826, Louis: 20068
OPlInputMultiple01.txt | 5 candidates, 3 parties, Pluto - Becky: 30025, Jonah: 30092, Green - Einstein: 29829, Nasa: 29890, Ind - John: 30164
OPLInputMultiple02.txt | 5 candidates, 3 parties, Pluto - Becky: 19098, Jonah: 19085, Green - Einstein: 18999, Nasa: 18955, Ind - John: 18863
OPLInputMultiple03.txt | 5 candidates, 3 parties, Pluto - Becky: 51912, Jonah: 52053, Green - Einstein: 52311, Nasa: 52201, Ind - John: 51523

MPO
------------------------
Name                   | Information
MPOInput01.txt         | 4 parties, 5 candidates, Jon - 20169, Shana - 20132, Risako - 20108, Chris - 19636, Daniel - 19955
MPOPartyInfo01.txt     | 4 parties, 5 candidates: properly formatted
MPOPartyInfo02.txt     | 4 parties, 5 candidates: missing party info for one candidate
MPOPartyInfo03.txt     | 4 parties, 5 candidates: missing candidate name for one party
MPOBallotTest01.txt    | 4 parties, 5 candidates: Properly formatted Ballots: Jon - 150224, Shana - 150437, Risako - 149660, Chris - 150129, Daniel - 149550, 750000 votes
MPOBallotTest02.txt    | not properly formatted, an l instead of a 1 in one vote
MPOBallotTest03.txt    | no votes
MPOInputMulti01.txt    | 4 parties, 6 candidates: Jon - 166483, Shana - 166680, Risako - 166996, Chris - 166385, Carl - 166630, Daniel - 166826, 1000000 votes
MPOInputMulti02.txt    | 4 parties, 6 candidates: Jon - 83580, Shana - 83161, Risako - 83237, Chris - 83570, Carl - 83382, Daniel - 83070, 500000 votes
MPOInputMulti03.txt    | 4 parties, 6 candidates: Jon - 100405, Shana - 100572, Risako - 100124, Chris - 100003, Carl - 100527, Daniel - 100369
