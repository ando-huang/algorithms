a = input().split()
b = input().split()

n = len(a)

#dp table
dp = [n][n]

#alignment table
at = [n][n]

#for reducing the size of large numbers
def mod(a):
    modulo = 1000000009
    if(a >= modulo):
        return a%modulo
    elif(a < 0):
        return (modulo + a%modulo)%modulo
    return a
    

#returns highest score of a table
def geneAlign(aList, bList):
    #add 2 to score for match char
    #-1 to score if unmatched char
    return 


"""use Dynamic programming to try and find the best alignment between a and b
 output should be 
    line1: score for alignment
    line2: number of occurences for that optimal alignment score

"""