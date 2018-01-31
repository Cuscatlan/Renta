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
import net.cuscatlan.domain.Rentpersona;
import net.cuscatlan.domain.Renttransaccion;

@Entity
@Setter
@Getter
@Table(name = "RENTCLIENTE")
public class Rentcliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
   // @NotNull(message ="No puede estar vacio el campo idcliente")
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "IDCLIENTE")//, nullable = false)
    @JsonView
    @GeneratedValue(strategy =GenerationType.AUTO, generator = "SEQ_RENTCLIENTE")
    @SequenceGenerator(name="SEQ_RENTCLIENTE",sequenceName="SEQ_RENTCLIENTE", allocationSize = 1, initialValue = 1)
    Integer idcliente;



    @Column(name = "PASSWORD",length =100, nullable = true)
    @JsonView
    String password;


    @Column(name = "TARGETACLIENTE", nullable = true)
    @JsonView
    Integer targetacliente;


    @Column(name = "CORREOCLIENTE",length =50, nullable = true)
    @JsonView
    String correocliente;


    @Column(name = "CODIGOCLIENTE",length =200, nullable = true)
    @JsonView
    String codigocliente;


    @ManyToOne(fetch = FetchType.LAZY)
    @Getter(onMethod = @__( @JsonIgnore))
    @JoinColumns({@JoinColumn(name = "IDPERSONA", referencedColumnName = "IDPERSONA")})
    Rentpersona rentpersona;

    @OneToMany(mappedBy = "rentcliente", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @Getter(onMethod = @__(@JsonIgnore))
    List<Renttransaccion> renttransaccions;


    public    Integer getRentpersonaDelegate(){
        return (this.rentpersona== null) ? null : this.rentpersona.getIdpersona();
    }

    public    Integer getRentpersonaDescriptionDelegate(){
        return (this.rentpersona== null) ? null : this.rentpersona.getIdpersona();
    }


}

