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
@Table(name = "RENTDIRECCION")
public class Rentdireccion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @NotNull(message ="No puede estar vacio el campo iddirecion")
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "IDDIRECION", nullable = false)
    @JsonView
    @GeneratedValue(strategy =GenerationType.AUTO, generator = "SEQ_RENTDIRECCION")
    @SequenceGenerator(name="SEQ_RENTDIRECCION",sequenceName="SEQ_RENTDIRECCION")
    Integer iddirecion;



    @Column(name = "TIPODIRECCION",length =10, nullable = true)
    @JsonView
    String tipodireccion;


    @Column(name = "SDIRECION",length =50, nullable = true)
    @JsonView
    String sdirecion;


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

