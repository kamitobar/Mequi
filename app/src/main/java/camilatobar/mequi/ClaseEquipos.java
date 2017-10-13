package camilatobar.mequi;

/**
 * Created by KaReNCaMiLa on 24/09/2017.
 */

public class ClaseEquipos {
    String nombre, hoja, protocolo, manual, video;

    public ClaseEquipos(){

    }

    public ClaseEquipos(String nombre, String hoja, String protocolo, String manual, String video) {
        this.nombre = nombre;
        this.hoja = hoja;
        this.protocolo = protocolo;
        this.manual = manual;
        this.video = video;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHoja() {
        return hoja;
    }

    public void setHoja(String hoja) {
        this.hoja = hoja;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getManual() {
        return manual;
    }

    public void setManual(String manual) {
        this.manual = manual;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
