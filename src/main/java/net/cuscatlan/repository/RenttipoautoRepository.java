package net.cuscatlan.repository;

import net.cuscatlan.domain.Renttipoauto;
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

public interface RenttipoautoRepository extends JpaRepository <Renttipoauto,    Integer >,PagingAndSortingRepository<Renttipoauto,    Integer >{

    @Query("SELECT x "
            + " FROM Renttipoauto x "
            + "WHERE"  
            + "  (:idtipoauto is null or :idtipoauto = x.idtipoauto )   "
            + " and (:tipoautos is null or LOWER(x.tipoautos) LIKE LOWER(CONCAT('%',:tipoautos ,'%')))   "
            + " ORDER BY x.idtipoauto ASC ")
     Page<Renttipoauto> findByFilters(Pageable page  ,@Param("idtipoauto")  String idtipoauto ,@Param("tipoautos")  String tipoautos);

    @Query(value ="SELECT  x.IDTIPOAUTO  ,  x.TIPOAUTOS  "
            + " FROM RENTTIPOAUTO x "
            + "WHERE"  
            + "  (:idtipoauto is null or :idtipoauto = x.IDTIPOAUTO )   "
            + " and (:tipoautos is null or LOWER(x.TIPOAUTOS) LIKE LOWER('%' || :tipoautos || '%'))   "
            + " ORDER BY x.IDTIPOAUTO ASC ",nativeQuery=true)
     List<Object[]> findByFilters( @Param("idtipoauto")  String idtipoauto ,@Param("tipoautos")  String tipoautos);

}

