package House;

public class Apartment {
    private String tenPhong;
    private String tenKhach;

    public Apartment(String tenPhong, String tenKhach) {
        this.tenPhong = tenPhong;
        this.tenKhach = tenKhach;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getTenKhach() {
        return tenKhach;
    }

    public void setTenKhach(String tenKhach) {
        this.tenKhach = tenKhach;
    }
}
