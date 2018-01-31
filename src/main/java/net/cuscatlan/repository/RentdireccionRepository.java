package net.cuscatlan.repository;

import net.cuscatlan.domain.Rentdireccion;
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

public interface RentdireccionRepository extends JpaRepository <Rentdireccion,    Integer >,PagingAndSortingRepository<Rentdireccion,    Integer >{

    @Query("SELECT x "
            + " FROM Rentdireccion x "
            + "WHERE"  
            + "  (:iddirecion is null or :iddirecion = x.iddirecion )   "
            + " and (:tipodireccion is null or LOWER(x.tipodireccion) LIKE LOWER(CONCAT('%',:tipodireccion ,'%')))   "
            + " and (:sdirecion is null or LOWER(x.sdirecion) LIKE LOWER(CONCAT('%',:sdirecion ,'%')))   "
            + " and  (:fkidpersonarentpersona is null or :fkidpersonarentpersona = x.rentpersona.idpersona) "
            + " ORDER BY x.iddirecion ASC ")
     Page<Rentdireccion> findByFilters(Pageable page  ,@Param("iddirecion")  String iddirecion ,@Param("tipodireccion")  String tipodireccion ,@Param("sdirecion")  String sdirecion ,@Param("fkidpersonarentpersona")  String fkidpersonarentpersona);

    @Query(value ="SELECT  x.IDDIRECION  ,  x.TIPODIRECCION  ,  x.SDIRECION  ,  x.IDPERSONA  "
            + " FROM RENTDIRECCION x "
            + "WHERE"  
            + "  (:iddirecion is null or :iddirecion = x.IDDIRECION )   "
            + " and (:tipodireccion is null or LOWER(x.TIPODIRECCION) LIKE LOWER('%' || :tipodireccion || '%'))   "
            + " and (:sdirecion is null or LOWER(x.SDIRECION) LIKE LOWER('%' || :sdirecion || '%'))   "
            + " and  (:fkidpersonarentpersona is null or :fkidpersonarentpersona = x.IDPERSONA) "
            + " ORDER BY x.IDDIRECION ASC ",nativeQuery=true)
     List<Object[]> findByFilters( @Param("iddirecion")  String iddirecion ,@Param("tipodireccion")  String tipodireccion ,@Param("sdirecion")  String sdirecion ,@Param("fkidpersonarentpersona")  String fkidpersonarentpersona);

}

