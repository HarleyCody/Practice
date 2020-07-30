
_______________________________________________________________________________________Best Solution_________________________________________________________________
// bit + dfs
class Solution {
    List<Integer> sol = new ArrayList<>();
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        Map<String, Integer> idx = new HashMap<>(); 
        int n = 0;
        for (String s : req_skills){
            idx.put(s, n++);///skills are represented by 0, 1, 2....
        }
        int[] pe = new int[people.size()];
        // build personal skill set by bit
        for (int i = 0; i < pe.length; i++) {
            for (String p : people.get(i)) {
                int skill = idx.get(p);
                pe[i] += 1 << skill;
            }
        } // each person is transferred to a number, of which the bits of 1 means the guy has the skill
        search(0, pe, new ArrayList<Integer>(), n);  
        int[] ans = new int[sol.size()];
        for (int i = 0; i < sol.size(); i++) ans[i] = sol.get(i);
        return ans;
    }
    
    public void search(int cur, int[] pe, List<Integer> onesol, int n) { 
        if (cur == (1<<n) - 1) {  // when all bits are 1, all skills are coverred
            if (sol.size() == 0 || onesol.size() < sol.size()) {
                sol = new ArrayList<>(onesol);
            }
            return;
        }
        if (sol.size() != 0 && onesol.size() >= sol.size()) return;    //pruning
        int zeroBit = 0;
        // find skill that is not satisfied, in bit its 0;
        while (((cur>>zeroBit)&1) == 1) zeroBit++;   
        for (int i = 0; i < pe.length; i++) {
            int per = pe[i];
            if (((per>>zeroBit)&1) == 1) {
                onesol.add(i); // when a person can cover a zero bit in the current number, we can add him
                search(cur|per, pe, onesol, n);
                onesol.remove(onesol.size() - 1);  //search in a backtracking way
            }
        } 
    }
}
_____________________________________________________________________________Dp Solution_________________________________________________________________

class Solution {
    //dp[i]( ie skillArr) records minimal team list when team can fill tech req as i; i is bit set, reprsents multiple skills 
    // pick by skills so every time we can decided on weather we need this skill or not, so there are 2^n skills set situation
    public int[] smallestSufficientTeam(String[] skills, List<List<String>> people) {
        int sLen = skills.length, pLen = people.size();
        
        Map<String, Integer> skmap = new HashMap<>();
        for(int i = 0; i < sLen; i++)
            skmap.put(skills[i], i);
        
        Set<Integer>[] skillArr = new Set[1 << sLen];
        skillArr[0] = new HashSet<>();
        
        for(int ppl = 0; ppl < pLen; ppl++){
            int pplSkill = 0;
            for(String sks : people.get(ppl)){
                pplSkill |= 1 << (skmap.get(sks));
            }
            
            for(int k = 0; k < skillArr.length; k++){
                if(skillArr[k] == null) continue;
                Set<Integer> currSkills = skillArr[k];
                int combined = k | pplSkill;
                if(combined == k) continue;
                if(skillArr[combined] == null || skillArr[combined].size() > currSkills.size() + 1){
                    Set<Integer> cSkills = new HashSet<>(currSkills);
                    cSkills.add(ppl);
                    skillArr[combined] = cSkills;
                }
            }
        }
        
        Set<Integer> resSet = skillArr[(1 << sLen) - 1];
        int[] res = new int[resSet.size()];
        int pos = 0;
        for(Integer n : resSet)
            res[pos++] = n;
        
        return res;
    }
}
___________________________________________________________________________________________My Solution_________________________________________________________________
class Solution {
    //bfs with self define class team
    //build team based on what current team need
    class Team{
        HashSet<String> reqTech = new HashSet();
        HashSet<Integer> teamMem = new HashSet();
        public Team(HashSet<String> tech, int tl, List<String> tlTech){
            reqTech = tech;
            teamMem.add(tl);
            reqTech.removeAll(tlTech);
        }
        
        public Team(Team t){
            this.reqTech = new HashSet(t.reqTech);
            this.teamMem = new HashSet(t.teamMem);
        }
        
        public void addTeamMember(int id, List<String> skills){
            reqTech.removeAll(skills);
            teamMem.add(id);
        }
    }
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        HashSet<String> tech = new HashSet(Arrays.asList(req_skills));
        HashMap<String, List<Integer>> hr = new HashMap();
        for(int i = 0; i < people.size(); ++i){
            for(String skill : people.get(i)){
                hr.putIfAbsent(skill, new ArrayList());
                hr.get(skill).add(i);
            }
        }
        Queue<Team> pq = new LinkedList();
        for(int i = 0; i < people.size(); ++i){
            Team t = new Team(new HashSet(tech), i, people.get(i));
            pq.offer(t);
        }
        
        HashSet<Integer> ansSet = new HashSet();
        while(!pq.isEmpty()){
            Team curTeam = pq.poll();
            if(curTeam.reqTech.isEmpty()){
                ansSet = curTeam.teamMem;
                break;
            }
            String skill = curTeam.reqTech.iterator().next();
            List<Integer> candidates = hr.get(skill);
            for(int can : candidates){
                Team nt = new Team(curTeam);
                nt.addTeamMember(can, people.get(can));
                pq.offer(nt);
            }
        }
        
        int[] ans = new int[ansSet.size()];
        int idx = 0;
        for(int can : ansSet){
            ans[idx++] = can;
        }
        
        return ans;
    }
}
