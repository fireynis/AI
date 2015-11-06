package City;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadCities {

    public ArrayList<City> readFile(String path) throws IOException {
        File in;
        FileReader reader;
        BufferedReader buffer;
        String line;
        ArrayList<City> cities = new ArrayList<>();
        Pattern p = Pattern.compile("^\\d");
        Matcher m;
        String[] split;

        if (path.isEmpty()) {
            URL url = getClass().getResource("/resources/berlin52.tsp");
            in = new File(url.getPath());
        } else {
            in = new File(path);
        }

        reader = new FileReader(in);

        buffer = new BufferedReader(reader);

        while ((line = buffer.readLine()) != null) {
            m = p.matcher(line);
            if (m.find()) {
                split = line.split("\\s");
                cities.add(new City(Double.parseDouble(split[1]), Double.parseDouble(split[2]), Integer.parseInt(split[0])));
            }
        }

        return cities;
    }
}
