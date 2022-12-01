
print("Advent of Code Day 1")

file = "2022\Day1\input.txt"

topElf = 0
twoElf = 0
threeElf = 0

with open(file, "r") as f:
    localmax = 0
    for line in f:
        #print(line, end='')
        if (line == "\n"):
            print("Last Elf " + str(localmax))
            if (localmax > topElf):
                threeElf = twoElf
                twoElf = topElf
                topElf = localmax
            elif (localmax > twoElf):
                threeElf = twoElf
                twoElf = localmax
            elif (localmax > threeElf):
                threeElf = localmax
            localmax = 0
        elif (line != "\n"):
            localmax = localmax + int(line)

print("topElf " + str(topElf))
print("twoElf " + str(twoElf))
print("threeElf " + str(threeElf))
print("Total " + str(topElf + twoElf + threeElf))
