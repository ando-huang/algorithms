a = input()
b = input()

n = len(a)
m = len(b)
#dp table
dp = [[2 for x in range(m)] for y in range(n)]

#alignment table
at = [[0 for x in range(m)] for y in range(n)]

'''#populate dp and at
for i in range(n):
    dp[i][0] = (-1* i) #gap score
for i in range(m):
    dp[0][i] = (-1*i)
'''
#for reducing the size of large numbers
def mod(a):
    mod = 1000000009
    if(a >= mod):
        return a%mod
    elif(a < 0):
        return (mod + a%mod)%mod
    return a

def max(x, y, z):
    max = x
    if y > max:
        max = y
    if z > max:
        max = z
    return max
    
def recursiveAlign(aList, bList):
    s1 = mod(recursiveAlign(aList[:len(aList)-1], bList[:len(bList)-1])) #in the case that we incr with both
    if aList[len(a)-1] == bList[len(bList)-1]:
        s1 += 2
    else:
        s1 -= 1
    s1 = mod(s1)
    s2 = mod(recursiveAlign(aList[:len(aList)], bList[:len(aList)-1])-1)
    s3 = mod(recursiveAlign(aList[:len(aList)-1], bList[:len(bList)])-1)

    align = max(s1, s2, s3)

    return align

#returns highest score of a table
def geneAlign(aList, bList):
    #add 2 to score for match char
    #-1 to score if unmatched char/blank
    best = 0
    score1, score2, score3 = 0,0,0
    for i in range(1, n):
        for j in range(1, m):
            #match
            if(a[i-1]==b[j-1]):
                score1 = mod(dp[i-1][j-1]) + 2
            else:
                score1 = mod(dp[i-1][j-1]) - 1
            score2 = mod(dp[i][j-1]) -1
            score3 = mod(dp[i-1][j]) -1
            best = max(score1, score2, score3)
            dp[i][j] = best
    return

def alignTable(aList, bList):
    for i in range(1, n):
        for j in range(1, m):
            if(dp[i][j] == dp[i-1][j]-1):
                at[i][j] += mod(at[i-1][j])
            elif(dp[i][j] == dp[i][j-1]-1):
                at[i][j] += mod(at[i][j-1])
            elif(dp[i][j] == dp[i-1][j-1]-1):
                at[i][j] += mod(at[i-1][j-1])

def getScore():
    score = [dp[0][0], 0]
    for i in range(n):
        for j in range(n):
            if dp[i][j] > score[0]:
                score[0] = dp[i][j]
                score[1] = 1 #resets scorecount for new max
            elif dp[i][j] == score:
                score[1] += 1
    return score


#actual code
geneAlign(a, b)

values = getScore()

print(values[0]) #score
print(values[1]) #occurences

"""
use Dynamic programming to try and find the best alignment between a and b
 output should be 
    line1: score for alignment
    line2: number of occurences for that optimal alignment score

"""