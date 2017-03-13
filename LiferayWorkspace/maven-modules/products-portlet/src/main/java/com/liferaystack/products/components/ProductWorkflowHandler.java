package com.liferaystack.products.components;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.BaseWorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandler;
import com.liferaystack.products.service.ProductLocalService;
import com.liferaystack.products.model.Product;

@Component(
		 property = {"model.class.name=com.liferaystack.products.model.Product"},
		 service = WorkflowHandler.class
)

public class ProductWorkflowHandler<Product> extends BaseWorkflowHandler<Product> {
	
	private ProductLocalService _productLocalService;
	private static Log _log = LogFactoryUtil.getLog(ProductWorkflowHandler.class);
	
	   @Reference(unbind = "-")
	   protected void setProductLocalService(ProductLocalService productLocalService) {
	         _productLocalService = productLocalService;
	    }
	  
	  @Override
	  public String getClassName() {
	     return com.liferaystack.products.model.Product.class.getName();
	  }
	  @Override
	  public String getType(Locale locale) {
	     return "product";
	  }

	 @Override
	 public Product updateStatus(int status, Map<String, Serializable> workflowContext) throws PortalException {
	 
		Product product = null;
	    long userId = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));
	    long productId = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
	    ServiceContext serviceContext = (ServiceContext)workflowContext.get("serviceContext");
	    
	    _log.info("updating product workflow status");
	    product = (Product) _productLocalService.updateWorkFlowStatus(userId, productId, status, serviceContext);
	    return product;
	 }
}
