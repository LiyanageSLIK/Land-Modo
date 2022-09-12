import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        PlayGround obj = new PlayGround(10, 10);
        obj.setModo(5, 6);
        obj.setMonsters(5);
        obj.setTrees(5);
        Warriors[] warriorList = new Warriors[5];
        for (int i=0;i<warriorList.length;i++) {
            if(i==4){
                warriorList[i] = new Warriors(true);
            }else{
                warriorList[i] = new Warriors(false);
            } 
        }
        for (Warriors i : warriorList) {
            i.setWarrior(obj.play_ground);
            i.targetX=obj.modox;
            i.targetY=obj.modoy;
            i.plane=obj.play_ground;
            
        }
        for (int i = 0; i < warriorList.length; i++) {
            warriorList[i].start();
        }
        
        obj.twoDArrayVisualizer(obj.play_ground);
        // System.out.println(WARRIOR_1.nextlocation[0]+","+WARRIOR_1.nextlocation[1]);
        System.out.println(warriorList[1].succses);
        System.out.println(Arrays.toString(warriorList[1].location));
    }
}
