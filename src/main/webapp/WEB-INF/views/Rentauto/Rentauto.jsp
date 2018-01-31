<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jf" uri="/WEB-INF/tlds/jform.tld" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="/WEB-INF/views/includes.jsp" %>
        <c:url value="/gridRentauto" var="urlgridRentauto" />
        <c:url value="/saveRentauto" var="urlsaveRentauto" />
        <c:url value="/deleteRentauto" var="urldeleteRentauto" />
        <c:url value="/exportRentauto" var="recordsExportUrlRentauto" />
    </head>
    <body>
        <div class="wrapper">
            <!-- top navbar-->
            <%@include file="/WEB-INF/views/header.jsp" %>
            <!-- sidebar-->
            <%@include file="/WEB-INF/views/menu.jsp" %>
            <!-- offsidebar-->
            <%@include file="/WEB-INF/views/settings.jsp" %>
            <!-- Main section-->
            <section>
                <!-- Page content-->
                <div class="content-wrapper">
                    <div class="row">
                        <h3>Listado
                            <small>de autos</small>
                        </h3>
                        <div class="jqgrid-responsive mb-lg">
                            <jf:grid urlgrid="${urlgridRentauto}" 
                                     urlsave="${urlsaveRentauto}"
                                     urldelete="${urldeleteRentauto}"
                                     urlexport="${recordsExportUrlRentauto}"
                                     entity="Rentauto" 
                                     caption="Rentauto grid"
                                     />
                        </div>
                    </div>
                </div>
            </section>
            <!-- Page footer-->
            <%@include file="/WEB-INF/views/footer.jsp" %>
        </div>

        <!-- Modal-->
        <div id="modalRentauto" tabindex="-1" role="dialog" aria-labelledby="myModalLabelLarge" aria-hidden="true" class="modal fade">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" data-dismiss="modal" aria-label="Close" class="close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 id="myModalLabelLarge" class="modal-title">Datos automovil</h4>
                    </div>

                    <div class="modal-body">
                        <form method="POST" id="formRentauto" action="${urlsaveRentauto}" data-parsley-validate="" novalidate="" class="form-horizontal">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <div class="form-group">
                                        <!--  <label class="col-sm-2 control-label">IDAUTO</label>
                                         <div class="col-lg-10">
 
                                        <jf:numberbox  precision="10"    id="idauto" name="idauto"  required="true" />
                                       
                                    </div> -->
                                          <input type="hidden" id="idauto" name="idauto" >
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">TIPO</label>
                                    <div class="col-lg-10">
                                        <jf:combobox url="/AutosRent/cbofilterRenttipoauto"   id="renttipoautoDelegate"  name="renttipoauto.idtipoauto" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">MARCA</label>
                                    <div class="col-lg-10">
                                        <jf:textbox  tabindex="6" maxlength="15"   id="targetaauto" name="targetaauto" />
                                    </div>
                                </div> 
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">MODELO</label>
                                    <div class="col-lg-10">

                                        <jf:textbox  tabindex="4" maxlength="50"   id="modeloauto" name="modeloauto" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">COLOR</label>
                                    <div class="col-lg-10">

                                        <jf:textbox  tabindex="3" maxlength="10"   id="colorauto" name="colorauto" />
                                    </div>
                                </div>
                                    
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">PLACA</label>
                                    <div class="col-lg-10">
                                        <jf:textbox  tabindex="5" maxlength="10"   id="placaauto" name="placaauto" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">FECHA</label>
                                    <div class="col-lg-10">

                                        <jf:datetime tabindex="1"   id="fechaauto" name="fechaauto" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">PRECIO X DIA</label>
                                    <div class="col-lg-10">


                                        <jf:textbox tabindex="2" id="preciodiaauto" name="preciodiaauto" />
                                    </div>
                                </div>
                            </div>
                            <div class="panel-footer text-right">
                                <button type="button" data-dismiss="modal" class="btn btn-default">Close</button>
                                <button type="submit" class="btn btn-">Run validation</button>
                            </div>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>


