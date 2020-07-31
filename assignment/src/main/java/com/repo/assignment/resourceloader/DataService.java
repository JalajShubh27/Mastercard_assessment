package com.repo.assignment.resourceloader;

import com.repo.assignment.util.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataService {

    @Autowired
    ResourceLoader resourceLoader;

    public String showPath(String origin, String destination) throws IOException {

        if (origin.equalsIgnoreCase(destination)) {
            return "yes";
        }

        Resource resource = resourceLoader.getResource("classpath:static/city.txt");
        InputStream in = resource.getInputStream();
        Map<String, Integer> codeCities = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        
        origin = origin.toLowerCase();
        destination = destination.toLowerCase();
        List<String> city1 = new ArrayList<>();
        List<String> city2 = new ArrayList<>();

        int code = -1;
        while (true) {
            String line = reader.readLine();
            if (line == null)
                break;

            String[] rowSplit = line.split(", ");

            if (!codeCities.containsKey(rowSplit[0].toLowerCase())) {
                code++;
                codeCities.put(rowSplit[0].toLowerCase(), code);
            }
            if (!codeCities.containsKey(rowSplit[1].toLowerCase())) {
                code++;
                codeCities.put(rowSplit[1].toLowerCase(), code);
            }
            city1.add(rowSplit[0].toLowerCase());
            city2.add(rowSplit[1].toLowerCase());
        }

        Graph g = new Graph(codeCities.size());

        for (int i = 0; i < city1.size(); i++) {
            g.addEdge(codeCities.get(city1.get(i)), codeCities.get(city2.get(i)));
        }
        if (!city1.contains(origin) || !city2.contains(destination))
            return "No";
        else
            return g.isReachable(codeCities.get(origin), codeCities.get(destination)) || g.isReachable(codeCities.get(destination), codeCities.get(origin)) ? "Yes" : "No";
    }
}