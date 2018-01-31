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
import net.cuscatlan.domain.Renttipotransaccion;
import net.cuscatlan.domain.Rentauto;
import net.cuscatlan.domain.Rentvendedor;

@Entity
@Setter
@Getter
@Table(name = "RENTTRANSACCION")
public class Renttransaccion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    //@NotNull(message ="No puede estar vacio el campo idtransaccion")
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "IDTRANSACCION")//, nullable = false)
    @JsonView
    @GeneratedValue(strategy =GenerationType.AUTO, generator = "SEQ_RENTTRANSACCION")
    @SequenceGenerator(name="SEQ_RENTTRANSACCION",sequenceName="SEQ_RENTTRANSACCION", allocationSize = 1, initialValue = 1)
    Integer idtransaccion;


    @Column(name = "TARGETAASOCIADATRANSACCION", nullable = true)
    @JsonView
    Integer targetaasociadatransaccion;


    @Column(name = "LUGARENTREGATRANSACCION",length =45, nullable = true)
    @JsonView
    String lugarentregatransaccion;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")

    @Column(name = "FECHAINICIOTRANSACCION", nullable = true)
    @JsonView
    Date fechainiciotransaccion;


    @Column(name = "LUGARRECEPCIONTRANSACCION",length =45, nullable = true)
    @JsonView
    String lugarrecepciontransaccion;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")

    @Column(name = "FACHEFINTRANSACCIONR", nullable = true)
    @JsonView
    Date fachefintransaccionr;


    @Column(name = "TOTALTRANSACCION", nullable = true)
    @JsonView
    String totaltransaccion;


    @ManyToOne(fetch = FetchType.LAZY)
    @Getter(onMethod = @__( @JsonIgnore))
    @JoinColumns({@JoinColumn(name = "IDCLIENTE", referencedColumnName = "IDCLIENTE")})
    Rentcliente rentcliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter(onMethod = @__( @JsonIgnore))
    @JoinColumns({@JoinColumn(name = "IDTIPOTRANSACCION", referencedColumnName = "IDTIPOTRANSACCION")})
    Renttipotransaccion renttipotransaccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter(onMethod = @__( @JsonIgnore))
    @JoinColumns({@JoinColumn(name = "IDAUTO", referencedColumnName = "IDAUTO")})
    Rentauto rentauto;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter(onMethod = @__( @JsonIgnore))
    @JoinColumns({@JoinColumn(name = "IDVENDEDOR", referencedColumnName = "IDVENDEDOR")})
    Rentvendedor rentvendedor;

    public    Integer getRentclienteDelegate(){
        return (this.rentcliente== null) ? null : this.rentcliente.getIdcliente();
    }

    public    Integer getRentclienteDescriptionDelegate(){
        return (this.rentcliente== null) ? null : this.rentcliente.getIdcliente();
    }

    public    Integer getRenttipotransaccionDelegate(){
        return (this.renttipotransaccion== null) ? null : this.renttipotransaccion.getIdtipotransaccion();
    }

    public    Integer getRenttipotransaccionDescriptionDelegate(){
        return (this.renttipotransaccion== null) ? null : this.renttipotransaccion.getIdtipotransaccion();
    }

    public    Integer getRentautoDelegate(){
        return (this.rentauto== null) ? null : this.rentauto.getIdauto();
    }

    public    Integer getRentautoDescriptionDelegate(){
        return (this.rentauto== null) ? null : this.rentauto.getIdauto();
    }

    public    String getRentvendedorDelegate(){
        return (this.rentvendedor== null) ? null : this.rentvendedor.getIdvendedor();
    }

    public    String getRentvendedorDescriptionDelegate(){
        return (this.rentvendedor== null) ? null : this.rentvendedor.getIdvendedor();
    }


}

