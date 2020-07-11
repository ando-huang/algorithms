a = input().split()
b = input().split()

size = len(a)

#dp table
dp = [size][size]

at = [size]

def mod(n):
    if(n>10000000009):
        return n%10000000009
    else:
        return n

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