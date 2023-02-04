
package com.webdev22_23.webdevelopment_project.database.model;

import java.util.UUID;

public class Review {
    private UUID reviewId;
    private String title;
    private String body;
    private float rating;
    private String writtenBy;
    private UUID referedTo;

    public UUID getReviewId() {
        return reviewId;
    }

    public void setReviewId(UUID reviewId) {
        this.reviewId = reviewId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getWrittenBy() {
        return writtenBy;
    }

    public void setWrittenBy(String writtenBy) {
        this.writtenBy = writtenBy;
    }

    public UUID getReferedTo() {
        return referedTo;
    }

    public void setReferedTo(UUID referedTo) {
        this.referedTo = referedTo;
    }
}
