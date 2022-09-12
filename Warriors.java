import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Warriors extends Thread {
    int[] location=new int[2];
    int[] nextlocation=new int[2];
    boolean win=false;
    boolean loss=false;
    boolean vision;
    String name;
    String succses;
    int targetX;
    int targetY;
    String plane[][];
    Warriors(boolean visionTrueFall){
        this.vision=visionTrueFall;
    }
    public int[] randomStartPoint(String zeroLineXorY,int TotalPoints) {
        int[] cordinate=new int[2];
        if (zeroLineXorY=="X"){
            Random randI = new Random();
            int myRandInt = randI.nextInt(TotalPoints);
            cordinate[0]=0;
            cordinate[1]=myRandInt;
        }
        if (zeroLineXorY=="Y"){
            Random randI = new Random();
            int myRandInt = randI.nextInt(TotalPoints);
            cordinate[1]=0;
            cordinate[0]=myRandInt;
        }
        // location=cordinate;
        return cordinate;
    }

    public void setWarrior(String playground[][]) {
        boolean i=true;
        while (i){
            location=randomStartPoint("Y", 11);
            if (playground[location[1]][location[0]]==null){
                if(vision){
                    playground[location[1]][location[0]]="S_WARRIOR";
                }else{
                    playground[location[1]][location[0]]="WARRIOR";
                } 
                i=false;
            }else{
                continue;
            }
        }
    }
    private String check(int point[],String plane[][]) {
        String result=null;
        try {
            result=plane[point[1]][point[0]];
        } catch (Exception e) {
            result="FAIL";
        }
        return result;

    }
    private void  stepForword() {
        succses="NO";
        ArrayList<Integer> selector = new ArrayList<Integer>();
        int [] nextTemplocation= new int[2];
        int[][] aroundCordinate= new int[4][2];
        String [] aroundPoints =new String[4]; //X+Y+X-Y-
        selector.add(0);selector.add(1);selector.add(2);selector.add(3);
        Collections.shuffle(selector);
        for(int i :selector){
            if (i==0) {
                nextTemplocation[0]=(location[0]+1);
                nextTemplocation[1]=(location[1]);
                aroundPoints[i]=check(nextTemplocation, plane);
                aroundCordinate[i][0]=nextTemplocation[0];aroundCordinate[i][1]=nextTemplocation[1];
            }
            if (i==1) {
                nextTemplocation[0]=(location[0]);
                nextTemplocation[1]=(location[1]+1);
                aroundPoints[i]=check(nextTemplocation, plane);
                aroundCordinate[i][0]=nextTemplocation[0];aroundCordinate[i][1]=nextTemplocation[1];
            }
            if (i==2) {
                nextTemplocation[0]=(location[0]-1);
                nextTemplocation[1]=(location[1]);
                aroundPoints[i]=check(nextTemplocation, plane);
                aroundCordinate[i][0]=nextTemplocation[0];aroundCordinate[i][1]=nextTemplocation[1];
            }
            if (i==3) {
                nextTemplocation[0]=(location[0]);
                nextTemplocation[1]=(location[1]-1);
                aroundPoints[i]=check(nextTemplocation, plane);
                aroundCordinate[i][0]=nextTemplocation[0];aroundCordinate[i][1]=nextTemplocation[1];
            }
        }
        for (int i : selector) {
            if (location[0]==targetX && location[1]==targetY){
                win=true;
                break;}
            
            if (vision) {
                List<String> nameList = new ArrayList<String>(Arrays.asList("WARRIOR","S_WARRIOR","TREE","FAIL"));
                if (nameList.contains(aroundPoints[i])) {
                    continue;
                }
                if(aroundPoints[i]=="MODO"){
                    win=true;
                    succses="MODO+S_WARRIOR";
                    plane[location[1]][location[0]]=null;
                    nextlocation[0]=aroundCordinate[i][0];nextlocation[1]=aroundCordinate[i][1];
                    break;
                }
                if(aroundPoints[i]=="MONSTER"){
                    loss=true;
                    succses="MONSTER+S_WARRIOR";
                    plane[location[1]][location[0]]=null;
                    nextlocation[0]=aroundCordinate[i][0];nextlocation[1]=aroundCordinate[i][1];
                    break;
                }
                if(aroundPoints[i]==null){
                    succses="YES";
                    plane[location[1]][location[0]]=null;
                    nextlocation[0]=aroundCordinate[i][0];nextlocation[1]=aroundCordinate[i][1];
                    location[0]=aroundCordinate[i][0];location[1]=aroundCordinate[i][1];
                    plane[location[1]][location[0]]="S_WARRIOR";
                    break;
                }
            
            }else
            {
                List<String> nameList = new ArrayList<String>(Arrays.asList("WARRIOR","S_WARRIOR","FAIL"));
                if (nameList.contains(aroundPoints[i])) {
                    continue;
                }
                if(aroundPoints[i]=="MODO"){
                    win=true;
                    succses="MODO+WARRIOR";
                    plane[location[1]][location[0]]=null;
                    nextlocation[0]=aroundCordinate[i][0];nextlocation[1]=aroundCordinate[i][1];
                    break;
                }
                if(aroundPoints[i]=="MONSTER"){
                    loss=true;
                    succses="MONSTER+WARRIOR";
                    plane[location[1]][location[0]]=null;
                    nextlocation[0]=aroundCordinate[i][0];nextlocation[1]=aroundCordinate[i][1];
                    break;
                }
                if(aroundPoints[i]=="TREE"){
                    loss=true;
                    succses="TREE+WARRIOR";
                    plane[location[1]][location[0]]=null;
                    nextlocation[0]=aroundCordinate[i][0];nextlocation[1]=aroundCordinate[i][1];
                    break;
                }
                if(aroundPoints[i]==null){
                    succses="YES";
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
        while (true) {
            if (win||loss) {
                System.out.println(win);
                System.out.println(loss);
                break;
            }else{
                stepForword();
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                continue;
            }
            
        }
        
        
    }
}

