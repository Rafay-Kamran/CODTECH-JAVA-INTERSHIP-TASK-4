package com.example;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.util.List;

public class RecommenderExample {
    public static void main(String[] args) {
        try {
            DataModel model = new FileDataModel(new File("data/ratings.csv"));
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);
            UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

            List<RecommendedItem> recommendations = recommender.recommend(1, 3);

            System.out.println("Recommendations for User 1:");
            for (RecommendedItem item : recommendations) {
                System.out.println("Item ID: " + item.getItemID() + " , Score: " + item.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
