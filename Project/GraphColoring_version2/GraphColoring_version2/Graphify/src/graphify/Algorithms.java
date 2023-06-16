/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphify;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Ayomitunde
 */
public class Algorithms {

    private GraphifyGUI GG;
    HashMap<Integer, Integer> connectionCache = new HashMap<>();
    private HashMap<Integer, Integer> glowMap = new HashMap<>();
    HashMap<Integer, HashSet<Integer>> nodes;
    private Queue<Integer> queue;
    private Stack<Integer> stack;
    private HashMap<Integer, Integer> distTo;
    private HashMap<Integer, Integer> set = new HashMap<Integer, Integer>();
    private HashMap<Integer, Integer> visited;
    private HashMap<Integer, Integer> color;
    private HashMap<Integer, Integer> greedyresult;
    private HashSet<Integer> _colors2;
    private ArrayList<Integer> conn;
    private ArrayList<Integer> bconn;
    private ArrayList<Integer> cutV;
    private ArrayList<Integer> nodeColors;
    private Color[] vertexColors;
    int _selectedNode = -1;
    int _SIZE_OF_NODE = 20;
    int id = 0;
    int time = 0;
    Integer maxColors = 0;
    int _source;
    int _dest;
    int totalColors = 6;

    public Algorithms(GraphifyGUI GG) {
        this.GG = GG;
        this.nodes = new HashMap<>();
        this.queue = new LinkedList<>();
        this.stack = new Stack<>();
        this.cutV = new ArrayList<>();
        this._colors2 = new HashSet<>();
        this.visited = new HashMap<>();
        this.set = new HashMap<>();
        this.visited = new HashMap<>();
        this.color = new HashMap<>();
        this.greedyresult = new HashMap<>();
        this.vertexColors = new Color[]{Color.blue, Color.red, Color.yellow, Color.green, Color.magenta, Color.orange};
    }

    public HashSet<Integer> getEdge(int source) {
        nodes = GraphifyGUI.getNode();
        return nodes.get(source);
    }

    void APF(int u, HashMap<Integer, Integer> visited, HashMap<Integer, Integer> disc, HashMap<Integer, Integer> low, HashMap<Integer, Integer> parent, HashMap<Integer, Integer> ap) {
        int children = 0;
        visited.put(u, 0);
        time = ++time;
        disc.put(u, time);
        low.put(u, time);
        Iterator<Integer> i = getEdge(u).iterator();

        while (i.hasNext()) {
            int v = i.next(); // v is current adj to u
            if (visited.get(v) == -1) {
                children++;
                parent.put(v, u);
                APF(v, visited, disc, low, parent, ap); // recursive for it
                int val = Math.min(low.get(u), low.get(v));
                low.put(u, val);

                if (u == _source && children > 1) {
                    ap.put(u, 1);
                }
                // if u is not root and low value of one of its child is more than discovery value of u
                if (u != _source && low.get(v) >= disc.get(u)) { // need a check for this if statement.. always marks beginning as a cut even when it's not
                    ap.put(u, 1);
                }
            } else if (v != parent.get(u)) {
                int val = Math.min(low.get(u), low.get(v));
                low.put(u, val);
            }
        }
    }

    void AP() {
        nodes = GraphifyGUI.getNode();
        visited = new HashMap<>();
        boolean cutExist = false;
        HashMap<Integer, Integer> disc = new HashMap<>();
        HashMap<Integer, Integer> low = new HashMap<>();
        HashMap<Integer, Integer> parent = new HashMap<>();
        HashMap<Integer, Integer> ap = new HashMap<>();

        Iterator<Integer> allNodes = nodes.keySet().iterator();
        while (allNodes.hasNext()) {
            int key = allNodes.next();
            parent.put(key, -1);
            visited.put(key, -1);
            ap.put(key, 0);
        }

        allNodes = nodes.keySet().iterator();
        while (allNodes.hasNext()) {
            int key = allNodes.next();
            if (visited.get(key) == -1) {
                APF(key, visited, disc, low, parent, ap);
            }
        }

        allNodes = nodes.keySet().iterator();
        while (allNodes.hasNext()) {
            int key = allNodes.next();
            if (ap.get(key) == 1) {
                GG.printlnConsole(key + " is a cut vertex");
                cutV.add(key);
                cutExist = true;
            }
        }

        if (cutExist == false) {
            GG.printlnConsole("No cut vertex in Graph");
        }
    }

    void dfs(int source) {
  
    }

    void bfs(int source) {
    }

    void makeTree(int source) {
    }

    void vertexCover(int source) {}

    boolean isConnected() {
        return true;
    }

    boolean isEulerian() {
        return false;
    }

    boolean Consistent(int color, int var, int TotalNodes){
        
        HashSet<Integer> list = getEdge(var);
        Iterator<Integer> it = list.iterator();
        
        while(it.hasNext()){
            int temp = it.next();
            
            if( color==GG.greedyresult.get(temp) )
                return false;
        }

        return true;
    }

    boolean Backtrack(int TotalNodes, int currNode ){
        if(currNode>=TotalNodes)
            return true;
        
        for(int i=0 ; i<totalColors ; i++){
            if(Consistent(i, currNode, TotalNodes)==true){
                
                GG.greedyresult.put(currNode, i);
                boolean result = Backtrack(TotalNodes, currNode+1);
                
                if(result==true)
                    return true;
            }
        }
        return false;
    }

    boolean GCBacktrack() {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        nodes = GG.getNode();        
        int totalNodes = nodes.size();
            
        for(int i=0 ; i<totalNodes; i++)
            GG.greedyresult.put(i, -1);
        
        boolean flage = Backtrack(totalNodes,0);
        return flage;
    }

    void greedyColoring(int nc) {
    }

    void Bipartite(int source) {

    }

    public int hasPath(int v) {
        return visited.get(v);
    }

    public int distTo(int v) {
        return distTo.get(v);
    }

    public void shortestPath(int v, int e) {
    }
    //[0, 2, 19, 5, 7, 9, 14]

    public Queue getQueue() {
        return this.queue;
    }

    public Stack getStack() {
        return this.stack;
    }

    public HashMap getGlowMap() {
        return this.glowMap;
    }

    public HashMap distTo() {
        return this.distTo;
    }

    public HashMap getSet() {
        return this.set;
    }

    public HashMap getVisited() {
        return this.visited;
    }

    public HashMap getColor() {
        return this.color;
    }

    public HashMap getGreedyResult() {
        return this.greedyresult;
    }

    public HashSet getColors2() {
        return this._colors2;
    }

    public ArrayList getConn() {
        return this.conn;
    }

    public ArrayList getBConn() {
        return this.bconn;
    }

    public ArrayList getCutV() {
        return this.cutV;
    }

    public Color[] getVertexColors() {
        return this.vertexColors;
    }



}
