<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jf" uri="/WEB-INF/tlds/jform.tld" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="/WEB-INF/views/includes.jsp" %>
        <c:url value="/gridRentdireccion" var="urlgridRentdireccion" />
        <c:url value="/saveRentdireccion" var="urlsaveRentdireccion" />
        <c:url value="/deleteRentdireccion" var="urldeleteRentdireccion" />
        <c:url value="/exportRentdireccion" var="recordsExportUrlRentdireccion" />
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
                        <h3>Rentdireccion
                            <small> page</small>
                        </h3>
                        <div class="jqgrid-responsive mb-lg">
                            <jf:grid urlgrid="${urlgridRentdireccion}" 
                                     urlsave="${urlsaveRentdireccion}"
                                     urldelete="${urldeleteRentdireccion}"
                                     urlexport="${recordsExportUrlRentdireccion}"
                                     entity="Rentdireccion" 
                                     caption="Rentdireccion grid"
                                     />
                        </div>
                    </div>
                </div>
            </section>
            <!-- Page footer-->
            <%@include file="/WEB-INF/views/footer.jsp" %>
        </div>

        <!-- Modal-->
        <div id="modalRentdireccion" tabindex="-1" role="dialog" aria-labelledby="myModalLabelLarge" aria-hidden="true" class="modal fade">
           <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" data-dismiss="modal" aria-label="Close" class="close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 id="myModalLabelLarge" class="modal-title">Person Tittle</h4>
                    </div>

                    <div class="modal-body">
                       <form method="POST" id="formRentdireccion" action="${urlsaveRentdireccion}" data-parsley-validate="" novalidate="" class="form-horizontal">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <div class="form-group">
                                         <label class="col-sm-2 control-label">IDDIRECION</label>
                                         <div class="col-lg-10">

                                         <jf:numberbox  precision="10"    id="iddirecion" name="iddirecion"  required="true" />
                                         </div>
                                     </div>
 
                                    <div class="form-group">
                                         <label class="col-sm-2 control-label">TIPODIRECCION</label>
                                         <div class="col-lg-10">

                                         <jf:textbox  tabindex="1" maxlength="10"   id="tipodireccion" name="tipodireccion" />
                                         </div>
                                     </div>
 
                                    <div class="form-group">
                                         <label class="col-sm-2 control-label">SDIRECION</label>
                                         <div class="col-lg-10">

                                         <jf:textbox  tabindex="2" maxlength="50"   id="sdirecion" name="sdirecion" />
                                         </div>
                                     </div>
 
                                    <div class="form-group">
                                         <label class="col-sm-2 control-label">IDPERSONA</label>
                                         <div class="col-lg-10">

                                             <jf:combobox url="/AutosRent/cbofilterRentpersona"   id="rentpersonaDelegate"  name="rentpersona.idpersona" />
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
        

