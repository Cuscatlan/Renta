package net.cuscatlan.controller;

import java.util.List;
import net.cuscatlan.common.ObjectUtils;
import net.cuscatlan.common.JqgridFilter;
import net.cuscatlan.common.JqgridResponse;
import net.cuscatlan.domain.Rentvendedor;

import net.cuscatlan.repository.RentvendedorRepository;
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
public class RentvendedorController {
    
    @Autowired
    RentvendedorRepository rentvendedorRepository;
    
    
    @RequestMapping("/indexRentvendedor")
    public ModelAndView indexRentvendedor(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("rentvendedor", new Rentvendedor());
        mv.setViewName("Rentvendedor/Rentvendedor.jsp");
        return mv;
    }
    
    @RequestMapping(value = "/saveRentvendedor", method = RequestMethod.POST)
    public @ResponseBody String saveRentvendedor(@ModelAttribute("Rentvendedor") @Validated Rentvendedor rentvendedor ) {
         rentvendedorRepository.save(rentvendedor);
         return null;
     }
    
    @RequestMapping(value = "/deleteRentvendedor", method = RequestMethod.POST)
    public @ResponseBody String deleteRentvendedor(@ModelAttribute("Rentvendedor")  Rentvendedor rentvendedor ) {
         rentvendedorRepository.delete(rentvendedor);
         return null;
     }
    
    @RequestMapping(value = "/gridRentvendedor", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody JqgridResponse<Rentvendedor> gridRentvendedor(
        @RequestParam(value = "filters", required = false) String filters,
        @RequestParam(value = "page", required = false) Integer page,
        @RequestParam(value = "rows", required = false) Integer rows
    ) {
    
        Page<Rentvendedor> list = rentvendedorRepository.findByFilters(
            new PageRequest(page - 1, rows)
           ,JqgridFilter.getField(filters, "idvendedor") 
            ,JqgridFilter.getField(filters, "correovendedor") 
            ,JqgridFilter.getField(filters, "comicionvendedor") 
            ,JqgridFilter.getField(filters, "rentpersonaDescriptionDelegate") 
        );

       JqgridResponse<Rentvendedor> jqgridRentvendedor = new JqgridResponse<Rentvendedor>();
       return jqgridRentvendedor.jGridFill(list, page, rows);
    }
    
    @RequestMapping(value = "/exportRentvendedor", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void exportRentvendedor(
        HttpServletResponse response,@RequestParam(value = "filters", required = false) String filters
    ) {
    
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet("libro");
        
        List<String> header = new ArrayList<String>();
        header.add("idvendedor"); 
        header.add("correovendedor"); 
        header.add("comicionvendedor"); 
        header.add("rentpersonaDescriptionDelegate"); 
        
        LayOutDynamic.buildReport(worksheet, "Rentvendedor", header);
        
        List<Object[]> list = rentvendedorRepository.findByFilters(
           JqgridFilter.getField(filters, "idvendedor") 
            ,JqgridFilter.getField(filters, "correovendedor") 
            ,JqgridFilter.getField(filters, "comicionvendedor") 
            ,JqgridFilter.getField(filters, "rentpersonaDescriptionDelegate") 
        );
        

       LayOutDynamic.fillReport(worksheet, header.size(),list);
       String fileName = "Rentvendedor.xls";
       response.setHeader("Content-Disposition", "inline; filename=" + fileName);
       response.setContentType("application/vnd.ms-excel");
       Writer.write(response, worksheet);
    }
    
    @RequestMapping(value = {"/cbofilterRentvendedor"}, method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody List<CboFilter> cbofilterRentvendedor() {
        List<Rentvendedor> list = rentvendedorRepository.findAll();
        List<CboFilter> response = new ArrayList<CboFilter>();
        for (int i = 0; i < list.size(); i++) {
            response.add(new CboFilter(list.get(i).getIdvendedor().toString(), list.get(i).getIdvendedor().toString()));
        }
        return response;
    }
    
}

