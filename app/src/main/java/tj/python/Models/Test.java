package tj.python.Models;

import java.util.ArrayList;
import java.util.List;

public class Test {

    private String  id;
    private String javob;
    private String savol;
    private String id_sub_mavzu;
    private List<String> variants = new ArrayList<>();

    public String getId_sub_mavzu() {
        return id_sub_mavzu;
    }

    public void setId_sub_mavzu(String id_sub_mavzu) {
        this.id_sub_mavzu = id_sub_mavzu;
    }

    public String getSavol() {
        return savol;
    }

    public void setSavol(String savol) {
        this.savol = savol;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJavob() {
        return javob;
    }

    public void setJavob(String javob) {
        this.javob = javob;
    }

    public List<String> getVariants() {
        return variants;
    }

    public void setVariants(List<String> variants) {
        this.variants = variants;
    }
}
