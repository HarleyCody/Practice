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
