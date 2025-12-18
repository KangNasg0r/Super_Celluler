package form;
public class UserID {
    private static String id;
    private static String nama;
    private static String jenkel;
    private static String hp;

    static void setIdAdmin(String idKas) {
        UserID.id = idKas;
    }
    public static String getIdAdmin() {
        return id;
    }
    public static void setNamaAdmin(String namaAdmin) {
        UserID.nama = namaAdmin;
    }
    public static String getNamaAdmin() {
        return nama;
    }
   
    public static void setJenkelAdmin(String jenkelAdmin) {
        UserID.jenkel = jenkelAdmin;
    }
    public static String getJenkelAdmin() {
        return jenkel;
    }
    public static void setHPAdmin(String hpAdmin) {
        UserID.hp = hpAdmin;
    }
    public static String getHPAdmin() {
        return hp;
    }
}
