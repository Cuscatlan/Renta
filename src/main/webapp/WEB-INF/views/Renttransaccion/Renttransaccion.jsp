<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jf" uri="/WEB-INF/tlds/jform.tld" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="/WEB-INF/views/includes.jsp" %>
        <c:url value="/gridRenttransaccion" var="urlgridRenttransaccion" />
        <c:url value="/saveRenttransaccion" var="urlsaveRenttransaccion" />
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
                        <h3>Renttransaccion
                            <small> page</small>
                        </h3>
                        <div class="jqgrid-responsive mb-lg">
                            <jf:grid urlgrid="${urlgridRenttransaccion}" 
                                     urlsave="${urlsaveRenttransaccion}"
                                     urldelete="${urldeleteRenttransaccion}"
                                     urlexport="${recordsExportUrlRenttransaccion}"
                                     entity="Renttransaccion" 
                                     caption="Renttransaccion grid"
                                     />
                        </div>
                    </div>
                </div>
            </section>
            <!-- Page footer-->
            <%@include file="/WEB-INF/views/footer.jsp" %>
        </div>

        <!-- Modal-->
        <div id="modalRenttransaccion" tabindex="-1" role="dialog" aria-labelledby="myModalLabelLarge" aria-hidden="true" class="modal fade">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" data-dismiss="modal" aria-label="Close" class="close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 id="myModalLabelLarge" class="modal-title">Person Tittle</h4>
                    </div>

                    <div class="modal-body">
                        <form method="POST" id="formRenttransaccion" action="${urlsaveRenttransaccion}" data-parsley-validate="" novalidate="" class="form-horizontal">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <div class="form-group">
                                       <!-- <label class="col-sm-2 control-label">IDTRANSACCION</label>
                                        <div class="col-lg-10">
                                            <jf:numberbox  precision="10"    id="idtransaccion" name="idtransaccion"  required="true" /> -->
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">TARGETAASOCIADATRANSACCION</label>
                                        <div class="col-lg-10">

                                            <jf:numberbox  precision="10"    id="targetaasociadatransaccion" name="targetaasociadatransaccion" />
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">LUGARENTREGATRANSACCION</label>
                                        <div class="col-lg-10">

                                            <jf:textbox  tabindex="2" maxlength="45"   id="lugarentregatransaccion" name="lugarentregatransaccion" />
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">FECHAINICIOTRANSACCION</label>
                                        <div class="col-lg-10">

                                            <jf:datetime tabindex="3"   id="fechainiciotransaccion" name="fechainiciotransaccion" />
                                            
                                        </div>
                                            
                                            
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">LUGARRECEPCIONTRANSACCION</label>
                                        <div class="col-lg-10">

                                            <jf:textbox  tabindex="4" maxlength="45"   id="lugarrecepciontransaccion" name="lugarrecepciontransaccion" />
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">FACHEFINTRANSACCIONR</label>
                                        <div class="col-lg-10">

                                            <jf:datetime tabindex="5"   id="fachefintransaccionr" name="fachefintransaccionr" />
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">TOTALTRANSACCION</label>
                                        <div class="col-lg-10">

                                            <jf:textbox tabindex="6" id="totaltransaccion" name="totaltransaccion" />
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
                                        <label class="col-sm-2 control-label">IDAUTO</label>
                                        <div class="col-lg-10">

                                            <jf:combobox url="/AutosRent/cbofilterRentauto"   id="rentautoDelegate"  name="rentauto.idauto" />
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
            </div>
        </div>
    </body>
</html>


