package net.cuscatlan.repository;

import net.cuscatlan.domain.Renttelefono;
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

public interface RenttelefonoRepository extends JpaRepository <Renttelefono,    Integer >,PagingAndSortingRepository<Renttelefono,    Integer >{

    @Query("SELECT x "
            + " FROM Renttelefono x "
            + "WHERE"  
            + "  (:idtelefono is null or :idtelefono = x.idtelefono )   "
            + " and (:tipotelefono is null or LOWER(x.tipotelefono) LIKE LOWER(CONCAT('%',:tipotelefono ,'%')))   "
            + " and (:numerotelefono is null or LOWER(x.numerotelefono) LIKE LOWER(CONCAT('%',:numerotelefono ,'%')))   "
            + " and  (:fkidpersonarentpersona is null or :fkidpersonarentpersona = x.rentpersona.idpersona) "
            + " ORDER BY x.idtelefono ASC ")
     Page<Renttelefono> findByFilters(Pageable page  ,@Param("idtelefono")  String idtelefono ,@Param("tipotelefono")  String tipotelefono ,@Param("numerotelefono")  String numerotelefono ,@Param("fkidpersonarentpersona")  String fkidpersonarentpersona);

    @Query(value ="SELECT  x.IDTELEFONO  ,  x.TIPOTELEFONO  ,  x.NUMEROTELEFONO  ,  x.IDPERSONA  "
            + " FROM RENTTELEFONO x "
            + "WHERE"  
            + "  (:idtelefono is null or :idtelefono = x.IDTELEFONO )   "
            + " and (:tipotelefono is null or LOWER(x.TIPOTELEFONO) LIKE LOWER('%' || :tipotelefono || '%'))   "
            + " and (:numerotelefono is null or LOWER(x.NUMEROTELEFONO) LIKE LOWER('%' || :numerotelefono || '%'))   "
            + " and  (:fkidpersonarentpersona is null or :fkidpersonarentpersona = x.IDPERSONA) "
            + " ORDER BY x.IDTELEFONO ASC ",nativeQuery=true)
     List<Object[]> findByFilters( @Param("idtelefono")  String idtelefono ,@Param("tipotelefono")  String tipotelefono ,@Param("numerotelefono")  String numerotelefono ,@Param("fkidpersonarentpersona")  String fkidpersonarentpersona);

}

