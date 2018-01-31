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

@Entity
@Setter
@Getter
@Table(name = "RENTTELEFONO")
public class Renttelefono implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @NotNull(message ="No puede estar vacio el campo idtelefono")
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "IDTELEFONO", nullable = false)
    @JsonView
    @GeneratedValue(strategy =GenerationType.AUTO, generator = "SEQ_RENTTELEFONO")
    @SequenceGenerator(name="SEQ_RENTTELEFONO",sequenceName="SEQ_RENTTELEFONO")
    Integer idtelefono;



    @Column(name = "TIPOTELEFONO",length =10, nullable = true)
    @JsonView
    String tipotelefono;


    @Column(name = "NUMEROTELEFONO",length =10, nullable = true)
    @JsonView
    String numerotelefono;


    @ManyToOne(fetch = FetchType.LAZY)
    @Getter(onMethod = @__( @JsonIgnore))
    @JoinColumns({@JoinColumn(name = "IDPERSONA", referencedColumnName = "IDPERSONA")})
    Rentpersona rentpersona;


    public    Integer getRentpersonaDelegate(){
        return (this.rentpersona== null) ? null : this.rentpersona.getIdpersona();
    }

    public    Integer getRentpersonaDescriptionDelegate(){
        return (this.rentpersona== null) ? null : this.rentpersona.getIdpersona();
    }


}

