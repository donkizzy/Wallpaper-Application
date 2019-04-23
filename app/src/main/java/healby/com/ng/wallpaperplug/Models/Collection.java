package healby.com.ng.wallpaperplug.Models;

import com.google.gson.annotations.SerializedName;

public class Collection {
    @SerializedName("id")
    private int id ;

    @SerializedName("title")
    private String title ;

    @SerializedName("description")
    private String description ;

    @SerializedName("total_photos")
    private  int  totalPhotos ;
    @SerializedName("cover_photo")
    private Photos coverPhoto =  new Photos();
    @SerializedName("user")
    private User user =  new User();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalPhotos() {
        return totalPhotos;
    }

    public void setTotalPhotos(int totalPhotos) {
        this.totalPhotos = totalPhotos;
    }

    public Photos getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(Photos coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
