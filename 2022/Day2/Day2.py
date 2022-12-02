
print("Advent of Code Day 2")

file = "2022\Day1\input.txt"

playRock = 1
playPaper = 2
playScissors = 3

resultLoss = 0
resultDraw = 3
resultWin = 6

finalScore = 0

def ScoreCalcPart2(input):
    if (input[0] == 'A'):
        # Rock
        if (input[1] == 'X'):
            # Lose
            return playScissors + resultLoss
        elif (input[1] == 'Y'):
            # Draw
            return playRock + resultDraw
        elif (input[1] == 'Z'):
            # Win
            return playPaper + resultWin
    elif (input[0] == 'B'):
        # Paper
        if (input[1] == 'X'):
            # Lose
            return playRock + resultLoss
        elif (input[1] == 'Y'):
            # Draw
            return playPaper + resultDraw
        elif (input[1] == 'Z'):
            # Win
            return playScissors + resultWin
    elif (input[0] == 'C'):
        # Scissors
        if (input[1] == 'X'):
            # Lose
            return playPaper + resultLoss
        elif (input[1] == 'Y'):
            # Draw
            return playScissors + resultDraw
        elif (input[1] == 'Z'):
            # Win
            return playRock + resultWin

def ScoreCalcPart1(input):
    if (input[0] == 'A'):
        # Rock
        if (input[1] == 'X'):
            # Rock
            return playRock + resultDraw
        elif (input[1] == 'Y'):
            # Paper
            return playPaper + resultWin
        elif (input[1] == 'Z'):
            # Scissors
            return playScissors + resultLoss
    elif (input[0] == 'B'):
        # Paper
        if (input[1] == 'X'):
            # Rock
            return playRock + resultLoss
        elif (input[1] == 'Y'):
            # Paper
            return playPaper + resultDraw
        elif (input[1] == 'Z'):
            # Scissors
            return playScissors + resultWin
    elif (input[0] == 'C'):
        # Scissors
        if (input[1] == 'X'):
            # Rock
            return playRock + resultWin
        elif (input[1] == 'Y'):
            # Paper
            return playPaper + resultLoss
        elif (input[1] == 'Z'):
            # Scissors
            return playScissors + resultDraw

with open(file, "r") as f:
    localmax = 0
    for line in f:
        input = line.strip().split(" ")
        print(input)
        finalScore = finalScore + ScoreCalcPart2(input)
        

print("Final Score: " + str(finalScore))
