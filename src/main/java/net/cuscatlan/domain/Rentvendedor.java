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
@Table(name = "RENTVENDEDOR")
public class Rentvendedor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull(message = "No puede estar vacio el campo idvendedor")
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "IDVENDEDOR", length = 10, nullable = false)
    @JsonView
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_RENTVENDEDOR")
    @SequenceGenerator(name = "SEQ_RENTVENDEDOR", sequenceName = "SEQ_RENTVENDEDOR")
    String idvendedor;

    @Column(name = "CORREOVENDEDOR", length = 50, nullable = true)
    @JsonView
    String correovendedor;

    @Column(name = "COMICIONVENDEDOR", nullable = true)
    @JsonView
    String comicionvendedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter(onMethod = @__(
            @JsonIgnore))
    @JoinColumns({
        @JoinColumn(name = "IDPERSONA", referencedColumnName = "IDPERSONA")})
    Rentpersona rentpersona;

    @OneToMany(mappedBy = "rentvendedor", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @Getter(onMethod = @__(
            @JsonIgnore))
    List<Renttransaccion> renttransaccions;

    public Integer getRentpersonaDelegate() {
        return (this.rentpersona == null) ? null : this.rentpersona.getIdpersona();
    }

    public Integer getRentpersonaDescriptionDelegate() {
        return (this.rentpersona == null) ? null : this.rentpersona.getIdpersona();
    }

}
