def mergesort(arr){ #mergesort takes in an array
    if (len(arr) > 1): #if array is size 1 then its sorted
        mid = len(arr)//2 # mid is the middle of the array, integer index
        left = arr[:mid] #contains all elements 0-mid
        right = arr[mid:] #contains all elements mid-end

        mergesort(left) #recursive call to the left subarr
        mergesort(right) #recur call to right subarr

        i = j = k = 0 #set index counters to 0

        while i < len(left) and j < len(right):
            if left[i] < right[j]:
                arr[k] = left[i]
                i+=1
            else:
                arr[k] = right[i]
                j+= 1
            k+=1
        
        while i < len(left):
            arr[k] = left[i]
            i+=1
            k+=1
        
        while j < len(right):
            arr[k] = right[j]
            j+=1
            k+=1
}

def print_list(arr){
    for i in range(len(arr)):
        print(arr[i], end = " ")
    print()
}

if __name__ == '__main__': 
    arr = [12, 11, 13, 5, 6, 7]  
    print ("Given array is", end="\n")  
    printList(arr) 
    mergeSort(arr) 
    print("Sorted array is: ", end="\n") 
    printList(arr) 
