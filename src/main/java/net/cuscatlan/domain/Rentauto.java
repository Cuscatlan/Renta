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
import net.cuscatlan.domain.Renttipoauto;
import net.cuscatlan.domain.Renttransaccion;

@Entity
@Setter
@Getter
@Table(name = "RENTAUTO")
public class Rentauto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    //@NotNull(message ="No puede estar vacio el campo idauto")
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "IDAUTO")//, nullable = false)
    @JsonView
    @GeneratedValue(strategy =GenerationType.AUTO, generator = "SEQ_RENTAUTO")
    @SequenceGenerator(name="SEQ_RENTAUTO",sequenceName="SEQ_RENTAUTO", allocationSize = 1, initialValue = 1)
    Integer idauto;

    
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "FECHAAUTO", nullable = true)
    @JsonView
    Date fechaauto;


    @Column(name = "PRECIODIAAUTO", nullable = true)
    @JsonView
    String preciodiaauto;


    @Column(name = "COLORAUTO",length =10, nullable = true)
    @JsonView
    String colorauto;


    @Column(name = "MODELOAUTO",length =50, nullable = true)
    @JsonView
    String modeloauto;


    @Column(name = "PLACAAUTO",length =10, nullable = true)
    @JsonView
    String placaauto;


    @Column(name = "TARGETAAUTO",length =15, nullable = true)
    @JsonView
    String targetaauto;


    @ManyToOne(fetch = FetchType.LAZY)
    @Getter(onMethod = @__( @JsonIgnore))
    @JoinColumns({@JoinColumn(name = "IDTIPOAUTO", referencedColumnName = "IDTIPOAUTO")})
    Renttipoauto renttipoauto;

    @OneToMany(mappedBy = "rentauto", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @Getter(onMethod = @__(@JsonIgnore))
    List<Renttransaccion> renttransaccions;


    public    Integer getRenttipoautoDelegate(){
        return (this.renttipoauto== null) ? null : this.renttipoauto.getIdtipoauto();
    }

    public    Integer getRenttipoautoDescriptionDelegate(){
        return (this.renttipoauto== null) ? null : this.renttipoauto.getIdtipoauto();
    }


}

