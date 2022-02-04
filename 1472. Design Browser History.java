//My Solution: Use array and pointer to simulate stack
class BrowserHistory{
	String[] histories;
	int cur = 0;
	int rightBound = 0;
	public BrowserHistory(String homepage){
	histories = new String[5001];
	histories[0] = homepage;
}

public void visit(String url){
	histories[++cur] = url;
	rightBound = cur;
}

public String back(int steps){
int move = Math.min(steps, cur);
cur -= 	move;
return histories[cur];
}

public String forward(int steps){
	int move = Math.min(steps, rightBound - cur);
	cur += move;
		return histories[cur];
}
}
