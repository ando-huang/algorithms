a = input()
b = input()
n = len(a)
m = len(b)

dp = [[0 for i in range(n+1)] for j in range(m+1)]
at = [[0 for i in range(n+1)]]

for i in range(len(dp)):
    dp[i][0] = -i
for j in range(len(dp[0])):
    dp[0][j] = -j

def printMatrix(mat):
    for i in range(len(mat)):
        print("[", end = "")
        for j in range(mat[0]):
            print(mat[i][j], end = " ")
        print("]\n")

def max3(x, y, z):
    m = x
    if y > m:
        m = y
    if z > m:
        m = z
    return m

def mod(a):
    m = 1000000009
    if a > m:
        return a%m
    elif a < 0:
        return (m+ (a%m)) % m
    else:
        return a

match = 2
nomatch = -1

def score(x, y):
    if x == y:
        return match
    else:
        return nomatch

def align(s1, s2):
    for i in range(1, len(s1)+1):
        for j in range(1, len(s2)+1):
            m1 = dp[i-1][j-1] + score(s1[i-1], s2[j-1])
            m2 = dp[i-1][j] -1
            m3 = dp[i][j-1] -1 
            dp[i][j] = max3(m1,m2,m3)

        