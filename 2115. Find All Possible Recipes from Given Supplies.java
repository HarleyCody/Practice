//My Solution: try to make each one by dfs, check the circle
// Add each doable recipe into list; only works for one recipe with one name
class Solution{
	public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies){
        Set<String> suppliesSet = new HashSet(Arrays.asList(supplies));
		Map<String, List<String>> ingredientsMap = new HashMap();
		for(int i = 0; i < recipes.length; ++i){
			ingredientsMap.put(recipes[i], ingredients.get(i));
        }
        
        LinkedList<String> ans = new LinkedList();
        for(String recipe : recipes){
            if(canMake(recipe, ingredientsMap, suppliesSet, new HashSet())){
                ans.offer(recipe);
            }
        }
        return ans;
    }

    private boolean canMake(String name, Map<String, List<String>> ingredientsMap, Set<String> supplies, Set<String> neededIngredients ){
        if(supplies.contains(name)) return true;
        if(!neededIngredients.add(name) || !ingredientsMap.containsKey(name)) return false;
        for(String ingredient : ingredientsMap.get(name)){
            if(!canMake(ingredient, ingredientsMap, supplies, neededIngredients)) return false;
        }
        return supplies.add(name);
    }
}

// Topology: start from base which is ingredients and try to expand recipe to supplies
class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, List<String>> adjList = new HashMap<>();
        Map<String, Integer> indegree = new HashMap<>();
        Set<String> recipeSet = new HashSet<>();
        
        for (int i = 0; i < recipes.length; i++) {
            String recipe = recipes[i];
            recipeSet.add(recipe);
            List<String> ingredientOfRecipe = ingredients.get(i);
            
            for (String ingredient : ingredientOfRecipe) {
                adjList.computeIfAbsent(ingredient, x -> new ArrayList<>()).add(recipe);
                indegree.put(recipe, indegree.getOrDefault(recipe, 0) + 1);
            }
        }
        
        Deque<String> queue = new ArrayDeque<>();
        List<String> result = new ArrayList<>();
        
        for (String supply : supplies) queue.add(supply);
        
        while (!queue.isEmpty()) {
            String curr = queue.removeFirst();
            if (recipeSet.contains(curr)) result.add(curr);
            
            if (adjList.containsKey(curr)) {
                for (String neighbor : adjList.get(curr)) {
                    int newOccurrence = indegree.get(neighbor) - 1;
                    indegree.put(neighbor, newOccurrence);
                    if (newOccurrence == 0) queue.add(neighbor);
                }
            }
        }
        
        return result;
        
    }
}
