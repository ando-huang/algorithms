#karatsuba implementation based on memory
def karatsuba(list1, list2):
    #base case, needs edits
    if(len(list1) == 1 and len(list2) == 1):
        return [list1[0] * list2[0]] #some processable list

    midpt = len(list1)//2

    l1_high = list1[:midpt]
    l1_low = list1[midpt:]
    l2_high = list2[:midpt]
    l2_low = list2[midpt:]

    high = karatsuba(l1_high, l2_high)
    mid1 = karatsuba(l1_high, l2_low)
    mid2 = karatsuba(l1_low, l2_high)
    low = karatsuba(l1_low, l2_low)

    #add mid1 and mid2
    mid = []
    for i in range(len(mid1)):
        mid.append(mid1[i]+mid2[i])

    
    return high+low+mid
    
    
#testcode
l1 = [2,4,4,5]
l2 = [5, 1, 7, 2]
res = karatsuba(l1, l2)
print(res)
print("actual\n10 22 38 61 41 43 10")
