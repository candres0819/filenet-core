package com.pruebas.filenet.core.filenetcore;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.pruebas.filenet.core.domain.Products;
import com.pruebas.filenet.core.repository.ProductsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilenetCoreApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(FilenetCoreApplicationTests.class);

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();

        Products products = new Products();
        products.setName("Test");
        products.setPrice(new BigDecimal(2000));
        products.setActive(true);
        products.setDate(new Date());

        this.productsRepository.save(products);
    }

    @Test
    public void testProducts() throws Exception {
        log.debug("[INVOKE] /api/v1/products");
        ResultActions resultActions = this.mvc.perform(post("/api/v1/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        resultActions.andDo(print());
    }
}
