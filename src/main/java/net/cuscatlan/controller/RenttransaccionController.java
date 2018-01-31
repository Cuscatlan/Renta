package net.cuscatlan.controller;

import java.util.List;
import net.cuscatlan.common.ObjectUtils;
import net.cuscatlan.common.JqgridFilter;
import net.cuscatlan.common.JqgridResponse;
import net.cuscatlan.domain.Renttransaccion;

import net.cuscatlan.repository.RenttransaccionRepository;
import net.cuscatlan.poi.LayOutDynamic;
import net.cuscatlan.poi.Writer;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.Calendar;
import net.cuscatlan.common.CboFilter;
import java.util.Date;

@Controller
@RequestMapping("/")
public class RenttransaccionController {

    @Autowired
    RenttransaccionRepository renttransaccionRepository;

    @RequestMapping("/indexRenttransaccion")
    public ModelAndView indexRenttransaccion() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("renttransaccion", new Renttransaccion());
        mv.setViewName("Renttransaccion/Renttransaccion.jsp");
        return mv;
    }

    //Codigo agregado por Job Gomez
    @RequestMapping("/indexcotizacion")
    public ModelAndView indexcotizacion() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("renttransaccion", new Renttransaccion());
        mv.setViewName("Renttransaccion/Cotizacion.jsp");
        return mv;
    }

    @RequestMapping(value = "/saveRenttransaccion", method = RequestMethod.POST)
    public @ResponseBody
    String saveRenttransaccion(@ModelAttribute("Renttransaccion") @Validated Renttransaccion renttransaccion) {
        renttransaccionRepository.save(renttransaccion);
        return null;
    }

    //codigo agregado por Job
    @RequestMapping(value = "/CalcularRenttransaccion", method = RequestMethod.POST)
    public ModelAndView calcularRenttransaccion(@ModelAttribute("Renttransaccion") Renttransaccion renttransaccion) {
        ModelAndView mv = new ModelAndView();
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(renttransaccion.getFechainiciotransaccion());
        cal2.setTime(renttransaccion.getFachefintransaccionr());
        String preciodia = renttransaccion.getRentauto().getPreciodiaauto();
        //float preciod = Float.parseFloat(preciodia);
        //calculo que el anio sea el correcto.
        boolean correcto = false;
        Date fecha1 = renttransaccion.getFechainiciotransaccion();
        Date fecha2 = renttransaccion.getFechainiciotransaccion();
        if (renttransaccion.getFechainiciotransaccion().compareTo(renttransaccion.getFechainiciotransaccion())<0){
      
            System.out.println("fecha inicio:" + cal1.getTime() + " mayor que fecha fin:" + cal2.getTime());
            int dias=(int) ((fecha2.getTime()-fecha1.getTime())/86400000);
 
		System.out.println("Hay "+dias+" dias de diferencia");
        }

        mv.addObject("renttransaccion", new Renttransaccion());
        mv.setViewName("Renttransaccion/Cotizacion.jsp");
        return mv;
    }

    @RequestMapping(value = "/deleteRenttransaccion", method = RequestMethod.POST)
    public @ResponseBody
    String deleteRenttransaccion(@ModelAttribute("Renttransaccion") Renttransaccion renttransaccion) {
        renttransaccionRepository.delete(renttransaccion);
        return null;
    }

    @RequestMapping(value = "/gridRenttransaccion", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    JqgridResponse<Renttransaccion> gridRenttransaccion(
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "rows", required = false) Integer rows
    ) {

        Page<Renttransaccion> list = renttransaccionRepository.findByFilters(
                new PageRequest(page - 1, rows),
                JqgridFilter.getField(filters, "idtransaccion"),
                JqgridFilter.getField(filters, "targetaasociadatransaccion"),
                JqgridFilter.getField(filters, "lugarentregatransaccion"),
                JqgridFilter.getField(filters, "fechainiciotransaccion"),
                JqgridFilter.getField(filters, "lugarrecepciontransaccion"),
                JqgridFilter.getField(filters, "fachefintransaccionr"),
                JqgridFilter.getField(filters, "totaltransaccion"),
                JqgridFilter.getField(filters, "rentclienteDescriptionDelegate"),
                JqgridFilter.getField(filters, "renttipotransaccionDescriptionDelegate"),
                JqgridFilter.getField(filters, "rentautoDescriptionDelegate"),
                JqgridFilter.getField(filters, "rentvendedorDescriptionDelegate")
        );

        JqgridResponse<Renttransaccion> jqgridRenttransaccion = new JqgridResponse<Renttransaccion>();
        return jqgridRenttransaccion.jGridFill(list, page, rows);
    }

    @RequestMapping(value = "/exportRenttransaccion", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void exportRenttransaccion(
            HttpServletResponse response, @RequestParam(value = "filters", required = false) String filters
    ) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet("libro");

        List<String> header = new ArrayList<String>();
        header.add("idtransaccion");
        header.add("targetaasociadatransaccion");
        header.add("lugarentregatransaccion");
        header.add("fechainiciotransaccion");
        header.add("lugarrecepciontransaccion");
        header.add("fachefintransaccionr");
        header.add("totaltransaccion");
        header.add("rentclienteDescriptionDelegate");
        header.add("renttipotransaccionDescriptionDelegate");
        header.add("rentautoDescriptionDelegate");
        header.add("rentvendedorDescriptionDelegate");

        LayOutDynamic.buildReport(worksheet, "Renttransaccion", header);

        List<Object[]> list = renttransaccionRepository.findByFilters(
                JqgridFilter.getField(filters, "idtransaccion"),
                JqgridFilter.getField(filters, "targetaasociadatransaccion"),
                JqgridFilter.getField(filters, "lugarentregatransaccion"),
                JqgridFilter.getField(filters, "fechainiciotransaccion"),
                JqgridFilter.getField(filters, "lugarrecepciontransaccion"),
                JqgridFilter.getField(filters, "fachefintransaccionr"),
                JqgridFilter.getField(filters, "totaltransaccion"),
                JqgridFilter.getField(filters, "rentclienteDescriptionDelegate"),
                JqgridFilter.getField(filters, "renttipotransaccionDescriptionDelegate"),
                JqgridFilter.getField(filters, "rentautoDescriptionDelegate"),
                JqgridFilter.getField(filters, "rentvendedorDescriptionDelegate")
        );

        LayOutDynamic.fillReport(worksheet, header.size(), list);
        String fileName = "Renttransaccion.xls";
        response.setHeader("Content-Disposition", "inline; filename=" + fileName);
        response.setContentType("application/vnd.ms-excel");
        Writer.write(response, worksheet);
    }

}
