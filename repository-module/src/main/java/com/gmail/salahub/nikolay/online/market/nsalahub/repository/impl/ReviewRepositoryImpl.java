package com.gmail.salahub.nikolay.online.market.nsalahub.repository.impl;

import com.gmail.salahub.nikolay.online.market.nsalahub.repository.ReviewRepository;
import com.gmail.salahub.nikolay.online.market.nsalahub.repository.model.review.Review;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("reviewRepository")
public class ReviewRepositoryImpl extends GenericRepositoryImpl<Long, Review> implements ReviewRepository {

    @Override
    public void updateButchShowingById(List<String> showingString, List<String> stringsIds) {
        String partOfQuery = getStringForUpdateBuchquery(showingString, stringsIds);
        String hqlQuery = "UPDATE Review R SET R.isShowing = CASE R.id"
                + partOfQuery +
                " ELSE R.isShowing END";
        Query query = entityManager.createQuery(hqlQuery);
        query.executeUpdate();
    }

    @Override
    public void deleteById(Long id) {
        String hqlQuery = "UPDATE Review R SET R.isDeleted = true WHERE R.id =:id";
        Query query = entityManager.createQuery(hqlQuery);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    private String getStringForUpdateBuchquery(List<String> showingString, List<String> stringsIds) {
        String result = null;
        for (int lengthArray = 0; lengthArray < showingString.size(); lengthArray++) {
            if (result == null) {
                result = " " + "WHEN" + " " + stringsIds.get(0) + " " + "THEN" + " " + showingString.get(0) + " ";
            } else {
                result = result + "WHEN" + " " + stringsIds.get(lengthArray) + " " + "THEN"
                        + " " + showingString.get(lengthArray) + " ";
            }
        }
        return result;
    }
}