#include<bits/stdc++.h>
#define SIZE 1000
using namespace std;


bool flage;
int TotalNodes, TotalColors;
int matrix[SIZE][SIZE];
int NodeColor[SIZE];

bool consistency(int color, int var){

    for(int i=0 ; i<TotalNodes ; i++){
        if(matrix[var][i]==1 )
            if(color == NodeColor[i])
                return false;
    }

    return true;
}

bool BackTracking(int node){

    if(node>=TotalNodes)
        return true;

    int var = node;

    for(int i=0 ; i<TotalColors ; i++){

        if(consistency(i,var)){

            NodeColor[var] = i;
            bool result = BackTracking(var+1);

            if(result)
                return true;
        }
    }

    return false;
}

void InputGraph(){
    int x, y;

    memset(matrix,0,sizeof(matrix));

    while(true){
        cin>>x>>y;
        if(x==0 && y==0)
            break;
        matrix[x][y] = 1;
        matrix[y][x] = 1;
    }
}

int main(){

    cout<<"Total nodes: "<<endl;
    cin>>TotalNodes;

    cout<<"Input Graph: (enter 0 0 to exit)"<<endl;
    InputGraph();

    cout<<"Colors: "<<endl;
    cin>>TotalColors;

    memset(NodeColor,-1,sizeof(NodeColor));

    bool flage = BackTracking(0);

    if(flage){
        for(int i=0 ; i<TotalNodes ; i++)
            cout<<"Node : "<<i<<" -> "<<NodeColor[i]<<endl;
    }
    else
        cout<<"failure"<<endl;

}
