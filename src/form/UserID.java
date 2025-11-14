package form;

public class UserID {

    private static String id;
    private static String nama;

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
}
