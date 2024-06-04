package team.project.drivee.models;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import team.project.drivee.models.Trip;
import team.project.drivee.models.Vehicle;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_gen")
    @SequenceGenerator(name = "users_id_gen", sequenceName = "users_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "f_name")
    private String fName;

    @Column(name = "m_name")
    private String mName;

    @Column(name = "l_name")
    private String lName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password", nullable = false, length = Integer.MAX_VALUE)
    private String password;

    @Column(name = "photo")
    private byte[] photo;

    @Column(name = "number_of_movers")
    private Integer numberOfMovers;

    @ColumnDefault("false")
    @Column(name = "type_acc", nullable = false)
    private Boolean typeAcc = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @OneToMany(mappedBy = "client")
    private Set<Trip> trips = new LinkedHashSet<>();

    @OneToMany(mappedBy = "driver")
    private Set<Trip> trips_driver = new LinkedHashSet<>();

    public String encrytString(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(input.getBytes());
        BigInteger bigInt = new BigInteger(1, digest);
        return bigInt.toString(16);
    }


    public Boolean getTypeAcc() {
        return typeAcc;
    }

    public void setTypeAcc(Boolean typeAcc) {
        this.typeAcc = typeAcc;
    }

    public Set<Trip> getTrips_driver() {
        return trips_driver;
    }

    public void setTrips_driver(Set<Trip> trips_driver) {
        this.trips_driver = trips_driver;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getMName() {
        return mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        try {
            return encrytString(password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPassword(String password) {
        try {
            this.password = encrytString(password);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Ошибка при хэшировании пароля");
        }
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Integer getNumberOfMovers() {
        return numberOfMovers;
    }

    public void setNumberOfMovers(Integer numberOfMovers) {
        this.numberOfMovers = numberOfMovers;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }


    public Set<Trip> getTrips() {
        return trips;
    }

    public void setTrips(Set<Trip> trips) {
        this.trips = trips;
    }
}
