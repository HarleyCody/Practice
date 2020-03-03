/*
// Employee info
class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};
*/
class Solution {
    // similar to sum of binary tree
    HashMap<Integer, Employee> memo = new HashMap();
    public int getImportance(List<Employee> employees, int id) {
        
        // bind id with employee, donot need to find in every recursion
        if(memo.isEmpty()){
            for(Employee employee : employees){
                memo.put(employee.id, employee);
            }
        }
        
        Employee leader = memo.get(id);
        if(leader == null)return 0;
        
        int ans = leader.importance;
        for(Integer subordinate : leader.subordinates){
            ans += getImportance(employees, subordinate);
        }
        return ans;
    }
}
