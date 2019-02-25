package com.sl1_2;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Tester File - All things in this file are temporary.
 */

class Team {
    String id, name;

    ArrayList<Float> npOPRc = new ArrayList<>();
    ArrayList<Float> npOPR = new ArrayList<>();
    ArrayList<Float> OPR = new ArrayList<>();

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }
}

public class Tester {
    public static void main(String args[]) {
        Document doc = null;

        HashMap<String, Team> teams = new HashMap<>();

        teams.put("12599", new Team("12599", "Overcharged"));
        teams.put("12808", new Team("12808", "ReVamped"));
        teams.put("11089", new Team("10089", "Bytes of KitKats"));

        try {
            doc = Jsoup.connect("http://ftcstats.org").get();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        Elements rows = doc.select("tr");

        for (int i = 12; i < rows.size(); i++) {
            Element row = rows.get(i);

            Elements e = row.select("td");
            String id = e.get(1).text();

            if (teams.get(id) != null) {

            }
        }
    }
}
