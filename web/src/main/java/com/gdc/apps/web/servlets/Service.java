package com.gdc.apps.web.servlets;

import com.gdc.apps.service.ProductService;

public interface Service {
    final ProductService service = new ProductService();
}
