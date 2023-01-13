package ua.denis.jdbcproject.server.db.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class Car {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String brand;
  private String model;
  private int seatsCount;
  private int baggageMass;
  private BodyType bodyType;
  private FuelType fuelType;
  private Transmission transmission;
  private int volume;
  private int power;
  private long authorId;
  public String getCar() {
    return new StringBuilder()
        .append(brand + " | ")
        .append(model + " | ")
        .append(seatsCount + " seats | ")
        .append(baggageMass + " kg | ")
        .append(bodyType)
        .toString();
  }
  public enum Transmission{
    AUTOMATIC, MANUAL
  }
  public enum BodyType{
    COUPE, HATCHBACK, SEDAN, LIMOUSINE, MINIVAN, ROADSTER
  }
  public enum FuelType{
    GASOLINE, DIESEL, BIODIESEL, PROPANE, ELECTRICITY, HYDROGEN, HYBRID
  }
}
