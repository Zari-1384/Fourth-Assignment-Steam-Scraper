import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Parser {
    static List<Game> games = new ArrayList<>();

    public List<Game> sortByName(){
        List<Game> sortedByName = new ArrayList<>(games);
        sortedByName.sort(Comparator.comparing(Game::getName));
        return  sortedByName;
    }

    public List<Game> sortByRating(){
        List<Game> sortedByRating = new ArrayList<>(games);
        sortedByRating.sort(Comparator.comparing(Game::getRating).reversed());
        return sortedByRating;
    }

    public List<Game> sortByPrice(){
        List<Game> sortedByPrice = new ArrayList<>(games);
        sortedByPrice.sort(Comparator.comparing(Game::getPrice).reversed());
        return sortedByPrice;
    }

    public void setUp() throws IOException {
        File html = new File("src/Resources/Video_Games.html");
        Document document = Jsoup.parse(html, "UTF-8");

        Elements AllElements = document.select(".game");

        List<Element> chosenElements = AllElements.subList(0, 100);

        for(Element element : chosenElements)
        {
            String title = element.select(".game-name").text();
            double rating = Double.parseDouble(element.select(".game-rating").text().split("/")[0]);
            int price = Integer.parseInt(element.select(".game-price").text().replace("€","").trim());
            Game game = new Game(title, rating, price);
            games.add(game);
        }
    }

    public static void main(String[] args) {
    }
}
