public class Main {
    
    public static void main(String[] args) {
        PlayGround pGround = new PlayGround(10, 10);
        StaticObjects.setModo(5, 6,pGround.play_ground);
        StaticObjects.setMonsters(5,pGround.play_ground);
        StaticObjects.setTrees(5,pGround.play_ground);
        Warriors[] warriorList = new Warriors[5];
        for (int i=0;i<warriorList.length;i++) {
            if(i==4){
                warriorList[i] = new SupperWarrior();
            }else{
                warriorList[i] = new Warriors();
            } 
        }
        for (Warriors i : warriorList) {
            i.targetX=StaticObjects.modox;
            i.targetY=StaticObjects.modoy;
            i.plane=pGround.play_ground;
            i.setWarrior();
            
            
        }
        for (int i = 0; i < warriorList.length; i++) {
            warriorList[i].start();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
       
    }
}
