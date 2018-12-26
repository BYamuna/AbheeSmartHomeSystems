/*
 * package com.charvikent.abheeSmartHomeSystems.controller;
 * 
 * import javax.servlet.http.HttpServletRequest;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.ModelAttribute; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod;
 * 
 * import com.charvikent.abheeSmartHomeSystems.dao.ProductGuaranteeDao; import
 * com.charvikent.abheeSmartHomeSystems.model.ProductGuarantee;
 * 
 * public class ProductWarrantyController { private static final Logger LOGGER =
 * LoggerFactory.getLogger(ProductWarrantyController .class);
 * 
 * @Autowired ProductGuaranteeDao productGuaranteeDao;
 * 
 * @RequestMapping(value = "/pWarranty" ,method = RequestMethod.GET) public
 * String productWarrantyView(@ModelAttribute("warrantyf") ProductGuarantee
 * productGuarantee,Model model ,HttpServletRequest request) {
 * LOGGER.debug("Calling pWarranty at controller");
 * model.addAttribute("warrantyf", new ProductGuarantee());
 * model.addAttribute("customerid", productGuaranteeDao.getCustomersMap());
 * model.addAttribute("productmodelid", productGuaranteeDao.getProductsMap());
 * return "task";
 * 
 * } }
 */