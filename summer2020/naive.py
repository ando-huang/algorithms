a = input()
b = input()

n = len(a)
m = len(b)

dp = [[0 for i in range(m+1)] for j in range(n+1)] #if its a match at a0 bj then that align val is 2 there
at = [[0 for i in range(m)] for j in range(n)]
for i in range(len(dp)):
    dp[i][0] = -i
for j in range(len(dp[0])):
    dp[0][j] = -j

def mod(a):
    mod = 1000000009
    if(a >= mod):
        return a%mod
    elif(a < 0):
        return (mod + a%mod)%mod
    return a


def max3(x, y, z):
    m = x
    if y > m:
        m = y
    if z > m:
        m = z
    return m

def populateDP(a, b):
    for i in range(1, n+1):
        for j in range(1, m+1):
            s1 = dp[i-1][j-1]
            if a[i-1] == b[j-1]:
                s1 += 2
            else:
                s1 -= 1
            s2 = dp[i-1][j]
            s3 = dp[i][j-1]
            dp[i][j] = max3(s1, s2, s3)
            print(dp[i][j])
            print(i+j)

def alignTable(aList, bList):
    for i in range(1, n):
        for j in range(1, m):
            if(dp[i][i] == dp[i-1][j]-1):
                at[i][j] += mod(at[i-1][j])
            elif(dp[i+1][j+1] == dp[i+1][j]-1):
                at[i][j] += mod(at[i][j-1])
            elif(dp[i+1][j+1] == dp[i][j]-1):
                at[i][j] += mod(at[i-1][j-1])

def getScore():
    score = [at[0][0], 0]
    for i in range(len(at)):
        for j in range(len(at[0])):
            if at[i][j] > score[0]:
                score[0] = at[i][j]
                score[1] = 1 #resets scorecount for new max
            elif at[i][j] == score:
                score[1] += 1
    return score

populateDP(a, b)
alignTable(a, b)
#what is align table for
score = getScore()
print(score[0])
print(score[1])