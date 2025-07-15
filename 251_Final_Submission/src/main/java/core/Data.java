/**
 * Raphael Dela Pena
 * Morgan Lee
 * Sean Bartolome
 *
 * April 22, 2025
 *
 * T05
 */

package core;

import core.objects.Center;
import core.objects.Forward;
import core.objects.Guard;
import core.objects.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Data{

    private ArrayList<Player> players;

    public Data() {
        players = new ArrayList<>();
    }

    /**
     * @return the ArrayList with player objects
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }


    public String listOfPlayers() {
        StringBuilder playerList = new StringBuilder();
        String table = String.format("%-25s %-25s %-10s %10s %10s %10s %10s %10s", "NAME", "TEAM", "POSITION",
                "PPG","APG","RPG","US1","US2");
        System.out.println(table);
        for(Player p : players){
            playerList.append(String.format("%-25s %-25s %-10s %10s %10s %10s %10s %10s\n", p.getName(), p.getTeamName(), p.getPosition(),
            p.getPoints(),p.getAssists(),p.getRebounds(),p.getUniqueStat1(),p.getUniqueStat2()));
        }
        return playerList.toString();
    }

    /**
     * adds a player object to the ArrayList player list
     * @param player
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    String[] teams = {"Oklahoma City Thunder", "Denver Nuggets", "Golden State Warriors", "Los Angeles Lakers", "Boston Celtics", "New York Knicks", "Cleveland Cavaliers", "Milwaukee Bucks", "Chicago Bulls", "Toronto Raptors"};

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Players:\n");
        for (Player player : players) { //loop through player object list
            result.append(player.toString()).append("\n");
        }
        return result.toString();
    }

    /**
     * Method used to check if a team exists in the data or not
     * @param teamName Will be an input given by a user
     * @return Boolean
     */
    public boolean teamChecker(String teamName) {
        for (Player player : players) {
            if (teamName.equals(player.getTeamName())) {
                return true; // Checking if the given team exists
            }
        }
        return false;
    }

    /**
     * Finds the average amount of points a team averages
     * @return A float
     */
    public float opAvgScore(String opponentTeam) {
        float opAvgScore = 0; // Average points a team scores

        for (Player player : players) {
            if (opponentTeam.equals(player.getTeamName())) {
                float playerPoints = player.getPoints();
                opAvgScore += playerPoints;
            }
        }
        return opAvgScore;
    }

    /**
     * The average amount a team scores per game
     * @return A float
     */
    public float teamAvgScore(String teamName) {
        float avgScore = 0; // The average amount of points the given team scores

        for (Player player : players) {
            if (teamName.equals(player.getTeamName())) {
                float playerPoints = player.getPoints();
                avgScore += playerPoints;
            }
        }
        return avgScore;
    }

    /**
     * Gets a team's winning record as a %
     * @param teamName Will be an input given by a user
     * @return A float
     */
    public float teamWinCalculation(String teamName) {
        // Keeping track of wins and losses
        int win = 0;
        int loss = 0;

        for (String team : teams) {

            if (!team.equals(teamName)) { // Making sure not to compare the same team
                if (teamAvgScore(teamName) > opAvgScore(team)) {
                    win++;
                } else if (teamAvgScore(teamName) < opAvgScore(team)) {
                    loss++;
                }
            }
        }

        if (win == 0) {
            return (float) 0;
        } else if (loss == 0) {
            return (float) 100;
        } else {
            int total = win + loss;
            return ((float) win / total) * 100;
        }
    }

    /**
     * Orders the stats of all players that are on a team and orders them from highest to lowest
     * @param teamName Will be an input given by user
     */
    public String bestDuoTeam(String teamName) {
        List<Player> bestDuoPlayers = new ArrayList<>();

        for (Player player : players) {
            if (teamName.equals(player.getTeamName())) {
                bestDuoPlayers.add(player);
            }
        }

        // The following code was made with the help of chatGPT
        // Compares players and places them in order
        // Descending from the highest total to lowest
        bestDuoPlayers.sort((p1, p2) -> Float.compare(
                    (p2.getPoints() + p2.getRebounds() + p2.getAssists()),
                    (p1.getPoints() + p1.getRebounds() + p1.getAssists())));

        return ("The best duo on the " + teamName + " is: " +
                "\n" + bestDuoPlayers.get(0).getName() + " & " + bestDuoPlayers.get(1).getName());

    }

    public String bestThreeCalculation() {
        List<Player> startingGuard = new ArrayList<>();
        List<Player> startingForward = new ArrayList<>();
        List<Player> startingCenter = new ArrayList<>();

        for (String teamName : teams) {
            for (Player player : players) {
                if (teamName.equals(player.getTeamName())) {
                    if (player instanceof Guard) {
                        startingGuard.add(player);
                    } else if (player instanceof Forward) {
                        startingForward.add(player);
                    } else if (player instanceof Center) {
                        startingCenter.add(player);
                    }
                }
            }
        }

        // Compares players from the Guard class and places them in order
        // Descending from the highest total to lowest
        startingGuard.sort((p1, p2) -> Float.compare(
                (p2.getPoints() + p2.getRebounds() + p2.getAssists()),
                (p1.getPoints() + p1.getRebounds() + p1.getAssists())));

        // Compares players from the Forward class and places them in order
        startingForward.sort((p1, p2) -> Float.compare(
                (p2.getPoints() + p2.getRebounds() + p2.getAssists()),
                (p1.getPoints() + p1.getRebounds() + p1.getAssists())));

        // Compares players from the Center class and places them in order
        startingCenter.sort((p1, p2) -> Float.compare(
                (p2.getPoints() + p2.getRebounds() + p2.getAssists()),
                (p1.getPoints() + p1.getRebounds() + p1.getAssists())));


        return ("The best players based on position: " +
                "\nGuard: " + startingGuard.get(0).getName() +
                "\nForward: " + startingForward.get(0).getName() +
                "\nCenter: " + startingCenter.get(0).getName());
    }

    /**
     * takes the input of a player's name
     * will output all player's info if the player exists
     */
    public void lookUpSpecificPlayer(String playerName){

        for(Player player : players){ //looping through the players ArrayList
            if(player.getName().equals(playerName)) { // checking if the inputted name == a name in the data
                System.out.println(player); //outputs the player's info
            }
        }
    }

    /**
     *helper method to get list of players in a specific team
     * @param teamName
     */
    public ArrayList<Player> playersInSpecificTeam(String teamName){
        ArrayList<Player> teamList = new ArrayList<>();
        for(Player player : players) {
            if(player.getTeamName().equals(teamName)){
                teamList.add(player);
            }
        }
        return teamList;
    }

    /**
     * uses the playersInSpecificTeam()
     * then sort each player into their different positions and get the best overall for each position to calculate the % of winning league
     */
    public String chanceOfWinningLeague(String teamName){
        //loops through player list and if the getTeam() is the same as teamName, add it to a list
        //initialize lists for each different positions
        List<Player> guards = new ArrayList<>();
        List<Player> forwards = new ArrayList<>();
        List<Player> centers = new ArrayList<>();

        for(Player player : playersInSpecificTeam(teamName)) { // loops through players and sorts them by position
            if(player instanceof Guard){
                guards.add(player);

            } else if (player instanceof Forward) {
                forwards.add(player);

            } else if (player instanceof Center) {
                centers.add(player);
            }
        }
        guards.sort(Comparator.comparing(Player::overallUniqueStats).reversed()); // sorting the guards  by most to least overall Unique stats
        forwards.sort(Comparator.comparing(Player::overallUniqueStats).reversed()); // sorting the forwards by most to least overall unique stats
        centers.sort(Comparator.comparing(Player::overallUniqueStats).reversed()); //sorting the centers by most to least overall unique stats

        int guardOverall = guards.get(0).getUniqueStat1() + guards.get(0).getUniqueStat2(); //takes the highest overall player in each position by getting the first one in the list after sorting
        int forwardOverall = forwards.get(0).getUniqueStat1() + forwards.get(0).getUniqueStat2();
        int centerOverall = centers.get(0).getUniqueStat1() + centers.get(0).getUniqueStat2();

        String total = String.format("%.2f", ((double) (guardOverall + forwardOverall + centerOverall) / 2400) * 100);
        return total;
    }

    public String leadingPointsInPosition() {
        List<Player> startingGuard = new ArrayList<>();
        List<Player> startingForward = new ArrayList<>();
        List<Player> startingCenter = new ArrayList<>();

        for (String teamName : teams) {
            for (Player player : players) {
                if (teamName.equals(player.getTeamName())) {
                    if (player instanceof Guard) {
                        startingGuard.add(player);
                    } else if (player instanceof Forward) {
                        startingForward.add(player);
                    } else if (player instanceof Center) {
                        startingCenter.add(player);
                    }
                }
            }
        }

        // Compares players from the Guard class and places them in order
        // Descending from the highest total to lowest
        startingGuard.sort((p1, p2) -> Float.compare((p2.getPoints()), (p1.getPoints())));

        // Compares players from the Forward class and places them in order
        startingForward.sort((p1, p2) -> Float.compare((p2.getPoints()), (p1.getPoints())));

        // Compares players from the Center class and places them in order
        startingCenter.sort((p1, p2) -> Float.compare((p2.getPoints()), (p1.getPoints())));

        return ("The leading scorers for each position: " +
                "\nGuard: " + startingGuard.get(0).getName() + " Is averaging: " + startingGuard.get(0).getPoints() + " PPG" +
                "\nForward: " + startingForward.get(0).getName() +  " Is averaging: " + startingForward.get(0).getPoints() + " PPG" +
                "\nCenter: " + startingCenter.get(0).getName() + " Is averaging: " + startingCenter.get(0).getPoints() + " PPG");
    }
}
