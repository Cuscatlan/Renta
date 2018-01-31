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
@Table(name = "RENTUSUARIOS")
public class Rentusuarios implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @NotNull(message ="No puede estar vacio el campo idusuario")
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "IDUSUARIO", nullable = false)
    @JsonView
    @GeneratedValue(strategy =GenerationType.AUTO, generator = "SEQ_RENTUSUARIOS")
    @SequenceGenerator(name="SEQ_RENTUSUARIOS",sequenceName="SEQ_RENTUSUARIOS")
    Integer idusuario;



    @Column(name = "NOMBREUSUARIO",length =50, nullable = true)
    @JsonView
    String nombreusuario;


    @Column(name = "CLAVEUSUARIO",length =100, nullable = true)
    @JsonView
    String claveusuario;


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

