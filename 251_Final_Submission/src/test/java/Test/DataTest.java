package Test;
import core.Menu;
import core.objects.Center;
import core.objects.Guard;
import core.objects.Player;
import core.util.FileLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static core.Menu.data;
import static org.junit.jupiter.api.Assertions.*;

public class DataTest {




    @Test
    public void teamAvgScoreT1() {
        File file;
        file = new File("tests.txt");
        data = FileLoader.load(file);
        String team = "Denver Nuggets";

        float expected = (float) 95.3;
        float actual = data.teamAvgScore(team);

        assertEquals(expected, actual);
    }



    @Test
    public void opAvgScoreT1() {
        File file;
        file = new File("tests.txt");
        data = FileLoader.load(file);
        String team = "Golden State Warriors";

        float expected = (float) 84.399994;
        float actual = data.opAvgScore(team);

        assertEquals(expected, actual);
    }

    @Test
    public void teamWinCalculationT1() {
        File file;
        file = new File("tests.txt");
        data = FileLoader.load(file);
        String team = "Denver Nuggets";

        float expected = (float) 100.0;
        float actual = data.teamWinCalculation(team);

        assertEquals(expected, actual);
    }

    @Test
    public void LoadingFile() {
        int count = 0;
        File filename = new File("tests.txt");
        Menu.load(filename);
        for (Player p : data.getPlayers()) {
            System.out.println(p);
            if (p.getName().equals("Jakob Poeltl")) {
                assertEquals("Toronto Raptors", p.getTeamName());
                assertEquals('C', p.getPosition());
                assertEquals(12.4, p.getPoints(), 0.1);
                assertEquals(2.8, p.getAssists(), 0.1);
                assertEquals(9.1, p.getRebounds(), 0.1);
                assertEquals(72, p.getUniqueStat1());
                assertEquals(74, p.getUniqueStat2());
                count++;
            } else if (p.getName().equals("Stephen Curry")) {
                assertEquals("Golden State Warriors", p.getTeamName());
                assertEquals('G', p.getPosition());
                assertEquals(24.3, p.getPoints(), 0.1);
                assertEquals(6.1, p.getAssists(), 0.1);
                assertEquals(4.4, p.getRebounds(), 0.1);
                assertEquals(99, p.getUniqueStat1());
                assertEquals(99, p.getUniqueStat2());
                count++;
            } else if (p.getName().equals("Gary Trent Jr.")) {
                assertEquals("Toronto Raptors", p.getTeamName());
                assertEquals('G', p.getPosition());
                assertEquals(13.7, p.getPoints(), 0.1);
                assertEquals(1.6, p.getAssists(), 0.1);
                assertEquals(2.7, p.getRebounds(), 0.1);
                assertEquals(66, p.getUniqueStat1());
                assertEquals(65, p.getUniqueStat2());
                count++;
            }
        }
        //number of players in data
        assertEquals(3, count);
    }

    @Test
    public void LoadingFileInvalidFileName() {
        int count = 0;
        File filename = new File("playerssssssss.txt");
        Menu.load(filename);
    }

    @Test
    public void playersInSpecificTeamTorontoRaptors() {
        File filename = new File("player.txt");
        Menu.load(filename);
        int count = 0;
        String teamName = "Toronto Raptors";
        for(Player p : data.playersInSpecificTeam(teamName)){
            if(p.getName().equals("Scottie Barnes")){
                count++;
            } else if (p.getName().equals("RJ Barrett")) {
                count ++;
            } else if (p.getName().equals("Jakob Poeltl")) {
                count ++;
            } else if (p.getName().equals("Immanuel Quickley")) {
                count ++;
            } else if (p.getName().equals("Gary Trent Jr.")) {
                count ++;
            }else {
                count = 1; //reset the counter if the wrong player is in the list
            }
        }assertEquals(5, count);
    }

    @Test
    public void playersInSpecificTeamBostonCeltics() {
        File filename = new File("player.txt");
        Menu.load(filename);
        int count = 0;
        String teamName = "Boston Celtics";
        for(Player p : data.playersInSpecificTeam(teamName)){
            if(p.getName().equals("Jayson Tatum")){
                count++;
            } else if (p.getName().equals("Derrick White")) {
                count ++;
            } else if (p.getName().equals("Kristaps Porzingis")) {
                count ++;
            } else if (p.getName().equals("Al Horford")) {
                count ++;
            } else if (p.getName().equals("Sam Hauser")) {
                count ++;
            }else {
                count = 1; //reset counter if the wrong player is in the list
            }
        }assertEquals(5, count);
    }

    @Test
    public void playersInSpecificTeamEmptyTeam() {
        File filename = new File("player.txt");
        Menu.load(filename);
        int count = 0;
        String teamName = "no team";
        data.playersInSpecificTeam(teamName);

        assertTrue(data.playersInSpecificTeam(teamName).isEmpty());
    }

    @Test
    public void equalsT1() {
        Center center = new Center("Nikola Jokic", "Denver Nuggets", 'C', 26.2f, 8.3f, 11.0f, 91, 93);
        // Adding a player that is already in the data
        Center newCenter = new Center("Nikola Jokic", "Denver Nuggets", 'C', 26.2f, 8.3f, 11.0f, 91, 93);

        // Equals method looks for the new add player in the player array and returns true if they are duplicates / equal to each other
        Boolean duplicate = center.equals(newCenter);
        assertTrue(duplicate);
    }

    @Test
    public void equalsT2() {
        Guard guard = new Guard("Steve Kerr", "Chicago Bulls", 'G', 9.2f, 1.3f, 4.5f, 77, 93);
        // Adding a player that is not in the data
        Guard newGuard = new Guard("Micheal Jordan", "Chicago Bulls", 'G', 33.2f, 6.3f, 7.5f, 99, 93);

        Boolean duplicate = equals(newGuard);
        assertFalse(duplicate);
    }
}
