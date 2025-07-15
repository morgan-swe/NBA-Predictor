package core.objects;

import java.util.Objects;

public abstract class Player {
    private String name;
    private String teamName;
    private char position;
    private Float points, assists, rebounds;
    int uniqueStat1;
    int uniqueStat2;

    protected Player(String name, String teamName, char position, Float points, Float assists, Float rebounds, int uniqueStat1, int uniqueStat2) {
        this.name = name;
        this.teamName = teamName;
        this.position = position;
        this.points = points;
        this.assists = assists;
        this.rebounds = rebounds;
        this.uniqueStat1 = uniqueStat1;
        this.uniqueStat2 = uniqueStat2;

    }

    // ----------------------
    // GETTERS
    // ----------------------
    public String getName() {
        return name;
    }

    public String getTeamName() {
        return teamName;
    }

    public char getPosition() {
        return position;
    }

    public Float getPoints() {
        return points;
    }

    public Float getAssists() {
        return assists;
    }

    public Float getRebounds() {
        return rebounds;
    }

    public int getUniqueStat1() {
        return uniqueStat1;
    }

    public int getUniqueStat2() {
        return uniqueStat2;
    }


    public abstract int overallUniqueStats();

    // ----------------------
    // SETTERS
    // ----------------------

    public void setPoints(Float points) {
        this.points = points;
    }

    public void setAssists(Float assists) {
        this.assists = assists;
    }

    public void setRebounds(Float rebounds) {
        this.rebounds = rebounds;
    }

    public void setUniqueStat1(int uniqueStat1){
        this.uniqueStat1 = uniqueStat1;
    }

    public void setUniqueStat2(int uniqueStat2) {
        this.uniqueStat2 = uniqueStat2;
    }

    public void setTeamName(String teamName) {this.teamName = teamName;}

    // ----------------------
    // EXTEND EQUALS
    // ----------------------

    /**
     * Extends equals method that makes sure there are no duplicate players
     * @param o Player object
     * @return Boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Player)) return false;

        Player player = (Player) o;
        String p = String.valueOf(position); // Needed the string value otherwise can not compare chars

        return Objects.equals(name, player.name) &&
                Objects.equals(teamName, player.teamName) &&
                p.equals(String.valueOf(player.position));
    }


}