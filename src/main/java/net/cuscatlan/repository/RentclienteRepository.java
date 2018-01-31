package net.cuscatlan.repository;

import net.cuscatlan.domain.Rentcliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentclienteRepository extends JpaRepository <Rentcliente,    Integer >,PagingAndSortingRepository<Rentcliente,    Integer >{

    @Query("SELECT x "
            + " FROM Rentcliente x "
            + "WHERE"  
            + "  (:idcliente is null or :idcliente = x.idcliente )   "
            + " and (:password is null or LOWER(x.password) LIKE LOWER(CONCAT('%',:password ,'%')))   "
            + " and (:targetacliente is null or x.targetacliente = :targetacliente ) "
            + " and (:correocliente is null or LOWER(x.correocliente) LIKE LOWER(CONCAT('%',:correocliente ,'%')))   "
            + " and (:codigocliente is null or LOWER(x.codigocliente) LIKE LOWER(CONCAT('%',:codigocliente ,'%')))   "
            + " and  (:fkidpersonarentpersona is null or :fkidpersonarentpersona = x.rentpersona.idpersona) "
            + " ORDER BY x.idcliente ASC ")
     Page<Rentcliente> findByFilters(Pageable page  ,@Param("idcliente")  String idcliente ,@Param("password")  String password ,@Param("targetacliente")  String targetacliente ,@Param("correocliente")  String correocliente ,@Param("codigocliente")  String codigocliente ,@Param("fkidpersonarentpersona")  String fkidpersonarentpersona);

    @Query(value ="SELECT  x.IDCLIENTE  ,  x.PASSWORD  ,  x.TARGETACLIENTE  ,  x.CORREOCLIENTE  ,  x.CODIGOCLIENTE  ,  x.IDPERSONA  "
            + " FROM RENTCLIENTE x "
            + "WHERE"  
            + "  (:idcliente is null or :idcliente = x.IDCLIENTE )   "
            + " and (:password is null or LOWER(x.PASSWORD) LIKE LOWER('%' || :password || '%'))   "
            + " and (:targetacliente is null or x.TARGETACLIENTE = :targetacliente ) "
            + " and (:correocliente is null or LOWER(x.CORREOCLIENTE) LIKE LOWER('%' || :correocliente || '%'))   "
            + " and (:codigocliente is null or LOWER(x.CODIGOCLIENTE) LIKE LOWER('%' || :codigocliente || '%'))   "
            + " and  (:fkidpersonarentpersona is null or :fkidpersonarentpersona = x.IDPERSONA) "
            + " ORDER BY x.IDCLIENTE ASC ",nativeQuery=true)
     List<Object[]> findByFilters( @Param("idcliente")  String idcliente ,@Param("password")  String password ,@Param("targetacliente")  String targetacliente ,@Param("correocliente")  String correocliente ,@Param("codigocliente")  String codigocliente ,@Param("fkidpersonarentpersona")  String fkidpersonarentpersona);

}

