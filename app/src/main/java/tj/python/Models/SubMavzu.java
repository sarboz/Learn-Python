package tj.python.Models;

public class SubMavzu {
    private String id;
    private String idMavzu;
    private String name;
    private String img;
    private String  fav;

    public String isFav() {
        return fav;
    }

    public void setFav(String fav) {
        this.fav = fav;
    }

    public String getIdMavzu() {
        return idMavzu;
    }

    public void setIdMavzu(String idMavzu) {
        this.idMavzu = idMavzu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
