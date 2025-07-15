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
import core.objects.Center;
import core.objects.Forward;
import core.objects.Guard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileLoader {


    /**
     *reading through the given file, looping through each line and creating a new player depending on their position.
     * adding the player objects into the data
     * returns the data of all players
     */
    public static Data load(File file) {
        Data data = new Data();

            char position;
            String name, teamName;
            float points, assists, rebounds;
            int uniqueStat1, uniqueStat2;
            String line;
                //reading from file, reading through each line
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));

                while ((line = reader.readLine()) != null) {

                    String[] values = line.split(",");

                    //setting each variable to the correct index in the file
                    name = values[0].trim();
                    teamName = values[1].trim();
                    position = values[2].charAt(0);
                    points = Float.parseFloat(values[3].trim());
                    assists = Float.parseFloat(values[4].trim());
                    rebounds = Float.parseFloat(values[5].trim());
                    uniqueStat1 = Integer.parseInt(values[6].trim());
                    uniqueStat2 = Integer.parseInt(values[7].trim());

                    //creating specific new player depending on their position
                    if (values[2].equals("G")) {
                        Guard myGuard = new Guard(name, teamName, position, points, assists, rebounds, uniqueStat1, uniqueStat2);
                        data.addPlayer(myGuard); //creating new Guard player object adding to data

                    } else if (values[2].equals("F")) {
                        Forward myForward = new Forward(name, teamName, position, points, assists, rebounds, uniqueStat1, uniqueStat2);
                        data.addPlayer(myForward); //creating new Forward player object adding to data

                    } else if (values[2].equals("C")) {
                        Center myCenter = new Center(name, teamName, position, points, assists, rebounds, uniqueStat1, uniqueStat2);
                        data.addPlayer(myCenter); // creating new Center player object adding to data
                    }

                }
                reader.close();
                return data; //return data with the list of all players and their stats
            } catch (IOException ioe) {
                System.out.println("Error, File not found.");
            }
            return null;
        }
    }

