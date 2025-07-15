package core.objects;

public class Guard extends Player{
    public Guard(String name, String teamName, char position, Float points, Float assists, Float rebounds, int uniqueStat1, int uniqueStat2){
        super(name, teamName, position, points, assists, rebounds, uniqueStat1, uniqueStat2);
        this.uniqueStat1 = uniqueStat1;
        this.uniqueStat2 = uniqueStat2;
    }

    /**
     * @return player's uniquestats average based on position
     */
    @Override
    public int overallUniqueStats() {
        return (uniqueStat1 + uniqueStat2) / 200;
    }

    /**
     * override toString method
     * @return player's all basic info and stats along with their unique stats based on positiion
     */
    @Override
    public String toString() {

        return getName() +" "+ getTeamName() +" "+ getPosition() +"\n\n====Stats====\n"+ "PPG: " +getPoints() +"\tAPG: "+ getAssists() +"\tRPG: "+ getRebounds() +"\n\n====Unique Guard Ratings====\n" + "Shooting Ability: " +getUniqueStat1() +"\tPassing Ability: "+getUniqueStat2();
    }
}
