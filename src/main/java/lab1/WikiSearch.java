package lab1;

/*
* Класс делает GET запрос, парсит с помощью GSON, возвращает список статей
* */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class WikiSearch {
    public static List<WikiArticle> search(String request) {
        List<WikiArticle> results = new ArrayList<>();
        try {
            URL url = new URL(request);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) JavaWikiClient/1.0");

            connection.setConnectTimeout(5000); // максимум 5 секунд на подключение
            connection.setReadTimeout(5000); // максимум 5 секунд на чтение

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8")); // для построчного чтения
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class); // создание типа JsonObject
            JsonArray searchResults = jsonObject.getAsJsonObject("query").getAsJsonArray("search");

            for (int i = 0; i < searchResults.size(); i++) {
                JsonObject item = searchResults.get(i).getAsJsonObject();
                int pageId = item.get("pageid").getAsInt();
                String title = item.get("title").getAsString();
                String snippet = item.get("snippet").getAsString().replaceAll("<[^>]*>", "");
                results.add(new WikiArticle(pageId, title, snippet));
            }
        }
        catch (Exception e) {
            System.out.println("Ошибка при поиске: " + e.getMessage());
        }

        return results;
    }
}
