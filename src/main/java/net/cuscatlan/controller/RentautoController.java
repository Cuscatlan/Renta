package net.cuscatlan.controller;

import java.util.List;
import net.cuscatlan.common.ObjectUtils;
import net.cuscatlan.common.JqgridFilter;
import net.cuscatlan.common.JqgridResponse;
import net.cuscatlan.domain.Rentauto;

import net.cuscatlan.repository.RentautoRepository;
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
public class RentautoController {
    
    @Autowired
    RentautoRepository rentautoRepository;
    
    
    @RequestMapping("/indexRentauto")
    public ModelAndView indexRentauto(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("rentauto", new Rentauto());
        mv.setViewName("Rentauto/Rentauto.jsp");
        return mv;
    }
    
    @RequestMapping(value = "/saveRentauto", method = RequestMethod.POST)
    public @ResponseBody String saveRentauto(@ModelAttribute("Rentauto") @Validated Rentauto rentauto ) {
         rentautoRepository.save(rentauto);
         return null;
     }
    
    @RequestMapping(value = "/deleteRentauto", method = RequestMethod.POST)
    public @ResponseBody String deleteRentauto(@ModelAttribute("Rentauto")  Rentauto rentauto ) {
         rentautoRepository.delete(rentauto);
         return null;
     }
    
    @RequestMapping(value = "/gridRentauto", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody JqgridResponse<Rentauto> gridRentauto(
        @RequestParam(value = "filters", required = false) String filters,
        @RequestParam(value = "page", required = false) Integer page,
        @RequestParam(value = "rows", required = false) Integer rows
    ) {
    
        Page<Rentauto> list = rentautoRepository.findByFilters(
            new PageRequest(page - 1, rows)
           ,JqgridFilter.getField(filters, "idauto") 
            ,JqgridFilter.getField(filters, "fechaauto") 
            ,JqgridFilter.getField(filters, "preciodiaauto") 
            ,JqgridFilter.getField(filters, "colorauto") 
            ,JqgridFilter.getField(filters, "modeloauto") 
            ,JqgridFilter.getField(filters, "placaauto") 
            ,JqgridFilter.getField(filters, "targetaauto") 
            ,JqgridFilter.getField(filters, "renttipoautoDescriptionDelegate") 
        );

       JqgridResponse<Rentauto> jqgridRentauto = new JqgridResponse<Rentauto>();
       return jqgridRentauto.jGridFill(list, page, rows);
    }
    
    @RequestMapping(value = "/exportRentauto", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void exportRentauto(
        HttpServletResponse response,@RequestParam(value = "filters", required = false) String filters
    ) {
    
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet("libro");
        
        List<String> header = new ArrayList<String>();
        header.add("idauto"); 
        header.add("fechaauto"); 
        header.add("preciodiaauto"); 
        header.add("colorauto"); 
        header.add("modeloauto"); 
        header.add("placaauto"); 
        header.add("targetaauto"); 
        header.add("renttipoautoDescriptionDelegate"); 
        
        LayOutDynamic.buildReport(worksheet, "Rentauto", header);
        
        List<Object[]> list = rentautoRepository.findByFilters(
           JqgridFilter.getField(filters, "idauto") 
            ,JqgridFilter.getField(filters, "fechaauto") 
            ,JqgridFilter.getField(filters, "preciodiaauto") 
            ,JqgridFilter.getField(filters, "colorauto") 
            ,JqgridFilter.getField(filters, "modeloauto") 
            ,JqgridFilter.getField(filters, "placaauto") 
            ,JqgridFilter.getField(filters, "targetaauto") 
            ,JqgridFilter.getField(filters, "renttipoautoDescriptionDelegate") 
        );
        

       LayOutDynamic.fillReport(worksheet, header.size(),list);
       String fileName = "Rentauto.xls";
       response.setHeader("Content-Disposition", "inline; filename=" + fileName);
       response.setContentType("application/vnd.ms-excel");
       Writer.write(response, worksheet);
    }
    
    @RequestMapping(value = {"/cbofilterRentauto"}, method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody List<CboFilter> cbofilterRentauto() {
        List<Rentauto> list = rentautoRepository.findAll();
        List<CboFilter> response = new ArrayList<CboFilter>();
        for (int i = 0; i < list.size(); i++) {
            response.add(new CboFilter(list.get(i).getIdauto().toString(), list.get(i).getModeloauto()));
        }
        return response;
    }
    
}

