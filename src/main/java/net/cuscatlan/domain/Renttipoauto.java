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
import net.cuscatlan.domain.Rentauto;

@Entity
@Setter
@Getter
@Table(name = "RENTTIPOAUTO")
public class Renttipoauto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    //@NotNull(message ="No puede estar vacio el campo idtipoauto")
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "IDTIPOAUTO")
    @JsonView
    @GeneratedValue(strategy =GenerationType.AUTO, generator = "SEQ_RENTTIPOAUTO")
    @SequenceGenerator(name="SEQ_RENTTIPOAUTO",sequenceName="SEQ_RENTTIPOAUTO", allocationSize = 1, initialValue = 1)
    Integer idtipoauto;



    @Column(name = "TIPOAUTOS",length =15, nullable = true)
    @JsonView
    String tipoautos;


    @OneToMany(mappedBy = "renttipoauto", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @Getter(onMethod = @__(@JsonIgnore))
    List<Rentauto> rentautos;



}

