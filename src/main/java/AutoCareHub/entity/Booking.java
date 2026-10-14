package AutoCareHub.entity;
import jakarta.persistence.*;
@Entity
@Table(name="bookings")
public class Booking {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private String customerName; private String phone; private String vehicle; private String service; private String appointmentDate; private String status; private Double price;
 @Column(length=1000) private String notes;
 public Booking() {}
 public Long getId(){return id;} public void setId(Long id){this.id=id;}
 public String getCustomerName(){return customerName;} public void setCustomerName(String v){customerName=v;}
 public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
 public String getVehicle(){return vehicle;} public void setVehicle(String v){vehicle=v;}
 public String getService(){return service;} public void setService(String v){service=v;}
 public String getAppointmentDate(){return appointmentDate;} public void setAppointmentDate(String v){appointmentDate=v;}
 public String getStatus(){return status;} public void setStatus(String v){status=v;}
 public Double getPrice(){return price;} public void setPrice(Double v){price=v;}
 public String getNotes(){return notes;} public void setNotes(String v){notes=v;}
}
