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
import net.cuscatlan.domain.Renttransaccion;

@Entity
@Setter
@Getter
@Table(name = "RENTTIPOTRANSACCION")
public class Renttipotransaccion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    //@NotNull(message ="No puede estar vacio el campo idtipotransaccion")
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "IDTIPOTRANSACCION")//, nullable = false)
    @JsonView
    @GeneratedValue(strategy =GenerationType.AUTO, generator = "SEQ_RENTTIPOTRANSACCION")
    @SequenceGenerator(name="SEQ_RENTTIPOTRANSACCION",sequenceName="SEQ_RENTTIPOTRANSACCION", allocationSize = 1, initialValue = 1)
    Integer idtipotransaccion;



    @Column(name = "NOMBRETIPOTRANSACCION",length =10, nullable = true)
    @JsonView
    String nombretipotransaccion;


    @OneToMany(mappedBy = "renttipotransaccion", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @Getter(onMethod = @__(@JsonIgnore))
    List<Renttransaccion> renttransaccions;



}

