package net.cuscatlan.controller;

import java.util.List;
import net.cuscatlan.common.ObjectUtils;
import net.cuscatlan.common.JqgridFilter;
import net.cuscatlan.common.JqgridResponse;
import net.cuscatlan.domain.Rentcliente;

import net.cuscatlan.repository.RentclienteRepository;
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
import net.cuscatlan.common.CboFilter;
import java.util.Date;

@Controller
@RequestMapping("/")
public class RentclienteController {
    
    @Autowired
    RentclienteRepository rentclienteRepository;
    
    
    @RequestMapping("/indexRentcliente")
    public ModelAndView indexRentcliente(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("rentcliente", new Rentcliente());
        mv.setViewName("Rentcliente/Rentcliente.jsp");
        return mv;
    }
    
    @RequestMapping(value = "/saveRentcliente", method = RequestMethod.POST)
    public @ResponseBody String saveRentcliente(@ModelAttribute("Rentcliente") @Validated Rentcliente rentcliente ) {
         rentclienteRepository.save(rentcliente);
         return null;
     }
    
    @RequestMapping(value = "/deleteRentcliente", method = RequestMethod.POST)
    public @ResponseBody String deleteRentcliente(@ModelAttribute("Rentcliente")  Rentcliente rentcliente ) {
         rentclienteRepository.delete(rentcliente);
         return null;
     }
    
    @RequestMapping(value = "/gridRentcliente", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody JqgridResponse<Rentcliente> gridRentcliente(
        @RequestParam(value = "filters", required = false) String filters,
        @RequestParam(value = "page", required = false) Integer page,
        @RequestParam(value = "rows", required = false) Integer rows
    ) {
    
        Page<Rentcliente> list = rentclienteRepository.findByFilters(
            new PageRequest(page - 1, rows)
           ,JqgridFilter.getField(filters, "idcliente") 
            ,JqgridFilter.getField(filters, "password") 
            ,JqgridFilter.getField(filters, "targetacliente") 
            ,JqgridFilter.getField(filters, "correocliente") 
            ,JqgridFilter.getField(filters, "codigocliente") 
            ,JqgridFilter.getField(filters, "rentpersonaDescriptionDelegate") 
        );

       JqgridResponse<Rentcliente> jqgridRentcliente = new JqgridResponse<Rentcliente>();
       return jqgridRentcliente.jGridFill(list, page, rows);
    }
    
    @RequestMapping(value = "/exportRentcliente", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void exportRentcliente(
        HttpServletResponse response,@RequestParam(value = "filters", required = false) String filters
    ) {
    
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet("libro");
        
        List<String> header = new ArrayList<String>();
        header.add("idcliente"); 
        header.add("password"); 
        header.add("targetacliente"); 
        header.add("correocliente"); 
        header.add("codigocliente"); 
        header.add("rentpersonaDescriptionDelegate"); 
        
        LayOutDynamic.buildReport(worksheet, "Rentcliente", header);
        
        List<Object[]> list = rentclienteRepository.findByFilters(
           JqgridFilter.getField(filters, "idcliente") 
            ,JqgridFilter.getField(filters, "password") 
            ,JqgridFilter.getField(filters, "targetacliente") 
            ,JqgridFilter.getField(filters, "correocliente") 
            ,JqgridFilter.getField(filters, "codigocliente") 
            ,JqgridFilter.getField(filters, "rentpersonaDescriptionDelegate") 
        );
        

       LayOutDynamic.fillReport(worksheet, header.size(),list);
       String fileName = "Rentcliente.xls";
       response.setHeader("Content-Disposition", "inline; filename=" + fileName);
       response.setContentType("application/vnd.ms-excel");
       Writer.write(response, worksheet);
    }
    
    @RequestMapping(value = {"/cbofilterRentcliente"}, method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody List<CboFilter> cbofilterRentcliente() {
        List<Rentcliente> list = rentclienteRepository.findAll();
        List<CboFilter> response = new ArrayList<CboFilter>();
        for (int i = 0; i < list.size(); i++) {
            response.add(new CboFilter(list.get(i).getIdcliente().toString(), list.get(i).getIdcliente().toString()));
        }
        return response;
    }
    
}

