package net.cuscatlan.repository;

import net.cuscatlan.domain.Renttipotransaccion;
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

public interface RenttipotransaccionRepository extends JpaRepository <Renttipotransaccion,    Integer >,PagingAndSortingRepository<Renttipotransaccion,    Integer >{

    @Query("SELECT x "
            + " FROM Renttipotransaccion x "
            + "WHERE"  
            + "  (:idtipotransaccion is null or :idtipotransaccion = x.idtipotransaccion )   "
            + " and (:nombretipotransaccion is null or LOWER(x.nombretipotransaccion) LIKE LOWER(CONCAT('%',:nombretipotransaccion ,'%')))   "
            + " ORDER BY x.idtipotransaccion ASC ")
     Page<Renttipotransaccion> findByFilters(Pageable page  ,@Param("idtipotransaccion")  String idtipotransaccion ,@Param("nombretipotransaccion")  String nombretipotransaccion);

    @Query(value ="SELECT  x.IDTIPOTRANSACCION  ,  x.NOMBRETIPOTRANSACCION  "
            + " FROM RENTTIPOTRANSACCION x "
            + "WHERE"  
            + "  (:idtipotransaccion is null or :idtipotransaccion = x.IDTIPOTRANSACCION )   "
            + " and (:nombretipotransaccion is null or LOWER(x.NOMBRETIPOTRANSACCION) LIKE LOWER('%' || :nombretipotransaccion || '%'))   "
            + " ORDER BY x.IDTIPOTRANSACCION ASC ",nativeQuery=true)
     List<Object[]> findByFilters( @Param("idtipotransaccion")  String idtipotransaccion ,@Param("nombretipotransaccion")  String nombretipotransaccion);

}

