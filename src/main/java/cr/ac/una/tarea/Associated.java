package cr.ac.una.tarea;

public class Associated {

    public String name;
    public int age;

    public String Folio;

    public String Photo;

    public Associated(String name, int age, String folio, String photo) {
        this.name = name;
        this.age = age;
        Folio = folio;
        Photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFolio() {
        return Folio;
    }

    public void setFolio(String folio) {
        Folio = folio;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public void createFolio(){

    }
}
