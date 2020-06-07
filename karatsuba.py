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

    #combine l1_high and l1_low, same for l2
    amid = []
    bmid = []
    for i in range(len(l1_high)):
        amid.append(l1_high[i]+l1_low[i])
        bmid.append(l2_high[i]+l2_low[i])

    hmid = karatsuba(amid, bmid)
    hhi = karatsuba(l1_high, l2_high)
    hlo = karatsuba(l1_low, l2_low)

    
    for i in range(len(hhi)):
        hmid[i] -= (hhi[i]+hlo[i])      
    
    return hhi+hmid+hlo
    
    
#testcode
l1 = [2,4,4,5]
l2 = [5, 1, 7, 2]
res = karatsuba(l1, l2)
print(res)
print("10 22 38 61 41 43 10")
