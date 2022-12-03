
print("Advent of Code Day 3")

file = "2022\Day3\input.txt"


def getValue(input):
    if (input.isupper()):
        value = ord(input) - 38
    else:
        value = ord(input) - 96
    return value


sumOfProps = 0
sumOfSecurity = 0
with open(file, "r") as f:
    group = []
    counter = 0
    for line in f:
        # Part 1
        compartmentSize = int(len(line) / 2)
        left = set(line.strip()[:compartmentSize])
        right = set(line.strip()[compartmentSize:])
        sumOfProps = sumOfProps + getValue(sorted(left.intersection(right))[0])

        # Part 2
        group.append(set(line.strip()))
        counter = counter + 1
        if(counter >= 3):
            print("group: " + str(group))
            common = sorted(group[0].intersection(group[1]).intersection(group[2]))
            print("Common: " + str(common) + str(getValue(common[0])))
            sumOfSecurity = sumOfSecurity + getValue(common[0])
            counter = 0
            group = []
        

print("Sum of Prorities = " + str(sumOfProps))
print("Sum of Security = " + str(sumOfSecurity))
