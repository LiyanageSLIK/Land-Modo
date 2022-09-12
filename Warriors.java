import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public class Warriors extends Thread {
    ArrayList<String> visibleThings;
    ArrayList<Integer> location = new ArrayList<Integer>();
    ArrayList<Integer> nextlocation = new ArrayList<Integer>();
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
    public ArrayList<Integer> randomStartPoint(String zeroLineXorY) {
        ArrayList<Integer> cordinate = new ArrayList<Integer>(2);
        //int[] cordinate=new int[2];
        if (zeroLineXorY=="X"){
            Random randI = new Random();
            int myRandInt = randI.nextInt(plane[0].length);
            cordinate.add(0, 0);
            cordinate.add(1,( myRandInt));
        }
        if (zeroLineXorY=="Y"){
            Random randI = new Random();
            int myRandInt = randI.nextInt(plane[0].length);
            cordinate.add(1, 0);
            cordinate.add(0, (myRandInt));
            
        }
        return cordinate;
    }

    public void setWarrior() {
        boolean i=true;
        while (i){
            location=randomStartPoint("Y");
            if (plane[location.get(1)][location.get(0)]==null){
                if(binocular){
                    plane[location.get(1)][location.get(0)]="S_WARRIOR";
                }else{
                    plane[location.get(1)][location.get(0)]="WARRIOR";
                } 
                i=false;
            }else{
                continue;
            }
        }
    }
    private String check( List<Integer> point) {
        String result=null;
        try {
            result=plane[point.get(1)][point.get(0)];
        } catch (Exception e) {
            result="OutOfPlayGround";
        }
        return result;

    }
    private void  stepForword() {
        message="NO";
        ArrayList<Integer> selector = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> aroundCordinate = new ArrayList<>();
        ArrayList<Integer> nextTemplocation = new ArrayList<Integer>(2);
        //int [] nextTemplocation= new int[2];
        //int[][] aroundCordinate= new int[4][2];
        String [] aroundPoints =new String[4]; //X+Y+X-Y-
        selector.add(0);selector.add(1);selector.add(2);selector.add(3);
        Collections.shuffle(selector);
        for(int i :selector){
            if (i==0) {
                nextTemplocation.add(0, (location.get(0)+maxStep));
                nextTemplocation.add(1, (location.get(1)));
                aroundPoints[i]=check(nextTemplocation);
                aroundCordinate.add(i, nextTemplocation);
            }
            if (i==1) {
                nextTemplocation.add(0, (location.get(0)));
                nextTemplocation.add(1, (location.get(1)+maxStep));
                aroundPoints[i]=check(nextTemplocation);
                aroundCordinate.add(i, nextTemplocation);
            }
            if (i==2) {
                nextTemplocation.add(0, (location.get(0)-maxStep));
                nextTemplocation.add(1, (location.get(1)));
                aroundPoints[i]=check(nextTemplocation);
                aroundCordinate.add(i, nextTemplocation);
            }
            if (i==3) {
                nextTemplocation.add(0, (location.get(0)));
                nextTemplocation.add(1, (location.get(1)-maxStep));
                aroundPoints[i]=check(nextTemplocation);
                aroundCordinate.add(i, nextTemplocation);
            }
        }
        for (int i : selector) {
            if (location.get(0)==targetX && location.get(1)==targetY){
                win=true;
                break;}
            
            if (binocular) {
                if (visibleThings.contains(aroundPoints[i])) {
                    continue;
                }
                if(aroundPoints[i]=="MODO"){
                    win=true;
                    message="MODO+S_WARRIOR";
                    plane[location.get(1)][location.get(0)]=null;
                    nextlocation.clear();nextlocation.addAll(aroundCordinate.get(i));
                    location.clear();location.addAll(aroundCordinate.get(i));
                    break;
                }
                if(aroundPoints[i]=="MONSTER"){
                    loss=true;
                    message="MONSTER+S_WARRIOR";
                    plane[location.get(1)][location.get(0)]=null;
                    nextlocation.clear();nextlocation.addAll(aroundCordinate.get(i));
                    break;
                }
                if(aroundPoints[i]==null){
                    message="YES";
                    plane[location.get(1)][location.get(0)]=null;
                    nextlocation.clear();nextlocation.addAll(aroundCordinate.get(i));
                    location.clear();location.addAll(aroundCordinate.get(i));
                    plane[location.get(1)][location.get(0)]="S_WARRIOR";
                    break;
                }
            
            }else{
                if (visibleThings.contains(aroundPoints[i])) {
                    continue;
                }
                if(aroundPoints[i]=="MODO"){
                    win=true;
                    message="MODO+WARRIOR";
                    plane[location.get(1)][location.get(0)]=null;
                    nextlocation.clear();nextlocation.addAll(aroundCordinate.get(i));
                    location.clear();location.addAll(aroundCordinate.get(i));
                    break;
                }
                if(aroundPoints[i]=="MONSTER"){
                    loss=true;
                    message="MONSTER+WARRIOR";
                    plane[location.get(1)][location.get(0)]=null;
                    nextlocation.clear();nextlocation.addAll(aroundCordinate.get(i));
                    break;
                }
                if(aroundPoints[i]=="TREE"){
                    loss=true;
                    message="TREE+WARRIOR";
                    plane[location.get(1)][location.get(0)]=null;
                    setWarrior();
                    nextlocation.clear();nextlocation.addAll(aroundCordinate.get(i));
                    break;
                }
                if(aroundPoints[i]==null){
                    message="YES";
                    plane[location.get(1)][location.get(0)]=null;
                    nextlocation.clear();nextlocation.addAll(aroundCordinate.get(i));
                    location.clear();location.addAll(aroundCordinate.get(i));
                    plane[location.get(1)][location.get(0)]="WARRIOR";
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
                    e.printStackTrace();
                }
                continue;
            }   
        }    
    }
}

