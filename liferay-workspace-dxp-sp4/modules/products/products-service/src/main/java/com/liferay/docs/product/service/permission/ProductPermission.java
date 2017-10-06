package com.liferay.docs.product.service.permission;


import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import products.model.Product;
import products.service.ProductLocalService;

@Component(
    immediate = true,
    property = {"model.class.name=products.model.Product"}
)
public class ProductPermission implements BaseModelPermissionChecker {

    public static void check(
        PermissionChecker permissionChecker, long productId, String actionId)
        throws PortalException, SystemException {

        if (!contains(permissionChecker, productId, actionId)) {
            throw new PrincipalException();
        }
    }

    public static void check(PermissionChecker permissionChecker, long groupId, long productId, String actionId) throws PortalException {
        if (!contains(permissionChecker, groupId, actionId)) {
            throw new PrincipalException.MustHavePermission(permissionChecker, Product.class.getName(), productId,actionId);
        }
    }

    public static boolean contains(PermissionChecker permissionChecker, long groupId, long productId, String actionId) throws PortalException {
    	Product product = _productLocalService.getProduct(productId);
        return ProductModelPermission.contains(permissionChecker, groupId, actionId);
    }

    public static boolean contains(PermissionChecker permissionChecker, long productId, String actionId) throws PortalException, SystemException {

    	Product product = _productLocalService.getProduct(productId);
        return contains(permissionChecker, product, actionId);
    }

    public static boolean contains( PermissionChecker permissionChecker, Product product, String actionId) throws PortalException, SystemException {
        return permissionChecker.hasPermission(product.getGroupId(), Product.class.getName(), product.getProductId(), actionId);
    }

    @Reference(unbind = "-")
    protected void setProductLocalService(ProductLocalService productLocalService) {
    	_productLocalService = productLocalService;
    }

    private static ProductLocalService _productLocalService;

    @Override
    public void checkBaseModel(PermissionChecker permissionChecker, long groupId, long productId, String actionId) throws PortalException {
            check(permissionChecker, productId, actionId);
    }
}