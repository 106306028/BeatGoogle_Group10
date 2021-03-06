import java.io.IOException;
import java.util.ArrayList;


public class WebTree {
	public WebNode root;

	private StringBuilder sb = new StringBuilder();
	
	public WebTree(WebPage rootPage) throws IOException {
		this.root = new WebNode(rootPage);
		
	}
	
	public void setPostOrderScore(ArrayList<Keyword> keywordList) throws IOException {
		setPostOrderScore(root, keywordList);
	}
	
	private void setPostOrderScore(WebNode startNode, ArrayList<Keyword> keywordList) throws IOException {
		for(WebNode child : startNode.children) {
			setPostOrderScore(child, keywordList);
		}
		
		startNode.setNodeScore(keywordList);
	}
	
	public void printTree() {
		printTree(root);
		System.out.println(sb.toString());
	}
	
	public void printTree(WebNode startNode) {
		sb.append("(" + startNode.webPage.name + ", " + startNode.nodeScore);
		for(WebNode child : startNode.children) {
			sb.append("\n");
			for(int i = 1; i < child.getDepth() ; i++) {sb.append("\t");}
			printTree(child);
		}
		if(!startNode.children.isEmpty()) {
			sb.append("\n");
			for(int i = 1; i < startNode.getDepth() ; i++) {sb.append("\t");}
		}
		sb.append(")");
	}

}
