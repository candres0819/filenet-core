package com.pruebas.filenet.core.business;

import java.io.InputStream;
import java.util.List;

import com.pruebas.filenet.core.domain.Products;

public interface FilenetBusiness {

    /**
     * 
     * @param excelFile
     * @return
     */
    List<Products> saveExcelFile(InputStream excelFile);

    /**
     * 
     * @return
     */
    List<Products> products();

}
