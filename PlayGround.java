import java.util.Random;

public class PlayGround {
    int xaxis;
    int yaxis;
    public int modox;
    public int modoy;
    public String[][] play_ground;
    PlayGround(int X, int Y){
        this.xaxis=X+1;
        this.yaxis=Y+1;
        play_ground =new String[yaxis][xaxis];
    }
    public int[] randomCordinate() {
        int[] cordinate=new int[2];
        for(int i=0;i<cordinate.length;i++ ){
            Random randI = new Random();
            int myRandInt = (randI.nextInt((play_ground.length)-1))+1;
            cordinate[i]=myRandInt;
        } 
        return cordinate;
    }
    public void setModo(int X, int Y) {
        modox=X;
        modoy=Y;
        play_ground[Y][X]="MODO";
    }
    public void setMonsters(int monster_count) {
        for(int i=1;i<=monster_count;){
            int[]cordinate=randomCordinate();
            if (play_ground[cordinate[0]][cordinate[1]]==null){
                play_ground[cordinate[0]][cordinate[1]]="MONSTER";
                i++;
            }else{
                continue;
            }
        }
    }
    public void setTrees(int tree_count) {
        for(int i=1;i<=tree_count;){
            int[]cordinate=randomCordinate();
            if (play_ground[cordinate[0]][cordinate[1]]==null){
                play_ground[cordinate[0]][cordinate[1]]="TREE";
                i++;
            }else{
                continue;
            }
        }
    }
    public void twoDArrayVisualizer(Object arr[][]) {
        for(int i = ((arr[0].length)-1); i>=0; i--){
            System.out.println("");
            System.out.println("");
            for(int j=0; j<arr[1].length; j++){
               System.out.print(arr[i][j]+"\t");
            }
        }
    }
   
}
