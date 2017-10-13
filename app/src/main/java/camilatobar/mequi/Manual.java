package camilatobar.mequi;

/**
 * Created by KaReNCaMiLa on 24/09/2017.
 */

public class Manual {
    String nombre, link;

    public Manual(){}

    public Manual(String nombre, String link) {
        this.nombre = nombre;
        this.link = link;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
