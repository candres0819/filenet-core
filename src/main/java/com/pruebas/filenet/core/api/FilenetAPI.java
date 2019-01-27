package com.pruebas.filenet.core.api;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pruebas.filenet.core.business.FilenetBusiness;
import com.pruebas.filenet.core.domain.Products;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class FilenetAPI {

    private static final Logger log = LoggerFactory.getLogger(FilenetAPI.class);

    private final FilenetBusiness filenetBusiness;

    @Autowired
    public FilenetAPI(FilenetBusiness filenetBusiness) {
        super();
        this.filenetBusiness = filenetBusiness;
    }

    @PostMapping("/upload")
    public String upload() {
        return "upload";
    }

    @PostMapping("/upload-excel-file")
    public List<Products> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("uploadFile");
        return this.filenetBusiness.saveExcelFile(file.getInputStream());
    }

    @PostMapping("/products")
    public List<Products> products() {
        log.info("products");
        return this.filenetBusiness.products();
    }
}
