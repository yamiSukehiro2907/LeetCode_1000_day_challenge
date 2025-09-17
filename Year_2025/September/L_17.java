import java.util.*;

public class L_17 {
    public static void main(String[] args) {

    }
/*
class FoodRatings {
    private final Map<String, PriorityQueue<Pair>> cuisineMap;
    private final Map<String, Integer> foodMap;
    private final Map<String, String> foodToCuisineMap;
    private final String[] cuisines;
    private final String[] foods;
    private final int[] ratings;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        this.cuisineMap = new HashMap<>();
        this.foodToCuisineMap = new HashMap<>();
        this.foodMap = new HashMap<>();
        this.foods = foods;
        this.cuisines = cuisines;
        this.ratings = ratings;
        fill();
    }

    public void changeRating(String food, int newRating) {
        foodMap.put(food, newRating);
        String cuisine = foodToCuisineMap.get(food);
        cuisineMap.get(cuisine).add(new Pair(food, newRating));
    }

    public String highestRated(String cuisine) {
        PriorityQueue<Pair> pq = cuisineMap.get(cuisine);
        Pair highestRatedFood = pq.peek();
        while (foodMap.get(highestRatedFood.food) != highestRatedFood.rating) { // here we are just removing the foods who cuisines are not matching with the recent changes in food
            pq.poll();
            highestRatedFood = pq.peek();
        }
        return highestRatedFood.food;
    }

    private void fill() {
        int length = foods.length;
        for (int i = 0; i < length; i++) {
            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];
            if (!cuisineMap.containsKey(cuisine)) {
                cuisineMap.put(cuisine, new PriorityQueue<>(
                        (a, b) -> {
                            return a.rating == b.rating ? a.food.compareTo(b.food)
                                    : Integer.compare(b.rating, a.rating);
                        }));
            }
            cuisineMap.get(cuisine).add(new Pair(food, rating));
            foodMap.put(food, rating);
            foodToCuisineMap.put(food, cuisine);
        }
    }

    private class Pair {
        String food;
        int rating;

        Pair(String food, int rating) {
            this.food = food;
            this.rating = rating;
        }
    }
}          
*/

    private static class FoodRatings {
        private final Map<String, PriorityQueue<Pair>> cuisineMap;
        private final Map<String, Integer> foodRatings;
        private final Map<String, String> foodToCuisine;

        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
            int capacity = foods.length;
            this.cuisineMap = new HashMap<>(capacity);
            this.foodRatings = new HashMap<>(capacity);
            this.foodToCuisine = new HashMap<>(capacity);
            Map<String, Integer> cuisineCount = new HashMap<>();
            for (String cuisine : cuisines)
                cuisineCount.merge(cuisine, 1, Integer::sum);
            for (Map.Entry<String, Integer> entry : cuisineCount.entrySet())
                cuisineMap.put(entry.getKey(), new PriorityQueue<>(entry.getValue(),
                        (a, b) -> a.rating != b.rating ? Integer.compare(b.rating, a.rating)
                                : a.food.compareTo(b.food)));
            for (int i = 0; i < foods.length; i++) {
                String food = foods[i];
                String cuisine = cuisines[i];
                int rating = ratings[i];
                cuisineMap.get(cuisine).offer(new Pair(food, rating));
                foodRatings.put(food, rating);
                foodToCuisine.put(food, cuisine);
            }
        }

        public void changeRating(String food, int newRating) {
            foodRatings.put(food, newRating);
            cuisineMap.get(foodToCuisine.get(food)).offer(new Pair(food, newRating));
        }

        public String highestRated(String cuisine) {
            PriorityQueue<Pair> pq = cuisineMap.get(cuisine);
            Pair top;
            while ((top = pq.peek()).rating != foodRatings.get(top.food))
                pq.poll();
            return top.food;
        }

        private static final class Pair {
            final String food;
            final int rating;

            Pair(String food, int rating) {
                this.food = food;
                this.rating = rating;
            }
        }
    }
}
