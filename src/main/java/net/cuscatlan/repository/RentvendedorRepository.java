package net.cuscatlan.repository;

import net.cuscatlan.domain.Rentvendedor;
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

public interface RentvendedorRepository extends JpaRepository <Rentvendedor,    String >,PagingAndSortingRepository<Rentvendedor,    String >{

    @Query("SELECT x "
            + " FROM Rentvendedor x "
            + "WHERE"  
            + "  (:idvendedor is null or :idvendedor = x.idvendedor )   "
            + " and (:correovendedor is null or LOWER(x.correovendedor) LIKE LOWER(CONCAT('%',:correovendedor ,'%')))   "
            + " and (:comicionvendedor is null or LOWER(x.comicionvendedor) LIKE LOWER(CONCAT('%',:comicionvendedor ,'%')))   "
            + " and  (:fkidpersonarentpersona is null or :fkidpersonarentpersona = x.rentpersona.idpersona) "
            + " ORDER BY x.idvendedor ASC ")
     Page<Rentvendedor> findByFilters(Pageable page  ,@Param("idvendedor")  String idvendedor ,@Param("correovendedor")  String correovendedor ,@Param("comicionvendedor")  String comicionvendedor ,@Param("fkidpersonarentpersona")  String fkidpersonarentpersona);

    @Query(value ="SELECT  x.IDVENDEDOR  ,  x.CORREOVENDEDOR  ,  x.COMICIONVENDEDOR  ,  x.IDPERSONA  "
            + " FROM RENTVENDEDOR x "
            + "WHERE"  
            + "  (:idvendedor is null or :idvendedor = x.IDVENDEDOR )   "
            + " and (:correovendedor is null or LOWER(x.CORREOVENDEDOR) LIKE LOWER('%' || :correovendedor || '%'))   "
            + " and (:comicionvendedor is null or LOWER(x.COMICIONVENDEDOR) LIKE LOWER('%' || :comicionvendedor || '%'))   "
            + " and  (:fkidpersonarentpersona is null or :fkidpersonarentpersona = x.IDPERSONA) "
            + " ORDER BY x.IDVENDEDOR ASC ",nativeQuery=true)
     List<Object[]> findByFilters( @Param("idvendedor")  String idvendedor ,@Param("correovendedor")  String correovendedor ,@Param("comicionvendedor")  String comicionvendedor ,@Param("fkidpersonarentpersona")  String fkidpersonarentpersona);

}

