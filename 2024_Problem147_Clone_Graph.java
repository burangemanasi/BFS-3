//133. Clone Graph - https://leetcode.com/problems/clone-graph/description/
//Time Complexity: O(V+E) ~ going through all edges and vertices
//Space Complexity: O(V)

class Solution {
    HashMap<Node, Node> map;
    public Node cloneGraph(Node node) {
        //base case
        if(node == null) return null;

        this.map = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        //add 1st node to queue
        q.add(node);

        while(!q.isEmpty()){
            Node curr = q.poll(); //get node
            Node copyCurr = clone(curr); //create a clone first
            for(Node neighbor: curr.neighbors){ //go over all neighbors
                if(!map.containsKey(neighbor)){ //if new node, add to queue
                    q.add(neighbor);
                }//then add to the new nodes neighbor list
                copyCurr.neighbors.add(clone(neighbor));
            }
        }

        return map.get(node);
    }

    private Node clone(Node node){
        if(node == null) return null;
        if(!map.containsKey(node)){
            map.put(node, new Node(node.val));
        }
        return map.get(node);
    }
}