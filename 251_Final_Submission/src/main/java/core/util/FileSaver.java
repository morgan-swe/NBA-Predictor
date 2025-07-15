/**
 * Raphael Dela Pena
 * Morgan Lee
 * Sean Bartolome
 *
 * April 22, 2025
 *
 * T05
 */

package core.util;

import core.Data;
import core.objects.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileSaver {

    public static boolean save(File file, Data data) {
        try (FileWriter fw = new FileWriter(file)) {

            for(Player player: data.getPlayers()){
                fw.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%n", player.getName(),player.getTeamName(),player.getPosition(),player.getPoints(),player.getAssists(), player.getRebounds(), player.getUniqueStat1(), player.getUniqueStat2()));
            }

            return true;
        } catch (IOException ioe) {
            return false;
        }
    }
}