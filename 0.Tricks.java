return a = 1; // it will return 1.

Array.copyOfRange is slower than List.toArray(new int[List.size()][]
use for loop to transfer is fastest way.

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
________________________________________________java.util.Arrays.binarySearch___________________________________________________________
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

__________________________________________________________________Union Find________________________________________________________________
C:
int getfather(int v)
{
    if (father[v]==v)
      return v;
    else
    {
        father[v]=getfather(father[v]);//路径压缩
        return father[v];
     }
}
//compress by rank
void Union(int x ,int y)

{
     fx = getfather(x);
     fy = getfather(y);

     if (rank[fx]>rank[fy])
        father[fy] = fx;
     else
     {
        father[fx] = fy;
        if(rank[fx]==rank[fy])
           ++rank[fy]; //重要的是祖先的rank，所以只用修改祖先的rank就可以了，子节点的rank不用管
     }
}
eg: private String find(String str){
        if(father.get(str) == null) return null;
        if(father.get(str).equals(str)) return str; // root found
       
        weight.put(str, weight.get(str) * weight.get(father.get(str))); // a/b , b/c -> a/c = a/b * b/c original father * ogfather / newfather 
        father.put(str, find(father.get(str)));// update new connections, a/c 
        return father.get(str);
    }
    
    private void union(String a, String b, double w){
        String root_a = find(a);
        String root_b = find(b);
        if(!root_a.equals(root_b)){
            father.put(root_b, root_a);
            weight.put(root_b, w * weight.get(a) / weight.get(b));
        }
    }
_______________________________________________________________List with Array____________________________________________________________
List<Integer>[] graph = new ArrayList[numCourses];
____________________________________________________________for loop for character________________________________________________________
for(char c : String.toCharArray()) is faster than for(int i = 0; i < String.length(); ++i) {String.charAt(i)}
_______________________________________________________________211 conclusion_____________________________________________________________
if(chs[pos] != '.'){} else{} is faster than if(chs[pos] == '.'){} else{};
conclusion: when two conditions are mutally exclusive, check major condition first(which scanrio occurs more).

_______________________________________________________________Priority Queue(order)______________________________________________________
while(!pq.isEmpty()) will poll element by order of comparator
for(int i : pq){} will return by stored order, comparator will not affect( iterator will not be influenced by comparator)
  
________________________________________________________Char Array to String______________________________________________________________
String.valueOf(charArray) is faster than sb.insert(0, char). When its reverse order, valueOf() is better
________________________________________________________HashMap find element ______________________________________________________________
if(!recorder.containsKey(str) || recorder.get(str) < int){
}O(2n)
can be improved as
if(int >= recorder.getOrDefault(str, 0)) O(n)
_____________________________________________________________Quick Sort___________________________________________________________________
Median Base:
int pivot = arr[(h + l)/2];
int low = l;
int high = h;
while(low < high){
  while(arr[low] < pivot) low++;
  while(arr[hight] > pivot) high--;
  if(low <= high) swap(arr, low++, high--);
}
quickSort(arr, l, high);
quickSort(arr, low , h);

First Base:
int pivot = arr[l];
int low = l;
int high = h;
while(low < high){
  while(arr[low] < pivot) low++;
  while(arr[hight] > pivot) high--;
  if(low < high) swap(arr, low, high);
}
arr[l] = pivot;
quickSort(arr, l, low - 1);
quickSort(arr, low , low + 1);

____________________________________________________Arrays.copyOf, System.arraycopy____________________________________________
The difference: 
Arrays.copyOf does not only copy elements, it also creates a new array. cannot rewrite array but create a new
System.arrayCopy copies into an existing array.

____________________________________________________String Tricks(split, equals)______________________________________________
S.startsWith(str) faster than S.substring().quals(str);
s.substring(0, indexOf("/")) faster than S.split("/")[2];

____________________________________________________HashMap SubMap()___________________________________________________________
1: HashMap.subMap(from key, to key);
2: HashMap.subMap(from key, boolean, to key, boolean); boolean claims the key is inclusive or not

Submap can be used as reducing the range of searching, faster the program.
