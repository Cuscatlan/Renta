<%-- 
    Document   : Cotizacion
    Created on : 01-26-2018, 04:42:39 PM
    Author     : DELL
--%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jf" uri="/WEB-INF/tlds/jform.tld" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="/WEB-INF/views/includes.jsp" %>
        <c:url value="/gridRenttransaccion" var="urlgridRenttransaccion" />
        <c:url value="/CalcularRenttransaccion" var="urlcalculoRenttransaccion" />
        <c:url value="/deleteRenttransaccion" var="urldeleteRenttransaccion" />
        <c:url value="/exportRenttransaccion" var="recordsExportUrlRenttransaccion" />
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
                        <h1>Servicio de Renta</h1>
                        <h2>puedes cotizar la renta con tu auto de preferencia sin ningun compromiso</h2>
                        <div class="modal-body">
                            <form method="POST" id="formRenttransaccion" action="${urlcalculoRenttransaccion}" data-parsley-validate="" novalidate="" class="form-horizontal">
                                <div class="panel panel-default">

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">FECHA DE RECEPCION</label>
                                        <div class="col-lg-10">
                                            <jf:datetime tabindex="3"   id="fechainiciotransaccion" name="fechainiciotransaccion" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">LUGAR DE RECEPCION</label>
                                        <div class="col-lg-10">
                                            <jf:textbox  tabindex="4" maxlength="45"   id="lugarrecepciontransaccion" name="lugarrecepciontransaccion" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">FECHA DE ENTREGA</label>
                                        <div class="col-lg-10">

                                            <jf:datetime tabindex="5"   id="fachefintransaccionr" name="fachefintransaccionr" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label"></label>
                                        <div class="col-lg-10">

                                            <jf:combobox url="/AutosRent/cbofilterRentauto"   id="rentautoDelegate"  name="rentauto.idauto" />
                                        </div>
                                    </div>
                                        
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">IDCLIENTE</label>
                                        <div class="col-lg-10">

                                            <jf:combobox url="/AutosRent/cbofilterRentcliente"   id="rentclienteDelegate"  name="rentcliente.idcliente" />
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">IDTIPOTRANSACCION</label>
                                        <div class="col-lg-10">

                                            <jf:combobox url="/AutosRent/cbofilterRenttipotransaccion"   id="renttipotransaccionDelegate"  name="renttipotransaccion.idtipotransaccion" />
                                        </div>
                                    </div>

                                   

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">IDVENDEDOR</label>
                                        <div class="col-lg-10">

                                            <jf:combobox url="/AutosRent/cbofilterRentvendedor"   id="rentvendedorDelegate"  name="rentvendedor.idvendedor" />
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
            </section>
            <!-- Page footer-->
            <%@include file="/WEB-INF/views/footer.jsp" %>
        </div>
    </body>

