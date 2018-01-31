package net.cuscatlan.repository;

import net.cuscatlan.domain.Renttransaccion;
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

public interface RenttransaccionRepository extends JpaRepository <Renttransaccion,    Integer >,PagingAndSortingRepository<Renttransaccion,    Integer >{

    @Query("SELECT x "
            + " FROM Renttransaccion x "
            + "WHERE"  
            + "  (:idtransaccion is null or :idtransaccion = x.idtransaccion )   "
            + " and (:targetaasociadatransaccion is null or x.targetaasociadatransaccion = :targetaasociadatransaccion ) "
            + " and (:lugarentregatransaccion is null or LOWER(x.lugarentregatransaccion) LIKE LOWER(CONCAT('%',:lugarentregatransaccion ,'%')))   "
            + " and (:fechainiciotransaccion is null or x.fechainiciotransaccion = :fechainiciotransaccion ) "
            + " and (:lugarrecepciontransaccion is null or LOWER(x.lugarrecepciontransaccion) LIKE LOWER(CONCAT('%',:lugarrecepciontransaccion ,'%')))   "
            + " and (:fachefintransaccionr is null or x.fachefintransaccionr = :fachefintransaccionr ) "
            + " and (:totaltransaccion is null or LOWER(x.totaltransaccion) LIKE LOWER(CONCAT('%',:totaltransaccion ,'%')))   "
            + " and  (:fkidclienterentcliente is null or :fkidclienterentcliente = x.rentcliente.idcliente) "
            + " and  (:fkidtipotransaccionrenttipotransaccion is null or :fkidtipotransaccionrenttipotransaccion = x.renttipotransaccion.idtipotransaccion) "
            + " and  (:fkidautorentauto is null or :fkidautorentauto = x.rentauto.idauto) "
            + " and  (:fkidvendedorrentvendedor is null or :fkidvendedorrentvendedor = x.rentvendedor.idvendedor) "
            + " ORDER BY x.idtransaccion ASC ")
     Page<Renttransaccion> findByFilters(Pageable page  ,@Param("idtransaccion")  String idtransaccion ,@Param("targetaasociadatransaccion")  String targetaasociadatransaccion ,@Param("lugarentregatransaccion")  String lugarentregatransaccion ,@Param("fechainiciotransaccion")  String fechainiciotransaccion ,@Param("lugarrecepciontransaccion")  String lugarrecepciontransaccion ,@Param("fachefintransaccionr")  String fachefintransaccionr ,@Param("totaltransaccion")  String totaltransaccion ,@Param("fkidclienterentcliente")  String fkidclienterentcliente ,@Param("fkidtipotransaccionrenttipotransaccion")  String fkidtipotransaccionrenttipotransaccion ,@Param("fkidautorentauto")  String fkidautorentauto ,@Param("fkidvendedorrentvendedor")  String fkidvendedorrentvendedor);

    @Query(value ="SELECT  x.IDTRANSACCION  ,  x.TARGETAASOCIADATRANSACCION  ,  x.LUGARENTREGATRANSACCION  ,  x.FECHAINICIOTRANSACCION  ,  x.LUGARRECEPCIONTRANSACCION  ,  x.FACHEFINTRANSACCIONR  ,  x.TOTALTRANSACCION  ,  x.IDCLIENTE  ,  x.IDTIPOTRANSACCION  ,  x.IDAUTO  ,  x.IDVENDEDOR  "
            + " FROM RENTTRANSACCION x "
            + "WHERE"  
            + "  (:idtransaccion is null or :idtransaccion = x.IDTRANSACCION )   "
            + " and (:targetaasociadatransaccion is null or x.TARGETAASOCIADATRANSACCION = :targetaasociadatransaccion ) "
            + " and (:lugarentregatransaccion is null or LOWER(x.LUGARENTREGATRANSACCION) LIKE LOWER('%' || :lugarentregatransaccion || '%'))   "
            + " and (:fechainiciotransaccion is null or x.FECHAINICIOTRANSACCION = :fechainiciotransaccion ) "
            + " and (:lugarrecepciontransaccion is null or LOWER(x.LUGARRECEPCIONTRANSACCION) LIKE LOWER('%' || :lugarrecepciontransaccion || '%'))   "
            + " and (:fachefintransaccionr is null or x.FACHEFINTRANSACCIONR = :fachefintransaccionr ) "
            + " and (:totaltransaccion is null or LOWER(x.TOTALTRANSACCION) LIKE LOWER('%' || :totaltransaccion || '%'))   "
            + " and  (:fkidclienterentcliente is null or :fkidclienterentcliente = x.IDCLIENTE) "
            + " and  (:fkidtipotransaccionrenttipotransaccion is null or :fkidtipotransaccionrenttipotransaccion = x.IDTIPOTRANSACCION) "
            + " and  (:fkidautorentauto is null or :fkidautorentauto = x.IDAUTO) "
            + " and  (:fkidvendedorrentvendedor is null or :fkidvendedorrentvendedor = x.IDVENDEDOR) "
            + " ORDER BY x.IDTRANSACCION ASC ",nativeQuery=true)
     List<Object[]> findByFilters( @Param("idtransaccion")  String idtransaccion ,@Param("targetaasociadatransaccion")  String targetaasociadatransaccion ,@Param("lugarentregatransaccion")  String lugarentregatransaccion ,@Param("fechainiciotransaccion")  String fechainiciotransaccion ,@Param("lugarrecepciontransaccion")  String lugarrecepciontransaccion ,@Param("fachefintransaccionr")  String fachefintransaccionr ,@Param("totaltransaccion")  String totaltransaccion ,@Param("fkidclienterentcliente")  String fkidclienterentcliente ,@Param("fkidtipotransaccionrenttipotransaccion")  String fkidtipotransaccionrenttipotransaccion ,@Param("fkidautorentauto")  String fkidautorentauto ,@Param("fkidvendedorrentvendedor")  String fkidvendedorrentvendedor);

}

