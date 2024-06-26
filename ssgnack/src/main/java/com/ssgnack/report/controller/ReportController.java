package com.ssgnack.report.controller;

import com.ssgnack.admin.controller.dto.AdminFormDto;
import com.ssgnack.report.model.dto.ReportResDTO;
import com.ssgnack.report.model.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class ReportController {

    @Autowired
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    /***
     * 초기화 화면: 로그인 화면
     */
    @GetMapping("")
    public String firstPage(Model model){
        log.info("여기여기 login 페이지");

        AdminFormDto adminFormDto = new AdminFormDto();
        model.addAttribute("adminFormDto", adminFormDto);
        return "/admin/login";
    }

    /***
     * 상품별
     */
    @GetMapping("/report/productGraph")
    public String productPage(){
        return "/report/productGraph";
    }

    /***
     * 브랜드별
     */
    @GetMapping("/report/brandGraph")
    public String brandPage(){
        return "/report/brandGraph";
    }

    /***
     * 기간별
     */
    @GetMapping("/report/monthlyGraph")
    public String monthlyPage(){
        return "/report/monthlyGraph";
    }

    /***
     * MENU버튼 메인페이지 이동
     */
    @GetMapping("/main")
    public String mainPage(){
        return "common/fragment/main";
    }


    /***
     * 전체 매출 현황
     */
    @GetMapping("/totalSales")
    @ResponseBody
    public ReportResDTO totalSales(Model model){
        ReportResDTO reportDTOList = reportService.totalSales();
        System.out.println("reportDTOList.getTotalMonth() = " + reportDTOList.getTotalMonth());
        System.out.println("reportDTOList.getTotalSale() = " + reportDTOList.getTotalSale());
        System.out.println("reportDTOList.getTotalIncome() = " + reportDTOList.getTotalIncome());

        return reportDTOList;
    }

    /***
     * 상품별 매출 현황
     */
    @GetMapping("/productSales")
    @ResponseBody
    public ReportResDTO productSales(Model model, @RequestParam(required = false) String productName){
        log.info("productName {}", productName);
        ReportResDTO reportDTOList = reportService.productSales(productName);
        System.out.println("reportDTOList.getTotalMonth() = " + reportDTOList.getTotalMonth());
        System.out.println("reportDTOList.getTotalSale() = " + reportDTOList.getTotalSale());
        System.out.println("reportDTOList.getTotalIncome() = " + reportDTOList.getTotalIncome());
        return reportDTOList;
    }

    /***
     * 브랜드별 매출 현황
     */
    @GetMapping("/brandSales")
    @ResponseBody
    public ReportResDTO brandSales(Model model, @RequestParam(required = false) String companyName){
        log.info("companyName {}", companyName);
        ReportResDTO reportDTOList = reportService.brandSales(companyName);
        System.out.println("reportDTOList.getProductName() = " + reportDTOList.getProductName());
        System.out.println("reportDTOList.getTotalSale() = " + reportDTOList.getTotalSale());
        System.out.println("reportDTOList.getTotalIncome() = " + reportDTOList.getTotalIncome());

        return reportDTOList;
    }

    /***
     * 기간별 매출 현황
     */
    @GetMapping("/monthlySales")
    @ResponseBody
    public ReportResDTO monthlySales(Model model, @RequestParam(required = false) String start, @RequestParam(required = false) String end){
        log.info("startDate {}", start);
        log.info("endDate {}", end);
        ReportResDTO reportDTOList = reportService.monthlySales(start,end);
        System.out.println("reportDTOList.getTotalMonth() = " + reportDTOList.getTotalMonth());
        System.out.println("reportDTOList.getTotalSale() = " + reportDTOList.getTotalSale());
        System.out.println("reportDTOList.getTotalIncome() = " + reportDTOList.getTotalIncome());

        return reportDTOList;
    }

}
