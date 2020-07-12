a = input()
b = input()

n = len(a)+1
m = len(b)+1

#create table
dp = [[0 for i in range(m)] for j in range(n)]
at = [[0 for i in range(m)] for j in range(n)]

for i in range(len(dp)):
    dp[i][0] = -i
for j in range(len(dp[0])):
    dp[0][j] = -j

def mod(a):
    m = 1000000009
    if a >= m:
        return a%mod
    if a<0:
        return (mod + a%mod)%mod
    return a

def max3(x, y, z):
    m = x
    if y > m:
        m = y
    if z > m:
        m = z
    return m

for i in range(1, n):
    for j in range(1, m):
        s1 = dp[i-1][j-1]
        if(a[i-1]== b[j-1]):
            s1+= 2
        else:
            s1-= 1
        s2 = dp[i-1][j]
        s3 = dp[i][j-1]
        dp[i][j] = max3(s1,s2,s3)
        if(dp[i][j]==s1):
            at[i][j] += mod(at[i-1][j-1])
        if dp[i][j] == s2:
            at[i][j] += mod(at[i-1][j])
        if dp[i][j] == s3: 
            at[i][j] += mod(at[i][j-1])
        

m = dp[1][1]
count = 0
for i in range(len(dp)):
    for j in range(len(dp[0])):
        if dp[i][j] > m:
            m = dp[i][j]
            count = 1
        elif dp[i][j] == m:
            count+= 1
print(m)
print(count)


