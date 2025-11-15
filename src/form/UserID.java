package form;

public class UserID {

    private static String id;
    private static String nama;
    private static String jenkel;
    private static String hp;

    static void setIdKasir(String idKas) {
        UserID.id = idKas;
    }

    public static String getIdKasir() {
        return id;
    }

    public static void setNamaKasir(String namaKasir) {
        UserID.nama = namaKasir;
    }

    public static String getNamaKasir() {
        return nama;
    }
    
    public static void setJenkelKasir(String jenkelKasir) {
        UserID.jenkel = jenkelKasir;
    }

    public static String getJenkelKasir() {
        return jenkel;
    }
    
    public static void setHPKasir(String hpKasir) {
        UserID.hp = hpKasir;
    }

    public static String getHPKasir() {
        return hp;
    }
}
