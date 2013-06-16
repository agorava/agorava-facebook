/*
 * Copyright 2012 Agorava
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.agorava.facebook.model;

import java.util.Date;
import java.util.List;


/**
 * Model class representing a photo.
 *
 * @author Craig Walls
 */
public class Photo {
    private String id;

    private String name;

    private Reference from;

    private String link;

    private String icon;

    private Integer position;

    private Date createdTime;

    private Date updatedTime;

    private List<Tag> tags;

    private Image oversizedImage;

    private Image sourceImage;

    private Image smallImage;

    private Image albumImage;

    private Image tinyImage;

    private Photo(String id, Reference from, String link, String icon, Date createdTime, List<Image> images) {
        this.id = id;
        this.from = from;
        this.link = link;
        this.icon = icon;
        this.createdTime = createdTime;

        int i = 0;
        if (images.size() == 5) {
            this.oversizedImage = images.get(i++);
        }
        this.sourceImage = images.get(i++);
        this.albumImage = images.get(i++);
        this.smallImage = images.get(i++);
        this.tinyImage = images.get(i++);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Reference getFrom() {
        return from;
    }

    public String getLink() {
        return link;
    }

    public String getIcon() {
        return icon;
    }

    public Integer getPosition() {
        return position;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * An oversized image. May be null if no oversized image was provided.
     */
    public Image getOversizedImage() {
        return oversizedImage;
    }

    public Image getSourceImage() {
        return sourceImage;
    }

    public Image getSmallImage() {
        return smallImage;
    }

    public Image getAlbumImage() {
        return albumImage;
    }

    public Image getTinyImage() {
        return tinyImage;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public static class Image {

        private final int width;

        private final int height;

        private final String source;

        public Image(String source, int width, int height) {
            this.source = source;
            this.width = width;
            this.height = height;

        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public String getSource() {
            return source;
        }
    }

}
