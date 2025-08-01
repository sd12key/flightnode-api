package org.alvio.flightnode.rest.aircraft;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.alvio.flightnode.rest.flight.Flight;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Aircraft {

    @Id
    // @SequenceGenerator(name = "aircraft_sequence", sequenceName = "aircraft_sequence", allocationSize = 1, initialValue = 1)
    // @GeneratedValue(generator = "aircraft_sequence")

    // set for MySQL only
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Aircraft type is required.")
    @Pattern(regexp = "^[A-Za-z0-9 .'-]+$", message = "Invalid aircraft type.")
    @Size(max = 100, message = "Maximum 100 characters.")
    @Column(nullable = false, length = 100)
    private String type;

    @NotBlank(message = "Airline name is required.")
    @Pattern(
            regexp = "^[A-Za-z0-9 &'’\\-.,()]+$",
            message = "Invalid airline name (only letters, numbers, spaces, hyphens, apostrophes, and basic punctuation allowed)."
    )
    @Size(max = 100, message = "Maximum 100 characters.")
    @Column(nullable = false, length = 100)
    private String airlineName;

    @Min(value = 1, message = "Capacity must be a positive number.")
    @Column(nullable = false)
    private int capacity;

    @OneToMany(mappedBy = "aircraft", fetch = FetchType.EAGER)
    private List<Flight> flights = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type.trim();
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName.trim();
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Flight> getFlights() {
        return flights;
    }
}
