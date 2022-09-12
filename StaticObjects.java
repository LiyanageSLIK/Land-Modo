import java.util.Random;

public class StaticObjects {

    public static int modox;
    public static int modoy;
    
    private static int[] randomCordinate(String[][]plane) {
        int[] cordinate=new int[2];
        for(int i=0;i<cordinate.length;i++ ){
            Random randI = new Random();
            int myRandInt = (randI.nextInt((plane.length)-1))+1;
            cordinate[i]=myRandInt;
        } 
        return cordinate;
    }
    public static void setTrees(int treesCount,String[][]plane) {
        for(int i=1;i<=treesCount;){
            int[]cordinate=randomCordinate(plane);
            if (plane[cordinate[0]][cordinate[1]]==null){
                plane[cordinate[0]][cordinate[1]]="TREE";
                i++;
            }else{
                continue;
            }
        }
    }
    public static void setMonsters(int monstersCount,String[][]plane) {
        for(int i=1;i<=monstersCount;){
            int[]cordinate=randomCordinate(plane);
            if (plane[cordinate[0]][cordinate[1]]==null){
                plane[cordinate[0]][cordinate[1]]="MONSTER";
                i++;
            }else{
                continue;
            }
        }
    }
    public static void setModo(int X, int Y,String[][]plane) {
        modox=X;
        modoy=Y;
        plane[Y][X]="MODO";
    }
}
