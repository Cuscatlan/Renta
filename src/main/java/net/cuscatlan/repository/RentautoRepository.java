package net.cuscatlan.repository;

import net.cuscatlan.domain.Rentauto;
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

public interface RentautoRepository extends JpaRepository <Rentauto,    Integer >,PagingAndSortingRepository<Rentauto,    Integer >{

    @Query("SELECT x "
            + " FROM Rentauto x "
            + "WHERE"  
            + "  (:idauto is null or :idauto = x.idauto )   "
            + " and (:fechaauto is null or x.fechaauto = :fechaauto ) "
            + " and (:preciodiaauto is null or LOWER(x.preciodiaauto) LIKE LOWER(CONCAT('%',:preciodiaauto ,'%')))   "
            + " and (:colorauto is null or LOWER(x.colorauto) LIKE LOWER(CONCAT('%',:colorauto ,'%')))   "
            + " and (:modeloauto is null or LOWER(x.modeloauto) LIKE LOWER(CONCAT('%',:modeloauto ,'%')))   "
            + " and (:placaauto is null or LOWER(x.placaauto) LIKE LOWER(CONCAT('%',:placaauto ,'%')))   "
            + " and (:targetaauto is null or LOWER(x.targetaauto) LIKE LOWER(CONCAT('%',:targetaauto ,'%')))   "
            + " and  (:fkidtipoautorenttipoauto is null or :fkidtipoautorenttipoauto = x.renttipoauto.idtipoauto) "
            + " ORDER BY x.idauto ASC ")
     Page<Rentauto> findByFilters(Pageable page  ,@Param("idauto")  String idauto ,@Param("fechaauto")  String fechaauto ,@Param("preciodiaauto")  String preciodiaauto ,@Param("colorauto")  String colorauto ,@Param("modeloauto")  String modeloauto ,@Param("placaauto")  String placaauto ,@Param("targetaauto")  String targetaauto ,@Param("fkidtipoautorenttipoauto")  String fkidtipoautorenttipoauto);

    @Query(value ="SELECT  x.IDAUTO  ,  x.FECHAAUTO  ,  x.PRECIODIAAUTO  ,  x.COLORAUTO  ,  x.MODELOAUTO  ,  x.PLACAAUTO  ,  x.TARGETAAUTO  ,  x.IDTIPOAUTO  "
            + " FROM RENTAUTO x "
            + "WHERE"  
            + "  (:idauto is null or :idauto = x.IDAUTO )   "
            + " and (:fechaauto is null or x.FECHAAUTO = :fechaauto ) "
            + " and (:preciodiaauto is null or LOWER(x.PRECIODIAAUTO) LIKE LOWER('%' || :preciodiaauto || '%'))   "
            + " and (:colorauto is null or LOWER(x.COLORAUTO) LIKE LOWER('%' || :colorauto || '%'))   "
            + " and (:modeloauto is null or LOWER(x.MODELOAUTO) LIKE LOWER('%' || :modeloauto || '%'))   "
            + " and (:placaauto is null or LOWER(x.PLACAAUTO) LIKE LOWER('%' || :placaauto || '%'))   "
            + " and (:targetaauto is null or LOWER(x.TARGETAAUTO) LIKE LOWER('%' || :targetaauto || '%'))   "
            + " and  (:fkidtipoautorenttipoauto is null or :fkidtipoautorenttipoauto = x.IDTIPOAUTO) "
            + " ORDER BY x.IDAUTO ASC ",nativeQuery=true)
     List<Object[]> findByFilters( @Param("idauto")  String idauto ,@Param("fechaauto")  String fechaauto ,@Param("preciodiaauto")  String preciodiaauto ,@Param("colorauto")  String colorauto ,@Param("modeloauto")  String modeloauto ,@Param("placaauto")  String placaauto ,@Param("targetaauto")  String targetaauto ,@Param("fkidtipoautorenttipoauto")  String fkidtipoautorenttipoauto);

}

