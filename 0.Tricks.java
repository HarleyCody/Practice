return a = 1; // it will return 1.

Array.copyOfRange is slower than List.toArray(new int[List.size()][]

int[] temp = connect.get(i).stream().mapToInt(Integer::intValue).toArray(); // transfer list to array int[]

// first condition in for is only exceuted once, second will be terminate condition to for loop, third can be a operation statement.
for (i = l; ++i < newIntervals.length; newIntervals[i] = intervals[r++]);
_________________________________________________________________________________________________________________________________________                                         
Difference between ArrayList and LinkedList
ArrayList and LinkedList both implements List interface and maintains insertion order. Both are non synchronized classes.

However, there are many differences between ArrayList and LinkedList classes that are given below.

ArrayList	LinkedList
1) ArrayList internally uses a dynamic array to store the elements.	
  LinkedList internally uses a doubly linked list to store the elements.
2) Manipulation with ArrayList is slow because it internally uses an array. If any element is removed from the array, all the bits are shifted in memory.	
  Manipulation with LinkedList is faster than ArrayList because it uses a doubly linked list, so no bit shifting is required in memory.
3) An ArrayList class can act as a list only because it implements List only.
  LinkedList class can act as a list and queue both because it implements List and Deque interfaces.
4) ArrayList is better for ‘storing’ and accessing data.
  LinkedList is better for ‘manipulating’ data.
_________________________________________________________________________________________________________________________________________
Description
The java.util.Arrays.binarySearch(Object[] a, int fromIndex, int toIndex, Object key) method searches a range of the specified array 
for the specified object using the binary search algorithm.
The range must be sorted into ascending order according to the natural ordering of its elements before making this call.
If it is not sorted, the results are undefined.

Arrays.binarySearch() method

public static int binarySearch(Object[] a, int fromIndex, int toIndex, Object key)
Parameters
a − This is the array to be searched.

fromIndex − This is the index of the first element (inclusive) to be searched.

toIndex − This is the index of the last element (exclusive) to be searched.

key − This is the value to be searched for.

Return Value
This method returns index of the search key, if it is contained in the array, else it returns (-(insertion point) - 1). 
The insertion point is the point at which the key would be inserted into the array; the index of the first element in the 
range greater than the key, or toIndex if all elements in the range are less than the specified key.
