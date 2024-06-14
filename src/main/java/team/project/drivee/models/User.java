package team.project.drivee.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import team.project.drivee.models.Enum.Role;

import java.util.*;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {
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

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "password", length = Integer.MAX_VALUE)
    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", unique = true)
    private Vehicle vehicle;

    @OneToMany(mappedBy = "client")
    private Set<Trip> trips = new LinkedHashSet<>();

    @OneToMany(mappedBy = "driver")
    private Set<Trip> trips_driver = new LinkedHashSet<>();

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "roles_project",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> name = new HashSet<>();


    public Set<Trip> getTrips_driver() {
        return trips_driver;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return name;
    }

    @Override
    public String getUsername() {
        return phone;
    }

    public void setPassword(String password) {
            this.password = password;
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

    public Set<Role> getRoles() {
        return name;
    }

    public void setRoles(Set<Role> name) {
        this.name = name;
    }

}
