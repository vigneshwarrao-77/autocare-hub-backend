package AutoCareHub.controller;
import AutoCareHub.entity.Booking;
import AutoCareHub.repository.BookingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/bookings") @CrossOrigin(origins="http://localhost:5173")
public class BookingController {
 private final BookingRepository repository;
 public BookingController(BookingRepository repository){this.repository=repository;}
 @GetMapping public List<Booking> all(){return repository.findAll();}
 @GetMapping("/{id}") public ResponseEntity<Booking> one(@PathVariable Long id){return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());}
 @PostMapping public ResponseEntity<?> create(@RequestBody Booking b){if(b.getCustomerName()==null||b.getVehicle()==null||b.getService()==null||b.getAppointmentDate()==null)return ResponseEntity.badRequest().body("Customer, vehicle, service and date are required.");if(b.getStatus()==null)b.setStatus("Pending");if(b.getPrice()==null)b.setPrice(0.0);return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(b));}
 @PutMapping("/{id}") public ResponseEntity<Booking> update(@PathVariable Long id,@RequestBody Booking input){return repository.findById(id).map(b->{if(input.getCustomerName()!=null)b.setCustomerName(input.getCustomerName());if(input.getPhone()!=null)b.setPhone(input.getPhone());if(input.getVehicle()!=null)b.setVehicle(input.getVehicle());if(input.getService()!=null)b.setService(input.getService());if(input.getAppointmentDate()!=null)b.setAppointmentDate(input.getAppointmentDate());if(input.getStatus()!=null)b.setStatus(input.getStatus());if(input.getPrice()!=null)b.setPrice(input.getPrice());if(input.getNotes()!=null)b.setNotes(input.getNotes());return ResponseEntity.ok(repository.save(b));}).orElse(ResponseEntity.notFound().build());}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repository.existsById(id))return ResponseEntity.notFound().build();repository.deleteById(id);return ResponseEntity.noContent().build();}
}
