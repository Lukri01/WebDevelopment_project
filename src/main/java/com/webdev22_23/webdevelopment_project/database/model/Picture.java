
package com.webdev22_23.webdevelopment_project.database.model;

import java.util.UUID;

public class Picture {
    private UUID pictureId;
    private String data;

    public UUID getPictureId() {
        return pictureId;
    }

    public void setPictureId(UUID pictureId) {
        this.pictureId = pictureId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
