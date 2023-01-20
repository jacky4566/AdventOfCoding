
print("Advent of Code Day 4")

file = "2022\Day4\input.txt"

countFullContain = 0
countPartialContain = 0

def doesFullContain(input):
    leftMin = int(input[0].split("-")[0])
    leftMax = int(input[0].split("-")[1])
    rightMin = int(input[1].split("-")[0])
    rightMax = int(input[1].split("-")[1])
    # Left contains right
    if (leftMin <= rightMin and leftMax >= rightMax):
        return True
    # right contains left
    elif (rightMin <= leftMin and rightMax >= leftMax):
        return True
    return False

def doesPartialContain(input):
    leftMin = int(input[0].split("-")[0])
    leftMax = int(input[0].split("-")[1])
    rightMin = int(input[1].split("-")[0])
    rightMax = int(input[1].split("-")[1])
    # Left contains right
   
    if (leftMax >= rightMin and leftMin <= rightMax):
        return True
    return False

with open(file, "r") as f:
    for line in f:
        input = line.strip().split(",")
        fullContain = doesFullContain(input)
        partialContain = doesPartialContain(input)
        if fullContain:
            countFullContain = countFullContain + 1
        if partialContain:
            print(input)
            countPartialContain = countPartialContain + 1

print("countFullContain: " + str(countFullContain))
print("countPartialContain: " + str(countPartialContain))


# guess 951
