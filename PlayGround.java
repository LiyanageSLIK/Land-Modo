public class PlayGround {
    int xaxis;
    int yaxis;
    
    public String[][] play_ground;
    PlayGround(int X, int Y){
        this.xaxis=X+1;
        this.yaxis=Y+1;
        play_ground =new String[yaxis][xaxis];
    }
    
    public void PlayGroundVisualizer(Object arr[][]) {
        for(int i = ((arr[0].length)-1); i>=0; i--){
            System.out.println("");
            System.out.println("");
            for(int j=0; j<arr[1].length; j++){
               System.out.print(arr[i][j]+"\t");
            }
        }
    }
   
}
