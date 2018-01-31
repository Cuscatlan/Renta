package net.cuscatlan.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import net.cuscatlan.domain.Rentcliente;
import net.cuscatlan.domain.Rentdireccion;
import net.cuscatlan.domain.Renttelefono;
import net.cuscatlan.domain.Rentusuarios;
import net.cuscatlan.domain.Rentvendedor;

@Entity
@Setter
@Getter
@Table(name = "RENTPERSONA")
public class Rentpersona implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @NotNull(message ="No puede estar vacio el campo idpersona")
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "IDPERSONA", nullable = false)
    @JsonView
    @GeneratedValue(strategy =GenerationType.AUTO, generator = "SEQ_RENTPERSONA")
    @SequenceGenerator(name="SEQ_RENTPERSONA",sequenceName="SEQ_RENTPERSONA")
    Integer idpersona;



    @Column(name = "NOMBREPERSONA",length =50, nullable = true)
    @JsonView
    String nombrepersona;


    @Column(name = "DUIPERSONA",length =8, nullable = true)
    @JsonView
    String duipersona;


    @Column(name = "APELLIDOPERSONA",length =50, nullable = true)
    @JsonView
    String apellidopersona;


    @OneToMany(mappedBy = "rentpersona", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @Getter(onMethod = @__(@JsonIgnore))
    List<Rentcliente> rentclientes;

    @OneToMany(mappedBy = "rentpersona", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @Getter(onMethod = @__(@JsonIgnore))
    List<Rentdireccion> rentdireccions;

    @OneToMany(mappedBy = "rentpersona", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @Getter(onMethod = @__(@JsonIgnore))
    List<Renttelefono> renttelefonos;

    @OneToMany(mappedBy = "rentpersona", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @Getter(onMethod = @__(@JsonIgnore))
    List<Rentusuarios> rentusuarioss;

    @OneToMany(mappedBy = "rentpersona", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @Getter(onMethod = @__(@JsonIgnore))
    List<Rentvendedor> rentvendedors;



}

