import random


def createBallots(ballotNum, numParties):
    ballots = []
    for _ in range(ballotNum):
        num = random.randint(1, numParties)
        ballots.append(num)
    return ballots


def createPrintBallots(ballots, numParties, ballotNum):
    printBallots = []
    for _ in range(ballotNum):
        ballot = ["," for _ in range(numParties)]
        index = ballots[_] - 1
        ballot[index] = "1"
        printBallots.append("".join(ballot))
    return printBallots

def countOnesAtIndex(printBallots, index):
    count = 0
    for ballot in printBallots:
        if ballot[index] == "1":
            count += 1
    return count

def callCountOnesAtIndex(printBallots, numParties):
    for i in range(numParties):
        index = i
        print("Count of '1's at index", index, ":", countOnesAtIndex(printBallots, index))


ballotNum = 750000
numParties = 5
ballots = createBallots(ballotNum, numParties)

printBallots = createPrintBallots(ballots, numParties, ballotNum)

total_ones = sum(ballot.count("1") for ballot in printBallots)

print("Total number of '1's:", total_ones)

callCountOnesAtIndex(printBallots, numParties)

f = open("input1.txt", "w")
for ballot in printBallots:
    f.write(ballot)
    f.write("\n")

f.close()