package net.cuscatlan.repository;

import net.cuscatlan.domain.Rentpersona;
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

public interface RentpersonaRepository extends JpaRepository <Rentpersona,    Integer >,PagingAndSortingRepository<Rentpersona,    Integer >{

    @Query("SELECT x "
            + " FROM Rentpersona x "
            + "WHERE"  
            + "  (:idpersona is null or :idpersona = x.idpersona )   "
            + " and (:nombrepersona is null or LOWER(x.nombrepersona) LIKE LOWER(CONCAT('%',:nombrepersona ,'%')))   "
            + " and (:duipersona is null or LOWER(x.duipersona) LIKE LOWER(CONCAT('%',:duipersona ,'%')))   "
            + " and (:apellidopersona is null or LOWER(x.apellidopersona) LIKE LOWER(CONCAT('%',:apellidopersona ,'%')))   "
            + " ORDER BY x.idpersona ASC ")
     Page<Rentpersona> findByFilters(Pageable page  ,@Param("idpersona")  String idpersona ,@Param("nombrepersona")  String nombrepersona ,@Param("duipersona")  String duipersona ,@Param("apellidopersona")  String apellidopersona);

    @Query(value ="SELECT  x.IDPERSONA  ,  x.NOMBREPERSONA  ,  x.DUIPERSONA  ,  x.APELLIDOPERSONA  "
            + " FROM RENTPERSONA x "
            + "WHERE"  
            + "  (:idpersona is null or :idpersona = x.IDPERSONA )   "
            + " and (:nombrepersona is null or LOWER(x.NOMBREPERSONA) LIKE LOWER('%' || :nombrepersona || '%'))   "
            + " and (:duipersona is null or LOWER(x.DUIPERSONA) LIKE LOWER('%' || :duipersona || '%'))   "
            + " and (:apellidopersona is null or LOWER(x.APELLIDOPERSONA) LIKE LOWER('%' || :apellidopersona || '%'))   "
            + " ORDER BY x.IDPERSONA ASC ",nativeQuery=true)
     List<Object[]> findByFilters( @Param("idpersona")  String idpersona ,@Param("nombrepersona")  String nombrepersona ,@Param("duipersona")  String duipersona ,@Param("apellidopersona")  String apellidopersona);

}

