package com.pruebas.filenet.core.business;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebas.filenet.core.domain.Products;
import com.pruebas.filenet.core.repository.ProductsRepository;

@Service
public class FilenetBusinessImpl implements FilenetBusiness {

    private static final Logger log = LoggerFactory.getLogger(FilenetBusinessImpl.class);

    private final ProductsRepository productsRepository;

    @Autowired
    public FilenetBusinessImpl(ProductsRepository productsRepository) {
        super();
        this.productsRepository = productsRepository;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.pruebas.filenet.core.business.FilenetBusiness#saveExcelFile(java.io.InputStream)
     */
    @Override
    public List<Products> saveExcelFile(InputStream excelFile) {
        List<Products> products = new ArrayList<>();
        Products product = null;
        try {
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            int count = 0;
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();

                if (0 != count) {
                    product = new Products();
                    product.setName(currentRow.getCell(0).getStringCellValue());
                    product.setPrice(new BigDecimal(currentRow.getCell(1).getNumericCellValue()));
                    product.setActive(currentRow.getCell(2).getStringCellValue().equals("false") ? false : true);
                    product.setDate(currentRow.getCell(3).getDateCellValue());

                    this.productsRepository.save(product);
                    products.add(product);
                }
                count++;
            }
        } catch (FileNotFoundException e) {
            log.error("NotFound", e);
        } catch (IOException e) {
            log.error("IO", e);
        }
        return products;
    }

    @Override
    public List<Products> products() {
        return this.productsRepository.findAll();
    }
}
