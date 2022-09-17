import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
public class Warriors extends Thread {
    public static boolean game_win=false;
    
    ArrayList<String> visibleThings;
    int[]location={0,0};
    int[]nextlocation={0,0};
    int maxStep=1;
    boolean win=false;
    boolean loss=false;
    boolean binocular;
    String name;
    String message;
    int targetX=0;
    int targetY=0;
    String plane[][];
    Warriors(){
        this.binocular=false;
        visibleThings= new ArrayList<String>(Arrays.asList("WARRIOR","S_WARRIOR","OutOfPlayGround"));

    }
    public int [] randomStartPoint(String zeroLineXorY) {
        int[]cordinate = {0,0};
        //int[] cordinate=new int[2];
        if (zeroLineXorY=="X"){
            Random randI = new Random();
            int myRandInt = randI.nextInt(plane[0].length);
            cordinate[0]=0;
            cordinate[1]=( myRandInt);
        }
        if (zeroLineXorY=="Y"){
            Random randI = new Random();
            int myRandInt = randI.nextInt(plane[0].length);
            cordinate[0]=( myRandInt);
            cordinate[1]=0;
        }
        return cordinate;
    }

    public void setWarrior() {
        boolean i=true;
        while (i){
            location=randomStartPoint("Y");
            if (plane[location[1]][location[0]]==null){
                if(binocular){
                    plane[location[1]][location[0]]="S_WARRIOR";
                }else{
                    plane[location[1]][location[0]]="WARRIOR";
                } 
                i=false;
            }else{
                continue;
            }
        }
    }
    private String check( int[] point) {
        String result=null;
        try {
            result=plane[point[1]][point[0]];
        } catch (Exception e) {
            result="OutOfPlayGround";
        }
        return result;

    }
    private synchronized void  stepForword() {
        message="NO";
        ArrayList<Integer> selector = new ArrayList<Integer>(Arrays.asList(0,1,2,3));
        int[][] aroundCordinate = {{0,0},{0,0},{0,0},{0,0}};
        int[] nextTemplocation = {0,0};
        String [] aroundPoints =new String[4]; //X+Y+X-Y-
        Collections.shuffle(selector);
        for(int i :selector){
            if (i==0) {
                nextTemplocation[0]=(location[0]+maxStep);
                nextTemplocation[1]=(location[1]);
                aroundPoints[i]=check(nextTemplocation);
                aroundCordinate[i][0]= nextTemplocation[0];aroundCordinate[i][1]= nextTemplocation[1];
            }
            if (i==1) {
                nextTemplocation[0]=(location[0]);
                nextTemplocation[1]=(location[1]+maxStep);
                aroundPoints[i]=check(nextTemplocation);
                aroundCordinate[i][0]= nextTemplocation[0];aroundCordinate[i][1]= nextTemplocation[1];
            }
            if (i==2) {
                nextTemplocation[0]=(location[0]-maxStep);
                nextTemplocation[1]=(location[1]);
                aroundPoints[i]=check(nextTemplocation);
                aroundCordinate[i][0]= nextTemplocation[0];aroundCordinate[i][1]= nextTemplocation[1];
            }
            if (i==3) {
                nextTemplocation[0]=(location[0]);
                nextTemplocation[1]=(location[1]-maxStep);
                aroundPoints[i]=check(nextTemplocation);
                aroundCordinate[i][0]= nextTemplocation[0];aroundCordinate[i][1]= nextTemplocation[1];
            }
        }
        for (int i : selector) {
            if (location[0]==targetX && location[1]==targetY){
                win=true;
                break;}
            
            if (binocular) {
                if (visibleThings.contains(aroundPoints[i])) {
                    continue;
                }
                if(aroundPoints[i]=="MODO"){
                    win=true;
                    message="MODO+S_WARRIOR";
                    plane[location[1]][location[0]]=null;
                    nextlocation[0]=aroundCordinate[i][0];nextlocation[1]=aroundCordinate[i][1];
                    location[0]=aroundCordinate[i][0];location[1]=aroundCordinate[i][1];
                    break;
                }
                if(aroundPoints[i]=="MONSTER"){
                    loss=true;
                    message="MONSTER+S_WARRIOR";
                    plane[location[1]][location[0]]=null;
                    nextlocation[0]=aroundCordinate[i][0];nextlocation[1]=aroundCordinate[i][1];
                    location[0]=aroundCordinate[i][0];location[1]=aroundCordinate[i][1];
                    break;
                }
                if(aroundPoints[i]==null){
                    message="YES";
                    plane[location[1]][location[0]]=null;
                    nextlocation[0]=aroundCordinate[i][0];nextlocation[1]=aroundCordinate[i][1];
                    location[0]=aroundCordinate[i][0];location[1]=aroundCordinate[i][1];
                    plane[location[1]][location[0]]="S_WARRIOR";
                    break;
                }
            
            }else{
                if (visibleThings.contains(aroundPoints[i])) {
                    continue;
                }
                if(aroundPoints[i]=="MODO"){
                    win=true;
                    message="MODO+WARRIOR";
                    plane[location[1]][location[0]]=null;
                    nextlocation[0]=aroundCordinate[i][0];nextlocation[1]=aroundCordinate[i][1];
                    location[0]=aroundCordinate[i][0];location[1]=aroundCordinate[i][1];
                    break;
                }
                if(aroundPoints[i]=="MONSTER"){
                    loss=true;
                    message="MONSTER+WARRIOR";
                    plane[location[1]][location[0]]=null;
                    nextlocation[0]=aroundCordinate[i][0];nextlocation[1]=aroundCordinate[i][1];
                    location[0]=aroundCordinate[i][0];location[1]=aroundCordinate[i][1];
                    break;
                }
                if(aroundPoints[i]=="TREE"){
                    loss=false;
                    message="TREE+WARRIOR+ReStarted";
                    plane[location[1]][location[0]]=null;
                    setWarrior();
                    nextlocation[0]=aroundCordinate[i][0];nextlocation[1]=aroundCordinate[i][1];
                    break;
                }
                if(aroundPoints[i]==null){
                    message="YES";
                    plane[location[1]][location[0]]=null;
                    nextlocation[0]=aroundCordinate[i][0];nextlocation[1]=aroundCordinate[i][1];
                    location[0]=aroundCordinate[i][0];location[1]=aroundCordinate[i][1];
                    plane[location[1]][location[0]]="WARRIOR";
                    break;
                }
            }
        }
    }

    
    public void run() {
        while (!game_win) {
            
            try {
                synchronized(this){
                    Thread.sleep(100);
                    PlayGround.PlayGroundVisualizer(plane);
                    System.out.println("\n");
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            if (win||loss) {
                // System.out.println(win);
                // System.out.println(loss);
                // System.out.println(message);
                if (win){
                    game_win=true;
                }
                break;
            }else{
                // System.out.println(message);
                
                try {
                    sleep(1000);
                    stepForword();
                    continue;
                } catch (InterruptedException e) {}
                
            }   
        }    
    }
}

