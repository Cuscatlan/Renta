package net.cuscatlan.repository;

import net.cuscatlan.domain.Rentusuarios;
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

public interface RentusuariosRepository extends JpaRepository <Rentusuarios,    Integer >,PagingAndSortingRepository<Rentusuarios,    Integer >{

    @Query("SELECT x "
            + " FROM Rentusuarios x "
            + "WHERE"  
            + "  (:idusuario is null or :idusuario = x.idusuario )   "
            + " and (:nombreusuario is null or LOWER(x.nombreusuario) LIKE LOWER(CONCAT('%',:nombreusuario ,'%')))   "
            + " and (:claveusuario is null or LOWER(x.claveusuario) LIKE LOWER(CONCAT('%',:claveusuario ,'%')))   "
            + " and  (:fkidpersonarentpersona is null or :fkidpersonarentpersona = x.rentpersona.idpersona) "
            + " ORDER BY x.idusuario ASC ")
     Page<Rentusuarios> findByFilters(Pageable page  ,@Param("idusuario")  String idusuario ,@Param("nombreusuario")  String nombreusuario ,@Param("claveusuario")  String claveusuario ,@Param("fkidpersonarentpersona")  String fkidpersonarentpersona);

    @Query(value ="SELECT  x.IDUSUARIO  ,  x.NOMBREUSUARIO  ,  x.CLAVEUSUARIO  ,  x.IDPERSONA  "
            + " FROM RENTUSUARIOS x "
            + "WHERE"  
            + "  (:idusuario is null or :idusuario = x.IDUSUARIO )   "
            + " and (:nombreusuario is null or LOWER(x.NOMBREUSUARIO) LIKE LOWER('%' || :nombreusuario || '%'))   "
            + " and (:claveusuario is null or LOWER(x.CLAVEUSUARIO) LIKE LOWER('%' || :claveusuario || '%'))   "
            + " and  (:fkidpersonarentpersona is null or :fkidpersonarentpersona = x.IDPERSONA) "
            + " ORDER BY x.IDUSUARIO ASC ",nativeQuery=true)
     List<Object[]> findByFilters( @Param("idusuario")  String idusuario ,@Param("nombreusuario")  String nombreusuario ,@Param("claveusuario")  String claveusuario ,@Param("fkidpersonarentpersona")  String fkidpersonarentpersona);

}

