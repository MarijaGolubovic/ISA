package com.example.demo.controller;

import com.example.demo.model.BloodBank;
import com.example.demo.service.PDFReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path="api/PDFReport")
public class PDFReportController {

    private final PDFReportService pdfReportService;
    @Autowired
    public PDFReportController(PDFReportService pdfReportService) {
        this.pdfReportService=pdfReportService;
    }

    @PostMapping(path="/{bankName}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void savePDFReport(@PathVariable String bankName, @RequestParam("file")   MultipartFile file) throws IOException {
        pdfReportService.saveFile(bankName,file);

    }

}
