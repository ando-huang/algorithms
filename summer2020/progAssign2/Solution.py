n = int(input())

pairs = []

for i in range(n):
    l = input().strip().split()
    if l[0] == "1":
        pairs.append([l[1], int(l[2])])
    elif l[0] == "2":
        a = min(l[1], l[2])
        b = max(l[1], l[2])
        for i in range(len(pairs)):
            if pairs[i][0] >= a and pairs[i][0] <= b:
                pairs[i][1] *= -1
    else:
        answer = -1
        for i, x in enumerate(pairs):
            if x[0] == l[1]:
                answer = x[1]
        print(answer)