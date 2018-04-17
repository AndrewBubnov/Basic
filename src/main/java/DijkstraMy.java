import java.util.*;

public class DijkstraMy {
    final static String[] cities = {"Киев","Одесса","Донецк","Львов","Днепропетровск","Харьков","Николаев","Запорожье"};
                                  //  0       1        2        3          4              5         6           7

    final static int[][] distances = {
            {   0,  -1, 266,  -1,  -1, 487,  -1, 568}, // 0
            {  -1,   0,  -1,  -1,  -1,  -1, 134, 487}, // 1
            { 266,  -1,   0, 369, 239,  -1,  -1,  -1}, // 2
            {  -1,  -1, 369,   0, 127,  -1,  -1,  -1}, // 3
            {  -1,  -1, 239, 127,   0,  -1,  -1,  -1}, // 4
            { 487,  -1,  -1,  -1,  -1,   0, 551, 303}, // 5
            {  -1, 134,  -1,  -1,  -1, 551,   0, 352}, // 6
            { 568, 487,  -1,  -1,  -1, 303, 352,   0}, // 7
              //0  //1  //2  //3  //4  //5  //6  //7
    };

    public static void main(String[] args) {
        int start = 2;
        int end = 7;
        int current = start;
        int pathLength = 0;

        List<Integer> visited = new ArrayList<>();
        Map<Integer, Integer> cityMap = new HashMap<>();
        for (int i = 0; i < cities.length; i++) {
            if (i == start){
                cityMap.put(i, 0);
            } else cityMap.put(i, Integer.MAX_VALUE);
        }

        List<Integer> path = new ArrayList<>();
        while (current != end) {
        if (!visited.contains(current)) {
            visited.add(current);
            path.add(current);

        }
        if (adjacentCities(current).contains(end)){
            visited.add(end);
            path.add(end);
            System.out.printf("Путь из %s в %s выглядит как: ", cities[start], cities[end]);
            for (int i = 0; i < path.size() - 1; i++) {
                System.out.printf("%s-", cities[path.get(i)]);
            }
            System.out.printf("%s%s", cities[path.get(path.size() - 1)], ".");
            for (int i = 0; i < path.size() - 1; i++) {
              pathLength += distances[path.get(i)][path.get(i + 1)];
            }
            System.out.println();
            System.out.printf("Длина пути: %d%s", pathLength, "км.");
            break;
        }
        Queue<Integer> queue = relax(cityMap, distances, current);

        if (!queue.isEmpty()){
            current = queue.poll();
        }
        while (visited.contains(current)){
            if (!queue.isEmpty()){
                current = queue.poll();

            } else if (path.size() > 1){
                path.remove(path.size() - 1);
                current = path.get(path.size() - 1);
                break;
            } else {
                System.out.println("Путь не найден");
                return;
            }
        }
    }
}

    private static List<Integer> adjacentCities(int city){
        List<Integer> adjacent = new ArrayList<>();
        for (int i = 0; i < distances.length; i++) {
            if (distances[city][i] > 0){
                adjacent.add(i);
            }
        }
        return adjacent;

    }
    private static Queue<Integer> relax(Map<Integer, Integer> cityMap, int[][] distances, int current) {
        int[] temp = new int[adjacentCities(current).size()];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < adjacentCities(current).size(); i++) {
            if (cityMap.get(adjacentCities(current).get(i)) > distances[current][adjacentCities(current).get(i)]) {
                cityMap.put(adjacentCities(current).get(i), distances[current][adjacentCities(current).get(i)]);
                temp[i] = distances[current][adjacentCities(current).get(i)];

            } else {
                queue.add(adjacentCities(current).get(i));

            }
        }
        Arrays.sort(temp);
        for (int i : temp) {
            for (int j = 0; j < distances.length; j++) {
                if (i == distances[current][j]) {
                    queue.add(j);
                    break;
                }
            }
        }
        return queue;
    }
}
